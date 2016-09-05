package com.chuangyou.xianni.battle.buffer;

import com.chuangyou.xianni.entity.buffer.SkillBufferTemplateInfo;
import com.chuangyou.xianni.role.objects.Living;

/** 计算公式类buffer */
public abstract class FormulaBuffer extends Buffer {

	protected FormulaBuffer(Living source, Living target, SkillBufferTemplateInfo bufferInfo) {
		super(source, target, bufferInfo);
	}

	public abstract int calculation(int parameter, int parameter2);

	public int formulaExe(int parameter, int parameter2) {
		// 公式类型的buff不会调用exe方法，所以直接在此处扣费
		if (checkValid() && exeCost()) {
			return calculation(parameter, parameter2);
		}
		return 0;
	}
}
