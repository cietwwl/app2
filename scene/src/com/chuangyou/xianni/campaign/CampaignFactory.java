package com.chuangyou.xianni.campaign;

import com.chuangyou.common.util.JSONUtil;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.campaign.CampaignTemplateInfo;
import com.chuangyou.xianni.world.ArmyProxy;

public class CampaignFactory {
	public static final int	SINGLE	= 1;
	public static final int	TEAM	= 2;

	/**
	 * 创建副本
	 */
	public static Campaign createCampaign(CampaignTemplateInfo tempInfo, ArmyProxy creater) {
		switch (tempInfo.getType()) {
			case SINGLE:
				return new SingleCampaign(tempInfo, creater);
			case TEAM:
				return new TeamCampaign(tempInfo, creater);
			default:
				break;
		}
		Log.error("the campaignType not exists ," + JSONUtil.getJSONString(tempInfo));
		return null;
	}
}
