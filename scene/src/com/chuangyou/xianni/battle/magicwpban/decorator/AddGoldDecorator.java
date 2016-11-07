package com.chuangyou.xianni.battle.magicwpban.decorator;

import com.chuangyou.xianni.battle.magicwpban.MagicwpBanBaseDecorator;
import com.chuangyou.xianni.constant.MagicwpBanConstant;
import com.chuangyou.xianni.role.objects.Player;

public class AddGoldDecorator extends MagicwpBanBaseDecorator {

	public AddGoldDecorator(Player master, int level) {
		super(master, level, MagicwpBanConstant.ADD_GOLD);
	}

	@Override
	public boolean isEffect() {
		return true;
	}

	@Override
	public int getEffectValue() {
		return Math.min(8 + 2 * level, 40);
	}
}
