package com.chuangyou.xianni.battle.magicwpban.decorator;

import com.chuangyou.xianni.battle.magicwpban.MagicwpBanBaseDecorator;
import com.chuangyou.xianni.constant.MagicwpBanConstant;
import com.chuangyou.xianni.role.objects.Player;

public class MonstarDamageAddDecorator extends MagicwpBanBaseDecorator {
	private int percent;

	public MonstarDamageAddDecorator(Player master, int level) {
		super(master, level, MagicwpBanConstant.MONSTER_DAMAGE_ADD);
		percent = Math.min(5 + (level - 1), 20);
	}

	public int getEffectValue() {
		return percent;
	}

}
