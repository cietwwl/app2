package com.chuangyou.xianni.campaign.action;

import com.chuangyou.xianni.campaign.Campaign;
import com.chuangyou.xianni.exec.Action;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
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
		campaign.removeArmy(army, true);
		army.getPlayer().removeCampaignBuffer();
	}

}
