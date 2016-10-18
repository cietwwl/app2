package com.chuangyou.xianni.team.cmd;

import com.chuangyou.common.protobuf.pb.team.TeamMemberLeaveRespProto.TeamMemberLeaveRespMsg;
import com.chuangyou.xianni.campaign.CampaignMgr;
import com.chuangyou.xianni.campaign.TeamCampaign;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.socket.Command;
import com.chuangyou.xianni.team.Team;
import com.chuangyou.xianni.team.TeamMgr;
import com.chuangyou.xianni.world.ArmyProxy;
import com.chuangyou.xianni.world.WorldMgr;

import io.netty.channel.Channel;

@Cmd(code = Protocol.S_TEAM_LEAVE, desc = "离开")
public class RemoveMemberTeamCmd implements Command {

	@Override
	public void execute(Channel channel, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		TeamMemberLeaveRespMsg msg = TeamMemberLeaveRespMsg.parseFrom(packet.getBytes());
		int teamId = msg.getTeamId();
		Team t = TeamMgr.getAllTeams().get(teamId);
		t.removeMember(msg.getMemberPlayerId());

		// todo同步player数据
		ArmyProxy player = WorldMgr.getArmy(msg.getMemberPlayerId());
		if (player != null) {
			player.getPlayer().updateProperty(EnumAttr.TEAM_ID, 0);
		}

		TeamCampaign campaign = (TeamCampaign) CampaignMgr.getCampagin(t.getCampaignId());
		if (campaign != null) {
			ArmyProxy army = WorldMgr.getArmy(msg.getMemberPlayerId());
			if (army != null) {
				campaign.onKick(army);
			}
		}
	}

}
