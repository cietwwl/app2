package com.chuangyou.xianni.fieldBoss.action;

import com.chuangyou.xianni.exec.DelayAction;
import com.chuangyou.xianni.exec.ThreadManager;
import com.chuangyou.xianni.fieldBoss.manager.WorldBossManager;

public class CreateWorldBossAction extends DelayAction {

	public CreateWorldBossAction(int delay) {
		// TODO Auto-generated constructor stub
		super(ThreadManager.actionExecutor.getDefaultQueue(), delay);
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		WorldBossManager.createNext();
	}

}
