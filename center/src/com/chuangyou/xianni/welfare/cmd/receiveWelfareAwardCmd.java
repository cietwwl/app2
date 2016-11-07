package com.chuangyou.xianni.welfare.cmd;

import com.chuangyou.common.protobuf.pb.welfare.ReceiveWelfareMsgProto.ReceiveWelfareMsg;
import com.chuangyou.xianni.base.AbstractCommand;
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
			return;
		}
		//检查完毕
		//给奖励
		int itemId = template.getItem1();
		if (itemId != 0) {
			int bind = template.getBind1();
			int num = template.getNum1();
			player.getBagInventory().addItem(itemId, num, ItemAddType.WELFARE, bind == 1);
		}
		itemId = template.getItem2();
		if (itemId != 0) {
			int bind = template.getBind2();
			int num = template.getNum2();
			player.getBagInventory().addItem(itemId, num, ItemAddType.WELFARE, bind == 1);
		}
		itemId = template.getItem3();
		if (itemId != 0) {
			int bind = template.getBind3();
			int num = template.getNum3();
			player.getBagInventory().addItem(itemId, num, ItemAddType.WELFARE, bind == 1);
		}
		itemId = template.getItem4();
		if (itemId != 0) {
			int bind = template.getBind4();
			int num = template.getNum4();
			player.getBagInventory().addItem(itemId, num, ItemAddType.WELFARE, bind == 1);
		}
		itemId = template.getItem5();
		if (itemId != 0) {
			int bind = template.getBind5();
			int num = template.getNum5();
			player.getBagInventory().addItem(itemId, num, ItemAddType.WELFARE, bind == 1);
		}
		itemId = template.getItem6();
		if (itemId != 0) {
			int bind = template.getBind6();
			int num = template.getNum6();
			player.getBagInventory().addItem(itemId, num, ItemAddType.WELFARE, bind == 1);
		}
		itemId = template.getItem7();
		if (itemId != 0) {
			int bind = template.getBind7();
			int num = template.getNum7();
			player.getBagInventory().addItem(itemId, num, ItemAddType.WELFARE, bind == 1);
		}
		itemId = template.getItem8();
		if (itemId != 0) {
			int bind = template.getBind8();
			int num = template.getNum8();
			player.getBagInventory().addItem(itemId, num, ItemAddType.WELFARE, bind == 1);
		}
		//改变领取状态
		info.setStatus(WelfareInventory.STATE_2);
		//更新客户端状态
		WelfareManager.updateOneWelfare(id, WelfareInventory.STATE_2, player);
	}

}
