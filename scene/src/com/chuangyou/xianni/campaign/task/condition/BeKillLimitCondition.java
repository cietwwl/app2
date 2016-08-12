package com.chuangyou.xianni.campaign.task.condition;

import com.chuangyou.xianni.campaign.task.CTBaseCondition;
import com.chuangyou.xianni.entity.campaign.CampaignTaskTemplateInfo;

/** 成员最小死亡次数 */
public class BeKillLimitCondition extends CTBaseCondition {

	public BeKillLimitCondition(CampaignTaskTemplateInfo tempInfo) {
		super(tempInfo);
	}

	/** 是否完成 */
	public boolean isComplated() {
		return record.getProgress() > tempInfo.getParam1();
	}

	public boolean addProgress(int param) {
		if (!isComplated()) {
			record.setProgress(record.getProgress() + param);
			return true;
		}
		return false;
	}
}
