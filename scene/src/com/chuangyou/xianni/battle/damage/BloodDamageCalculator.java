package com.chuangyou.xianni.battle.damage;

import com.chuangyou.common.util.ThreadSafeRandom;
import com.chuangyou.xianni.entity.skill.SkillActionTemplateInfo;
import com.chuangyou.xianni.role.objects.Living;

/**
 * 气血伤害 计算公式： （max（攻击-对方防御*1.2，0）+攻击*0.025）*random（0.8,1.2）
 */
public class BloodDamageCalculator implements DamageCalculator {

	@Override
	public int calcDamage(Living source, Living target, SkillActionTemplateInfo skillTemp) {
		int attack = source.getAttack();
		int defence = target.getDefence();
		ThreadSafeRandom random = new ThreadSafeRandom();
		int damageValue = (int) ((Math.max(attack - defence * 1.2, 0) + attack * 0.025) * random.next(80, 120) / 100);
		damageValue = damageValue * skillTemp.getParamParent1() / 100 + skillTemp.getParamValue1();
		return damageValue;
	}

}
