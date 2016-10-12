package com.chuangyou.xianni.battle.action;

import com.chuangyou.xianni.role.objects.Robot;

public class RobotPollingAction extends PollingAction {

	public RobotPollingAction(Robot queue) {
		super(queue, PollingAction.DELAY);
	}

	@Override
	public void exec() {

	}

}
