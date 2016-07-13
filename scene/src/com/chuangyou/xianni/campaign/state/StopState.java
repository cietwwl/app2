package com.chuangyou.xianni.campaign.state;

import com.chuangyou.xianni.campaign.Campaign;

public class StopState extends CampaignState {

	public StopState(Campaign campaign) {
		super(campaign);
		this.code = CampaignState.STOP;
	}

	@Override
	public void work() {

	}

}
