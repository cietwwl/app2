package com.chuangyou.xianni.skill;

import com.chuangyou.xianni.army.Living;
import com.chuangyou.xianni.entity.property.BaseProperty;
import com.chuangyou.xianni.skill.template.SimpleProperty;

public class SkillUtil {

	/** 解析属性 */
	public static SimpleProperty readPro(int pro) {
		int key = pro / 1000000;
		int val = pro % 1000000;
		SimpleProperty property = readPro(key, val);
		return property;
	}
	/** 解析属性 */
	public static SimpleProperty readPro(int key, int val){
		SimpleProperty property = new SimpleProperty();
		// 正数系
		if (key <= 1000) { // 增加固定数值
			property.setType(key);
			property.setDataType(SimpleProperty.NUMERICAL);
			property.setValue(val);
		}
		if (key > 1000 && key <= 2000) {
			property.setType(key - 1000);
			property.setDataType(SimpleProperty.PERCENTAGE);
			property.setValue(val);
		}
		// 负数系
		if (key > 2000 && key <= 3000) {
			property.setType(key - 2000);
			property.setDataType(SimpleProperty.DECR_NUMERICAL);
			property.setValue(0 - val);
		}
		if (key > 3000 && key <= 4000) {
			property.setType(key - 3000);
			property.setDataType(SimpleProperty.DECR_PERCENTAGE);
			property.setValue(0 - val);
		}
		return property;
	}

	/** 属性赋值 */
	public static void joinPro(BaseProperty temp, int key, int val) {
		switch (key - 1) {
			case Living.SOUL:
				temp.addSoul(val);
				break;
			case Living.BLOOD:
				temp.addBlood(val);
				break;
			case Living.ATTACK:
				temp.addAttack(val);
				break;
			case Living.DEFENCE:
				temp.addDefence(val);
				break;
			case Living.SOUL_ATTACK:
				temp.addSoulAttack(val);
				break;
			case Living.SOUL_DEFENCE:
				temp.addSoulDefence(val);
				break;
			case Living.ACCURATE:
				temp.addAccurate(val);
				break;
			case Living.DODGE:
				temp.addDodge(val);
				break;
			case Living.CRIT:
				temp.addCrit(val);
				break;
			case Living.CRIT_DEFENCE:
				temp.addCritDefence(val);
				break;
			case Living.CRIT_ADDTION:
				temp.addCritAddtion(val);
				break;
			case Living.CRIT_CUT:
				temp.addCritCut(val);
				break;
			case Living.BLOOD_ATTACK_ADDTION:
				temp.addBloodAttackAddtion(val);
				break;
			case Living.BLOOD_ATTACK_CUT:
				temp.addBloodAttackCut(val);
				break;
			case Living.SOUL_ATTACK_ADDTION:
				temp.addSoulAttackAddtion(val);
				break;
			case Living.SOUL_ATTACK_CUT:
				temp.addSoulAttackCut(val);
				break;
			case Living.REGAIN_SOUL:
				temp.addRegainSoul(val);
				break;
			case Living.REGAIN_BLOOD:
				temp.addRegainBlood(val);
				break;
			case Living.METAL:
				temp.addMetal(val);
				break;
			case Living.WOOD:
				temp.addWood(val);
				break;
			case Living.WATER:
				temp.addWater(val);
				break;
			case Living.FIRE:
				temp.addFire(val);
				break;
			case Living.EARTH:
				temp.addEarth(val);
				break;
			case Living.METAL_DEFENCE:
				temp.addMetalDefence(val);
				break;
			case Living.WOOD_DEFENCE:
				temp.addWoodDefence(val);
				break;
			case Living.WATER_DEFENCE:
				temp.addWaterDefence(val);
				break;
			case Living.FIRE_DEFENCE:
				temp.addFireDefence(val);
				break;
			case Living.EARTH_DEFENCE:
				temp.addEarthDefence(val);
				break;
			case Living.SPEED:
				temp.addSpeed(val);
				break;
			default:
				break;
		}
	}

	/** 属性赋值 */
	public static void assignPro(BaseProperty temp, int key, int val) {
		switch (key - 1) {
			case Living.SOUL:
				temp.setSoul(val);
				break;
			case Living.BLOOD:
				temp.setBlood(val);
				break;
			case Living.ATTACK:
				temp.setAttack(val);
				break;
			case Living.DEFENCE:
				temp.setDefence(val);
				break;
			case Living.SOUL_ATTACK:
				temp.setSoulAttack(val);
				break;
			case Living.SOUL_DEFENCE:
				temp.setSoulDefence(val);
				break;
			case Living.ACCURATE:
				temp.setAccurate(val);
				break;
			case Living.DODGE:
				temp.setDodge(val);
				break;
			case Living.CRIT:
				temp.setCrit(val);
				break;
			case Living.CRIT_DEFENCE:
				temp.setCritDefence(val);
				break;
			case Living.CRIT_ADDTION:
				temp.setCritAddtion(val);
				break;
			case Living.CRIT_CUT:
				temp.setCritCut(val);
				break;
			case Living.BLOOD_ATTACK_ADDTION:
				temp.setBloodAttackAddtion(val);
				break;
			case Living.BLOOD_ATTACK_CUT:
				temp.setBloodAttackCut(val);
				break;
			case Living.SOUL_ATTACK_ADDTION:
				temp.setSoulAttackAddtion(val);
				break;
			case Living.SOUL_ATTACK_CUT:
				temp.setSoulAttackCut(val);
				break;
			case Living.REGAIN_SOUL:
				temp.setRegainSoul(val);
				break;
			case Living.REGAIN_BLOOD:
				temp.setRegainBlood(val);
				break;
			case Living.METAL:
				temp.setMetal(val);
				break;
			case Living.WOOD:
				temp.setWood(val);
				break;
			case Living.WATER:
				temp.setWater(val);
				break;
			case Living.FIRE:
				temp.setFire(val);
				break;
			case Living.EARTH:
				temp.setEarth(val);
				break;
			case Living.METAL_DEFENCE:
				temp.setMetalDefence(val);
				break;
			case Living.WOOD_DEFENCE:
				temp.setWoodDefence(val);
				break;
			case Living.WATER_DEFENCE:
				temp.setWaterDefence(val);
				break;
			case Living.FIRE_DEFENCE:
				temp.setFireDefence(val);
				break;
			case Living.EARTH_DEFENCE:
				temp.setEarthDefence(val);
				break;
			case Living.SPEED:
				temp.setSpeed(val);
				break;
			default:
				break;
		}
	}

}
