package com.chuangyou.xianni.army;

import com.chuangyou.common.protobuf.pb.army.PropertyListMsgProto.PropertyListMsg;
import com.chuangyou.common.protobuf.pb.army.PropertyMsgProto.PropertyMsg;
import com.chuangyou.common.protobuf.pb.player.PlayerAttUpdateProto.PlayerAttUpdateMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.common.template.PropertyFightingTemplateMgr;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.player.PlayerJoinInfo;
import com.chuangyou.xianni.entity.property.BaseProperty;
import com.chuangyou.xianni.player.GamePlayer;

/**
 * 所有属性集合,出站技能等汇聚的载体
 */
public abstract class Living {

	/** 客户端对应属性ID = 服务器属性ID+1 **/
	public static final int	SOUL					= 0;	// 元魂
	public static final int	BLOOD					= 1;	// 气血
	public static final int	ATTACK					= 2;	// 攻击
	public static final int	DEFENCE					= 3;	// 防御
	public static final int	SOUL_ATTACK				= 4;	// 魂攻
	public static final int	SOUL_DEFENCE			= 5;	// 魂防
	public static final int	ACCURATE				= 6;	// 命中
	public static final int	DODGE					= 7;	// 闪避
	public static final int	CRIT					= 8;	// 暴击
	public static final int	CRIT_DEFENCE			= 9;	// 抗暴
	public static final int	CRIT_ADDTION			= 10;	// 暴击伤害
	public static final int	CRIT_CUT				= 11;	// 抗暴减伤
	public static final int	BLOOD_ATTACK_ADDTION	= 12;	// 气血伤害增加
	public static final int	BLOOD_ATTACK_CUT		= 13;	// 气血伤害减免
	public static final int	SOUL_ATTACK_ADDTION		= 14;	// 元魂伤害增加
	public static final int	SOUL_ATTACK_CUT			= 15;	// 元魂伤害减免
	public static final int	REGAIN_SOUL				= 16;	// 每10秒回魂
	public static final int	REGAIN_BLOOD			= 17;	// 每10秒回血
	public static final int	METAL					= 18;	// 金
	public static final int	WOOD					= 19;	// 木
	public static final int	WATER					= 20;	// 水
	public static final int	FIRE					= 21;	// 火
	public static final int	EARTH					= 22;	// 土
	public static final int	METAL_DEFENCE			= 23;	// 金抗
	public static final int	WOOD_DEFENCE			= 24;	// 木抗
	public static final int	WATER_DEFENCE			= 25;	// 水抗
	public static final int	FIRE_DEFENCE			= 26;	// 火抗
	public static final int	EARTH_DEFENCE			= 27;	// 土抗
	public static final int	SPEED					= 28;	// 移动速度

	// public static final int//当前元魂
	// public static final int //当前气血
	// public static final int //无敌
	// public static final int //最大元魂
	// public static final int //最大气血

	protected Property[]	properties;
	protected int			fighting;						// 战斗力

	public Living() {
		properties = new Property[29];
		properties[SOUL] = new Property("元魂", EnumAttr.SOUL.getValue());
		properties[BLOOD] = new Property("气血", EnumAttr.BLOOD.getValue());
		properties[ATTACK] = new Property("攻击", EnumAttr.ATTACK.getValue());
		properties[DEFENCE] = new Property("防御", EnumAttr.DEFENCE.getValue());
		properties[SOUL_ATTACK] = new Property("魂攻", EnumAttr.SOUL_ATTACK.getValue());
		properties[SOUL_DEFENCE] = new Property("魂防", EnumAttr.SOUL_DEFENCE.getValue());
		properties[ACCURATE] = new Property("命中", EnumAttr.ACCURATE.getValue());
		properties[DODGE] = new Property("闪避", EnumAttr.DODGE.getValue());
		properties[CRIT] = new Property("暴击", EnumAttr.CRIT.getValue());
		properties[CRIT_DEFENCE] = new Property("抗暴", EnumAttr.CRIT_DEFENCE.getValue());
		properties[CRIT_ADDTION] = new Property("暴击伤害", EnumAttr.CRIT_ADDTION.getValue());
		properties[CRIT_CUT] = new Property("抗暴减伤", EnumAttr.CRIT_CUT.getValue());
		properties[BLOOD_ATTACK_ADDTION] = new Property("气血伤害增加", EnumAttr.ATTACK_ADDTION.getValue());
		properties[BLOOD_ATTACK_CUT] = new Property("气血伤害减免", EnumAttr.ATTACK_CUT.getValue());
		properties[SOUL_ATTACK_ADDTION] = new Property("元魂伤害增加", EnumAttr.SOUL_ATTACK_ADDTION.getValue());
		properties[SOUL_ATTACK_CUT] = new Property("元魂伤害减免", EnumAttr.SOUL_ATTACK_CUT.getValue());
		properties[REGAIN_SOUL] = new Property("每10秒回魂", EnumAttr.REGAIN_SOUL.getValue());
		properties[REGAIN_BLOOD] = new Property("每10秒回血", EnumAttr.REGAIN_BLOOD.getValue());
		properties[METAL] = new Property("金", EnumAttr.METAL.getValue());
		properties[WOOD] = new Property("木", EnumAttr.WOOD.getValue());
		properties[WATER] = new Property("水", EnumAttr.WATER.getValue());
		properties[FIRE] = new Property("火", EnumAttr.FIRE.getValue());
		properties[EARTH] = new Property("土", EnumAttr.EARTH.getValue());
		properties[METAL_DEFENCE] = new Property("金抗", EnumAttr.METAL_DEFENCE.getValue());
		properties[WOOD_DEFENCE] = new Property("木抗", EnumAttr.WOOD_DEFENCE.getValue());
		properties[WATER_DEFENCE] = new Property("水抗", EnumAttr.WATER_DEFENCE.getValue());
		properties[FIRE_DEFENCE] = new Property("火抗", EnumAttr.FIRE_DEFENCE.getValue());
		properties[EARTH_DEFENCE] = new Property("土抗", EnumAttr.EARTH_DEFENCE.getValue());
		properties[SPEED] = new Property("速度", EnumAttr.SPEED.getValue());
	}

