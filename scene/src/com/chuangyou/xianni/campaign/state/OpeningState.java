package com.chuangyou.xianni.campaign.state;

import com.chuangyou.xianni.campaign.Campaign;

public class OpeningState extends CampaignState {
	
	public OpeningState(Campaign campaign) {
		super(campaign);
		this.code = CampaignState.OPENING;
	}

	@Override
	public void work() {

	}

}
