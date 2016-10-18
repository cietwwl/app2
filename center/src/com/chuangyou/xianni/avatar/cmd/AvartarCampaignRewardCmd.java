package com.chuangyou.xianni.avatar.cmd;

import com.chuangyou.common.protobuf.pb.avatar.PassAvatarCampaignProto.PassAvatarCampaignMsg;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.socket.Command;
import com.chuangyou.xianni.word.WorldMgr;

import io.netty.channel.Channel;

@Cmd(code = Protocol.C_PASS_CAMPAIGN_N2CENTER, desc = "通关分身副本，发放分身奖励")
public class AvartarCampaignRewardCmd implements Command {

	@Override
	public void execute(Channel channel, PBMessage packet) throws Exception {
		PassAvatarCampaignMsg msg = PassAvatarCampaignMsg.parseFrom(packet.getBytes());
		GamePlayer player = WorldMgr.getPlayer(msg.getPlayerId());
		if (player != null && player.getAvatarInventory() != null) {
			player.getAvatarInventory().challageReward(msg.getTempId(), msg.getRewardCount());
		}
	}

}
