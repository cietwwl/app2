package com.chuangyou.xianni.army;

import com.chuangyou.xianni.player.GamePlayer;

/**
 * 战斗部队，用户战斗信息管理
 */
public class Army {
	private long	armyId;
	private Hero	hero;

	public Army(GamePlayer player) {
		this.armyId = player.getPlayerId();
		hero = new Hero(player);
		int curSoul = player.getBasePlayer().getPlayerJoinInfo().getCurSoul();
		if (curSoul <= 0) {
			curSoul = 10000;
		}
		hero.setCurSoul(curSoul);
		int curBlood = player.getBasePlayer().getPlayerJoinInfo().getCurBlood();
		if (curBlood <= 0) {
			curBlood = 10000;
		}
		hero.setCurBlood(curBlood);
		
		int mana = player.getBasePlayer().getPlayerJoinInfo().getMana();
		hero.setMana(mana);
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

}
