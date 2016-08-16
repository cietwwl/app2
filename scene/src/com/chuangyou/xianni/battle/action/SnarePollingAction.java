package com.chuangyou.xianni.battle.action;

import com.chuangyou.xianni.ai.proxy.SnareAI;
import com.chuangyou.xianni.role.objects.Snare;

public class SnarePollingAction extends PollingAction {
	Snare	living;
	SnareAI	ai;

	public SnarePollingAction(Snare snare, SnareAI ai) {
		super(snare, 500);
		this.living = snare;
		this.ai = ai;
	}

	@Override
	public void exec() {
		ai.exe();
	}

}
