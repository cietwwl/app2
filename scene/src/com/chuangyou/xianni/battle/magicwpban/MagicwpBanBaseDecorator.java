package com.chuangyou.xianni.battle.magicwpban;

import com.chuangyou.common.util.ThreadSafeRandom;
import com.chuangyou.xianni.battle.damage.Damage;
import com.chuangyou.xianni.constant.MagicwpBanConstant;
import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.role.objects.Player;

public abstract class MagicwpBanBaseDecorator {
	// 等级
	protected int						level;
	// 所有人
	protected Player					master;
	// 随机数
	protected static ThreadSafeRandom	random	= new ThreadSafeRandom();
	// 禁制模板
	protected MagicwpBanConstant		temp;

	public MagicwpBanBaseDecorator(Player master, int level, MagicwpBanConstant temp) {
		this.level = level;
		this.master = master;
		this.temp = temp;
	}

	public int getCd() {
		return temp.getCd();
	}

	public boolean isEffect() {
		return false;
	}

	public int getEffectValue() {
		return 0;
	}

	public void exe() {

	}

	public void exe(int value) {

	}

	public void exe(Damage damage) {

	}

	public void exe(Living target) {

	}

	public String toString() {
		return temp.toString();
	}

	public int getLevel() {
		return level;
	}

	public Player getMaster() {
		return master;
	}

	public MagicwpBanConstant getTemp() {
		return temp;
	}

}
