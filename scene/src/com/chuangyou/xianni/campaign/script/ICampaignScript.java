package com.chuangyou.xianni.campaign.script;

import com.chuangyou.xianni.script.IScript;

public interface ICampaignScript extends IScript {

	public void notInCampaignCreateField(long playerId, int campaignId);
}
