package com.chuangyou.xianni.campaign.action;

import java.util.List;

import com.chuangyou.xianni.campaign.Campaign;
import com.chuangyou.xianni.exec.Action;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.objects.Avatar;
import com.chuangyou.xianni.world.ArmyProxy;

public class CampainOverLeaveAction extends Action {
	Campaign	campaign;
	ArmyProxy	army;

	public CampainOverLeaveAction(Campaign campaign, ArmyProxy army) {
		super(campaign);
		this.army = army;
		this.campaign = campaign;
	}

	@Override
	public void execute() {
		PBMessage quit = new PBMessage(Protocol.C_QUIT_CAMPAIGN);
		army.sendPbMessage(quit);
		campaign.removeArmy(army);
		// campaign.setExpiredTime(System.currentTimeMillis() + 30 * 60 * 1000);
		army.getPlayer().removeCampaignBuffer();

		List<Avatar> avatars = army.getAvatars();
		for (Avatar avatar : avatars) {
			avatar.leaveCampaign();
		}
	}

}
