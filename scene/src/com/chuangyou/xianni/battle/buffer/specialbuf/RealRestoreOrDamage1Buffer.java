package com.chuangyou.xianni.battle.buffer.specialbuf;

import com.chuangyou.xianni.battle.AttackOrder;
import com.chuangyou.xianni.battle.buffer.Buffer;
import com.chuangyou.xianni.battle.damage.Damage;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.entity.buffer.SkillBufferTemplateInfo;
import com.chuangyou.xianni.role.objects.Living;

public class RealRestoreOrDamage1Buffer extends Buffer {

	public RealRestoreOrDamage1Buffer(Living source, Living target, SkillBufferTemplateInfo bufferInfo) {
		super(source, target, bufferInfo);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void exec(AttackOrder attackOrder, Damage beDamage1, Damage beDamage2) {
		// TODO Auto-generated method stub

		int damageValue1 = 0;
		int type1 = bufferInfo.getValueType();

		if (type1 > 0) {
			int initValue = 0;
			if (type1 == EnumAttr.CUR_BLOOD.getValue()) {
				initValue = target.getCurBlood();
			}
			if (type1 == EnumAttr.CUR_SOUL.getValue()) {
				initValue = target.getCurSoul();
			}
			damageValue1 = bufferInfo.getValue() + (int) (bufferInfo.getValuePercent() * 1f * initValue / 10000);
			beDamage1.setTarget(target);
			beDamage1.setSource(source);
			beDamage1.setFromType(Damage.BUFFER);
			beDamage1.setFromId(this.getBufferId());
			beDamage1.setDamageType(type1);
			beDamage1.setDamageValue(damageValue1);
			beDamage1.setCalcType(getDamageType());
		}

		int type2 = bufferInfo.getValueType1();
		int damageValue2 = 0;
		if (type2 > 0) {
			int initValue = 0;
			if (type2 == EnumAttr.CUR_BLOOD.getValue()) {
				initValue = target.getCurBlood();
			}
			if (type2 == EnumAttr.CUR_SOUL.getValue()) {
				initValue = target.getCurSoul();
			}
			damageValue2 = bufferInfo.getValue1() + (int) (bufferInfo.getValuePercent1() * 1f * initValue / 10000);
			beDamage2.setFromType(Damage.BUFFER);
			beDamage2.setFromId(this.getBufferId());
			beDamage2.setTarget(target);
			beDamage2.setSource(source);
			beDamage2.setDamageType(type2);
			beDamage2.setDamageValue(damageValue2);
			beDamage2.setCalcType(getDamageType());
		}
	}

}
