package com.chuangyou.xianni.role.objects;

import com.chuangyou.xianni.battle.damage.Damage;
import com.chuangyou.xianni.battle.damage.DamageStatistic;
import com.chuangyou.xianni.warfield.spawn.MonsterSpawnNode;

public class FieldBoss extends Monster {

	/**
	 * 伤害统计
	 */
	private DamageStatistic statistic;
	
	public FieldBoss(MonsterSpawnNode node) {
		super(node);
		// TODO Auto-generated constructor stub
		statistic = new DamageStatistic(this);
	}

	@Override
	public int takeDamage(Damage damage) {
		// TODO Auto-generated method stub
		synchronized (statistic) {
			statistic.addDamage(damage);
		}
		return super.takeDamage(damage);
	}
	
	@Override
	public boolean fullState() {
		// TODO Auto-generated method stub
		super.fullState();
		synchronized (statistic) {
			statistic.reset();
		}
		
		return true;
	}
	
	@Override
	public boolean onDie(Living killer) {
		// TODO Auto-generated method stub
		synchronized (statistic) {
			statistic.setKiller(killer);
		}
		return super.onDie(killer);
	}

	public DamageStatistic getStatistic() {
		return statistic;
	}
}
