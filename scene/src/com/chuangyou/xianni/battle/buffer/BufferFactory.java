package com.chuangyou.xianni.battle.buffer;

import com.chuangyou.xianni.entity.buffer.SkillBufferTemplateInfo;
import com.chuangyou.xianni.role.helper.IDMakerHelper;
import com.chuangyou.xianni.role.objects.Living;

/**
 * BufferFactory创建的工厂类
 */
public class BufferFactory {

	/**
	 * 建立BUFFER
	 */
	public static Buffer createBuffer(Living source, Living target, SkillBufferTemplateInfo bufferInfo) {
		Buffer buffer = null;
		switch (bufferInfo.getType()) {
			case BufferType.COMMON_DAMANGE:
			case BufferType.ONLY_BLOOD:
			case BufferType.ONLY_SOUL:
			case BufferType.COMMON_RESTORE:
			case BufferType.ONLY_RESTORE_BLOOD:
			case BufferType.ONLY_RESTORE_SOUL:
				buffer = new LivingPropertyChangeBuffer(IDMakerHelper.bufferId(), source, target, bufferInfo);
			default:
				buffer = new LivingPropertyChangeBuffer(IDMakerHelper.bufferId(), source, target, bufferInfo);
		}
		buffer.setExecuted(false);
		return buffer;
	}
}
