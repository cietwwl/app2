package com.chuangyou.xianni.campaign;

import java.util.List;

import com.chuangyou.xianni.campaign.state.StopState;
import com.chuangyou.xianni.entity.campaign.CampaignTemplateInfo;
import com.chuangyou.xianni.team.Team;
import com.chuangyou.xianni.team.TeamMgr;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.world.ArmyProxy;
import com.chuangyou.xianni.world.WorldMgr;

public class TeamCampaign extends Campaign {

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
		super.over();
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

}
