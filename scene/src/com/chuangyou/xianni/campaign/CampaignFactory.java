package com.chuangyou.xianni.campaign;

import com.chuangyou.common.util.JSONUtil;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.campaign.CampaignTemplateInfo;
import com.chuangyou.xianni.inverseBead.InverseBeadCampaign;
import com.chuangyou.xianni.world.ArmyProxy;

public class CampaignFactory {
	public static final int	SINGLE	= 1;
	public static final int	TEAM	= 2;
	public static final int INVERSEBEAD=3;//天逆珠逆境

	/**
	 * 创建副本
	 */
	public static Campaign createCampaign(CampaignTemplateInfo tempInfo, ArmyProxy creater, int taskId) {
		switch (tempInfo.getType()) {
			case SINGLE:
				return new SingleCampaign(tempInfo, creater,taskId);
			case TEAM:
				return new TeamCampaign(tempInfo, creater,taskId);
			case INVERSEBEAD:
				return new InverseBeadCampaign(tempInfo, creater, taskId);
			default:
				break;
		}
		Log.error("the campaignType not exists ," + JSONUtil.getJSONString(tempInfo));
		return null;
	}
}
