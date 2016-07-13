package com.chuangyou.xianni.entity.property;

import com.chuangyou.xianni.entity.DataObject;

/**
 * 基础技能属性对象
 */
public class SkillBaseProperty extends DataObject {
	private int soul = 0;						// 元魂
	private int blood = 0;						// 气血
	private int attack = 0;						// 攻击
	private int defence = 0;					// 防御
	private int soulAttack = 0;					// 魂攻
	private int soulDefence = 0;				// 魂防
	private int accurate = 0;					// 命中
	private int dodge = 0;						// 闪避
	private int crit = 0;						// 暴击
	private int critDefence = 0;				// 抗暴
	private int critAddtion;				// 暴击伤害
	private int critCut;					// 抗暴减伤
	private int bloodAttackAddtion;			// 气血伤害增加
	private int bloodAttackCut;				// 气血伤害减免
	private int soulAttackAddtion;			// 元魂伤害增加
	private int soulAttackCut;				// 元魂伤害减免
	private int regainSoul;					// 每10秒回魂
	private int regainBlood;				// 每10秒回血
	private int metal;						// 金
	private int wood;						// 木
	private int water;						// 水
	private int fire;						// 火
	private int earth;						// 土
	private int metalDefence;				// 金抗
	private int woodDefence;				// 木抗
	private int waterDefence;				// 水抗
	private int fireDefence;				// 火抗
	private int earthDefence;				// 土抗
	private int speed;						// 移动速度

	public int getSoul() {
		return soul;
	}

	public void setSoul(int soul) {
		this.soul = soul;
	}

	public void addSoul(int soul) {
		this.soul += soul;
	}

	public int getBlood() {
		return blood;
	}

	public void setBlood(int blood) {
		this.blood = blood;
	}

