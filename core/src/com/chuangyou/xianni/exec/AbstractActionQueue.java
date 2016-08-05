package com.chuangyou.xianni.exec;


import java.util.LinkedList;
import java.util.Queue;

import com.chuangyou.common.util.Log;


public class AbstractActionQueue implements ActionQueue {

	private Queue<Action> queue;
	private ActionExecutor executor;

	public AbstractActionQueue(ActionExecutor executor) {
		this.executor = executor;
		queue = new LinkedList<Action>();
	}

	public AbstractActionQueue(ActionExecutor executor, Queue<Action> queue) {
		this.executor = executor;
		this.queue = queue;
	}

	public ActionQueue getActionQueue() {
		return this;
	}

	public Queue<Action> getQueue() {
		return queue;
	}

	public void enDelayQueue(DelayAction delayAction) {
		executor.enDelayQueue(delayAction);
	}

	public void enqueue(Action action) {
		boolean canExec = false;
		synchronized (queue) {
			queue.add(action);
			if (queue.size() == 1) {
				canExec = true;
			} else if (queue.size() > 1000) {
				Log.warn(action.toString() + " queue size : " + queue.size());
			}
		}

		if (canExec) {
			executor.execute(action);
		}
	}

	public void dequeue(Action action) {
		Action nextAction = null;
		synchronized (queue) {
			if (queue.size() == 0) {
				Log.error("queue.size() is 0.");
				return;
			}
			Action temp = queue.remove();
			if (temp != action) {
				Log.error("action queue error. temp " + temp.toString() + ", action : " + action.toString());
			}
			if (queue.size() != 0) {
				nextAction = queue.peek();
			}
		}

		if (nextAction != null) {
			executor.execute(nextAction);
		}
	}

	public void clear() {
		synchronized (queue) {
			queue.clear();
		}
	}
}