	public Property[] getProperty() {
		return properties;
	}

	public int getTotalProperty(int property) {
		return properties[property].getTotalJoin();
	}

	public int getBaseProperty(int property) {
		return properties[property].getBaseJoin();
	}

	public void clearBag() {
		for (int i = 0; i < properties.length; i++) {
			properties[i].clearBag();
		}
	}

	public Property getProperty(int property) {
		return properties[property];
	}

	public Property getProperty(EnumAttr attr) {
		int property = attr.getValue() - 1;
		return properties[property];
	}

	public abstract void ghost(Living otherLiving);

	public void addBag(BaseProperty bagData, BaseProperty bagPer) {
		// 添加背包数据
		properties[SOUL].setBagData(bagData.getSoul());
		properties[BLOOD].setBagData(bagData.getBlood());
		properties[ATTACK].setBagData(bagData.getAttack());
		properties[DEFENCE].setBagData(bagData.getDefence());
		properties[SOUL_ATTACK].setBagData(bagData.getSoulAttack());
		properties[SOUL_DEFENCE].setBagData(bagData.getSoulDefence());
		properties[ACCURATE].setBagData(bagData.getAccurate());
		properties[DODGE].setBagData(bagData.getDodge());
		properties[CRIT].setBagData(bagData.getCrit());
		properties[CRIT_DEFENCE].setBagData(bagData.getCritDefence());
		properties[CRIT_ADDTION].setBagData(bagData.getCritAddtion());
		properties[CRIT_CUT].setBagData(bagData.getCritCut());
		properties[BLOOD_ATTACK_ADDTION].setBagData(bagData.getBloodAttackAddtion());
		properties[BLOOD_ATTACK_CUT].setBagData(bagData.getBloodAttackCut());
		properties[SOUL_ATTACK_ADDTION].setBagData(bagData.getSoulAttackAddtion());
		properties[SOUL_ATTACK_CUT].setBagData(bagData.getBloodAttackCut());
		properties[REGAIN_SOUL].setBagData(bagData.getRegainSoul());
		properties[REGAIN_BLOOD].setBagData(bagData.getRegainBlood());
		properties[METAL].setBagData(bagData.getMetal());
		properties[WOOD].setBagData(bagData.getWood());
		properties[WATER].setBagData(bagData.getWater());
		properties[FIRE].setBagData(bagData.getFire());
		properties[EARTH].setBagData(bagData.getEarth());
		properties[METAL_DEFENCE].setBagData(bagData.getMetalDefence());
		properties[WOOD_DEFENCE].setBagData(bagData.getWoodDefence());
		properties[WATER_DEFENCE].setBagData(bagData.getWaterDefence());
		properties[FIRE_DEFENCE].setBagData(bagData.getFireDefence());
		properties[EARTH_DEFENCE].setBagData(bagData.getEarthDefence());
		properties[SPEED].setBagData(bagData.getSpeed());

		// 添加背包百分比加成
		properties[SOUL].setBagPer(bagPer.getSoul());
		properties[BLOOD].setBagPer(bagPer.getBlood());
		properties[ATTACK].setBagPer(bagPer.getAttack());
		properties[DEFENCE].setBagPer(bagPer.getDefence());
		properties[SOUL_ATTACK].setBagPer(bagPer.getSoulAttack());
		properties[SOUL_DEFENCE].setBagPer(bagPer.getSoulDefence());
		properties[ACCURATE].setBagPer(bagPer.getAccurate());
		properties[DODGE].setBagPer(bagPer.getDodge());
		properties[CRIT].setBagPer(bagPer.getCrit());
		properties[CRIT_DEFENCE].setBagPer(bagPer.getCritDefence());
		properties[CRIT_ADDTION].setBagPer(bagPer.getCritAddtion());
		properties[CRIT_CUT].setBagPer(bagPer.getCritCut());
		properties[BLOOD_ATTACK_ADDTION].setBagPer(bagPer.getBloodAttackAddtion());
		properties[BLOOD_ATTACK_CUT].setBagPer(bagPer.getBloodAttackCut());
		properties[SOUL_ATTACK_ADDTION].setBagPer(bagPer.getSoulAttackAddtion());
		properties[SOUL_ATTACK_CUT].setBagPer(bagPer.getBloodAttackCut());
		properties[REGAIN_SOUL].setBagPer(bagPer.getRegainSoul());
		properties[REGAIN_BLOOD].setBagPer(bagPer.getRegainBlood());
		properties[METAL].setBagPer(bagPer.getMetal());
		properties[WOOD].setBagPer(bagPer.getWood());
		properties[WATER].setBagPer(bagPer.getWater());
		properties[FIRE].setBagPer(bagPer.getFire());
		properties[EARTH].setBagPer(bagPer.getEarth());
		properties[METAL_DEFENCE].setBagPer(bagPer.getMetalDefence());
		properties[WOOD_DEFENCE].setBagPer(bagPer.getWoodDefence());
		properties[WATER_DEFENCE].setBagPer(bagPer.getWaterDefence());
		properties[FIRE_DEFENCE].setBagPer(bagPer.getFireDefence());
		properties[EARTH_DEFENCE].setBagPer(bagPer.getEarthDefence());
		properties[SPEED].setBagPer(bagPer.getSpeed());
	}

