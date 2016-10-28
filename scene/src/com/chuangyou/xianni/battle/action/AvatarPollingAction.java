package com.chuangyou.xianni.battle.action;

import com.chuangyou.xianni.role.objects.Living;

public class AvatarPollingAction extends PollingAction {

	public AvatarPollingAction(Living queue) {
		super(queue, PollingAction.DELAY);
	}

	@Override
	public void exec() {
		
	}

}
