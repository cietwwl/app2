package com.chuangyou.xianni.campaign;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.campaign.CampaignTaskTemplateInfo;
import com.chuangyou.xianni.entity.campaign.CampaignTemplateInfo;
import com.chuangyou.xianni.sql.dao.DBManager;

public class CampaignTempMgr {
	public static Map<Integer, CampaignTemplateInfo>	campaignTemps	= new HashMap<>();
	private static Map<Integer, Integer>				campaignScore	= new HashMap<>();

	public static boolean init() {
		reload();
		return true;
	}

	public static boolean reload() {
		List<CampaignTemplateInfo> campaignTemplateInfos = DBManager.getCampaignTemplateInfoDao().getAll();
		if (campaignTemplateInfos == null || campaignTemplateInfos.size() == 0) {
			return false;
		}
		for (CampaignTemplateInfo info : campaignTemplateInfos) {
			campaignTemps.put(info.getTemplateId(), info);
			if (info.getAttrTaskIds() != null) {
				for (int tid : info.getAttrTaskIds()) {
					CampaignTaskTemplateInfo tinfo = CampaignTaskTempMgr.get(tid);
					if (tinfo != null) {
						int toalScore = 0;
						if (campaignScore.containsKey(info.getTemplateId())) {
							toalScore = campaignScore.get(info.getTemplateId());
						}
						toalScore += tinfo.getPoint();
						campaignScore.put(info.getTemplateId(), toalScore);
					}
				}
			}
		}

		return true;
	}

	public static CampaignTemplateInfo getTempInfo(int id) {
		return campaignTemps.get(id);
	}

	public static int getMaxPoint(int campaignId) {
		if (campaignScore.containsKey(campaignId)) {
			return campaignScore.get(campaignId);
		}
		return 0;
	}

}
