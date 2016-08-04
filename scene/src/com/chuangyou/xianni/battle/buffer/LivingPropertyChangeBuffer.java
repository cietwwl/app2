package com.chuangyou.xianni.battle.buffer;

import com.chuangyou.common.util.ThreadSafeRandom;
import com.chuangyou.xianni.battle.AttackOrder;
import com.chuangyou.xianni.battle.damage.Damage;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.entity.buffer.SkillBufferTemplateInfo;
import com.chuangyou.xianni.role.objects.Living;

public class LivingPropertyChangeBuffer extends Buffer {

	protected LivingPropertyChangeBuffer(long bufferId, Living source, Living target, SkillBufferTemplateInfo bufferInfo) {
		super(bufferId, source, target, bufferInfo);
	}

	@Override
	protected void exec(AttackOrder attackOrder, Damage beDamage1, Damage beDamage2) {
		int damageValue1 = 0;
		int type1 = bufferInfo.getValueType();

		if (type1 > 0) {
			if (type1 == EnumAttr.CUR_BLOOD.getValue()) {
				damageValue1 = calBlood();
			}
			if (type1 == EnumAttr.CUR_SOUL.getValue()) {
				damageValue1 = calSoul();
			}
			beDamage1.setTarget(target);
			beDamage1.setSource(source);
			beDamage1.setSkillId(0);
			beDamage1.setDamageType(type1);
			beDamage1.setDamageValue(damageValue1);
			beDamage1.setCalcType(getDamageType());
		}

		int type2 = bufferInfo.getValueType1();
		int damageValue2 = 0;
		if (type2 > 0) {
			if (type2 == EnumAttr.CUR_BLOOD.getValue()) {
				damageValue2 = calBlood();
			}
			if (type2 == EnumAttr.CUR_SOUL.getValue()) {
				damageValue2 = calSoul();
			}
			beDamage2.setSkillId(0);
			beDamage2.setTarget(target);
			beDamage2.setSource(source);
			beDamage2.setDamageType(type2);
			beDamage2.setDamageValue(damageValue2);
			beDamage2.setCalcType(getDamageType());
		}
	}

	// 向上取整{max（攻击-对方防御*1.2，攻击*0.025）*random（0.8,1.2）*MAX[1+（破血-对方血抗）/10000，0.1]}
	private int calBlood() {
		int attack = source.getAttack();
		int defence = target.getDefence();

		int damageValue = (int) Math.ceil(((Math.max(attack - defence * 1.2, 0) + attack * 0.025) * RND.next(80, 120) / 100));
		damageValue = damageValue * bufferInfo.getValuePercent() / 100 + bufferInfo.getValue();
		return damageValue;
	}

	// 向上取整{max（魂攻-对方魂防*1.2，0）*random（0.7,1.3）*MAX[1+（破魂-对方魂抗）/10000，0.1]}
	private int calSoul() {
		// 已方魂攻
		int soulAttack = source.getSoulAttack();
		// 对方魂防
		int soulDeffence = target.getSoulDefence();
		// 当对方处于元魂状态时，对方魂防御降低50%
		if (target.isSoulState()) {
			soulDeffence = soulDeffence / 2;
		}
		int damageValue = (int) Math.ceil((Math.max(soulAttack - soulDeffence * 1.2, 0) * RND.next(70, 130) / 100));
		damageValue = damageValue * bufferInfo.getValuePercent() / 100 + bufferInfo.getValue();
		return damageValue;
	}
}
