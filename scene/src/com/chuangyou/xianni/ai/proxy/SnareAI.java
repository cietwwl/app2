package com.chuangyou.xianni.ai.proxy;

import com.chuangyou.xianni.role.objects.Snare;

public class SnareAI implements AI {
	private Snare snare;

	public SnareAI(Snare snare) {
		this.snare = snare;
	}

	@Override
	public void exe() {
		snare.exe();
	}

}
