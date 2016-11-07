package com.chuangyou.xianni.http.util;

import com.chuangyou.xianni.army.Living;
import com.chuangyou.xianni.army.Property;
import com.chuangyou.xianni.bag.ItemManager;
import com.chuangyou.xianni.common.template.PropertyFightingTemplateMgr;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.entity.equip.EquipBarGradeCfg;
import com.chuangyou.xianni.entity.equip.EquipBarInfo;
import com.chuangyou.xianni.entity.item.BagType;
import com.chuangyou.xianni.entity.item.ItemInfo;
import com.chuangyou.xianni.entity.item.ItemTemplateInfo;
import com.chuangyou.xianni.entity.property.BaseProperty;
import com.chuangyou.xianni.equip.template.EquipTemplateMgr;
import com.chuangyou.xianni.skill.SkillUtil;
import com.chuangyou.xianni.skill.template.SimpleProperty;
import com.chuangyou.xianni.sql.dao.impl.EquipBarInfoDaoImpl;

public class CalFighting {
	protected Property[] properties;

	public CalFighting() {
		properties = new Property[29];
		properties[Living.SOUL] = new Property("元魂", EnumAttr.SOUL.getValue());
		properties[Living.BLOOD] = new Property("气血", EnumAttr.BLOOD.getValue());
		properties[Living.ATTACK] = new Property("攻击", EnumAttr.ATTACK.getValue());
		properties[Living.DEFENCE] = new Property("防御", EnumAttr.DEFENCE.getValue());
		properties[Living.SOUL_ATTACK] = new Property("魂攻", EnumAttr.SOUL_ATTACK.getValue());
		properties[Living.SOUL_DEFENCE] = new Property("魂防", EnumAttr.SOUL_DEFENCE.getValue());
		properties[Living.ACCURATE] = new Property("命中", EnumAttr.ACCURATE.getValue());
		properties[Living.DODGE] = new Property("闪避", EnumAttr.DODGE.getValue());
		properties[Living.CRIT] = new Property("暴击", EnumAttr.CRIT.getValue());
		properties[Living.CRIT_DEFENCE] = new Property("抗暴", EnumAttr.CRIT_DEFENCE.getValue());
		properties[Living.CRIT_ADDTION] = new Property("暴击伤害", EnumAttr.CRIT_ADDTION.getValue());
		properties[Living.CRIT_CUT] = new Property("抗暴减伤", EnumAttr.CRIT_CUT.getValue());
		properties[Living.BLOOD_ATTACK_ADDTION] = new Property("气血伤害增加", EnumAttr.ATTACK_ADDTION.getValue());
		properties[Living.BLOOD_ATTACK_CUT] = new Property("气血伤害减免", EnumAttr.ATTACK_CUT.getValue());
		properties[Living.SOUL_ATTACK_ADDTION] = new Property("元魂伤害增加", EnumAttr.SOUL_ATTACK_ADDTION.getValue());
		properties[Living.SOUL_ATTACK_CUT] = new Property("元魂伤害减免", EnumAttr.SOUL_ATTACK_CUT.getValue());
		properties[Living.REGAIN_SOUL] = new Property("每10秒回魂", EnumAttr.REGAIN_SOUL.getValue());
		properties[Living.REGAIN_BLOOD] = new Property("每10秒回血", EnumAttr.REGAIN_BLOOD.getValue());
		properties[Living.METAL] = new Property("金", EnumAttr.METAL.getValue());
		properties[Living.WOOD] = new Property("木", EnumAttr.WOOD.getValue());
		properties[Living.WATER] = new Property("水", EnumAttr.WATER.getValue());
		properties[Living.FIRE] = new Property("火", EnumAttr.FIRE.getValue());
		properties[Living.EARTH] = new Property("土", EnumAttr.EARTH.getValue());
		properties[Living.METAL_DEFENCE] = new Property("金抗", EnumAttr.METAL_DEFENCE.getValue());
		properties[Living.WOOD_DEFENCE] = new Property("木抗", EnumAttr.WOOD_DEFENCE.getValue());
		properties[Living.WATER_DEFENCE] = new Property("水抗", EnumAttr.WATER_DEFENCE.getValue());
		properties[Living.FIRE_DEFENCE] = new Property("火抗", EnumAttr.FIRE_DEFENCE.getValue());
		properties[Living.EARTH_DEFENCE] = new Property("土抗", EnumAttr.EARTH_DEFENCE.getValue());
		properties[Living.SPEED] = new Property("速度", EnumAttr.SPEED.getValue());
	}

