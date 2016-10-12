package com.chuangyou.xianni.pvp_1v1.action;

import com.chuangyou.xianni.exec.ActionQueue;
import com.chuangyou.xianni.exec.DelayAction;
import com.chuangyou.xianni.pvp_1v1.PvP1v1Manager;

public class SwitchControlAction extends DelayAction {

	public SwitchControlAction(ActionQueue queue, int delay) {
		super(queue, delay);
	}

	@Override
	public void execute() {
		PvP1v1Manager.billingAndClear();
	}
}
