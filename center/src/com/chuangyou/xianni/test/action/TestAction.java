package com.chuangyou.xianni.test.action;

import com.chuangyou.xianni.exec.Action;
import com.chuangyou.xianni.exec.ThreadManager;

public class TestAction extends Action {
	private String name;

	public TestAction(String name) {
		super(ThreadManager.actionExecutor.getDefaultQueue());
		this.name = name;
	}

	@Override
	public void execute() {
		//System.out.println("-----------" + name + "---------------");
	}

}
