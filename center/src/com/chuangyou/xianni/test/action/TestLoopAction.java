package com.chuangyou.xianni.test.action;

import com.chuangyou.xianni.exec.DelayAction;
import com.chuangyou.xianni.exec.ThreadManager;

public class TestLoopAction extends DelayAction {
	private int	delay;

	private int	count	= 100;

	public TestLoopAction(int delay) {
		super(ThreadManager.actionExecutor.getDefaultQueue(), delay);
		this.delay = delay;
	}

	@Override
	public void execute() {
		if (count <= 0) {
			return;
		}
		count--;
		System.out.println("TestLoopAction_count:" + count + "  exectm:" + System.currentTimeMillis());
		this.execTime = System.currentTimeMillis() + delay;
		ThreadManager.actionExecutor.getDefaultQueue().enDelayQueue(this);
	}

}
