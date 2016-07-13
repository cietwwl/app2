package com.chuangyou.xianni.campaign;

import com.chuangyou.xianni.entity.campaign.CampaignTemplateInfo;
import com.chuangyou.xianni.world.ArmyProxy;

public class SingleCampaign extends Campaign {

	public SingleCampaign(CampaignTemplateInfo tempInfo, ArmyProxy creater) {
		super(tempInfo, creater);
	}

	public boolean agreedToEnter(ArmyProxy army) {
		if (super.agreedToEnter(army)) {
			return army.getPlayerId() == creater;
		}
		return false;
	}
}
