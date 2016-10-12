package com.chuangyou.xianni.entity.truck;

public class TruckSkillConfig {
	
	/** 个人 */
	public static final int PERSONAL = 1;
	/** 镖师 */
	public static final int TRUCKER = 3;
	/** 帮派 */
	public static final int GUILD = 2;
	
	private int id;
	private int EscortType;
	private int skillType;
	private int level;
	private int nextSkillId;
	private int needPoints;
	private int valueType;
	private int value;
	private int valuePercent;
	private int consump;	//消耗
	private int buff;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEscortType() {
		return EscortType;
	}
	public void setEscortType(int escortType) {
		EscortType = escortType;
	}
	public int getSkillType() {
		return skillType;
	}
	public void setSkillType(int skillType) {
		this.skillType = skillType;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getNextSkillId() {
		return nextSkillId;
	}
	public void setNextSkillId(int nextSkillId) {
		this.nextSkillId = nextSkillId;
	}
	public int getNeedPoints() {
		return needPoints;
	}
	public void setNeedPoints(int needPoints) {
		this.needPoints = needPoints;
	}
	public int getValueType() {
		return valueType;
	}
	public void setValueType(int valueType) {
		this.valueType = valueType;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getValuePercent() {
		return valuePercent;
	}
	public void setValuePercent(int valuePercent) {
		this.valuePercent = valuePercent;
	}
	public int getConsump() {
		return consump;
	}
	public void setConsump(int consump) {
		this.consump = consump;
	}
	public int getBuff() {
		return buff;
	}
	public void setBuff(int buff) {
		this.buff = buff;
	}
}
