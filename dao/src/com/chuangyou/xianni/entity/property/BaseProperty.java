package com.chuangyou.xianni.entity.property;

import com.chuangyou.xianni.entity.DataObject;

/**
 * 基础属性对象
 */
public class BaseProperty extends DataObject {
	// public static final int SOUL = 1; // 元魂
	// public static final int BLOOD = 2; // 气血
	// public static final int ATTACK = 3; // 攻击
	// public static final int DEFENCE = 4; // 防御
	// public static final int SOUL_ATTACK = 5; // 魂攻
	// public static final int SOUL_DEFENCE = 6; // 魂防
	// public static final int ACCURATE = 7; // 命中
	// public static final int DODGE = 8; // 闪避
	// public static final int CRIT = 9; // 暴击
	// public static final int CRIT_DEFENCE = 10; // 抗暴
	// public static final int CRIT_ADDTION = 11; // 暴击伤害
	// public static final int CRIT_CUT = 12; // 抗暴减伤
	// public static final int ATTACK_ADDTION = 13; // 气血伤害增加
	// public static final int ATTACK_CUT = 14; // 气血伤害减免
	// public static final int SOUL_ATTACK_ADDTION = 15; // 元魂伤害增加
	// public static final int SOUL_ATTACK_CUT = 16; // 元魂伤害减免
	// public static final int REGAIN_SOUL = 17; // 每10秒回魂
	// public static final int REGAIN_BLOOD = 18; // 每10秒回血
	// public static final int METAL = 19; // 金
	// public static final int WOOD = 20; // 木
	// public static final int WATER = 21; // 水
	// public static final int FIRE = 22; // 火
	// public static final int EARTH = 23; // 土
	// public static final int META_DEFENCE = 24; // 金抗
	// public static final int WOOD_DEFENCE = 25; // 木抗
	// public static final int WATER_DEFENCE = 26; // 水抗
	// public static final int FIRE_DEFENCE = 27; // 火抗
	// public static final int EARTH_DEFENCE = 28; // 土抗
	// public static final int SPEED = 29; // 移动速度

	private int				soul;						// 元魂
	private int				blood;						// 气血
	private int				attack;						// 攻击
	private int				defence;					// 防御
	private int				soulAttack;					// 魂攻
	private int				soulDefence;				// 魂防
	private int				accurate;					// 命中
	private int				dodge;						// 闪避
	private int				crit;						// 暴击
	private int				critDefence;				// 抗暴
	private int				critAddtion;				// 暴击伤害
	private int				critCut;					// 抗暴减伤
	private int				bloodAttackAddtion;			// 气血伤害增加
	private int				bloodAttackCut;				// 气血伤害减免
	private int				soulAttackAddtion;			// 元魂伤害增加
	private int				soulAttackCut;				// 元魂伤害减免
	private int				regainSoul;					// 每10秒回魂
	private int				regainBlood;				// 每10秒回血
	private int				metal;						// 金
	private int				wood;						// 木
	private int				water;						// 水
	private int				fire;						// 火
	private int				earth;						// 土
	private int				metalDefence;				// 金抗
	private int				woodDefence;				// 木抗
	private int				waterDefence;				// 水抗
	private int				fireDefence;				// 火抗
	private int				earthDefence;				// 土抗
	private int				speed;						// 移动速度

	public int getSoul() {
		return soul;
	}

	public void setSoul(int soul) {
		this.soul = soul;
	}

	public int getBlood() {
		return blood;
	}

	public void setBlood(int blood) {
		this.blood = blood;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefence() {
		return defence;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}

	public int getSoulAttack() {
		return soulAttack;
	}

	public void setSoulAttack(int soulAttack) {
		this.soulAttack = soulAttack;
	}

	public int getSoulDefence() {
		return soulDefence;
	}

	public void setSoulDefence(int soulDefence) {
		this.soulDefence = soulDefence;
	}

	public int getAccurate() {
		return accurate;
	}

	public void setAccurate(int accurate) {
		this.accurate = accurate;
	}

	public int getDodge() {
		return dodge;
	}

	public void setDodge(int dodge) {
		this.dodge = dodge;
	}

	public int getCrit() {
		return crit;
	}

	public void setCrit(int crit) {
		this.crit = crit;
	}

	public int getCritDefence() {
		return critDefence;
	}

	public void setCritDefence(int critDefence) {
		this.critDefence = critDefence;
	}

	public int getCritAddtion() {
		return critAddtion;
	}

	public void setCritAddtion(int critAddtion) {
		this.critAddtion = critAddtion;
	}

	public int getCritCut() {
		return critCut;
	}

	public void setCritCut(int critCut) {
		this.critCut = critCut;
	}

	public int getBloodAttackAddtion() {
		return bloodAttackAddtion;
	}

	public void setBloodAttackAddtion(int bloodAttackAddtion) {
		this.bloodAttackAddtion = bloodAttackAddtion;
	}

	public int getBloodAttackCut() {
		return bloodAttackCut;
	}

	public void setBloodAttackCut(int bloodAttackCut) {
		this.bloodAttackCut = bloodAttackCut;
	}

	public int getSoulAttackAddtion() {
		return soulAttackAddtion;
	}

	public void setSoulAttackAddtion(int soulAttackAddtion) {
		this.soulAttackAddtion = soulAttackAddtion;
	}

	public int getSoulAttackCut() {
		return soulAttackCut;
	}

	public void setSoulAttackCut(int soulAttackCut) {
		this.soulAttackCut = soulAttackCut;
	}

	public int getRegainSoul() {
		return regainSoul;
	}

	public void setRegainSoul(int regainSoul) {
		this.regainSoul = regainSoul;
	}

	public int getRegainBlood() {
		return regainBlood;
	}

	public void setRegainBlood(int regainBlood) {
		this.regainBlood = regainBlood;
	}

	public int getMetal() {
		return metal;
	}

	public void setMetal(int metal) {
		this.metal = metal;
	}

	public int getWood() {
		return wood;
	}

	public void setWood(int wood) {
		this.wood = wood;
	}

	public int getWater() {
		return water;
	}

	public void setWater(int water) {
		this.water = water;
	}

	public int getFire() {
		return fire;
	}

	public void setFire(int fire) {
		this.fire = fire;
	}

	public int getEarth() {
		return earth;
	}

	public void setEarth(int earth) {
		this.earth = earth;
	}

	public int getMetalDefence() {
		return metalDefence;
	}

	public void setMetalDefence(int metalDefence) {
		this.metalDefence = metalDefence;
	}

	public int getWoodDefence() {
		return woodDefence;
	}

	public void setWoodDefence(int woodDefence) {
		this.woodDefence = woodDefence;
	}

	public int getWaterDefence() {
		return waterDefence;
	}

	public void setWaterDefence(int waterDefence) {
		this.waterDefence = waterDefence;
	}

	public int getFireDefence() {
		return fireDefence;
	}

	public void setFireDefence(int fireDefence) {
		this.fireDefence = fireDefence;
	}

	public int getEarthDefence() {
		return earthDefence;
	}

	public void setEarthDefence(int earthDefence) {
		this.earthDefence = earthDefence;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
