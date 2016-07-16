package com.chuangyou.xianni.battleMode.action;

import com.chuangyou.xianni.email.manager.EmailManager;
import com.chuangyou.xianni.exec.ActionQueue;
import com.chuangyou.xianni.exec.LoopAction;

public class BattleModeAction extends LoopAction {

	public BattleModeAction(ActionQueue queue, int delay, int count) {
		super(queue, delay, count);
	}

	@Override
	public void loopExecute() {
		// TODO Auto-generated method stub
		System.out.println("插入邮件 ");
		EmailManager.insertEmail(1, "测试邮件", "测试邮件，没有附件的邮件");
	}
}
