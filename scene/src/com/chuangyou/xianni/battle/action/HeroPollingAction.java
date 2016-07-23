package com.chuangyou.xianni.battle.action;

import com.chuangyou.xianni.role.objects.Player;

/**
 * 英雄自检动作/buffer运算
 */
public class HeroPollingAction extends PollingAction {
	static int delay = 1000;

	public HeroPollingAction(Player player) {
		super(player, delay);
	}

	@Override
	public void exec() {

	}

}
