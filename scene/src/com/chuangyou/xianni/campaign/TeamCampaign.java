package com.chuangyou.xianni.campaign;

import java.util.ArrayList;
import java.util.List;

import com.chuangyou.common.protobuf.pb.campaign.CampaignInfoMsgProto.CampaignInfoMsg;
import com.chuangyou.common.protobuf.pb.campaign.CampaignStatuMsgProto.CampaignStatuMsg;
import com.chuangyou.xianni.campaign.state.CampaignState;
import com.chuangyou.xianni.constant.CampaignConstant.CampaignStatu;
import com.chuangyou.xianni.entity.campaign.CampaignTemplateInfo;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.team.Team;
import com.chuangyou.xianni.team.TeamMgr;
import com.chuangyou.xianni.world.ArmyProxy;
import com.chuangyou.xianni.world.WorldMgr;

public class TeamCampaign extends Campaign {

	public TeamCampaign(CampaignTemplateInfo tempInfo, ArmyProxy creater, int taskId) {
		super(tempInfo, creater, taskId);
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
		super.over();
	}

	public void onKick(ArmyProxy army) {

		/** 组队副本，当所有人离开时，销毁副本 */
		if (isEmpty() && getState().getCode() != CampaignState.STOP) {
			over();
		} else {
			CampaignStatuMsg.Builder cstatu = CampaignStatuMsg.newBuilder();
			cstatu.setIndexId(getIndexId());
			cstatu.setTempId(tempId);
			cstatu.setStatu(CampaignStatu.NOTITY2C_OUT);// 退出
			PBMessage statuMsg = MessageUtil.buildMessage(Protocol.C_CAMPAIGN_STATU, cstatu);
			army.sendPbMessage(statuMsg);

			PBMessage quit = new PBMessage(Protocol.C_QUIT_CAMPAIGN);
			army.sendPbMessage(quit);
			removeArmy(army);

			CampaignInfoMsg.Builder infoMsg = CampaignInfoMsg.newBuilder();
			infoMsg.setId(id);
			infoMsg.setCount(armys.size());
			infoMsg.setCreaterId(creater);
			infoMsg.setState(CampaignState.STOP);
			infoMsg.setTempId(tempId);
			PBMessage message = MessageUtil.buildMessage(Protocol.U_CAMPAIGN_INFO, infoMsg);
			army.sendPbMessage(message);
		}
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public List<ArmyProxy> getAllArmys() {
		Team team = TeamMgr.getTeam(creater);
		if (team != null) {
			List<ArmyProxy> all = new ArrayList<>();
			List<Long> members = team.getMembers();
			for (long playerId : members) {
				ArmyProxy army = WorldMgr.getArmy(playerId);
				if (army != null) {
					all.add(army);
				}
			}
			return all;
		} else {
			return super.getAllArmys();
		}
	}

}
