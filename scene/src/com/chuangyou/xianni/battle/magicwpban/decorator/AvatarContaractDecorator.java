package com.chuangyou.xianni.battle.magicwpban.decorator;

import com.chuangyou.xianni.battle.magicwpban.MagicwpBanBaseDecorator;
import com.chuangyou.xianni.constant.MagicwpBanConstant;
import com.chuangyou.xianni.role.objects.Player;

public class AvatarContaractDecorator extends MagicwpBanBaseDecorator {

	private int addition;

	public AvatarContaractDecorator(Player master, int level) {
		super(master, level, MagicwpBanConstant.AVATAR_CONTRACT);
		addition = 5 + Math.min(5 + (level - 1), 20);
	}

	public boolean isEffect() {
		return true;
	}

	public int getEffectValue() {
		return addition;
	}
}