	public void addBag(BaseProperty bagData, BaseProperty bagPer) {
		// 添加背包数据
		properties[Living.SOUL].setBagData(bagData.getSoul());
		properties[Living.BLOOD].setBagData(bagData.getBlood());
		properties[Living.ATTACK].setBagData(bagData.getAttack());
		properties[Living.DEFENCE].setBagData(bagData.getDefence());
		properties[Living.SOUL_ATTACK].setBagData(bagData.getSoulAttack());
		properties[Living.SOUL_DEFENCE].setBagData(bagData.getSoulDefence());
		properties[Living.ACCURATE].setBagData(bagData.getAccurate());
		properties[Living.DODGE].setBagData(bagData.getDodge());
		properties[Living.CRIT].setBagData(bagData.getCrit());
		properties[Living.CRIT_DEFENCE].setBagData(bagData.getCritDefence());
		properties[Living.CRIT_ADDTION].setBagData(bagData.getCritAddtion());
		properties[Living.CRIT_CUT].setBagData(bagData.getCritCut());
		properties[Living.BLOOD_ATTACK_ADDTION].setBagData(bagData.getAttackAddtion());
		properties[Living.BLOOD_ATTACK_CUT].setBagData(bagData.getAttackCut());
		properties[Living.SOUL_ATTACK_ADDTION].setBagData(bagData.getSoulAttackAddtion());
		properties[Living.SOUL_ATTACK_CUT].setBagData(bagData.getAttackCut());
		properties[Living.REGAIN_SOUL].setBagData(bagData.getRegainSoul());
		properties[Living.REGAIN_BLOOD].setBagData(bagData.getRegainBlood());
		properties[Living.METAL].setBagData(bagData.getMetal());
		properties[Living.WOOD].setBagData(bagData.getWood());
		properties[Living.WATER].setBagData(bagData.getWater());
		properties[Living.FIRE].setBagData(bagData.getFire());
		properties[Living.EARTH].setBagData(bagData.getEarth());
		properties[Living.METAL_DEFENCE].setBagData(bagData.getMetalDefence());
		properties[Living.WOOD_DEFENCE].setBagData(bagData.getWoodDefence());
		properties[Living.WATER_DEFENCE].setBagData(bagData.getWaterDefence());
		properties[Living.FIRE_DEFENCE].setBagData(bagData.getFireDefence());
		properties[Living.EARTH_DEFENCE].setBagData(bagData.getEarthDefence());
		properties[Living.SPEED].setBagData(bagData.getSpeed());

		// 添加背包百分比加成
		properties[Living.SOUL].setBagPer(bagPer.getSoul());
		properties[Living.BLOOD].setBagPer(bagPer.getBlood());
		properties[Living.ATTACK].setBagPer(bagPer.getAttack());
		properties[Living.DEFENCE].setBagPer(bagPer.getDefence());
		properties[Living.SOUL_ATTACK].setBagPer(bagPer.getSoulAttack());
		properties[Living.SOUL_DEFENCE].setBagPer(bagPer.getSoulDefence());
		properties[Living.ACCURATE].setBagPer(bagPer.getAccurate());
		properties[Living.DODGE].setBagPer(bagPer.getDodge());
		properties[Living.CRIT].setBagPer(bagPer.getCrit());
		properties[Living.CRIT_DEFENCE].setBagPer(bagPer.getCritDefence());
		properties[Living.CRIT_ADDTION].setBagPer(bagPer.getCritAddtion());
		properties[Living.CRIT_CUT].setBagPer(bagPer.getCritCut());
		properties[Living.BLOOD_ATTACK_ADDTION].setBagPer(bagPer.getAttackAddtion());
		properties[Living.BLOOD_ATTACK_CUT].setBagPer(bagPer.getAttackCut());
		properties[Living.SOUL_ATTACK_ADDTION].setBagPer(bagPer.getSoulAttackAddtion());
		properties[Living.SOUL_ATTACK_CUT].setBagPer(bagPer.getAttackCut());
		properties[Living.REGAIN_SOUL].setBagPer(bagPer.getRegainSoul());
		properties[Living.REGAIN_BLOOD].setBagPer(bagPer.getRegainBlood());
		properties[Living.METAL].setBagPer(bagPer.getMetal());
		properties[Living.WOOD].setBagPer(bagPer.getWood());
		properties[Living.WATER].setBagPer(bagPer.getWater());
		properties[Living.FIRE].setBagPer(bagPer.getFire());
		properties[Living.EARTH].setBagPer(bagPer.getEarth());
		properties[Living.METAL_DEFENCE].setBagPer(bagPer.getMetalDefence());
		properties[Living.WOOD_DEFENCE].setBagPer(bagPer.getWoodDefence());
		properties[Living.WATER_DEFENCE].setBagPer(bagPer.getWaterDefence());
		properties[Living.FIRE_DEFENCE].setBagPer(bagPer.getFireDefence());
		properties[Living.EARTH_DEFENCE].setBagPer(bagPer.getEarthDefence());
		properties[Living.SPEED].setBagPer(bagPer.getSpeed());
	}

