package com.chuangyou.xianni.battle.buffer.specialbuf;

import com.chuangyou.xianni.battle.AttackOrder;
import com.chuangyou.xianni.battle.buffer.Buffer;
import com.chuangyou.xianni.battle.damage.Damage;
import com.chuangyou.xianni.entity.buffer.SkillBufferTemplateInfo;
import com.chuangyou.xianni.role.objects.Living;

public class TruckHideNameBuffer extends Buffer {

	public TruckHideNameBuffer(Living source, Living target, SkillBufferTemplateInfo bufferInfo) {
		super(source, target, bufferInfo);
	}

	@Override
	protected void exec(AttackOrder attackOrder, Damage beDamage1, Damage beDamage2) {

	}

}
