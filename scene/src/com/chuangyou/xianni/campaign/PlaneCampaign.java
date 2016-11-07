package com.chuangyou.xianni.campaign;

import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.entity.campaign.CampaignTemplateInfo;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.world.ArmyProxy;

/** 位面副本 */
public class PlaneCampaign extends Campaign {

	public PlaneCampaign(CampaignTemplateInfo tempInfo, ArmyProxy creater, int taskId) {
		super(tempInfo, creater, taskId);
	}

	@Override
	public void success() {
		endTime = System.currentTimeMillis() + 1 * 1000;// 马上结束
	}

	public boolean agreedToEnter(ArmyProxy army) {
		if (super.agreedToEnter(army)) {
			return army.getPlayerId() == creater;
		}
		if (army.getPlayer().getField().id != tempId % Field.MAX_ID) {
			return false;
		}
		return false;
	}

	public Vector3 getBornNode(ArmyProxy player) {
		if (player.getPlayer().getPostion() != null && player.getPlayer().getPostion() != Vector3.Invalid) {
			return player.getPlayer().getPostion();
		}
		return super.getBornNode(player);
	}

}
