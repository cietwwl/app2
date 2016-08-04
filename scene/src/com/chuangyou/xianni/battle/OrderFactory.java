package com.chuangyou.xianni.battle;

import java.util.List;

import com.chuangyou.xianni.battle.calc.CalcFactory;
import com.chuangyou.xianni.battle.calc.SkillCalc;
import com.chuangyou.xianni.battle.skill.Skill;
import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.warfield.helper.Selector;

/**
 * 战斗指令工厂类
 * 
 */
public class OrderFactory {

	/**
	 * 创建战斗指令
	 * 
	 * @param source
	 * @param skillInfo
	 * @param targets
	 * @param aiInfo
	 * @return
	 */
	public static AttackOrder createAttackOrder(Living source, Skill skill, List<Living> targets, long attackId) {
		AttackOrder attackOrder = null;
		SkillCalc skillCalc = CalcFactory.createSkillCalc(skill.getTemplateInfo().getTemplateId());// ,
																									// skill.getTemplateInfo().getAttackType(),
																									// source.getJob());
		if (targets != null) {
			attackOrder = new AttackOrder(source, skill, targets, attackId);
		} else {
			// TODO服务器自动寻找目标
		}
		attackOrder.setSkillCalc(skillCalc);
		return attackOrder;
	}
}
