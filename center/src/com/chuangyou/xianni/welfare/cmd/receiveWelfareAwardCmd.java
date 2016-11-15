package com.chuangyou.xianni.welfare.cmd;

import java.util.ArrayList;
import java.util.List;

import com.chuangyou.common.protobuf.pb.welfare.ReceiveWelfareMsgProto.ReceiveWelfareMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.constant.CommonType.CurrencyItemType;
import com.chuangyou.xianni.entity.item.ItemAddType;
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
		// 新手在线时间没有监听事件，所以只在客户端认为可以领取奖励的时候加判断
		if (template.getType() == WelfareInventory.TYPE_NEW) {
			if (player.getWelfareInventory().getWelfareConditionHandleMap().get(WelfareConditionHandleFactory.ONLINE_TIME).judgeOneWelfare(info)) {
				info.setStatus(WelfareInventory.STATE_1);
			}
		}
		if (info.getStatus() != WelfareInventory.STATE_1) {
			// 没达到领取条件
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.WELFARE_INSUFFICIENT_CONDITION_ERROR, Protocol.C_RECEIVE_WELFARE_AWARD, "福利条件未达到！");
			return;
		}
		// 检查背包是否足够
		List<Reward> rewards = new ArrayList<>(8);// 所有奖励
		rewards.add(new Reward(template.getItem1(), template.getNum1(), 1 == template.getBind1()));
		rewards.add(new Reward(template.getItem2(), template.getNum2(), 1 == template.getBind2()));
		rewards.add(new Reward(template.getItem3(), template.getNum3(), 1 == template.getBind3()));
		rewards.add(new Reward(template.getItem4(), template.getNum4(), 1 == template.getBind4()));
		rewards.add(new Reward(template.getItem5(), template.getNum5(), 1 == template.getBind5()));
		rewards.add(new Reward(template.getItem6(), template.getNum6(), 1 == template.getBind6()));
		rewards.add(new Reward(template.getItem7(), template.getNum7(), 1 == template.getBind7()));
		rewards.add(new Reward(template.getItem8(), template.getNum8(), 1 == template.getBind8()));
		int needSpace = 0;
		for (Reward reward : rewards) {
			int itemId = reward.itemId;
			if (itemId == 0)
				continue;
			if (itemId == CurrencyItemType.MONEY_ITEM || itemId == CurrencyItemType.CASH_ITEM || itemId == CurrencyItemType.CASH_BIND_ITEM || itemId == CurrencyItemType.EQUIP_EXP
					|| itemId == CurrencyItemType.REPAIR_ITEM || itemId == CurrencyItemType.POINTS || itemId == CurrencyItemType.EXP || itemId == CurrencyItemType.VIP_EXP
					|| itemId == CurrencyItemType.VIP_TEMPORARY) {
				continue;
			}
			needSpace++;
		}
		if (player.getBagInventory().getEmptyCount() < needSpace) {
			ErrorMsgUtil.sendErrorMsg(player.getPlayerId(), ErrorCode.BAG_IS_FULL, (short) 0, "背包已满");
			return;
		}
		// 给奖励
		for (Reward reward : rewards) {
			player.getBagInventory().addItem(reward.itemId, reward.number, ItemAddType.WELFARE, reward.bind);
		}
		// 更新客户端状态
		WelfareManager.updateOneWelfare(id, WelfareInventory.STATE_2, player);
		// 改变领取状态
		info.setStatus(WelfareInventory.STATE_2);
		// 如果是在线奖励需要重置在线时间重新计时
		if (template.getType() == WelfareInventory.TYPE_NEW) {
			player.getWelfareConditionRecordInventory().setOnlineStartTime(System.currentTimeMillis());
			player.getWelfareConditionRecordInventory().getInfo().setOnLineTime(0);
		}
	}

	private class Reward {
		int		itemId;
		int		number;
		boolean	bind;

		public Reward(int itemId, int number, boolean bind) {
			this.itemId = itemId;
			this.number = number;
			this.bind = bind;
		}
	}
}