	public void addTemp(BaseProperty tempData) {
		// 添加模板数据
		properties[SOUL].setTempData(tempData.getSoul());
		properties[BLOOD].setTempData(tempData.getBlood());
		properties[ATTACK].setTempData(tempData.getAttack());
		properties[DEFENCE].setTempData(tempData.getDefence());
		properties[SOUL_ATTACK].setTempData(tempData.getSoulAttack());
		properties[SOUL_DEFENCE].setTempData(tempData.getSoulDefence());
		properties[SPEED].setTempData(tempData.getSpeed());

		// // 添加背包百分比加成
		// properties[SOUL].setTempPer(tempPer.getSoul());
		// properties[BLOOD].setTempPer(tempPer.getBlood());
		// properties[ATTACK].setTempPer(tempPer.getAttack());
		// properties[DEFENCE].setTempPer(tempPer.getDefence());
		// properties[SOUL_ATTACK].setTempPer(tempPer.getSoulAttack());
		// properties[SOUL_DEFENCE].setTempPer(tempPer.getSoulDefence());
		// properties[ACCURATE].setTempPer(tempPer.getAccurate());
		// properties[DODGE].setTempPer(tempPer.getDodge());
		// properties[CRIT].setTempPer(tempPer.getCrit());
		// properties[CRIT_DEFENCE].setTempPer(tempPer.getCritDefence());
		// properties[CRIT_ADDTION].setTempPer(tempPer.getCritAddtion());
		// properties[CRIT_CUT].setTempPer(tempPer.getCritCut());
		// properties[ATTACK_ADDTION].setTempPer(tempPer.getBloodAttackAddtion());
		// properties[ATTACK_CUT].setTempPer(tempPer.getBloodAttackCut());
		// properties[SOUL_ATTACK_ADDTION].setTempPer(tempPer.getSoulAttackAddtion());
		// properties[SOUL_ATTACK_CUT].setTempPer(tempPer.getBloodAttackCut());
		// properties[REGAIN_SOUL].setTempPer(tempPer.getRegainSoul());
		// properties[REGAIN_BLOOD].setTempPer(tempPer.getRegainBlood());
		// properties[METAL].setTempPer(tempPer.getMetal());
		// properties[WOOD].setTempPer(tempPer.getWood());
		// properties[WATER].setTempPer(tempPer.getWater());
		// properties[FIRE].setTempPer(tempPer.getFire());
		// properties[EARTH].setTempPer(tempPer.getEarth());
		// properties[META_DEFENCE].setTempPer(tempPer.getMetalDefence());
		// properties[WOOD_DEFENCE].setTempPer(tempPer.getWoodDefence());
		// properties[WATER_DEFENCE].setTempPer(tempPer.getWaterDefence());
		// properties[FIRE_DEFENCE].setTempPer(tempPer.getFireDefence());
		// properties[EARTH_DEFENCE].setTempPer(tempPer.getEarthDefence());
		// properties[SPEED].setTempPer(tempPer.getSpeed());

	}

