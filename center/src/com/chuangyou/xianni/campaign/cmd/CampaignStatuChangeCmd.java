package com.chuangyou.xianni.campaign.cmd;

import com.chuangyou.common.protobuf.pb.campaign.CampaignStatuMsgProto.CampaignStatuMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_CAMPAIGN_STATU, desc = "sence服务器进入副本结果")
public class CampaignStatuChangeCmd extends AbstractCommand {
	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		CampaignStatuMsg smsg = CampaignStatuMsg.parseFrom(packet.getBytes());
		if (smsg.getStatu() == 1) {
			player.setCurCampaign(smsg.getCampaignId());
		} else {
			player.setCurCampaign(0);
		}

	}
}
