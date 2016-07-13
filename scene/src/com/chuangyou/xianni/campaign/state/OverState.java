package com.chuangyou.xianni.campaign.state;

import com.chuangyou.xianni.campaign.Campaign;

public class OverState extends CampaignState {

	public OverState(Campaign campaign) {
		super(campaign);
		this.code = CampaignState.OVER;
	}

	@Override
	public void work() {

	}

}
