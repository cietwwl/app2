package com.chuangyou.xianni.exec;

import com.chuangyou.common.util.Log;
public abstract class Action implements Runnable {

	private ActionQueue queue;
	protected Long createTime;

	public Action(ActionQueue queue) {
		this.queue = queue;
		createTime = System.currentTimeMillis();
	}

	public ActionQueue getActionQueue() {
		return queue;
	}

	@Override
	public void run() {
		if (queue != null) {
			long start = System.currentTimeMillis();
			try {
				execute();
				long end = System.currentTimeMillis();
				long interval = end - start;
				long leftTime = end - createTime;
				if (interval >= 1000) {
					Log.warn("execute action : " + this.toString() + ", interval : " + interval + ", leftTime : " + leftTime + ", size : " + queue.getQueue().size());
				}
			} catch (Exception e) {
				e.printStackTrace();
				Log.error("run action execute exception. action : " + this.toString(), e);
			} finally {
				queue.dequeue(this);
			}
		}
	}

	public abstract void execute();
}
