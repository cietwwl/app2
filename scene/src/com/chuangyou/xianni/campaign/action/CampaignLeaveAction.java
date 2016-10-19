package com.chuangyou.xianni.campaign.action;

import java.util.List;

import com.chuangyou.xianni.campaign.Campaign;
import com.chuangyou.xianni.campaign.state.CampaignState;
import com.chuangyou.xianni.campaign.state.StopState;
import com.chuangyou.xianni.constant.CampaignConstant.CampaignType;
import com.chuangyou.xianni.exec.Action;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.objects.Avatar;
import com.chuangyou.xianni.world.ArmyProxy;

public class CampaignLeaveAction extends Action {
	ArmyProxy	army;
	Campaign	campaign;
	boolean		unline;

	public CampaignLeaveAction(Campaign campaign, ArmyProxy army) {
		super(campaign);
		this.army = army;
		this.campaign = campaign;
	}

	public CampaignLeaveAction(Campaign campaign, ArmyProxy army, boolean unLine) {
		super(campaign);
		this.army = army;
		this.campaign = campaign;
		this.unline = unLine;
	}

	@Override
	public void execute() {
		if (this.unline == false) {
			PBMessage quit = new PBMessage(Protocol.C_QUIT_CAMPAIGN);
			army.sendPbMessage(quit);
		}
		campaign.removeArmy(army,false);

		/** 挑战副本离开则销毁 */
		if (campaign.getTemp().getType() == CampaignType.AVATAR) {
			campaign.stateTransition(new StopState(campaign));
		}

		/** 竞技场副本离开则销毁 */
		if (campaign.getTemp().getType() == CampaignType.ARENA) {
			campaign.stateTransition(new StopState(campaign));
		}

		if (campaign.getTemp().getType() == CampaignType.GUILD_SEIZE) {

			/** 渡节副本成功离开时就销毁 */
			if (campaign.getTemp().getType() == CampaignType.STATE && campaign.getState().getCode() >= CampaignState.SUCCESS) {
				campaign.stateTransition(new StopState(campaign));
			}

			if (campaign.getTemp().getType() == CampaignType.GUILD_SEIZE) {
				campaign.stateTransition(new StopState(campaign));
			}

			/** 组队副本，当所有人离开时，销毁副本 */
			if (campaign.getTemp().getType() == CampaignType.TEAM && campaign.isEmpty() && campaign.getState().getCode() != CampaignState.STOP) {
				campaign.stateTransition(new StopState(campaign));
			} else {
				campaign.sendCampaignInfo(army);
			}
			army.getPlayer().removeCampaignBuffer();
			List<Avatar> avatars = army.getAvatars();
			for (Avatar avatar : avatars) {
				avatar.leaveCampaign();
			}
		}
	}
}
