package com.chuangyou.xianni.email.action;

import com.chuangyou.xianni.email.manager.EmailManager;
import com.chuangyou.xianni.exec.ActionQueue;
import com.chuangyou.xianni.exec.LoopAction;

public class EmailLoopAction extends LoopAction {

	public EmailLoopAction(ActionQueue queue, int delay, int count) {
		super(queue, delay, count);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void loopExecute() {
		// TODO Auto-generated method stub
		System.out.println("插入邮件 ");
		EmailManager.insertEmail(1, "测试邮件", "测试邮件，没有附件的邮件");
	}
}
