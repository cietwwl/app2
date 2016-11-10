package com.chuangyou.xianni.welfare.cmd;

import java.util.ArrayList;
import java.util.List;

import com.chuangyou.common.protobuf.pb.welfare.ReceiveWelfareMsgProto.ReceiveWelfareMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.item.BagType;
import com.chuangyou.xianni.entity.item.BindType;
import com.chuangyou.xianni.entity.item.ItemAddType;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.welfare.WelfareInfo;
import com.chuangyou.xianni.entity.welfare.WelfareTemplate;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.welfare.WelfareConditionHandleFactory;
import com.chuangyou.xianni.welfare.WelfareInventory;
import com.chuangyou.xianni.welfare.WelfareManager;

@Cmd(code = Protocol.C_RECEIVE_WELFARE_AWARD, desc = "领取福利奖励")
public class receiveWelfareAwardCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		ReceiveWelfareMsg msg = ReceiveWelfareMsg.parseFrom(packet.getBytes());
		int id = msg.getId();
		WelfareInventory inventory = player.getWelfareInventory();
		WelfareInfo info = inventory.getWelfareInfoMap().get(id);
		if (info == null) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_RECEIVE_WELFARE_AWARD, "福利数据不存在！");
			return;
		}
		WelfareTemplate template = WelfareManager.getConfigId().get(id);
		//新手在线时间没有监听事件，所以只在客户端认为可以领取奖励的时候加判断
		if (template.getType() == WelfareInventory.TYPE_NEW) {
			if (player.getWelfareInventory().getWelfareConditionHandleMap().get(WelfareConditionHandleFactory.ONLINE_TIME).judgeOneWelfare(info)) {
				info.setStatus(WelfareInventory.STATE_1);
				player.getWelfareConditionRecordInventory().setOnlineStartTime(System.currentTimeMillis());
				player.getWelfareConditionRecordInventory().getInfo().setOnLineTime(0);
			}
		}
		if (info.getStatus() != WelfareInventory.STATE_1) {
			//没达到领取条件
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.WELFARE_INSUFFICIENT_CONDITION_ERROR, Protocol.C_RECEIVE_WELFARE_AWARD, "福利条件未达到！");
			return;
		}
		//检查完毕
		//给奖励
		List<Reward> rewards = new ArrayList<>(8);// 所有奖励
		List<Reward> alreadyAddrewards = new ArrayList<>(8);// 已经加入背包的奖励
		rewards.add(new Reward(template.getItem1(), template.getNum1(), 1 == template.getBind1()));
		rewards.add(new Reward(template.getItem2(), template.getNum2(), 1 == template.getBind2()));
		rewards.add(new Reward(template.getItem3(), template.getNum3(), 1 == template.getBind3()));
		rewards.add(new Reward(template.getItem4(), template.getNum4(), 1 == template.getBind4()));
		rewards.add(new Reward(template.getItem5(), template.getNum5(), 1 == template.getBind5()));
		rewards.add(new Reward(template.getItem6(), template.getNum6(), 1 == template.getBind6()));
		rewards.add(new Reward(template.getItem7(), template.getNum7(), 1 == template.getBind7()));
		rewards.add(new Reward(template.getItem8(), template.getNum8(), 1 == template.getBind8()));
		for (Reward reward : rewards) {
			if (reward.itemId == 0)
				continue;
			
			if(player.getBagInventory().addItem(reward.itemId, reward.number, ItemAddType.WELFARE, reward.bind)) {
				alreadyAddrewards.add(reward);
			} else {
				for (Reward reward2 : alreadyAddrewards) {
					player.getBagInventory().removeItem(BagType.Play, reward2.itemId, reward2.number, reward2.bind ? BindType.BIND : BindType.NOBIND, ItemRemoveType.WELFARE_RECOVERY);
				}
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.BAG_IS_FULL, Protocol.C_RECEIVE_WELFARE_AWARD, "背包已满！");
				return;
			}
		}
		
		//改变领取状态
		info.setStatus(WelfareInventory.STATE_2);
		//更新客户端状态
		WelfareManager.updateOneWelfare(id, WelfareInventory.STATE_2, player);
	}

	private class Reward{
		int itemId;
		int number;
		boolean bind;
		public Reward(int itemId, int number, boolean bind) {
			this.itemId = itemId;
			this.number = number;
			this.bind = bind;
		}
	}
}