	/**
	 * 加入技能属性
	 * 
	 * @param tempData
	 * @param tempPer
	 */
	public void addSkillPro(BaseProperty skillData, BaseProperty skillPer) {
		// 添加技能属性
		properties[SOUL].setSkillData(skillData.getSoul());
		properties[BLOOD].setSkillData(skillData.getBlood());
		properties[ATTACK].setSkillData(skillData.getAttack());
		properties[DEFENCE].setSkillData(skillData.getDefence());
		properties[SOUL_ATTACK].setSkillData(skillData.getSoulAttack());
		properties[SOUL_DEFENCE].setSkillData(skillData.getSoulDefence());
		properties[ACCURATE].setSkillData(skillData.getAccurate());
		properties[DODGE].setSkillData(skillData.getDodge());
		properties[CRIT].setSkillData(skillData.getCrit());
		properties[CRIT_DEFENCE].setSkillData(skillData.getCritDefence());
		properties[CRIT_ADDTION].setSkillData(skillData.getCritAddtion());
		properties[CRIT_CUT].setSkillData(skillData.getCritCut());
		properties[BLOOD_ATTACK_ADDTION].setSkillData(skillData.getBloodAttackAddtion());
		properties[BLOOD_ATTACK_CUT].setSkillData(skillData.getBloodAttackCut());
		properties[SOUL_ATTACK_ADDTION].setSkillData(skillData.getSoulAttackAddtion());
		properties[SOUL_ATTACK_CUT].setSkillData(skillData.getBloodAttackCut());
		properties[REGAIN_SOUL].setSkillData(skillData.getRegainSoul());
		properties[REGAIN_BLOOD].setSkillData(skillData.getRegainBlood());
		properties[METAL].setSkillData(skillData.getMetal());
		properties[WOOD].setSkillData(skillData.getWood());
		properties[WATER].setSkillData(skillData.getWater());
		properties[FIRE].setSkillData(skillData.getFire());
		properties[EARTH].setSkillData(skillData.getEarth());
		properties[METAL_DEFENCE].setSkillData(skillData.getMetalDefence());
		properties[WOOD_DEFENCE].setSkillData(skillData.getWoodDefence());
		properties[WATER_DEFENCE].setSkillData(skillData.getWaterDefence());
		properties[FIRE_DEFENCE].setSkillData(skillData.getFireDefence());
		properties[EARTH_DEFENCE].setSkillData(skillData.getEarthDefence());

		// 技能百分比加成
		properties[SOUL].setSkillPer(skillPer.getSoul());
		properties[BLOOD].setSkillPer(skillPer.getBlood());
		properties[ATTACK].setSkillPer(skillPer.getAttack());
		properties[DEFENCE].setSkillPer(skillPer.getDefence());
		properties[SOUL_ATTACK].setSkillPer(skillPer.getSoulAttack());
		properties[SOUL_DEFENCE].setSkillPer(skillPer.getSoulDefence());
		properties[ACCURATE].setSkillPer(skillPer.getAccurate());
		properties[DODGE].setSkillPer(skillPer.getDodge());
		properties[CRIT].setSkillPer(skillPer.getCrit());
		properties[CRIT_DEFENCE].setSkillPer(skillPer.getCritDefence());
		properties[CRIT_ADDTION].setSkillPer(skillPer.getCritAddtion());
		properties[CRIT_CUT].setSkillPer(skillPer.getCritCut());
		properties[BLOOD_ATTACK_ADDTION].setSkillPer(skillPer.getBloodAttackAddtion());
		properties[BLOOD_ATTACK_CUT].setSkillPer(skillPer.getBloodAttackCut());
		properties[SOUL_ATTACK_ADDTION].setSkillPer(skillPer.getSoulAttackAddtion());
		properties[SOUL_ATTACK_CUT].setSkillPer(skillPer.getBloodAttackCut());
		properties[REGAIN_SOUL].setSkillPer(skillPer.getRegainSoul());
		properties[REGAIN_BLOOD].setSkillPer(skillPer.getRegainBlood());
		properties[METAL].setSkillPer(skillPer.getMetal());
		properties[WOOD].setSkillPer(skillPer.getWood());
		properties[WATER].setSkillPer(skillPer.getWater());
		properties[FIRE].setSkillPer(skillPer.getFire());
		properties[EARTH].setSkillPer(skillPer.getEarth());
		properties[METAL_DEFENCE].setSkillPer(skillPer.getMetalDefence());
		properties[WOOD_DEFENCE].setSkillPer(skillPer.getWoodDefence());
		properties[WATER_DEFENCE].setSkillPer(skillPer.getWaterDefence());
		properties[FIRE_DEFENCE].setSkillPer(skillPer.getFireDefence());
		properties[EARTH_DEFENCE].setSkillPer(skillPer.getEarthDefence());
		properties[SPEED].setSkillPer(skillPer.getSpeed());
	}

