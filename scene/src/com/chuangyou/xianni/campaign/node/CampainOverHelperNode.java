package com.chuangyou.xianni.campaign.node;

import com.chuangyou.xianni.campaign.Campaign;
import com.chuangyou.xianni.campaign.state.SuccessState;
import com.chuangyou.xianni.warfield.spawn.SpwanNode;

public class CampainOverHelperNode extends CampaignNodeDecorator {
	public void start(Campaign campaign, SpwanNode node) {
		campaign.stateTransition(new SuccessState(campaign));
	}
}
