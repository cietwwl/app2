package com.chuangyou.xianni.campaign.state;

import com.chuangyou.xianni.campaign.Campaign;

public class StartState extends CampaignState {

	public StartState(Campaign campaign) {
		super(campaign);
		this.code = CampaignState.START;
	}

	@Override
	public void work() {
		campaign.start();
	}

}
