package com.chuangyou.xianni.inverseBead.action;

import java.util.List;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.campaign.Campaign;
import com.chuangyou.xianni.campaign.CampaignFactory;
import com.chuangyou.xianni.campaign.CampaignMgr;
import com.chuangyou.xianni.campaign.CampaignTempMgr;
import com.chuangyou.xianni.campaign.TeamCampaign;
import com.chuangyou.xianni.entity.campaign.CampaignTemplateInfo;
import com.chuangyou.xianni.exec.Action;
import com.chuangyou.xianni.team.Team;
import com.chuangyou.xianni.team.TeamMgr;
import com.chuangyou.xianni.warfield.FieldMgr;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.world.ArmyProxy;
import com.chuangyou.xianni.world.WorldMgr;

public class CreateCampaignAction extends Action {
	private ArmyProxy army;
	private int campaignId;

	public CreateCampaignAction(ArmyProxy army, int campaignId) {
		super(army);
		this.army = army;
		this.campaignId = campaignId;

	}

	@Override
	public void execute() {
		Field curField = FieldMgr.getIns().getField(army.getFieldId());
		// 不允许在副本中创建副本
		if (curField != null && curField.getCampaignId() > 0) {
			Log.error("--user request create campaign but is aleady in campaign. playerId : " + army.getPlayerId());
//			System.out.println("--user request create campaign but is aleady in campaign. playerId : " + army.getPlayerId());
			return;
		}

		CampaignTemplateInfo temp = CampaignTempMgr.get(campaignId);
		if (temp == null) {
			Log.error("--playerId: " + army.getPlayerId() + " create campaign if fail ,campaignId:" + campaignId);
//			System.out.println("--playerId: " + army.getPlayerId() + " create campaign if fail ,campaignId:" + campaignId);
			return;
		}

		Campaign campaign = CampaignFactory.createCampaign(temp, army, 0);
		CampaignMgr.add(campaign);

		campaign.start();
		campaign.onPlayerEnter(army);
//		System.out.println("-----------campaign.onPlayerEnter(army);------------------");
	}

}