	/**
	 * 添加坐骑属性
	 * 
	 * @param mountData
	 */
	public void addMount(BaseProperty mountData, BaseProperty mountPer) {
		// 添加坐骑属性
		properties[SOUL].setMountData(mountData.getSoul());
		properties[BLOOD].setMountData(mountData.getBlood());
		properties[ATTACK].setMountData(mountData.getAttack());
		properties[DEFENCE].setMountData(mountData.getDefence());
		properties[SOUL_ATTACK].setMountData(mountData.getSoulAttack());
		properties[SOUL_DEFENCE].setMountData(mountData.getSoulDefence());
		properties[ACCURATE].setMountData(mountData.getAccurate());
		properties[DODGE].setMountData(mountData.getDodge());
		properties[CRIT].setMountData(mountData.getCrit());
		properties[CRIT_DEFENCE].setMountData(mountData.getCritDefence());
		properties[CRIT_ADDTION].setMountData(mountData.getCritAddtion());
		properties[CRIT_CUT].setMountData(mountData.getCritCut());
		properties[BLOOD_ATTACK_ADDTION].setMountData(mountData.getBloodAttackAddtion());
		properties[BLOOD_ATTACK_CUT].setMountData(mountData.getBloodAttackCut());
		properties[SOUL_ATTACK_ADDTION].setMountData(mountData.getSoulAttackAddtion());
		properties[SOUL_ATTACK_CUT].setMountData(mountData.getBloodAttackCut());
		properties[REGAIN_SOUL].setMountData(mountData.getRegainSoul());
		properties[REGAIN_BLOOD].setMountData(mountData.getRegainBlood());
		properties[METAL].setMountData(mountData.getMetal());
		properties[WOOD].setMountData(mountData.getWood());
		properties[WATER].setMountData(mountData.getWater());
		properties[FIRE].setMountData(mountData.getFire());
		properties[EARTH].setMountData(mountData.getEarth());
		properties[METAL_DEFENCE].setMountData(mountData.getMetalDefence());
		properties[WOOD_DEFENCE].setMountData(mountData.getWoodDefence());
		properties[WATER_DEFENCE].setMountData(mountData.getWaterDefence());
		properties[FIRE_DEFENCE].setMountData(mountData.getFireDefence());
		properties[EARTH_DEFENCE].setMountData(mountData.getEarthDefence());

		// 坐骑百分比加成
		properties[SOUL].setMountPer(mountPer.getSoul());
		properties[BLOOD].setMountPer(mountPer.getBlood());
		properties[ATTACK].setMountPer(mountPer.getAttack());
		properties[DEFENCE].setMountPer(mountPer.getDefence());
		properties[SOUL_ATTACK].setMountPer(mountPer.getSoulAttack());
		properties[SOUL_DEFENCE].setMountPer(mountPer.getSoulDefence());
		properties[ACCURATE].setMountPer(mountPer.getAccurate());
		properties[DODGE].setMountPer(mountPer.getDodge());
		properties[CRIT].setMountPer(mountPer.getCrit());
		properties[CRIT_DEFENCE].setMountPer(mountPer.getCritDefence());
		properties[CRIT_ADDTION].setMountPer(mountPer.getCritAddtion());
		properties[CRIT_CUT].setMountPer(mountPer.getCritCut());
		properties[BLOOD_ATTACK_ADDTION].setMountPer(mountPer.getBloodAttackAddtion());
		properties[BLOOD_ATTACK_CUT].setMountPer(mountPer.getBloodAttackCut());
		properties[SOUL_ATTACK_ADDTION].setMountPer(mountPer.getSoulAttackAddtion());
		properties[SOUL_ATTACK_CUT].setMountPer(mountPer.getBloodAttackCut());
		properties[REGAIN_SOUL].setMountPer(mountPer.getRegainSoul());
		properties[REGAIN_BLOOD].setMountPer(mountPer.getRegainBlood());
		properties[METAL].setMountPer(mountPer.getMetal());
		properties[WOOD].setMountPer(mountPer.getWood());
		properties[WATER].setMountPer(mountPer.getWater());
		properties[FIRE].setMountPer(mountPer.getFire());
		properties[EARTH].setMountPer(mountPer.getEarth());
		properties[METAL_DEFENCE].setMountPer(mountPer.getMetalDefence());
		properties[WOOD_DEFENCE].setMountPer(mountPer.getWoodDefence());
		properties[WATER_DEFENCE].setMountPer(mountPer.getWaterDefence());
		properties[FIRE_DEFENCE].setMountPer(mountPer.getFireDefence());
		properties[EARTH_DEFENCE].setMountPer(mountPer.getEarthDefence());
		properties[SPEED].setMountPer(mountPer.getSpeed());
	}

