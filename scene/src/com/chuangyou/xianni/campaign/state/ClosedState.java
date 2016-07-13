package com.chuangyou.xianni.campaign.state;

import com.chuangyou.xianni.campaign.Campaign;

public class ClosedState extends CampaignState {
	
	public ClosedState(Campaign campaign) {
		super(campaign);
		this.code = CampaignState.COLSEED;
	}

	@Override
	public void work() {

	}

}
