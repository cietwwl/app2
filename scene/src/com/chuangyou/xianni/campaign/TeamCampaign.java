package com.chuangyou.xianni.campaign;

import java.util.List;

import com.chuangyou.common.protobuf.pb.campaign.CampaignStatuMsgProto.CampaignStatuMsg;
import com.chuangyou.common.protobuf.pb.campaign.PassFbInnerProto.PassFbInnerMsg;
import com.chuangyou.xianni.campaign.state.StopState;
import com.chuangyou.xianni.entity.campaign.CampaignTemplateInfo;
import com.chuangyou.xianni.netty.GatewayLinkedSet;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.team.Team;
import com.chuangyou.xianni.team.TeamMgr;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.world.ArmyProxy;
import com.chuangyou.xianni.world.WorldMgr;

public class TeamCampaign extends Campaign {
	private int teamId;

	public TeamCampaign(CampaignTemplateInfo tempInfo, ArmyProxy creater) {
		super(tempInfo, creater);
	}

	public boolean agreedToEnter(ArmyProxy army) {
		if (!super.agreedToEnter(army)) {
			return false;
		}

		if (army.getPlayerId() == creater) {
			return true;
		}
		Team team = TeamMgr.getTeam(creater);
		if (team == null) {
			return false;
		}
		return team.inTeam(army.getPlayerId());
	}

	/**
	 * 副本结束
	 */
	public void over() {
		state = new StopState(this);

		CampaignStatuMsg.Builder cstatu = CampaignStatuMsg.newBuilder();
		cstatu.setCampaignId(getIndexId());
		cstatu.setStatu(0);// 退出
		PBMessage statuMsg = MessageUtil.buildMessage(Protocol.C_CAMPAIGN_STATU, cstatu);

		PassFbInnerMsg.Builder passFbMsg = PassFbInnerMsg.newBuilder();
		passFbMsg.setCampaignId(campaignId);
		passFbMsg.setTeamId(teamId);
		for (ArmyProxy army : armys.values()) {
			sendCampaignInfo(army);
			onPlayerLeave(army);
			// 通知center服务器,玩家副本销毁了
			army.sendPbMessage(statuMsg);
			passFbMsg.addPlayers(army.getPlayerId());
		}

		PBMessage passFbpkg = MessageUtil.buildMessage(Protocol.C_REQ_PASS_FB, passFbMsg);
		GatewayLinkedSet.send2Server(passFbpkg);
		setExpiredTime(System.currentTimeMillis() + 5 * 60 * 1000);

		Team team = TeamMgr.getTeam(creater);
		if (team != null) {
			List<Long> members = team.getMembers();
			for (long playerId : members) {
				ArmyProxy army = WorldMgr.getArmy(playerId);
				if (army != null) {
					sendCampaignInfo(army);
				}
			}
		}

	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

}