	/**
	 * 添加法宝属性
	 * 
	 * @param magicwpData
	 */
	public void addMagicwp(BaseProperty magicwpData, BaseProperty magicwpPer) {
		// 添加法宝属性
		properties[SOUL].setMagicwpData(magicwpData.getSoul());
		properties[BLOOD].setMagicwpData(magicwpData.getBlood());
		properties[ATTACK].setMagicwpData(magicwpData.getAttack());
		properties[DEFENCE].setMagicwpData(magicwpData.getDefence());
		properties[SOUL_ATTACK].setMagicwpData(magicwpData.getSoulAttack());
		properties[SOUL_DEFENCE].setMagicwpData(magicwpData.getSoulDefence());
		properties[ACCURATE].setMagicwpData(magicwpData.getAccurate());
		properties[DODGE].setMagicwpData(magicwpData.getDodge());
		properties[CRIT].setMagicwpData(magicwpData.getCrit());
		properties[CRIT_DEFENCE].setMagicwpData(magicwpData.getCritDefence());
		properties[CRIT_ADDTION].setMagicwpData(magicwpData.getCritAddtion());
		properties[CRIT_CUT].setMagicwpData(magicwpData.getCritCut());
		properties[BLOOD_ATTACK_ADDTION].setMagicwpData(magicwpData.getBloodAttackAddtion());
		properties[BLOOD_ATTACK_CUT].setMagicwpData(magicwpData.getBloodAttackCut());
		properties[SOUL_ATTACK_ADDTION].setMagicwpData(magicwpData.getSoulAttackAddtion());
		properties[SOUL_ATTACK_CUT].setMagicwpData(magicwpData.getBloodAttackCut());
		properties[REGAIN_SOUL].setMagicwpData(magicwpData.getRegainSoul());
		properties[REGAIN_BLOOD].setMagicwpData(magicwpData.getRegainBlood());
		properties[METAL].setMagicwpData(magicwpData.getMetal());
		properties[WOOD].setMagicwpData(magicwpData.getWood());
		properties[WATER].setMagicwpData(magicwpData.getWater());
		properties[FIRE].setMagicwpData(magicwpData.getFire());
		properties[EARTH].setMagicwpData(magicwpData.getEarth());
		properties[METAL_DEFENCE].setMagicwpData(magicwpData.getMetalDefence());
		properties[WOOD_DEFENCE].setMagicwpData(magicwpData.getWoodDefence());
		properties[WATER_DEFENCE].setMagicwpData(magicwpData.getWaterDefence());
		properties[FIRE_DEFENCE].setMagicwpData(magicwpData.getFireDefence());
		properties[EARTH_DEFENCE].setMagicwpData(magicwpData.getEarthDefence());

		// 法宝百分比加成
		properties[SOUL].setMagicwpPer(magicwpPer.getSoul());
		properties[BLOOD].setMagicwpPer(magicwpPer.getBlood());
		properties[ATTACK].setMagicwpPer(magicwpPer.getAttack());
		properties[DEFENCE].setMagicwpPer(magicwpPer.getDefence());
		properties[SOUL_ATTACK].setMagicwpPer(magicwpPer.getSoulAttack());
		properties[SOUL_DEFENCE].setMagicwpPer(magicwpPer.getSoulDefence());
		properties[ACCURATE].setMagicwpPer(magicwpPer.getAccurate());
		properties[DODGE].setMagicwpPer(magicwpPer.getDodge());
		properties[CRIT].setMagicwpPer(magicwpPer.getCrit());
		properties[CRIT_DEFENCE].setMagicwpPer(magicwpPer.getCritDefence());
		properties[CRIT_ADDTION].setMagicwpPer(magicwpPer.getCritAddtion());
		properties[CRIT_CUT].setMagicwpPer(magicwpPer.getCritCut());
		properties[BLOOD_ATTACK_ADDTION].setMagicwpPer(magicwpPer.getBloodAttackAddtion());
		properties[BLOOD_ATTACK_CUT].setMagicwpPer(magicwpPer.getBloodAttackCut());
		properties[SOUL_ATTACK_ADDTION].setMagicwpPer(magicwpPer.getSoulAttackAddtion());
		properties[SOUL_ATTACK_CUT].setMagicwpPer(magicwpPer.getBloodAttackCut());
		properties[REGAIN_SOUL].setMagicwpPer(magicwpPer.getRegainSoul());
		properties[REGAIN_BLOOD].setMagicwpPer(magicwpPer.getRegainBlood());
		properties[METAL].setMagicwpPer(magicwpPer.getMetal());
		properties[WOOD].setMagicwpPer(magicwpPer.getWood());
		properties[WATER].setMagicwpPer(magicwpPer.getWater());
		properties[FIRE].setMagicwpPer(magicwpPer.getFire());
		properties[EARTH].setMagicwpPer(magicwpPer.getEarth());
		properties[METAL_DEFENCE].setMagicwpPer(magicwpPer.getMetalDefence());
		properties[WOOD_DEFENCE].setMagicwpPer(magicwpPer.getWoodDefence());
		properties[WATER_DEFENCE].setMagicwpPer(magicwpPer.getWaterDefence());
		properties[FIRE_DEFENCE].setMagicwpPer(magicwpPer.getFireDefence());
		properties[EARTH_DEFENCE].setMagicwpPer(magicwpPer.getEarthDefence());
		properties[SPEED].setMagicwpPer(magicwpPer.getSpeed());
	}

