package com.chuangyou.xianni.entity.soul;

public class SoulFuseSkillConfig {

	private int id;
	private String name;
	/**
	 * 池ID
	 */
	private int poolId;
	/**
	 * 权重
	 */
	private int weight;
	/**
	 * 关联的BUFF
	 */
	private int buff;
	/**
	 * 效果基础触发几率
	 */
	private int chanceBase;
	/**
	 * 效果绿色品质触发几率
	 */
	private int chance1;
	/**
	 * 效果蓝色品质触发几率
	 */
	private int chance2;
	/**
	 * 效果紫色品质触发几率
	 */
	private int chance3;
	/**
	 * 效果橙色品质触发几率
	 */
	private int chance4;
	/**
	 * 魂魄数量加成比例影响buff中的参数
	 */
	private int effectParam;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPoolId() {
		return poolId;
	}
	public void setPoolId(int poolId) {
		this.poolId = poolId;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getBuff() {
		return buff;
	}
	public void setBuff(int buff) {
		this.buff = buff;
	}
	public int getChanceBase() {
		return chanceBase;
	}
	public void setChanceBase(int chanceBase) {
		this.chanceBase = chanceBase;
	}
	public int getChance1() {
		return chance1;
	}
	public void setChance1(int chance1) {
		this.chance1 = chance1;
	}
	public int getChance2() {
		return chance2;
	}
	public void setChance2(int chance2) {
		this.chance2 = chance2;
	}
	public int getChance3() {
		return chance3;
	}
	public void setChance3(int chance3) {
		this.chance3 = chance3;
	}
	public int getChance4() {
		return chance4;
	}
	public void setChance4(int chance4) {
		this.chance4 = chance4;
	}
	public int getEffectParam() {
		return effectParam;
	}
	public void setEffectParam(int effectParam) {
		this.effectParam = effectParam;
	}
	
	
	
}
