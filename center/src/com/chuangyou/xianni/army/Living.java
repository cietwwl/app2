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
import com.chuangyou.xianni.entity.rank.RankTempInfo;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.rank.RankServerManager;
import com.chuangyou.xianni.state.event.ArtifactStateEvent;

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
		properties[BLOOD_ATTACK_ADDTION].setBagData(bagData.getAttackAddtion());
		properties[BLOOD_ATTACK_CUT].setBagData(bagData.getAttackCut());
		properties[SOUL_ATTACK_ADDTION].setBagData(bagData.getSoulAttackAddtion());
		properties[SOUL_ATTACK_CUT].setBagData(bagData.getAttackCut());
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
		properties[BLOOD_ATTACK_ADDTION].setBagPer(bagPer.getAttackAddtion());
		properties[BLOOD_ATTACK_CUT].setBagPer(bagPer.getAttackCut());
		properties[SOUL_ATTACK_ADDTION].setBagPer(bagPer.getSoulAttackAddtion());
		properties[SOUL_ATTACK_CUT].setBagPer(bagPer.getAttackCut());
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
		// properties[ATTACK_ADDTION].setTempPer(tempPer.getAttackAddtion());
		// properties[ATTACK_CUT].setTempPer(tempPer.getAttackCut());
		// properties[SOUL_ATTACK_ADDTION].setTempPer(tempPer.getSoulAttackAddtion());
		// properties[SOUL_ATTACK_CUT].setTempPer(tempPer.getAttackCut());
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
		properties[BLOOD_ATTACK_ADDTION].setSkillData(skillData.getAttackAddtion());
		properties[BLOOD_ATTACK_CUT].setSkillData(skillData.getAttackCut());
		properties[SOUL_ATTACK_ADDTION].setSkillData(skillData.getSoulAttackAddtion());
		properties[SOUL_ATTACK_CUT].setSkillData(skillData.getAttackCut());
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
		properties[BLOOD_ATTACK_ADDTION].setSkillPer(skillPer.getAttackAddtion());
		properties[BLOOD_ATTACK_CUT].setSkillPer(skillPer.getAttackCut());
		properties[SOUL_ATTACK_ADDTION].setSkillPer(skillPer.getSoulAttackAddtion());
		properties[SOUL_ATTACK_CUT].setSkillPer(skillPer.getAttackCut());
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

	public void addInverseBead(BaseProperty inverseBeadData, BaseProperty inverseBeadPer) {
		properties[METAL].setInverseBeadData(inverseBeadData.getMetal());
		properties[WOOD].setInverseBeadData(inverseBeadData.getWood());
		properties[WATER].setInverseBeadData(inverseBeadData.getWater());
		properties[FIRE].setInverseBeadData(inverseBeadData.getFire());
		properties[EARTH].setInverseBeadData(inverseBeadData.getEarth());
		properties[METAL_DEFENCE].setInverseBeadData(inverseBeadData.getMetalDefence());
		properties[WOOD_DEFENCE].setInverseBeadData(inverseBeadData.getWoodDefence());
		properties[WATER_DEFENCE].setInverseBeadData(inverseBeadData.getWaterDefence());
		properties[FIRE_DEFENCE].setInverseBeadData(inverseBeadData.getFireDefence());
		properties[EARTH_DEFENCE].setInverseBeadData(inverseBeadData.getEarthDefence());

		properties[METAL].setInverseBeadper(inverseBeadPer.getMetal());
		properties[WOOD].setInverseBeadper(inverseBeadPer.getWood());
		properties[WATER].setInverseBeadper(inverseBeadPer.getWater());
		properties[FIRE].setInverseBeadper(inverseBeadPer.getFire());
		properties[EARTH].setInverseBeadper(inverseBeadPer.getEarth());
		properties[METAL_DEFENCE].setInverseBeadper(inverseBeadPer.getMetalDefence());
		properties[WOOD_DEFENCE].setInverseBeadper(inverseBeadPer.getWoodDefence());
		properties[WATER_DEFENCE].setInverseBeadper(inverseBeadPer.getWaterDefence());
		properties[FIRE_DEFENCE].setInverseBeadper(inverseBeadPer.getFireDefence());
		properties[EARTH_DEFENCE].setInverseBeadper(inverseBeadPer.getEarthDefence());
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
		properties[BLOOD_ATTACK_ADDTION].setMountData(mountData.getAttackAddtion());
		properties[BLOOD_ATTACK_CUT].setMountData(mountData.getAttackCut());
		properties[SOUL_ATTACK_ADDTION].setMountData(mountData.getSoulAttackAddtion());
		properties[SOUL_ATTACK_CUT].setMountData(mountData.getAttackCut());
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
		properties[BLOOD_ATTACK_ADDTION].setMountPer(mountPer.getAttackAddtion());
		properties[BLOOD_ATTACK_CUT].setMountPer(mountPer.getAttackCut());
		properties[SOUL_ATTACK_ADDTION].setMountPer(mountPer.getSoulAttackAddtion());
		properties[SOUL_ATTACK_CUT].setMountPer(mountPer.getAttackCut());
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
		properties[BLOOD_ATTACK_ADDTION].setMagicwpData(magicwpData.getAttackAddtion());
		properties[BLOOD_ATTACK_CUT].setMagicwpData(magicwpData.getAttackCut());
		properties[SOUL_ATTACK_ADDTION].setMagicwpData(magicwpData.getSoulAttackAddtion());
		properties[SOUL_ATTACK_CUT].setMagicwpData(magicwpData.getAttackCut());
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
		properties[BLOOD_ATTACK_ADDTION].setMagicwpPer(magicwpPer.getAttackAddtion());
		properties[BLOOD_ATTACK_CUT].setMagicwpPer(magicwpPer.getAttackCut());
		properties[SOUL_ATTACK_ADDTION].setMagicwpPer(magicwpPer.getSoulAttackAddtion());
		properties[SOUL_ATTACK_CUT].setMagicwpPer(magicwpPer.getAttackCut());
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
		properties[BLOOD_ATTACK_ADDTION].setPetData(petData.getAttackAddtion());
		properties[BLOOD_ATTACK_CUT].setPetData(petData.getAttackCut());
		properties[SOUL_ATTACK_ADDTION].setPetData(petData.getSoulAttackAddtion());
		properties[SOUL_ATTACK_CUT].setPetData(petData.getAttackCut());
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
		properties[BLOOD_ATTACK_ADDTION].setPetPer(petPer.getAttackAddtion());
		properties[BLOOD_ATTACK_CUT].setPetPer(petPer.getAttackCut());
		properties[SOUL_ATTACK_ADDTION].setPetPer(petPer.getSoulAttackAddtion());
		properties[SOUL_ATTACK_CUT].setPetPer(petPer.getAttackCut());
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

	/**
	 * 添加时装属性
	 * 
	 * @param petData
	 */
	public void addFashion(BaseProperty fashionData, BaseProperty fashionPer) {
		// 添加时装属性
		properties[SOUL].setFashionData(fashionData.getSoul());
		properties[BLOOD].setFashionData(fashionData.getBlood());
		properties[ATTACK].setFashionData(fashionData.getAttack());
		properties[DEFENCE].setFashionData(fashionData.getDefence());
		properties[SOUL_ATTACK].setFashionData(fashionData.getSoulAttack());
		properties[SOUL_DEFENCE].setFashionData(fashionData.getSoulDefence());
		properties[ACCURATE].setFashionData(fashionData.getAccurate());
		properties[DODGE].setFashionData(fashionData.getDodge());
		properties[CRIT].setFashionData(fashionData.getCrit());
		properties[CRIT_DEFENCE].setFashionData(fashionData.getCritDefence());
		properties[CRIT_ADDTION].setFashionData(fashionData.getCritAddtion());
		properties[CRIT_CUT].setFashionData(fashionData.getCritCut());
		properties[BLOOD_ATTACK_ADDTION].setFashionData(fashionData.getAttackAddtion());
		properties[BLOOD_ATTACK_CUT].setFashionData(fashionData.getAttackCut());
		properties[SOUL_ATTACK_ADDTION].setFashionData(fashionData.getSoulAttackAddtion());
		properties[SOUL_ATTACK_CUT].setFashionData(fashionData.getAttackCut());
		properties[REGAIN_SOUL].setFashionData(fashionData.getRegainSoul());
		properties[REGAIN_BLOOD].setFashionData(fashionData.getRegainBlood());
		properties[METAL].setFashionData(fashionData.getMetal());
		properties[WOOD].setFashionData(fashionData.getWood());
		properties[WATER].setFashionData(fashionData.getWater());
		properties[FIRE].setFashionData(fashionData.getFire());
		properties[EARTH].setFashionData(fashionData.getEarth());
		properties[METAL_DEFENCE].setFashionData(fashionData.getMetalDefence());
		properties[WOOD_DEFENCE].setFashionData(fashionData.getWoodDefence());
		properties[WATER_DEFENCE].setFashionData(fashionData.getWaterDefence());
		properties[FIRE_DEFENCE].setFashionData(fashionData.getFireDefence());
		properties[EARTH_DEFENCE].setFashionData(fashionData.getEarthDefence());

		// 时装百分比加成
		properties[SOUL].setFashionPer(fashionPer.getSoul());
		properties[BLOOD].setFashionPer(fashionPer.getBlood());
		properties[ATTACK].setFashionPer(fashionPer.getAttack());
		properties[DEFENCE].setFashionPer(fashionPer.getDefence());
		properties[SOUL_ATTACK].setFashionPer(fashionPer.getSoulAttack());
		properties[SOUL_DEFENCE].setFashionPer(fashionPer.getSoulDefence());
		properties[ACCURATE].setFashionPer(fashionPer.getAccurate());
		properties[DODGE].setFashionPer(fashionPer.getDodge());
		properties[CRIT].setFashionPer(fashionPer.getCrit());
		properties[CRIT_DEFENCE].setFashionPer(fashionPer.getCritDefence());
		properties[CRIT_ADDTION].setFashionPer(fashionPer.getCritAddtion());
		properties[CRIT_CUT].setFashionPer(fashionPer.getCritCut());
		properties[BLOOD_ATTACK_ADDTION].setFashionPer(fashionPer.getAttackAddtion());
		properties[BLOOD_ATTACK_CUT].setFashionPer(fashionPer.getAttackCut());
		properties[SOUL_ATTACK_ADDTION].setFashionPer(fashionPer.getSoulAttackAddtion());
		properties[SOUL_ATTACK_CUT].setFashionPer(fashionPer.getAttackCut());
		properties[REGAIN_SOUL].setFashionPer(fashionPer.getRegainSoul());
		properties[REGAIN_BLOOD].setFashionPer(fashionPer.getRegainBlood());
		properties[METAL].setFashionPer(fashionPer.getMetal());
		properties[WOOD].setFashionPer(fashionPer.getWood());
		properties[WATER].setFashionPer(fashionPer.getWater());
		properties[FIRE].setFashionPer(fashionPer.getFire());
		properties[EARTH].setFashionPer(fashionPer.getEarth());
		properties[METAL_DEFENCE].setFashionPer(fashionPer.getMetalDefence());
		properties[WOOD_DEFENCE].setFashionPer(fashionPer.getWoodDefence());
		properties[WATER_DEFENCE].setFashionPer(fashionPer.getWaterDefence());
		properties[FIRE_DEFENCE].setFashionPer(fashionPer.getFireDefence());
		properties[EARTH_DEFENCE].setFashionPer(fashionPer.getEarthDefence());
		properties[SPEED].setFashionPer(fashionPer.getSpeed());
	}

	/**
	 * 添加魂幡属性
	 * 
	 * @param petData
	 */
	public void addSoul(BaseProperty soulData, BaseProperty soulPer) {
		// 添加魂幡属性
		properties[SOUL].setSoulData(soulData.getSoul());
		properties[BLOOD].setSoulData(soulData.getBlood());
		properties[ATTACK].setSoulData(soulData.getAttack());
		properties[DEFENCE].setSoulData(soulData.getDefence());
		properties[SOUL_ATTACK].setSoulData(soulData.getSoulAttack());
		properties[SOUL_DEFENCE].setSoulData(soulData.getSoulDefence());
		properties[ACCURATE].setSoulData(soulData.getAccurate());
		properties[DODGE].setSoulData(soulData.getDodge());
		properties[CRIT].setSoulData(soulData.getCrit());
		properties[CRIT_DEFENCE].setSoulData(soulData.getCritDefence());
		properties[CRIT_ADDTION].setSoulData(soulData.getCritAddtion());
		properties[CRIT_CUT].setSoulData(soulData.getCritCut());
		properties[BLOOD_ATTACK_ADDTION].setSoulData(soulData.getAttackAddtion());
		properties[BLOOD_ATTACK_CUT].setSoulData(soulData.getAttackCut());
		properties[SOUL_ATTACK_ADDTION].setSoulData(soulData.getSoulAttackAddtion());
		properties[SOUL_ATTACK_CUT].setSoulData(soulData.getAttackCut());
		properties[REGAIN_SOUL].setSoulData(soulData.getRegainSoul());
		properties[REGAIN_BLOOD].setSoulData(soulData.getRegainBlood());
		properties[METAL].setSoulData(soulData.getMetal());
		properties[WOOD].setSoulData(soulData.getWood());
		properties[WATER].setSoulData(soulData.getWater());
		properties[FIRE].setSoulData(soulData.getFire());
		properties[EARTH].setSoulData(soulData.getEarth());
		properties[METAL_DEFENCE].setSoulData(soulData.getMetalDefence());
		properties[WOOD_DEFENCE].setSoulData(soulData.getWoodDefence());
		properties[WATER_DEFENCE].setSoulData(soulData.getWaterDefence());
		properties[FIRE_DEFENCE].setSoulData(soulData.getFireDefence());
		properties[EARTH_DEFENCE].setSoulData(soulData.getEarthDefence());

		// 魂幡百分比加成
		properties[SOUL].setSoulPer(soulPer.getSoul());
		properties[BLOOD].setSoulPer(soulPer.getBlood());
		properties[ATTACK].setSoulPer(soulPer.getAttack());
		properties[DEFENCE].setSoulPer(soulPer.getDefence());
		properties[SOUL_ATTACK].setSoulPer(soulPer.getSoulAttack());
		properties[SOUL_DEFENCE].setSoulPer(soulPer.getSoulDefence());
		properties[ACCURATE].setSoulPer(soulPer.getAccurate());
		properties[DODGE].setSoulPer(soulPer.getDodge());
		properties[CRIT].setSoulPer(soulPer.getCrit());
		properties[CRIT_DEFENCE].setSoulPer(soulPer.getCritDefence());
		properties[CRIT_ADDTION].setSoulPer(soulPer.getCritAddtion());
		properties[CRIT_CUT].setSoulPer(soulPer.getCritCut());
		properties[BLOOD_ATTACK_ADDTION].setSoulPer(soulPer.getAttackAddtion());
		properties[BLOOD_ATTACK_CUT].setSoulPer(soulPer.getAttackCut());
		properties[SOUL_ATTACK_ADDTION].setSoulPer(soulPer.getSoulAttackAddtion());
		properties[SOUL_ATTACK_CUT].setSoulPer(soulPer.getAttackCut());
		properties[REGAIN_SOUL].setSoulPer(soulPer.getRegainSoul());
		properties[REGAIN_BLOOD].setSoulPer(soulPer.getRegainBlood());
		properties[METAL].setSoulPer(soulPer.getMetal());
		properties[WOOD].setSoulPer(soulPer.getWood());
		properties[WATER].setSoulPer(soulPer.getWater());
		properties[FIRE].setSoulPer(soulPer.getFire());
		properties[EARTH].setSoulPer(soulPer.getEarth());
		properties[METAL_DEFENCE].setSoulPer(soulPer.getMetalDefence());
		properties[WOOD_DEFENCE].setSoulPer(soulPer.getWoodDefence());
		properties[WATER_DEFENCE].setSoulPer(soulPer.getWaterDefence());
		properties[FIRE_DEFENCE].setSoulPer(soulPer.getFireDefence());
		properties[EARTH_DEFENCE].setSoulPer(soulPer.getEarthDefence());
		properties[SPEED].setSoulPer(soulPer.getSpeed());
	}

	/**
	 * 添加境界属性加成
	 * 
	 * @param stateData
	 * @param statePer
	 */
	public void addState(BaseProperty stateData, BaseProperty statePer) {

		properties[SOUL].setStateData(stateData.getSoul());
		properties[BLOOD].setStateData(stateData.getBlood());
		properties[ATTACK].setStateData(stateData.getAttack());
		properties[DEFENCE].setStateData(stateData.getDefence());
		properties[SOUL_ATTACK].setStateData(stateData.getSoulAttack());
		properties[SOUL_DEFENCE].setStateData(stateData.getSoulDefence());
		properties[ACCURATE].setStateData(stateData.getAccurate());
		properties[DODGE].setStateData(stateData.getDodge());
		properties[CRIT].setStateData(stateData.getCrit());
		properties[CRIT_DEFENCE].setStateData(stateData.getCritDefence());
		properties[CRIT_ADDTION].setStateData(stateData.getCritAddtion());
		properties[CRIT_CUT].setStateData(stateData.getCritCut());
		properties[BLOOD_ATTACK_ADDTION].setStateData(stateData.getAttackAddtion());
		properties[BLOOD_ATTACK_CUT].setStateData(stateData.getAttackCut());
		properties[SOUL_ATTACK_ADDTION].setStateData(stateData.getSoulAttackAddtion());
		properties[SOUL_ATTACK_CUT].setStateData(stateData.getAttackCut());
		properties[REGAIN_SOUL].setStateData(stateData.getRegainSoul());
		properties[REGAIN_BLOOD].setStateData(stateData.getRegainBlood());
		properties[METAL].setStateData(stateData.getMetal());
		properties[WOOD].setStateData(stateData.getWood());
		properties[WATER].setStateData(stateData.getWater());
		properties[FIRE].setStateData(stateData.getFire());
		properties[EARTH].setStateData(stateData.getEarth());
		properties[METAL_DEFENCE].setStateData(stateData.getMetalDefence());
		properties[WOOD_DEFENCE].setStateData(stateData.getWoodDefence());
		properties[WATER_DEFENCE].setStateData(stateData.getWaterDefence());
		properties[FIRE_DEFENCE].setStateData(stateData.getFireDefence());
		properties[EARTH_DEFENCE].setStateData(stateData.getEarthDefence());

		properties[SOUL].setStatePer(statePer.getSoul());
		properties[BLOOD].setStatePer(statePer.getBlood());
		properties[ATTACK].setStatePer(statePer.getAttack());
		properties[DEFENCE].setStatePer(statePer.getDefence());
		properties[SOUL_ATTACK].setStatePer(statePer.getSoulAttack());
		properties[SOUL_DEFENCE].setStatePer(statePer.getSoulDefence());
		properties[ACCURATE].setStatePer(statePer.getAccurate());
		properties[DODGE].setStatePer(statePer.getDodge());
		properties[CRIT].setStatePer(statePer.getCrit());
		properties[CRIT_DEFENCE].setStatePer(statePer.getCritDefence());
		properties[CRIT_ADDTION].setStatePer(statePer.getCritAddtion());
		properties[CRIT_CUT].setStatePer(statePer.getCritCut());
		properties[BLOOD_ATTACK_ADDTION].setStatePer(statePer.getAttackAddtion());
		properties[BLOOD_ATTACK_CUT].setStatePer(statePer.getAttackCut());
		properties[SOUL_ATTACK_ADDTION].setStatePer(statePer.getSoulAttackAddtion());
		properties[SOUL_ATTACK_CUT].setStatePer(statePer.getAttackCut());
		properties[REGAIN_SOUL].setStatePer(statePer.getRegainSoul());
		properties[REGAIN_BLOOD].setStatePer(statePer.getRegainBlood());
		properties[METAL].setStatePer(statePer.getMetal());
		properties[WOOD].setStatePer(statePer.getWood());
		properties[WATER].setStatePer(statePer.getWater());
		properties[FIRE].setStatePer(statePer.getFire());
		properties[EARTH].setStatePer(statePer.getEarth());
		properties[METAL_DEFENCE].setStatePer(statePer.getMetalDefence());
		properties[WOOD_DEFENCE].setStatePer(statePer.getWoodDefence());
		properties[WATER_DEFENCE].setStatePer(statePer.getWaterDefence());
		properties[FIRE_DEFENCE].setStatePer(statePer.getFireDefence());
		properties[EARTH_DEFENCE].setStatePer(statePer.getEarthDefence());
		properties[SPEED].setStatePer(statePer.getSpeed());
	}

	/**
	 * 添加神器属性
	 * 
	 * @param artifactData
	 * @param artifactPer
	 */
	public void addArtifact(BaseProperty artifactData, BaseProperty artifactPer) {
		// 添加神器属性
		properties[SOUL].setArtifactData(artifactData.getSoul());
		properties[BLOOD].setArtifactData(artifactData.getBlood());
		properties[ATTACK].setArtifactData(artifactData.getAttack());
		properties[DEFENCE].setArtifactData(artifactData.getDefence());
		properties[SOUL_ATTACK].setArtifactData(artifactData.getSoulAttack());
		properties[SOUL_DEFENCE].setArtifactData(artifactData.getSoulDefence());
		properties[ACCURATE].setArtifactData(artifactData.getAccurate());
		properties[DODGE].setArtifactData(artifactData.getDodge());
		properties[CRIT].setArtifactData(artifactData.getCrit());
		properties[CRIT_DEFENCE].setArtifactData(artifactData.getCritDefence());
		properties[CRIT_ADDTION].setArtifactData(artifactData.getCritAddtion());
		properties[CRIT_CUT].setArtifactData(artifactData.getCritCut());
		properties[BLOOD_ATTACK_ADDTION].setArtifactData(artifactData.getAttackAddtion());
		properties[BLOOD_ATTACK_CUT].setArtifactData(artifactData.getAttackCut());
		properties[SOUL_ATTACK_ADDTION].setArtifactData(artifactData.getSoulAttackAddtion());
		properties[SOUL_ATTACK_CUT].setArtifactData(artifactData.getAttackCut());
		properties[REGAIN_SOUL].setArtifactData(artifactData.getRegainSoul());
		properties[REGAIN_BLOOD].setArtifactData(artifactData.getRegainBlood());
		properties[METAL].setArtifactData(artifactData.getMetal());
		properties[WOOD].setArtifactData(artifactData.getWood());
		properties[WATER].setArtifactData(artifactData.getWater());
		properties[FIRE].setArtifactData(artifactData.getFire());
		properties[EARTH].setArtifactData(artifactData.getEarth());
		properties[METAL_DEFENCE].setArtifactData(artifactData.getMetalDefence());
		properties[WOOD_DEFENCE].setArtifactData(artifactData.getWoodDefence());
		properties[WATER_DEFENCE].setArtifactData(artifactData.getWaterDefence());
		properties[FIRE_DEFENCE].setArtifactData(artifactData.getFireDefence());
		properties[EARTH_DEFENCE].setArtifactData(artifactData.getEarthDefence());

		// 神器百分比加成
		properties[SOUL].setArtifactPer(artifactPer.getSoul());
		properties[BLOOD].setArtifactPer(artifactPer.getBlood());
		properties[ATTACK].setArtifactPer(artifactPer.getAttack());
		properties[DEFENCE].setArtifactPer(artifactPer.getDefence());
		properties[SOUL_ATTACK].setArtifactPer(artifactPer.getSoulAttack());
		properties[SOUL_DEFENCE].setArtifactPer(artifactPer.getSoulDefence());
		properties[ACCURATE].setArtifactPer(artifactPer.getAccurate());
		properties[DODGE].setArtifactPer(artifactPer.getDodge());
		properties[CRIT].setArtifactPer(artifactPer.getCrit());
		properties[CRIT_DEFENCE].setArtifactPer(artifactPer.getCritDefence());
		properties[CRIT_ADDTION].setArtifactPer(artifactPer.getCritAddtion());
		properties[CRIT_CUT].setArtifactPer(artifactPer.getCritCut());
		properties[BLOOD_ATTACK_ADDTION].setArtifactPer(artifactPer.getAttackAddtion());
		properties[BLOOD_ATTACK_CUT].setArtifactPer(artifactPer.getAttackCut());
		properties[SOUL_ATTACK_ADDTION].setArtifactPer(artifactPer.getSoulAttackAddtion());
		properties[SOUL_ATTACK_CUT].setArtifactPer(artifactPer.getAttackCut());
		properties[REGAIN_SOUL].setArtifactPer(artifactPer.getRegainSoul());
		properties[REGAIN_BLOOD].setArtifactPer(artifactPer.getRegainBlood());
		properties[METAL].setArtifactPer(artifactPer.getMetal());
		properties[WOOD].setArtifactPer(artifactPer.getWood());
		properties[WATER].setArtifactPer(artifactPer.getWater());
		properties[FIRE].setArtifactPer(artifactPer.getFire());
		properties[EARTH].setArtifactPer(artifactPer.getEarth());
		properties[METAL_DEFENCE].setArtifactPer(artifactPer.getMetalDefence());
		properties[WOOD_DEFENCE].setArtifactPer(artifactPer.getWoodDefence());
		properties[WATER_DEFENCE].setArtifactPer(artifactPer.getWaterDefence());
		properties[FIRE_DEFENCE].setArtifactPer(artifactPer.getFireDefence());
		properties[EARTH_DEFENCE].setArtifactPer(artifactPer.getEarthDefence());
		properties[SPEED].setArtifactPer(artifactPer.getSpeed());
	}
	
	public void addGuildSkill(BaseProperty guildSkillData, BaseProperty guildSkillPer){
		// 添加帮派技能属性
		properties[SOUL].setGuildSkillData(guildSkillData.getSoul());
		properties[BLOOD].setGuildSkillData(guildSkillData.getBlood());
		properties[ATTACK].setGuildSkillData(guildSkillData.getAttack());
		properties[DEFENCE].setGuildSkillData(guildSkillData.getDefence());
		properties[SOUL_ATTACK].setGuildSkillData(guildSkillData.getSoulAttack());
		properties[SOUL_DEFENCE].setGuildSkillData(guildSkillData.getSoulDefence());
		properties[ACCURATE].setGuildSkillData(guildSkillData.getAccurate());
		properties[DODGE].setGuildSkillData(guildSkillData.getDodge());
		properties[CRIT].setGuildSkillData(guildSkillData.getCrit());
		properties[CRIT_DEFENCE].setGuildSkillData(guildSkillData.getCritDefence());
		properties[CRIT_ADDTION].setGuildSkillData(guildSkillData.getCritAddtion());
		properties[CRIT_CUT].setGuildSkillData(guildSkillData.getCritCut());
		properties[BLOOD_ATTACK_ADDTION].setGuildSkillData(guildSkillData.getAttackAddtion());
		properties[BLOOD_ATTACK_CUT].setGuildSkillData(guildSkillData.getAttackCut());
		properties[SOUL_ATTACK_ADDTION].setGuildSkillData(guildSkillData.getSoulAttackAddtion());
		properties[SOUL_ATTACK_CUT].setGuildSkillData(guildSkillData.getAttackCut());
		properties[REGAIN_SOUL].setGuildSkillData(guildSkillData.getRegainSoul());
		properties[REGAIN_BLOOD].setGuildSkillData(guildSkillData.getRegainBlood());
		properties[METAL].setGuildSkillData(guildSkillData.getMetal());
		properties[WOOD].setGuildSkillData(guildSkillData.getWood());
		properties[WATER].setGuildSkillData(guildSkillData.getWater());
		properties[FIRE].setGuildSkillData(guildSkillData.getFire());
		properties[EARTH].setGuildSkillData(guildSkillData.getEarth());
		properties[METAL_DEFENCE].setGuildSkillData(guildSkillData.getMetalDefence());
		properties[WOOD_DEFENCE].setGuildSkillData(guildSkillData.getWoodDefence());
		properties[WATER_DEFENCE].setGuildSkillData(guildSkillData.getWaterDefence());
		properties[FIRE_DEFENCE].setGuildSkillData(guildSkillData.getFireDefence());
		properties[EARTH_DEFENCE].setGuildSkillData(guildSkillData.getEarthDefence());

		// 帮派技能百分比加成
		properties[SOUL].setGuildSkillPer(guildSkillPer.getSoul());
		properties[BLOOD].setGuildSkillPer(guildSkillPer.getBlood());
		properties[ATTACK].setGuildSkillPer(guildSkillPer.getAttack());
		properties[DEFENCE].setGuildSkillPer(guildSkillPer.getDefence());
		properties[SOUL_ATTACK].setGuildSkillPer(guildSkillPer.getSoulAttack());
		properties[SOUL_DEFENCE].setGuildSkillPer(guildSkillPer.getSoulDefence());
		properties[ACCURATE].setGuildSkillPer(guildSkillPer.getAccurate());
		properties[DODGE].setGuildSkillPer(guildSkillPer.getDodge());
		properties[CRIT].setGuildSkillPer(guildSkillPer.getCrit());
		properties[CRIT_DEFENCE].setGuildSkillPer(guildSkillPer.getCritDefence());
		properties[CRIT_ADDTION].setGuildSkillPer(guildSkillPer.getCritAddtion());
		properties[CRIT_CUT].setGuildSkillPer(guildSkillPer.getCritCut());
		properties[BLOOD_ATTACK_ADDTION].setGuildSkillPer(guildSkillPer.getAttackAddtion());
		properties[BLOOD_ATTACK_CUT].setGuildSkillPer(guildSkillPer.getAttackCut());
		properties[SOUL_ATTACK_ADDTION].setGuildSkillPer(guildSkillPer.getSoulAttackAddtion());
		properties[SOUL_ATTACK_CUT].setGuildSkillPer(guildSkillPer.getAttackCut());
		properties[REGAIN_SOUL].setGuildSkillPer(guildSkillPer.getRegainSoul());
		properties[REGAIN_BLOOD].setGuildSkillPer(guildSkillPer.getRegainBlood());
		properties[METAL].setGuildSkillPer(guildSkillPer.getMetal());
		properties[WOOD].setGuildSkillPer(guildSkillPer.getWood());
		properties[WATER].setGuildSkillPer(guildSkillPer.getWater());
		properties[FIRE].setGuildSkillPer(guildSkillPer.getFire());
		properties[EARTH].setGuildSkillPer(guildSkillPer.getEarth());
		properties[METAL_DEFENCE].setGuildSkillPer(guildSkillPer.getMetalDefence());
		properties[WOOD_DEFENCE].setGuildSkillPer(guildSkillPer.getWoodDefence());
		properties[WATER_DEFENCE].setGuildSkillPer(guildSkillPer.getWaterDefence());
		properties[FIRE_DEFENCE].setGuildSkillPer(guildSkillPer.getFireDefence());
		properties[EARTH_DEFENCE].setGuildSkillPer(guildSkillPer.getEarthDefence());
		properties[SPEED].setGuildSkillPer(guildSkillPer.getSpeed());
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
		refresh(player);// 刷新
		player.getBasePlayer().getPlayerInfo().setFight(fighting);

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
				updatePlayer(i, property, joinInfo);
				PropertyMsg.Builder proMsg = PropertyMsg.newBuilder();
				proMsg.setType(i + 1);
				property.writeProto(proMsg);
				msg.addAtt(proMsg);
			} else {
				Log.error("------丢失属性-----丢失属性------丢失属性-------丢失属性---", new Exception("丢失属性"));
			}
		}
		/*----------------------------人物战斗力--------------------------*/
		refresh(player);// 刷新
		player.getBasePlayer().getPlayerInfo().setFight(fighting);

		PropertyMsg.Builder proMsg = PropertyMsg.newBuilder();
		proMsg.setType(EnumAttr.FightValue.getValue());
		proMsg.setTotalPoint(fighting);
		msg.addAtt(proMsg);
	}

	public void writeProto(PropertyListMsg.Builder propertyMsgs) {
		for (int index = 0; index < properties.length; index++) {
			if (index == SPEED) {
				continue;
			}
			Property property = properties[index];
			if (property != null) {
				PropertyMsg.Builder proMsg = PropertyMsg.newBuilder();
				proMsg.setType(index + 1);
				property.writeProto(proMsg);
				propertyMsgs.addPropertys(proMsg);
			} else {
				Log.error("------丢失属性-----丢失属性------丢失属性-------丢失属性---" + index, new Exception("丢失属性"));
			}
		}
	}

	public int getFighting() {
		return fighting;
	}

	public void refresh(GamePlayer player) {
		fighting = 0;
		long equipFighting = 0;
		long magicwpFighting = 0;
		long mountFighting = 0;
		long petFighting = 0;
		long soulFighting = 0;
		long stateFighting = 0;
		long artifactFighting = 0;
		for (Property p : properties) {
			fighting += p.getTotalJoin() * PropertyFightingTemplateMgr.getFighting(p.getType()) / 100;
			equipFighting += p.getBagTotal() * PropertyFightingTemplateMgr.getFighting(p.getType()) / 100;
			magicwpFighting += p.getMagicwpTotal() * PropertyFightingTemplateMgr.getFighting(p.getType()) / 100;
			mountFighting += p.getMountTotal() * PropertyFightingTemplateMgr.getFighting(p.getType()) / 100;
			petFighting += p.getPetTotal() * PropertyFightingTemplateMgr.getFighting(p.getType()) / 100;
			soulFighting += p.getSoulTotal() * PropertyFightingTemplateMgr.getFighting(p.getType()) / 100;
			stateFighting += p.getStateTotal() * PropertyFightingTemplateMgr.getFighting(p.getType()) / 100;
			artifactFighting += p.getArtifaceTotal() * PropertyFightingTemplateMgr.getFighting(p.getType()) / 100;
		}
		RankTempInfo info = RankServerManager.getInstance().getRankTempInfo(player.getPlayerId());
		if (info != null) {
			if(info.getPet()!=petFighting){
				info.setPet(petFighting);
				player.notifyListeners(new ObjectEvent(this, null, EventNameType.PET_FIGHT));
			}
			if(info.getEquip()!=equipFighting){				
				info.setEquip(equipFighting);	
				player.notifyListeners(new ObjectEvent(this, null, EventNameType.EQUIP));
			}
			if(info.getMagicwp()!=magicwpFighting){				
				info.setMagicwp(magicwpFighting);
				player.notifyListeners(new ObjectEvent(this, null, EventNameType.MAGICWP_FIGHT));
			}
			info.setMount(mountFighting);
			
			if(info.getSoul()!=soulFighting){				
				info.setSoul(soulFighting);
				player.notifyListeners(new ObjectEvent(this, null, EventNameType.SOUL_FIGHT));
			}
			if(info.getArtifact()!=artifactFighting){
				info.setArtifact(artifactFighting);
				player.notifyListeners(new ArtifactStateEvent(this,7,0,0,EventNameType.ARTIFACT));
			}
			info.setState(stateFighting);
			info.setOp(Option.Update);
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
				joinInfo.setAttackAddtion(property.getTotalJoin());
				break;
			case BLOOD_ATTACK_CUT:
				joinInfo.setAttackCut(property.getTotalJoin());
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

	public void addAvatar(BaseProperty avatarData, BaseProperty avatarPer) {
		// 添加分身数据
		properties[SOUL].setAvatarData(avatarData.getSoul());
		properties[BLOOD].setAvatarData(avatarData.getBlood());
		properties[ATTACK].setAvatarData(avatarData.getAttack());
		properties[DEFENCE].setAvatarData(avatarData.getDefence());
		properties[SOUL_ATTACK].setAvatarData(avatarData.getSoulAttack());
		properties[SOUL_DEFENCE].setAvatarData(avatarData.getSoulDefence());
		properties[ACCURATE].setAvatarData(avatarData.getAccurate());
		properties[DODGE].setAvatarData(avatarData.getDodge());
		properties[CRIT].setAvatarData(avatarData.getCrit());
		properties[CRIT_DEFENCE].setAvatarData(avatarData.getCritDefence());
		properties[CRIT_ADDTION].setAvatarData(avatarData.getCritAddtion());
		properties[CRIT_CUT].setAvatarData(avatarData.getCritCut());
		properties[BLOOD_ATTACK_ADDTION].setAvatarData(avatarData.getAttackAddtion());
		properties[BLOOD_ATTACK_CUT].setAvatarData(avatarData.getAttackCut());
		properties[SOUL_ATTACK_ADDTION].setAvatarData(avatarData.getSoulAttackAddtion());
		properties[SOUL_ATTACK_CUT].setAvatarData(avatarData.getAttackCut());
		properties[REGAIN_SOUL].setAvatarData(avatarData.getRegainSoul());
		properties[REGAIN_BLOOD].setAvatarData(avatarData.getRegainBlood());
		properties[METAL].setAvatarData(avatarData.getMetal());
		properties[WOOD].setAvatarData(avatarData.getWood());
		properties[WATER].setAvatarData(avatarData.getWater());
		properties[FIRE].setAvatarData(avatarData.getFire());
		properties[EARTH].setAvatarData(avatarData.getEarth());
		properties[METAL_DEFENCE].setAvatarData(avatarData.getMetalDefence());
		properties[WOOD_DEFENCE].setAvatarData(avatarData.getWoodDefence());
		properties[WATER_DEFENCE].setAvatarData(avatarData.getWaterDefence());
		properties[FIRE_DEFENCE].setAvatarData(avatarData.getFireDefence());
		properties[EARTH_DEFENCE].setAvatarData(avatarData.getEarthDefence());
		properties[SPEED].setAvatarData(avatarData.getSpeed());

		// 添加背包百分比加成
		properties[SOUL].setAvatarPer(avatarPer.getSoul());
		properties[BLOOD].setAvatarPer(avatarPer.getBlood());
		properties[ATTACK].setAvatarPer(avatarPer.getAttack());
		properties[DEFENCE].setAvatarPer(avatarPer.getDefence());
		properties[SOUL_ATTACK].setAvatarPer(avatarPer.getSoulAttack());
		properties[SOUL_DEFENCE].setAvatarPer(avatarPer.getSoulDefence());
		properties[ACCURATE].setAvatarPer(avatarPer.getAccurate());
		properties[DODGE].setAvatarPer(avatarPer.getDodge());
		properties[CRIT].setAvatarPer(avatarPer.getCrit());
		properties[CRIT_DEFENCE].setAvatarPer(avatarPer.getCritDefence());
		properties[CRIT_ADDTION].setAvatarPer(avatarPer.getCritAddtion());
		properties[CRIT_CUT].setAvatarPer(avatarPer.getCritCut());
		properties[BLOOD_ATTACK_ADDTION].setAvatarPer(avatarPer.getAttackAddtion());
		properties[BLOOD_ATTACK_CUT].setAvatarPer(avatarPer.getAttackCut());
		properties[SOUL_ATTACK_ADDTION].setAvatarPer(avatarPer.getSoulAttackAddtion());
		properties[SOUL_ATTACK_CUT].setAvatarPer(avatarPer.getAttackCut());
		properties[REGAIN_SOUL].setAvatarPer(avatarPer.getRegainSoul());
		properties[REGAIN_BLOOD].setAvatarPer(avatarPer.getRegainBlood());
		properties[METAL].setAvatarPer(avatarPer.getMetal());
		properties[WOOD].setAvatarPer(avatarPer.getWood());
		properties[WATER].setAvatarPer(avatarPer.getWater());
		properties[FIRE].setAvatarPer(avatarPer.getFire());
		properties[EARTH].setAvatarPer(avatarPer.getEarth());
		properties[METAL_DEFENCE].setAvatarPer(avatarPer.getMetalDefence());
		properties[WOOD_DEFENCE].setAvatarPer(avatarPer.getWoodDefence());
		properties[WATER_DEFENCE].setAvatarPer(avatarPer.getWaterDefence());
		properties[FIRE_DEFENCE].setAvatarPer(avatarPer.getFireDefence());
		properties[EARTH_DEFENCE].setAvatarPer(avatarPer.getEarthDefence());
		properties[SPEED].setAvatarPer(avatarPer.getSpeed());
	}

}
