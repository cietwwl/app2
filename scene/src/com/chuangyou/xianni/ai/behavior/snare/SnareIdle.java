package com.chuangyou.xianni.ai.behavior.snare;

import com.chuangyou.xianni.ai.AIState;
import com.chuangyou.xianni.role.objects.Snare;

public class SnareIdle extends SnareBaseBehavior {

	public SnareIdle(Snare snare) {
		super(AIState.IDLE, snare);
	}

	@Override
	public void exe() {

	}

	@Override
	public AIState next() {
		return null;
	}

}
