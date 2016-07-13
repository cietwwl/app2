package com.chuangyou.xianni.battle.calc;

import java.util.ArrayList;
import java.util.List;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.battle.AttackOrder;
import com.chuangyou.xianni.battle.damage.Damage;
import com.chuangyou.xianni.battle.damage.DamageCalculator;
import com.chuangyou.xianni.entity.skill.SkillActionTemplateInfo;
import com.chuangyou.xianni.role.helper.RoleConstants.RoleType;
import com.chuangyou.xianni.role.objects.Living;

/**
 * <pre>
 * 目前使用的技能计算类
 * </pre>
 */
public class SingleLivingAttack extends AbstractSkillCalc {

	/** 技能产生的伤害次序 */
	private int order;

	public SingleLivingAttack(int orderId, DamageCalculator calcutor) {
		super(orderId, calcutor);
	}

	@Override
	public final List<Damage> calcEffect(Living source, List<Living> targets, AttackOrder attackOrder) {
		if (targets == null || targets.size() <= 0) {
			Log.error("SingleLivingAttack targets = null, skillInfoId : " + attackOrder.getSkill().getTemplateInfo().getTemplateId());
			return null;
		}
		// 攻击者进入战斗状态
		source.fight();
		// 计算该技能指令产生的伤害
		List<Damage> damages = new ArrayList<Damage>(targets.size());

		for (Living target : targets) {
			// 受击者进入战斗状态
			target.fight();
			List<Damage> attackResult = calcEffectSingle(source, target, attackOrder, targets.size());
			if (attackResult != null) {
				damages.addAll(attackResult);
			}
		}
		// 清空伤害次序
		clearOrder();
		return damages;
	}

	/**
	 * 计算技能输出的伤害结果
	 * 
	 * @param source
	 * @param target
	 * @param attackOrder
	 * @return
	 */
	protected List<Damage> calcEffectSingle(Living source, Living target, AttackOrder attackOrder, int count) {
		SkillActionTemplateInfo tempInfo = attackOrder.getSkill().getTemplateInfo();
		// 技能伤害次数
		int time = tempInfo.getAttackTimes();
		// 伤害容器
		List<Damage> attackResult = new ArrayList<Damage>(time);
		// 受保护状态，不产生伤害
		if (target.isProtection()) {
			return attackResult;
		}
		// 提示类型
		int tipType = 0;
		// 计算伤害
		int damageValue = 1;//calcutor.calcDamage(source, target, tempInfo);
		// 当释放者处于元魂状态时，伤害增加10%
		if (source.isSoulState()) {
			damageValue += damageValue * 0.1;
		}
		damageValue +=1000;
		// 是否暴击
		boolean isCrit = isCrit(tempInfo.getIsCrit(), source.getCrit(), target.getCritDefence());

		// 未暴击时，计算miss概率
		if (!isCrit) {
			if (isMiss(source.getAccurate(), target.getDodge())) {
				damageValue = 0;
				tipType = Damage.MISS;
			}
		} else {
			damageValue += damageValue * getCoefficient(source.getCritAddtion(), target.getCritCut());
			tipType = Damage.CRIPT;
		}

		// 群攻分摊伤害技能
		if (tempInfo.getMasterType() == 2) {
			damageValue = damageValue / count;
		}

		for (int i = 0; i < time; i++) {
			Damage damage = new Damage(target, source);
			damage.setSkillId(attackOrder.getSkill().getSkillId());
			damage.setDamageType(tempInfo.getAttackType());
			damage.setDamageValue(damageValue);
			damage.setTipType(tipType);
			damage.setOrder(getOrder());
			attackResult.add(damage);
		}
		return attackResult;
	}

	public int getOrder() {
		return order++;
	}

	public void clearOrder() {
		order = 0;
	}

}
