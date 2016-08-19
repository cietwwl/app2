package com.chuangyou.xianni.inverseBead.cmd;

import com.chuangyou.common.protobuf.pb.inverseBead.ReqEnterCampaignMsgProto.ReqEnterCampaignMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.exec.ThreadManager;
import com.chuangyou.xianni.inverseBead.InverseBeadInventory;
import com.chuangyou.xianni.inverseBead.action.CreateInverSeBeadCampaignAction;
import com.chuangyou.xianni.inverseBead.action.InverseBeadLoopAction;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_CREATE_INVERSE_BEAD_CAMPAIGN, desc = "请求创建天逆珠秘境")
public class CreateCampaignCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// 请求创建副本X
		ReqEnterCampaignMsg msg = ReqEnterCampaignMsg.parseFrom(packet.getBytes());
		// InverseBeadLoopAction action = new InverseBeadLoopAction(player,
		// player.getActionQueue(),
		// InverseBeadInventory.getBeadRefreshIdList(),true);
		// ThreadManager.actionExecutor.enDelayQueue(action);

		// 向场景服务器申请创建副本
		CreateInverSeBeadCampaignAction action2 = new CreateInverSeBeadCampaignAction(player);
		player.getActionQueue().enqueue(action2);
	}

}
