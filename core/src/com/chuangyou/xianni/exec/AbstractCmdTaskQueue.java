package com.chuangyou.xianni.exec;

import java.util.LinkedList;
import java.util.Queue;

import com.chuangyou.common.util.Log;

/**
 * <pre>
 * CmdTaskQueue基本功能实现
 * </pre>
 */
public class AbstractCmdTaskQueue implements CmdTaskQueue {

	private Queue<CmdTask> queue;
	private CmdExecutor executor;

	public AbstractCmdTaskQueue(CmdExecutor executor) {
		this.executor = executor;
		queue = new LinkedList<CmdTask>();
	}

	public CmdTaskQueue getCmdTaskQueue() {
		return this;
	}

	public void enqueue(CmdTask cmd) {
		boolean canExec = false;
		synchronized (queue) {
			queue.add(cmd);
			if (queue.size() == 1) {
				canExec = true;
			} else if (queue.size() > 3000) {
				Log.warn(cmd.toString() + " queue size : " + queue.size());
			}
		}

		if (canExec) {
			executor.execute(cmd);
		}
	}

	public void dequeue(CmdTask cmdTask) {
		CmdTask nextCmdTask = null;
		synchronized (queue) {
			if (queue.size() == 0) {
				Log.error("queue.size() is 0.");
			}
			CmdTask temp = queue.remove();
			if (temp != cmdTask) {
				Log.error("action queue error. temp " + temp.toString() + ", action : " + cmdTask.toString());
			}
			if (queue.size() != 0) {
				nextCmdTask = queue.peek();
			}
		}

		if (nextCmdTask != null) {
			executor.execute(nextCmdTask);
		}
	}

	public Queue<CmdTask> getQueue() {
		return queue;
	}

	public void clear() {
		synchronized (queue) {
			queue.clear();
		}
	}
}
