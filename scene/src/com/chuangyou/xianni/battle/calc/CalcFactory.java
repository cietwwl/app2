package com.chuangyou.xianni.battle.calc;

import com.chuangyou.xianni.battle.damage.DamageCalculator;
import com.chuangyou.xianni.battle.damage.DamageFactory;

public class CalcFactory {

	/**
	 * 固定技能，固定方式计算
	 * 
	 * @param skillId
	 * @param calcWay
	 * @return
	 */
	public static SkillCalc createSkillCalc(int skillId, int calcWay, int job) {
		DamageCalculator damageCalc = DamageFactory.createCalculator(calcWay, job);
		if (damageCalc == null) {
			return null;
		}
		SkillCalc calc = null;
		switch (skillId) {
			case 1:
			default:
				calc = new SingleLivingAttack(skillId, damageCalc);
				break;
		}
		return calc;
	}

	/**
	 * 固定技能，固定伤害计算
	 * 
	 * @param skillId
	 * @param fixDamage
	 * @return
	 */
	/*
	 * public static SkillCalc createFixDamageCalc(int skillId, int fixDamage) {
	 * DamageCalculator damageCalc = new FixedDamage(fixDamage); if(damageCalc
	 * == null) { return null; }
	 * 
	 * SkillCalc calc = new SingleLivingAttack(skillId, damageCalc); return
	 * calc; }
	 */
}
