package com.chuangyou.xianni.pvp_1v1.action;

import com.chuangyou.xianni.exec.ActionQueue;
import com.chuangyou.xianni.exec.DelayAction;
import com.chuangyou.xianni.pvp_1v1.PvP1v1Manager;

public class MatchAction extends DelayAction {

	public MatchAction(ActionQueue queue, int delay) {
		super(queue, delay);
	}

	@Override
	public void execute() {
		if (PvP1v1Manager.STATUS == false) {
			return;
		}
		PvP1v1Manager.matching();
		this.execTime = System.currentTimeMillis() + 5 * 1000;
		this.getActionQueue().enDelayQueue(this);
	}

}