	/**
	 * 添加宠物属性
	 * 
	 * @param petData
	 */
	public void addPet(BaseProperty petData, BaseProperty petPer) {
		// 添加宠物属性
		properties[SOUL].setPetData(petData.getSoul());
		properties[BLOOD].setPetData(petData.getBlood());
		properties[ATTACK].setPetData(petData.getAttack());
		properties[DEFENCE].setPetData(petData.getDefence());
		properties[SOUL_ATTACK].setPetData(petData.getSoulAttack());
		properties[SOUL_DEFENCE].setPetData(petData.getSoulDefence());
		properties[ACCURATE].setPetData(petData.getAccurate());
		properties[DODGE].setPetData(petData.getDodge());
		properties[CRIT].setPetData(petData.getCrit());
		properties[CRIT_DEFENCE].setPetData(petData.getCritDefence());
		properties[CRIT_ADDTION].setPetData(petData.getCritAddtion());
		properties[CRIT_CUT].setPetData(petData.getCritCut());
		properties[BLOOD_ATTACK_ADDTION].setPetData(petData.getBloodAttackAddtion());
		properties[BLOOD_ATTACK_CUT].setPetData(petData.getBloodAttackCut());
		properties[SOUL_ATTACK_ADDTION].setPetData(petData.getSoulAttackAddtion());
		properties[SOUL_ATTACK_CUT].setPetData(petData.getBloodAttackCut());
		properties[REGAIN_SOUL].setPetData(petData.getRegainSoul());
		properties[REGAIN_BLOOD].setPetData(petData.getRegainBlood());
		properties[METAL].setPetData(petData.getMetal());
		properties[WOOD].setPetData(petData.getWood());
		properties[WATER].setPetData(petData.getWater());
		properties[FIRE].setPetData(petData.getFire());
		properties[EARTH].setPetData(petData.getEarth());
		properties[METAL_DEFENCE].setPetData(petData.getMetalDefence());
		properties[WOOD_DEFENCE].setPetData(petData.getWoodDefence());
		properties[WATER_DEFENCE].setPetData(petData.getWaterDefence());
		properties[FIRE_DEFENCE].setPetData(petData.getFireDefence());
		properties[EARTH_DEFENCE].setPetData(petData.getEarthDefence());

		// 宠物百分比加成
		properties[SOUL].setPetPer(petPer.getSoul());
		properties[BLOOD].setPetPer(petPer.getBlood());
		properties[ATTACK].setPetPer(petPer.getAttack());
		properties[DEFENCE].setPetPer(petPer.getDefence());
		properties[SOUL_ATTACK].setPetPer(petPer.getSoulAttack());
		properties[SOUL_DEFENCE].setPetPer(petPer.getSoulDefence());
		properties[ACCURATE].setPetPer(petPer.getAccurate());
		properties[DODGE].setPetPer(petPer.getDodge());
		properties[CRIT].setPetPer(petPer.getCrit());
		properties[CRIT_DEFENCE].setPetPer(petPer.getCritDefence());
		properties[CRIT_ADDTION].setPetPer(petPer.getCritAddtion());
		properties[CRIT_CUT].setPetPer(petPer.getCritCut());
		properties[BLOOD_ATTACK_ADDTION].setPetPer(petPer.getBloodAttackAddtion());
		properties[BLOOD_ATTACK_CUT].setPetPer(petPer.getBloodAttackCut());
		properties[SOUL_ATTACK_ADDTION].setPetPer(petPer.getSoulAttackAddtion());
		properties[SOUL_ATTACK_CUT].setPetPer(petPer.getBloodAttackCut());
		properties[REGAIN_SOUL].setPetPer(petPer.getRegainSoul());
		properties[REGAIN_BLOOD].setPetPer(petPer.getRegainBlood());
		properties[METAL].setPetPer(petPer.getMetal());
		properties[WOOD].setPetPer(petPer.getWood());
		properties[WATER].setPetPer(petPer.getWater());
		properties[FIRE].setPetPer(petPer.getFire());
		properties[EARTH].setPetPer(petPer.getEarth());
		properties[METAL_DEFENCE].setPetPer(petPer.getMetalDefence());
		properties[WOOD_DEFENCE].setPetPer(petPer.getWoodDefence());
		properties[WATER_DEFENCE].setPetPer(petPer.getWaterDefence());
		properties[FIRE_DEFENCE].setPetPer(petPer.getFireDefence());
		properties[EARTH_DEFENCE].setPetPer(petPer.getEarthDefence());
		properties[SPEED].setPetPer(petPer.getSpeed());
	}

	public void writeProto(GamePlayer player, PropertyListMsg.Builder propertyMsgs) {
		PlayerJoinInfo joinInfo = player.getBasePlayer().getPlayerJoinInfo();

		for (int index = 0; index < properties.length; index++) {
			Property property = properties[index];
			if (property != null) {
				updatePlayer(index, property, joinInfo);

				PropertyMsg.Builder proMsg = PropertyMsg.newBuilder();
				proMsg.setType(index + 1);
				property.writeProto(proMsg);
				propertyMsgs.addPropertys(proMsg);
			} else {
				Log.error("------丢失属性-----丢失属性------丢失属性-------丢失属性---", new Exception("丢失属性"));
			}
		}
		/*----------------------------人物战斗力--------------------------*/
		refresh();// 刷新
		PropertyMsg.Builder proMsg = PropertyMsg.newBuilder();
		proMsg.setType(EnumAttr.FightValue.getValue());
		proMsg.setTotalPoint(fighting);
		propertyMsgs.addPropertys(proMsg);
	}

