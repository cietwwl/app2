package com.chuangyou.xianni.test.action;

import com.chuangyou.xianni.exec.DelayAction;
import com.chuangyou.xianni.exec.ThreadManager;

public class TestDelayAction extends DelayAction {

	private String name;

	public TestDelayAction(String name, int delay) {
		super(ThreadManager.actionExecutor.getDefaultQueue(), delay);
		this.name = name;
	}

	@Override
	public void execute() {
		System.out.println("TestDelayAction : " + name + "  execTime:" + System.currentTimeMillis());
	}

}
