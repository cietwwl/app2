package com.chuangyou.xianni.campaign.state;

import com.chuangyou.xianni.campaign.Campaign;

public class FailState extends CampaignState {

	public FailState(Campaign campaign) {
		super(campaign);
		this.code = CampaignState.FAIL;
	}

	@Override
	public void work() {
		campaign.fail();
	}
}
