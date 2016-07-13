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
	protected void exec(AttackOrder attackOrder, Damage beDamage) {
		int damageValue = 0;
		int type = bufferInfo.getValueType();

		if (type == EnumAttr.CUR_BLOOD.getValue()) {
			int attack = source.getAttack();
			int defence = target.getDefence();
			ThreadSafeRandom random = new ThreadSafeRandom();
			damageValue = (int) ((Math.max(attack - defence * 1.2, 0) + attack * 0.025) * random.next(80, 120) / 100);
			damageValue = damageValue * bufferInfo.getValuePercent() / 100 + bufferInfo.getValue();
		}
		if (type == EnumAttr.CUR_SOUL.getValue()) {
			ThreadSafeRandom random = new ThreadSafeRandom();
			// 已方魂攻
			int soulAttack = source.getSoulAttack();
			// 对方魂防
			int soulDeffence = target.getSoulDefence();
			// 当对方处于元魂状态时，对方魂防御降低50%
			if (target.isSoulState()) {
				soulDeffence = soulDeffence / 2;
			}
			damageValue = (int) (Math.max(soulAttack - soulDeffence * 1.2, 0) * random.next(70, 13) / 10);
			damageValue = damageValue * bufferInfo.getValuePercent() / 100 + bufferInfo.getValue();
		}

		beDamage.setSkillId(0);
		beDamage.setTarget(target);
		beDamage.setSource(source);
		beDamage.setDamageType(type);
		beDamage.setDamageValue(damageValue);
		beDamage.setCalcType(getDamageType());
	}
}
