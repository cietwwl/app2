package com.chuangyou.xianni.battle.buffer;

import com.chuangyou.xianni.battle.AttackOrder;
import com.chuangyou.xianni.battle.damage.Damage;
import com.chuangyou.xianni.entity.buffer.SkillBufferTemplateInfo;
import com.chuangyou.xianni.role.objects.Living;

/**属性类型buffer*/
public class AttributesBuffer extends Buffer {

	protected AttributesBuffer(long bufferId, Living source, Living target, SkillBufferTemplateInfo bufferInfo) {
		super(bufferId, source, target, bufferInfo);
	}

	@Override
	protected void exec(AttackOrder attackOrder, Damage beDamage1, Damage beDamage2) {

	}

}
