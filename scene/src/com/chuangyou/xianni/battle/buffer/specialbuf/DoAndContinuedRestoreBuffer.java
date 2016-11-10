package com.chuangyou.xianni.battle.buffer.specialbuf;

import com.chuangyou.xianni.battle.AttackOrder;
import com.chuangyou.xianni.battle.buffer.Buffer;
import com.chuangyou.xianni.battle.damage.Damage;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.entity.buffer.SkillBufferTemplateInfo;
import com.chuangyou.xianni.role.objects.Living;

public class DoAndContinuedRestoreBuffer extends Buffer {

	public DoAndContinuedRestoreBuffer(Living source, Living target, SkillBufferTemplateInfo bufferInfo) {
		super(source, target, bufferInfo);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void afterAdd() {
		// TODO Auto-generated method stub
		super.afterAdd();
		
		int damageValue = 0;
		if(bufferInfo.getValueType() > 0){
			if(bufferInfo.getValueType() == EnumAttr.CUR_BLOOD.getValue()){
				damageValue = bufferInfo.getValue() - (int)target.getRegainBlood() * bufferInfo.getValuePercent() / 10000;
			}
			if(bufferInfo.getValueType() == EnumAttr.CUR_SOUL.getValue()){
				damageValue = bufferInfo.getValue() - (int)target.getRegainSoul() * bufferInfo.getValuePercent() / 10000;
			}
			
			Damage damage = new Damage(target, source);
			damage.setFromType(Damage.BUFFER);
			damage.setFromId(this.getBufferId());
			damage.setDamageType(bufferInfo.getValueType());
			damage.setDamageValue(damageValue);
			damage.setCalcType(getDamageType());
			target.takeDamage(damage);
		}
	}

	@Override
	protected void exec(AttackOrder attackOrder, Damage beDamage1, Damage beDamage2) {
		// TODO Auto-generated method stub

		int damageValue = 0;
		int valueType = bufferInfo.getValueType1();
		if(valueType > 0){
			int initValue = 0;
			if(valueType == EnumAttr.CUR_BLOOD.getValue()){
				initValue = target.getMaxBlood();
			}
			if(valueType == EnumAttr.CUR_SOUL.getValue()){
				initValue = target.getMaxSoul();
			}
			damageValue = bufferInfo.getValue1() - (int) (bufferInfo.getValuePercent1() * 1f * initValue / 10000);
			
			beDamage1.setFromType(Damage.BUFFER);
			beDamage1.setFromId(this.getBufferId());
			beDamage1.setTarget(target);
			beDamage1.setSource(source);
			beDamage1.setDamageType(valueType);
			beDamage1.setDamageValue(damageValue);
			beDamage1.setCalcType(getDamageType());
		}
	}

}
