package com.chuangyou.xianni.activity.action;

import com.chuangyou.xianni.activity.logic.ActityUtil;
import com.chuangyou.xianni.activity.template.ActivityTemplateMgr;
import com.chuangyou.xianni.entity.activity.ActivityConfig;
import com.chuangyou.xianni.exec.ActionQueue;
import com.chuangyou.xianni.exec.DelayAction;

public class TimingCheckActivityAction extends DelayAction {
	ActivityConfig config;

	public TimingCheckActivityAction(ActionQueue queue, int delay, ActivityConfig config) {
		super(queue, delay);
		this.config = config;
	}

	@Override
	public void execute() {
		boolean inTime = ActityUtil.inTime(config);
		// 在时间范围内，但是活动未开启
		if (inTime && config.getStatu() == ActivityConfig.ACTIVITY_CLOSE) {
			config.setStatu(ActivityConfig.ACTIVITY_OPEN);
			ActivityTemplateMgr.openActivity(config);
			ActivityTemplateMgr.sendActivityConfigChange(config);
		}
		if (!inTime && config.getStatu() == ActivityConfig.ACTIVITY_OPEN) {
			config.setStatu(ActivityConfig.ACTIVITY_CLOSE);
			ActivityTemplateMgr.closedActivity(config);
			ActivityTemplateMgr.sendActivityConfigChange(config);
		}
		config.setDelayChecking(false);
	}

}
