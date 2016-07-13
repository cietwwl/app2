package com.chuangyou.xianni.campaign.cmd;

import com.chuangyou.common.protobuf.pb.campaign.CampaignOptionMsgProto.CampaignOptionMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_CAMPAIGN_OPTION, desc = "副本操作")
public class CampaignOptionCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		CampaignOptionMsg reqMsg = CampaignOptionMsg.parseFrom(packet.getBytes());
		int op = reqMsg.getOptionType();
		CampaignOptionMsg.Builder builder = CampaignOptionMsg.newBuilder();

		builder.setOptionType(op);
		// 玩家当前副本
		builder.setParam1(player.getCurCampaign());
		PBMessage message = MessageUtil.buildMessage(Protocol.S_CAMPAIGN_OPTION, builder);
		player.sendPbMessage(message);
	}
}
