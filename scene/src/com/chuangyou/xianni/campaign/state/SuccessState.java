package com.chuangyou.xianni.campaign.state;

import com.chuangyou.xianni.campaign.Campaign;

public class SuccessState extends CampaignState {

	public SuccessState(Campaign campaign) {
		super(campaign);
		this.code = CampaignState.SUCCESS;
	}

	@Override
	public void work() {

	}
}