	public void addBlood(int blood) {
		this.blood += blood;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public void addAttack(int attack) {
		this.attack += attack;
	}

	public int getDefence() {
		return defence;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}

	public void addDefence(int defence) {
		this.defence += defence;
	}

	public int getSoulAttack() {
		return soulAttack;
	}

	public void setSoulAttack(int soulAttack) {
		this.soulAttack = soulAttack;
	}

	public void addSoulAttack(int soulAttack) {
		this.soulAttack += soulAttack;
	}

	public int getSoulDefence() {
		return soulDefence;
	}

	public void setSoulDefence(int soulDefence) {
		this.soulDefence = soulDefence;
	}

	public void addSoulDefence(int soulDefence) {
		this.soulDefence += soulDefence;
	}

	public int getAccurate() {
		return accurate;
	}

	public void setAccurate(int accurate) {
		this.accurate = accurate;
	}

	public void addAccurate(int accurate) {
		this.accurate += accurate;
	}

	public int getDodge() {
		return dodge;
	}

	public void setDodge(int dodge) {
		this.dodge = dodge;
	}

	public void addDodge(int dodge) {
		this.dodge += dodge;
	}

	public int getCrit() {
		return crit;
	}

	public void setCrit(int crit) {
		this.crit = crit;
	}

	public void addCrit(int crit) {
		this.crit += crit;
	}

	public int getCritDefence() {
		return critDefence;
	}

	public void setCritDefence(int critDefence) {
		this.critDefence = critDefence;
	}

	public void addCritDefence(int critDefence) {
		this.critDefence += critDefence;
	}
	//////////////

	public int getCritAddtion() {
		return critAddtion;
	}

	public void setCritAddtion(int critAddtion) {
		this.critAddtion = critAddtion;
	}

	public void addCritAddtion(int critAddtion) {
		this.critAddtion += critAddtion;
	}

	public int getCritCut() {
		return critCut;
	}

	public void setCritCut(int critCut) {
		this.critCut = critCut;
	}

	public void addCritCut(int critCut) {
		this.critCut += critCut;
	}

	public int getBloodAttackAddtion() {
		return bloodAttackAddtion;
	}

	public void setBloodAttackAddtion(int bloodAttackAddtion) {
		this.bloodAttackAddtion = bloodAttackAddtion;
	}

	public void addBloodAttackAddtion(int bloodAttackAddtion) {
		this.bloodAttackAddtion += bloodAttackAddtion;
	}

	public int getBloodAttackCut() {
		return bloodAttackCut;
	}

	public void setBloodAttackCut(int bloodAttackCut) {
		this.bloodAttackCut = bloodAttackCut;
	}

	public void addBloodAttackCut(int bloodAttackCut) {
		this.bloodAttackCut += bloodAttackCut;
	}

	public int getSoulAttackAddtion() {
		return soulAttackAddtion;
	}

	public void setSoulAttackAddtion(int soulAttackAddtion) {
		this.soulAttackAddtion = soulAttackAddtion;
	}

	public void addSoulAttackAddtion(int soulAttackAddtion) {
		this.soulAttackAddtion += soulAttackAddtion;
	}

	public int getSoulAttackCut() {
		return soulAttackCut;
	}

	public void setSoulAttackCut(int soulAttackCut) {
		this.soulAttackCut = soulAttackCut;
	}

	public void addSoulAttackCut(int soulAttackCut) {
		this.soulAttackCut += soulAttackCut;
	}

	public int getRegainSoul() {
		return regainSoul;
	}

	public void setRegainSoul(int regainSoul) {
		this.regainSoul = regainSoul;
	}

	public void addRegainSoul(int regainSoul) {
		this.regainSoul += regainSoul;
	}

	public int getRegainBlood() {
		return regainBlood;
	}

	public void setRegainBlood(int regainBlood) {
		this.regainBlood = regainBlood;
	}

	public void addRegainBlood(int regainBlood) {
		this.regainBlood += regainBlood;
	}

	public int getMetal() {
		return metal;
	}

	public void setMetal(int metal) {
		this.metal = metal;
	}

	public void addMetal(int metal) {
		this.metal += metal;
	}

	public int getWood() {
		return wood;
	}

	public void setWood(int wood) {
		this.wood = wood;
	}

	public void addWood(int wood) {
		this.wood += wood;
	}

	public int getWater() {
		return water;
	}

	public void setWater(int water) {
		this.water = water;
	}

	public void addWater(int water) {
		this.water += water;
	}

	public int getFire() {
		return fire;
	}

	public void setFire(int fire) {
		this.fire = fire;
	}

	public void addFire(int fire) {
		this.fire += fire;
	}

	public int getEarth() {
		return earth;
	}

	public void setEarth(int earth) {
		this.earth = earth;
	}

	public void addEarth(int earth) {
		this.earth += earth;
	}

	public int getMetalDefence() {
		return metalDefence;
	}

	public void setMetalDefence(int metalDefence) {
		this.metalDefence = metalDefence;
	}

	public void addMetalDefence(int metalDefence) {
		this.metalDefence += metalDefence;
	}

	public int getWoodDefence() {
		return woodDefence;
	}

	public void setWoodDefence(int woodDefence) {
		this.woodDefence = woodDefence;
	}

	public void addWoodDefence(int woodDefence) {
		this.woodDefence += woodDefence;
	}

	public int getWaterDefence() {
		return waterDefence;
	}

	public void setWaterDefence(int waterDefence) {
		this.waterDefence = waterDefence;
	}

	public void addWaterDefence(int waterDefence) {
		this.waterDefence += waterDefence;
	}

	public int getFireDefence() {
		return fireDefence;
	}

	public void setFireDefence(int fireDefence) {
		this.fireDefence = fireDefence;
	}

	public void addFireDefence(int fireDefence) {
		this.fireDefence += fireDefence;
	}

	public int getEarthDefence() {
		return earthDefence;
	}

	public void setEarthDefence(int earthDefence) {
		this.earthDefence = earthDefence;
	}

	public void addEarthDefence(int earthDefence) {
		this.earthDefence += earthDefence;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void addSpeed(int speed) {
		this.speed += speed;
	}
}
