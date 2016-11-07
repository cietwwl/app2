package com.chuangyou.xianni.battle.magicwpban.decorator;

import com.chuangyou.xianni.battle.magicwpban.MagicwpBanBaseDecorator;
import com.chuangyou.xianni.constant.MagicwpBanConstant;
import com.chuangyou.xianni.role.objects.Player;

public class DecomposeDecorator extends MagicwpBanBaseDecorator {

	public DecomposeDecorator(Player master, int level) {
		super(master, level, MagicwpBanConstant.DECOMPOSE);
	}

	public int getEffectValue() {
		return Math.min(5 + (level - 1), 50);
	}
}
