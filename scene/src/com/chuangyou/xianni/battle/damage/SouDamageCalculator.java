package com.chuangyou.xianni.battle.damage;

import com.chuangyou.xianni.role.objects.Living;

/**
 * 魂攻伤害 计算公式： max（魂攻-对方魂防*1.2，0）*random（0.7,1.3）
 */
public class SouDamageCalculator implements DamageCalculator {

	@Override
	public int calcDamage(Living source, Living target, int percent, int value) {
		// 已方魂攻
		int soulAttack = source.getSoulAttack();
		// 对方魂防
		int soulDeffence = target.getSoulDefence();

		// 当对方处于元魂状态时，对方魂防御降低50%
		if (target.isSoulState()) {
			soulDeffence -= soulDeffence * 0.3f;
		}
		int damageValue = (int) (Math.max(soulAttack - soulDeffence * 1.2, 0) * random.next(70, 130) / 100);
		damageValue = damageValue * percent / 10000 + value;
		return damageValue;

	}

}
