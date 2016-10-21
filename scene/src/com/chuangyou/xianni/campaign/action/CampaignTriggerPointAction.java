package com.chuangyou.xianni.campaign.action;

import com.chuangyou.xianni.campaign.Campaign;
import com.chuangyou.xianni.exec.Action;
import com.chuangyou.xianni.warfield.spawn.SpwanNode;
import com.chuangyou.xianni.world.ArmyProxy;

public class CampaignTriggerPointAction extends Action {
	private Campaign campaign;
	private ArmyProxy army;
	private SpwanNode node;

	public CampaignTriggerPointAction(Campaign campaign, ArmyProxy army, SpwanNode node) {
		super(campaign);
		// TODO Auto-generated constructor stub
		this.campaign = campaign;
		this.army = army;
		this.node = node;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

		campaign.triggerPoint(army, node);
	}

}
