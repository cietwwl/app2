package com.chuangyou.xianni.battle.buffer.specialbuf;

import com.chuangyou.xianni.battle.AttackOrder;
import com.chuangyou.xianni.battle.buffer.Buffer;
import com.chuangyou.xianni.battle.buffer.BufferTargetType;
import com.chuangyou.xianni.battle.damage.Damage;
import com.chuangyou.xianni.entity.buffer.SkillBufferTemplateInfo;
import com.chuangyou.xianni.entity.soul.SoulFuseSkillConfig;
import com.chuangyou.xianni.role.objects.Living;

/** 属性类型buffer */
public class AttributesBuffer extends Buffer {

	public AttributesBuffer(Living source, Living target, SkillBufferTemplateInfo bufferInfo) {
		super(source, target, bufferInfo);
	}

	@Override
	protected void exec(AttackOrder attackOrder, Damage beDamage1, Damage beDamage2) {
		if (getBufferInfo().getTargetType() == BufferTargetType.SOURCE) {
			if (getBufferInfo().getValueType() != 0) {
				source.refreshProperties(getBufferInfo().getValueType());
			}

			if (getBufferInfo().getValueType1() != 0) {
				source.refreshProperties(getBufferInfo().getValueType1());
			}
		}

		if (getBufferInfo().getTargetType() == BufferTargetType.SKILL_TARGET) {
			if (getBufferInfo().getValueType() != 0) {
				target.refreshProperties(getBufferInfo().getValueType());
			}

			if (getBufferInfo().getValueType1() != 0) {
				target.refreshProperties(getBufferInfo().getValueType1());
			}
		}
	}

	public int getAddValue(int type, int initValue) {
		int addValue = 0;
		if (getBufferInfo().getValueType() == type) {
			int effectValue = getBufferInfo().getValue() + (int) Math.ceil(initValue * (getBufferInfo().getValuePercent() / 10000f));
			addValue = calSoullv(effectValue, SoulFuseSkillConfig.EFFECT);
		}
		if (getBufferInfo().getValueType1() == type) {
			int effectValue = getBufferInfo().getValue1() + (int) Math.ceil(initValue * (getBufferInfo().getValuePercent1() / 10000f));
			addValue = calSoullv(effectValue, SoulFuseSkillConfig.EFFECT);
		}
		return addValue;
	}

}
