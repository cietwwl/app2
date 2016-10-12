package com.chuangyou.xianni.campaign.action;

import java.util.List;

import com.chuangyou.common.protobuf.pb.army.RobotInfoProto.RobotInfoMsg;
import com.chuangyou.common.protobuf.pb.campaign.CreateCampaignMsgProto.CreateCampaignMsg;
import com.chuangyou.xianni.exec.ActionQueue;
import com.chuangyou.xianni.exec.DelayAction;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.player.PlayerState;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.team.struct.Team;
import com.chuangyou.xianni.team.struct.TeamMember;
import com.chuangyou.xianni.word.WorldMgr;

public class CreateCampaignDelayAction extends DelayAction {
	Team				team;
	int					campaignId;
	static final int	count	= 3;

	public CreateCampaignDelayAction(ActionQueue queue, Team team, int campaignId, int delay) {
		super(queue, delay);
		this.team = team;
		this.campaignId = campaignId;
	}

	@Override
	public void execute() {

		CreateCampaignMsg.Builder ccmsg = CreateCampaignMsg.newBuilder();

		ccmsg.setCampaign(campaignId);

		GamePlayer teamLeader = WorldMgr.getPlayer(team.getLeader().getPlayerId());
		if (teamLeader != null) {
			// 人数不够，补分身
			int size = 4 - team.getMemberSize();
			if (size > 0) {
				List<RobotInfoMsg> robots = teamLeader.getAvatarInventory().getRandomAvatarMsg(size);
				ccmsg.addAllAvatars(robots);
			}
			// 通知scence服务器，用户请求创建副本
			PBMessage c2s = MessageUtil.buildMessage(Protocol.S_CREATE_CAMPAIGN, ccmsg);
			teamLeader.sendPbMessage(c2s);
		}
		for (TeamMember member : team.getMembers()) {
			GamePlayer player = WorldMgr.getPlayer(member.getPlayerId());
			if (player != null && player.getBasePlayer().getOnLineStatus() == PlayerState.ONLINE) {
				member.setStatu(TeamMember.JOIN_TARGET);
			}
		}
		team.changeTeamStatu(Team.GET_IN);
	}

}