	public void writeUpdateProto(GamePlayer player, PlayerAttUpdateMsg.Builder msg) {

		msg.setPlayerId(player.getPlayerId());
		PlayerJoinInfo joinInfo = player.getBasePlayer().getPlayerJoinInfo();
		for (int i = 0; i < properties.length; i++) {
			Property property = properties[i];
			if (property != null) {
				if (property.isChange()) {
					updatePlayer(i, property, joinInfo);
					PropertyMsg.Builder proMsg = PropertyMsg.newBuilder();
					proMsg.setType(i + 1);
					property.writeProto(proMsg);
					msg.addAtt(proMsg);
				}
			} else {
				Log.error("------丢失属性-----丢失属性------丢失属性-------丢失属性---", new Exception("丢失属性"));
			}
		}
		/*----------------------------人物战斗力--------------------------*/
		refresh();// 刷新
		PropertyMsg.Builder proMsg = PropertyMsg.newBuilder();
		proMsg.setType(EnumAttr.FightValue.getValue());
		proMsg.setTotalPoint(fighting);
		msg.addAtt(proMsg);
	}

	public int getFighting() {
		return fighting;
	}

	public void refresh() {
		fighting = 0;
		for (Property p : properties) {
			fighting += p.getTotalJoin() * PropertyFightingTemplateMgr.getFighting(p.getType()) / 100;
		}
	}

	public void updatePlayer(int index, Property property, PlayerJoinInfo joinInfo) {
		switch (index) {
			case SOUL:
				joinInfo.setSoul(property.getTotalJoin());
				break;
			case BLOOD:
				joinInfo.setBlood(property.getTotalJoin());
				break;
			case ATTACK:
				joinInfo.setAttack(property.getTotalJoin());
				break;
			case DEFENCE:
				joinInfo.setDefence(property.getTotalJoin());
				break;
			case SOUL_ATTACK:
				joinInfo.setSoulAttack(property.getTotalJoin());
				break;
			case SOUL_DEFENCE:
				joinInfo.setSoulDefence(property.getTotalJoin());
				break;
			case ACCURATE:
				joinInfo.setAccurate(property.getTotalJoin());
				break;
			case DODGE:
				joinInfo.setDodge(property.getTotalJoin());
				break;
			case CRIT:
				joinInfo.setCrit(property.getTotalJoin());
				break;
			case CRIT_DEFENCE:
				joinInfo.setCritDefence(property.getTotalJoin());
				break;
			case CRIT_ADDTION:
				joinInfo.setCritAddtion(property.getTotalJoin());
				break;
			case CRIT_CUT:
				joinInfo.setCritCut(property.getTotalJoin());
				break;
			case BLOOD_ATTACK_ADDTION:
				joinInfo.setBloodAttackAddtion(property.getTotalJoin());
				break;
			case BLOOD_ATTACK_CUT:
				joinInfo.setBloodAttackCut(property.getTotalJoin());
				break;
			case SOUL_ATTACK_ADDTION:
				joinInfo.setSoulAttackAddtion(property.getTotalJoin());
				break;
			case SOUL_ATTACK_CUT:
				joinInfo.setSoulAttackCut(property.getTotalJoin());
				break;
			case REGAIN_SOUL:
				joinInfo.setRegainSoul(property.getTotalJoin());
				break;
			case REGAIN_BLOOD:
				joinInfo.setRegainBlood(property.getTotalJoin());
				break;
			case METAL:
				joinInfo.setMetal(property.getTotalJoin());
				break;
			case WOOD:
				joinInfo.setWood(property.getTotalJoin());
				break;
			case WATER:
				joinInfo.setWater(property.getTotalJoin());
				break;
			case FIRE:
				joinInfo.setFire(property.getTotalJoin());
				break;
			case EARTH:
				joinInfo.setEarth(property.getTotalJoin());
				break;
			case METAL_DEFENCE:
				joinInfo.setMetalDefence(property.getTotalJoin());
				break;
			case WOOD_DEFENCE:
				joinInfo.setWoodDefence(property.getTotalJoin());
				break;
			case WATER_DEFENCE:
				joinInfo.setWaterDefence(property.getTotalJoin());
				break;
			case FIRE_DEFENCE:
				joinInfo.setFireDefence(property.getTotalJoin());
				break;
			case EARTH_DEFENCE:
				joinInfo.setEarthDefence(property.getTotalJoin());
				break;
			case SPEED:
				joinInfo.setSpeed(property.getTotalJoin());
				break;
			default:
				break;
		}
		joinInfo.setOp(Option.Update);
	}

}
