package com.chuangyou.xianni.entity.role;

import com.chuangyou.xianni.entity.DataObject;

/**
 * 角色创建配置
 *
 */
public class RoleConfig extends DataObject {

	private int id;
	private int rType;// 类型
	private String desc;// 描述
	private int modelId;// 模型信息
	private int showModelId;// 创建角色使用的id
	private double moveSpeed;// 移动速度 1. 主角的移动速度由服务端提供
	private String skill;// 初始技能
	private int curSoul;// 当前血量
	private int curBlood;// 当前魔法

	private int soul; // 元魂
	private int blood; // 气血
	private int attack; // 攻击
	private int defence; // 防御
	private int soulAttack; // 魂攻
	private int soulDefence; // 魂防
	private int accurate; // 命中
	private int dodge; // 闪避
	private int crit; // 暴击
	private int critDefence; // 抗暴
	private int critAddtion; // 暴击伤害
	private int critCut; // 抗暴减伤
	private int attackAddtion; // 气血伤害增加
	private int attackCut; // 气血伤害减免
	private int soulAttackAddtion; // 元魂伤害增加
	private int soulAttackCut; // 元魂伤害减免
	private int regainSoul; // 每10秒回魂
	private int regainBlood; // 每10秒回血
	private int metal; // 金
	private int wood; // 木
	private int water; // 水
	private int fire; // 火
	private int earth; // 土
	private int metalDefence; // 金抗
	private int woodDefence; // 木抗
	private int waterDefence; // 水抗
	private int fireDefence; // 火抗
	private int earthDefence; // 土抗
	private int speed; // 速度

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getrType() {
		return rType;
	}

	public void setrType(int rType) {
		this.rType = rType;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getModelId() {
		return modelId;
	}

	public void setModelId(int modelId) {
		this.modelId = modelId;
	}

	public int getShowModelId() {
		return showModelId;
	}

	public void setShowModelId(int showModelId) {
		this.showModelId = showModelId;
	}

	public double getMoveSpeed() {
		return moveSpeed;
	}

	public void setMoveSpeed(double moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public int getCurSoul() {
		return curSoul;
	}

	public void setCurSoul(int curSoul) {
		this.curSoul = curSoul;
	}

	public int getCurBlood() {
		return curBlood;
	}

	public void setCurBlood(int curBlood) {
		this.curBlood = curBlood;
	}

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

	public int getAttackAddtion() {
		return attackAddtion;
	}

	public void setAttackAddtion(int attackAddtion) {
		this.attackAddtion = attackAddtion;
	}

	public int getAttackCut() {
		return attackCut;
	}

	public void setAttackCut(int attackCut) {
		this.attackCut = attackCut;
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
