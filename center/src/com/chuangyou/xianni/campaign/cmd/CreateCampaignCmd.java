package com.chuangyou.xianni.campaign.cmd;

import com.chuangyou.common.protobuf.pb.campaign.CreateCampaignMsgProto.CreateCampaignMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.campaign.action.CreateCampaignAction;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_CREATE_CAMPAIGN, desc = "请求创建副本/前往组队目标")
public class CreateCampaignCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// 请求创建副本X
		CreateCampaignMsg msg = CreateCampaignMsg.parseFrom(packet.getBytes());
		// 向场景服务器申请创建副本
		CreateCampaignAction action = new CreateCampaignAction(player, msg.getCampaign(), msg.getTaskId());
		player.getActionQueue().enqueue(action);
	}
}
