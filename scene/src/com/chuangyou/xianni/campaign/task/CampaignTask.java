package com.chuangyou.xianni.campaign.task;

import com.chuangyou.xianni.campaign.Campaign;
import com.chuangyou.xianni.entity.campaign.CampaignTaskTemplateInfo;

public class CampaignTask {
	private Campaign		campaign;	// 副本
	private CTBaseCondition	conditon;	// 条件

	private State			state;		// 任务状态

	private enum State {
		PROCESSING(1), SUCCESS(2), FAIL(3);
		int code;

		private State(int code) {
			this.code = code;
		}
	}

	public CampaignTask(Campaign campaign, CampaignTaskTemplateInfo tempInfo) {
		this.campaign = campaign;
		conditon = CTBaseCondition.createCondition(tempInfo);
	}

	public int getConditionType() {
		return conditon.getTempInfo().getConditionType();
	}

	/** 任务结算 */
	public void billing() {

	}

	/** 任务更新 */
	public void update() {

	}

	/** 触发任务事件 */
	public void notityEvent(int... param) {

	}

}