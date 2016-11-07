package com.chuangyou.xianni.battle.magicwpban;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.battle.damage.Damage;
import com.chuangyou.xianni.role.objects.Living;

public class MagicwpCompanent {
	private MagicwpBanBaseDecorator	decorator;
	private long					exeTime;

	public MagicwpCompanent(MagicwpBanBaseDecorator decorator) {
		this.decorator = decorator;
	}

	public boolean isEffect() {
		try {
			long cur_now = System.currentTimeMillis();
			if (decorator.isEffect() && cur_now >= exeTime + decorator.getCd() * 1000) {
				exeTime = cur_now;
				return true;
			}
		} catch (Exception e) {
			Log.error("isEffect", e);
		}
		return false;
	}

	public int getEffectValue() {
		return decorator.getEffectValue();
	}

	public void exe() {
		try {
			decorator.exe();
		} catch (Exception e) {
			Log.error("exe", e);
		}
	}

	public void exe(int value) {
		try {
			decorator.exe(value);
		} catch (Exception e) {
			Log.error("exe(int value)", e);
		}
	}

	public void exe(Damage damage) {
		try {
			decorator.exe(damage);
		} catch (Exception e) {
			Log.error("exe(Damage damage)", e);
		}
	}

	public void exe(Living target) {
		try {
			decorator.exe(target);
		} catch (Exception e) {
			Log.error("exe(Living target)", e);
		}
	}
}
