package com.chuangyou.xianni.campaign.action;

import com.chuangyou.xianni.campaign.Campaign;
import com.chuangyou.xianni.campaign.PlaneCampaign;
import com.chuangyou.xianni.exec.Action;
import com.chuangyou.xianni.warfield.action.EnterFieldAction;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.world.ArmyPositionRecord;
import com.chuangyou.xianni.world.ArmyProxy;

public class CampainOverLeaveAction extends Action {
	Campaign	campaign;
	ArmyProxy	army;

	public CampainOverLeaveAction(Campaign campaign, ArmyProxy army) {
		super(campaign);
		this.army = army;
		this.campaign = campaign;
	}

	@Override
	public void execute() {
		campaign.removeArmy(army, true);
		army.getPlayer().removeCampaignBuffer();

		if (campaign instanceof PlaneCampaign) {
			int outFiledId = campaign.getTemp().getTemplateId() % Field.MAX_ID;
			EnterFieldAction action = new EnterFieldAction(army, outFiledId, outFiledId, army.getPlayer().getPostion());
			army.enqueue(action);
		} else {
			ArmyPositionRecord posRecord = army.getPosRecord();
			if (posRecord == null) {
				army.returnBornMap();
				return;
			}
			EnterFieldAction action = new EnterFieldAction(army, posRecord.getMapId(), posRecord.getMapTempId(), posRecord.getPos());
			army.enqueue(action);
		}
	}

}
