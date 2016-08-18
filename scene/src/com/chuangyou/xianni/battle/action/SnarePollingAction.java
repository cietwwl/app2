package com.chuangyou.xianni.battle.action;

import com.chuangyou.xianni.ai.proxy.SnareAI;
import com.chuangyou.xianni.role.action.UpdatePositionAction;
import com.chuangyou.xianni.role.objects.Snare;

public class SnarePollingAction extends PollingAction {
	Snare					living;
	SnareAI					ai;
	UpdatePositionAction	updataPos;

	public SnarePollingAction(Snare snare, SnareAI ai, UpdatePositionAction updataPos) {
		super(snare, PollingAction.DELAY);
		this.living = snare;
		this.ai = ai;
		this.updataPos = updataPos;
	}

	@Override
	public void exec() {
		ai.exe();
		updataPos.exe();
	}

}