	public long getFighting() {
		long fighting = 0l;
		for (Property p : properties) {
			fighting += p.getTotalJoin() * PropertyFightingTemplateMgr.getFighting(p.getType()) / 100;
		}
		return fighting;
	}

	/**
	 * 获取装备基础属性
	 */
	public float getItemProVal(ItemInfo info) {
		int itemBase = info.getPro();// 基础属性
		float qualityCoefficient = info.getQualityCoefficient() / 10000f;// 品质系数
		float grow = info.getGrow() / 10000f;// 成长系数

		SimpleProperty property = SkillUtil.readPro(itemBase);

		// 装备栏等级
		ItemTemplateInfo tempInfo = ItemManager.findItemTempInfo(info.getTemplateId());
		EquipBarInfoDaoImpl equipBarInfoDaoImpl = new EquipBarInfoDaoImpl();
		EquipBarInfo equipBar = equipBarInfoDaoImpl.getEquipBarInfo(info.getPlayerId(), info.getPos());
		// EquipBarInfo equipBar =
		// player.getEquipInventory().getEquipBarByPos(info.getPos());
		if (info.getBagType() != BagType.HeroEquipment) {
			return property.getValue();
		}
		int level = 0;
		int equipBarGrade = 0;
		if (equipBar != null) {
			level = equipBar.getLevel();
			equipBarGrade = equipBar.getGrade();
		}
		float proVal = (property.getValue() + level * grow) * (1 + level * qualityCoefficient); // 装备属性=（初始属性+装备等级*属性成长值）*（1+装备等级*品质系数）

		// 装备栏加持等级

		if (tempInfo.getJiachilimit() < equipBarGrade) {
			equipBarGrade = tempInfo.getJiachilimit();
		}
		EquipBarGradeCfg equipBarCfg = EquipTemplateMgr.getBarGradeCfg(info.getPos(), equipBarGrade);
		if (equipBarCfg != null) {
			proVal = proVal * (1 + equipBarCfg.getAddProperty() / 10000f);
		}
		System.out.println(proVal);
		return proVal;
	}

	/**
	 * 获取装备的加持属性
	 * 
	 * @param tempInfo
	 * @param equipBar
	 * @param bagData
	 * @param bagPer
	 */
	public void getJiachiPro(ItemTemplateInfo tempInfo, EquipBarInfo equipBar, BaseProperty bagData, BaseProperty bagPer) {
		// 加持激活额外属性
		if (tempInfo.getJiachi1() > 0 && tempInfo.getStatistics1() > 0) {
			if (equipBar.getGrade() >= tempInfo.getJiachi1()) {
				SimpleProperty jiachiPro1 = SkillUtil.readPro(tempInfo.getStatistics1());
				if (jiachiPro1.isPre()) {
					SkillUtil.joinPro(bagPer, jiachiPro1.getType(), jiachiPro1.getValue());
				} else {
					SkillUtil.joinPro(bagData, jiachiPro1.getType(), jiachiPro1.getValue());
				}
			}
		}
		if (tempInfo.getJiachi2() > 0 && tempInfo.getStatistics2() > 0) {
			if (equipBar.getGrade() >= tempInfo.getJiachi2()) {
				SimpleProperty jiachiPro2 = SkillUtil.readPro(tempInfo.getStatistics2());
				if (jiachiPro2.isPre()) {
					SkillUtil.joinPro(bagPer, jiachiPro2.getType(), jiachiPro2.getValue());
				} else {
					SkillUtil.joinPro(bagData, jiachiPro2.getType(), jiachiPro2.getValue());
				}
			}
		}
		if (tempInfo.getJiachi3() > 0 && tempInfo.getStatistics3() > 0) {
			if (equipBar.getGrade() >= tempInfo.getJiachi3()) {
				SimpleProperty jiachiPro3 = SkillUtil.readPro(tempInfo.getStatistics3());
				if (jiachiPro3.isPre()) {
					SkillUtil.joinPro(bagPer, jiachiPro3.getType(), jiachiPro3.getValue());
				} else {
					SkillUtil.joinPro(bagData, jiachiPro3.getType(), jiachiPro3.getValue());
				}
			}
		}
		if (tempInfo.getJiachi4() > 0 && tempInfo.getStatistics4() > 0) {
			if (equipBar.getGrade() >= tempInfo.getJiachi4()) {
				SimpleProperty jiachiPro4 = SkillUtil.readPro(tempInfo.getStatistics4());
				if (jiachiPro4.isPre()) {
					SkillUtil.joinPro(bagPer, jiachiPro4.getType(), jiachiPro4.getValue());
				} else {
					SkillUtil.joinPro(bagData, jiachiPro4.getType(), jiachiPro4.getValue());
				}
			}
		}
	}

}
