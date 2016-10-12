package com.chuangyou.xianni.entity.truck;

public class TruckLevelConfig {

	/** 个人 */
	public static final int PERSONAL = 1;
	/** 镖师 */
	public static final int TRUCKER = 2;
	/** 帮派 */
	public static final int GUILD = 3;
	
	private int id;
	private int type;
	private int level;
	private int points;
	private int modelId;
	private int skillId;
	private int needExp;
	
	public int getNeedExp() {
		return needExp;
	}
	public void setNeedExp(int needExp) {
		this.needExp = needExp;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getModelId() {
		return modelId;
	}
	public void setModelId(int modelId) {
		this.modelId = modelId;
	}
	public int getSkillId() {
		return skillId;
	}
	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}
	
}
