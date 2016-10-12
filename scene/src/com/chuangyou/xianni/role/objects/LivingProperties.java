package com.chuangyou.xianni.role.objects;

import java.util.HashMap;
import java.util.Map;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.exec.AbstractActionQueue;
import com.chuangyou.xianni.exec.ActionExecutor;
import com.chuangyou.xianni.exec.ThreadManager;

public class LivingProperties extends AbstractActionQueue {
	/* 修改成map结构，避免 出现不新增不连续属性时，无法纳入 */
	protected Map<EnumAttr, Property>	properties				= new HashMap<>();	// 所有基础属性

	public LivingProperties(ActionExecutor executor) {
		super(ThreadManager.actionExecutor);
		Property SOUL = new Property("元魂", EnumAttr.SOUL.getValue());
		properties.put(EnumAttr.SOUL, SOUL);
		Property BLOOD = new Property("气血", EnumAttr.BLOOD.getValue());
		properties.put(EnumAttr.BLOOD, BLOOD);
		Property ATTACK = new Property("攻击", EnumAttr.ATTACK.getValue());
		properties.put(EnumAttr.ATTACK, ATTACK);
		Property DEFENCE = new Property("防御", EnumAttr.DEFENCE.getValue());
		properties.put(EnumAttr.DEFENCE, DEFENCE);
		Property SOUL_ATTACK = new Property("魂攻", EnumAttr.SOUL_ATTACK.getValue());
		properties.put(EnumAttr.SOUL_ATTACK, SOUL_ATTACK);
		Property SOUL_DEFENCE = new Property("魂防", EnumAttr.SOUL_DEFENCE.getValue());
		properties.put(EnumAttr.SOUL_DEFENCE, SOUL_DEFENCE);
		Property ACCURATE = new Property("命中", EnumAttr.ACCURATE.getValue());
		properties.put(EnumAttr.ACCURATE, ACCURATE);
		Property DODGE = new Property("闪避", EnumAttr.DODGE.getValue());
		properties.put(EnumAttr.DODGE, DODGE);
		Property CRIT = new Property("暴击", EnumAttr.CRIT.getValue());
		properties.put(EnumAttr.CRIT, CRIT);
		Property CRIT_DEFENCE = new Property("抗暴", EnumAttr.CRIT_DEFENCE.getValue());
		properties.put(EnumAttr.CRIT_DEFENCE, CRIT_DEFENCE);
		Property CRIT_ADDTION = new Property("暴击伤害", EnumAttr.CRIT_ADDTION.getValue());
		properties.put(EnumAttr.CRIT_ADDTION, CRIT_ADDTION);
		Property CRIT_CUT = new Property("抗暴减伤", EnumAttr.CRIT_CUT.getValue());
		properties.put(EnumAttr.CRIT_CUT, CRIT_CUT);
		Property BLOOD_ATTACK_ADDTION = new Property("气血伤害增加", EnumAttr.ATTACK_ADDTION.getValue());
		properties.put(EnumAttr.ATTACK_ADDTION, BLOOD_ATTACK_ADDTION);
		Property BLOOD_ATTACK_CUT = new Property("气血伤害减免", EnumAttr.ATTACK_CUT.getValue());
		properties.put(EnumAttr.ATTACK_CUT, BLOOD_ATTACK_CUT);
		Property SOUL_ATTACK_ADDTION = new Property("元魂伤害增加", EnumAttr.SOUL_ATTACK_ADDTION.getValue());
		properties.put(EnumAttr.SOUL_ATTACK_ADDTION, SOUL_ATTACK_ADDTION);
		Property SOUL_ATTACK_CUT = new Property("元魂伤害减免", EnumAttr.SOUL_ATTACK_CUT.getValue());
		properties.put(EnumAttr.SOUL_ATTACK_CUT, SOUL_ATTACK_CUT);
		Property REGAIN_SOUL = new Property("每10秒回魂", EnumAttr.REGAIN_SOUL.getValue());
		properties.put(EnumAttr.REGAIN_SOUL, REGAIN_SOUL);
		Property REGAIN_BLOOD = new Property("每10秒回血", EnumAttr.REGAIN_BLOOD.getValue());
		properties.put(EnumAttr.REGAIN_BLOOD, REGAIN_BLOOD);
		Property METAL = new Property("金", EnumAttr.METAL.getValue());
		properties.put(EnumAttr.METAL, METAL);
		Property WOOD = new Property("木", EnumAttr.WOOD.getValue());
		properties.put(EnumAttr.WOOD, WOOD);
		Property WATER = new Property("水", EnumAttr.WATER.getValue());
		properties.put(EnumAttr.WATER, WATER);
		Property FIRE = new Property("火", EnumAttr.FIRE.getValue());
		properties.put(EnumAttr.FIRE, FIRE);
		Property EARTH = new Property("土", EnumAttr.EARTH.getValue());
		properties.put(EnumAttr.EARTH, EARTH);
		Property METAL_DEFENCE = new Property("金抗", EnumAttr.METAL_DEFENCE.getValue());
		properties.put(EnumAttr.METAL_DEFENCE, METAL_DEFENCE);
		Property WOOD_DEFENCE = new Property("木抗", EnumAttr.WOOD_DEFENCE.getValue());
		properties.put(EnumAttr.WOOD_DEFENCE, WOOD_DEFENCE);
		Property WATER_DEFENCE = new Property("水抗", EnumAttr.WATER_DEFENCE.getValue());
		properties.put(EnumAttr.WATER_DEFENCE, WATER_DEFENCE);
		Property FIRE_DEFENCE = new Property("火抗", EnumAttr.FIRE_DEFENCE.getValue());
		properties.put(EnumAttr.FIRE_DEFENCE, FIRE_DEFENCE);
		Property EARTH_DEFENCE = new Property("土抗", EnumAttr.EARTH_DEFENCE.getValue());
		properties.put(EnumAttr.EARTH_DEFENCE, EARTH_DEFENCE);
		Property SPEED = new Property("速度", EnumAttr.SPEED.getValue());
		properties.put(EnumAttr.SPEED, SPEED);
	}

	public int getInitData(EnumAttr type) {
		Property property = properties.get(type);
		if (property != null) {
			return property.getInitData();
		}
		return 0;
	}

	public void setInitData(EnumAttr type, int value) {
		Property property = properties.get(type);
		if (property != null) {
			property.setInitData(value);
		}
	}

	public int getToalData(EnumAttr type) {
		Property property = properties.get(type);
		if (property != null) {
			return property.getTotalJoin();
		}
		return 0;
	}

	public void clearBufferData(EnumAttr type) {
		Property property = properties.get(type);
		if (property != null) {
			property.clearBuff();
		}
	}

	public void setBufferData(EnumAttr type, int value) {
		Property property = properties.get(type);
		if (property != null) {
			property.clearBuff();
		}
		property.setBuffData(value);
	}
}
