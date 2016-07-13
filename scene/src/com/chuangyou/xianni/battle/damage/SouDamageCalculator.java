package com.chuangyou.xianni.battle.damage;

import com.chuangyou.common.util.ThreadSafeRandom;
import com.chuangyou.xianni.entity.skill.SkillActionTemplateInfo;
import com.chuangyou.xianni.role.objects.Living;

/**
 * 魂攻伤害 计算公式： max（魂攻-对方魂防*1.2，0）*random（0.7,1.3）
 */
public class SouDamageCalculator implements DamageCalculator {

	@Override
	public int calcDamage(Living source, Living target, SkillActionTemplateInfo skillTemp) {
		ThreadSafeRandom random = new ThreadSafeRandom();
		// 已方魂攻
		int soulAttack = source.getSoulAttack();
		// 对方魂防
		int soulDeffence = target.getSoulDefence();

		// 当对方处于元魂状态时，对方魂防御降低50%
		if (target.isSoulState()) {
			soulDeffence -= soulDeffence * 0.3f;
		}
		int damageValue = (int) (Math.max(soulAttack - soulDeffence * 1.2, 0) * random.next(70, 13) / 10);
		damageValue = damageValue * skillTemp.getParamParent1() / 100 + skillTemp.getParamValue1();
		return damageValue;

	}

}
