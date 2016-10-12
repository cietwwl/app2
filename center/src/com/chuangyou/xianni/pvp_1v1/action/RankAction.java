package com.chuangyou.xianni.pvp_1v1.action;

import com.chuangyou.xianni.exec.ActionQueue;
import com.chuangyou.xianni.exec.DelayAction;
import com.chuangyou.xianni.pvp_1v1.PvP1v1Manager;

public class RankAction extends DelayAction {

	public RankAction(ActionQueue queue, int delay) {
		super(queue, delay);
	}

	@Override
	public void execute() {
		PvP1v1Manager.rank();
		PvP1v1Manager.existsDelay = false;
	}

}
