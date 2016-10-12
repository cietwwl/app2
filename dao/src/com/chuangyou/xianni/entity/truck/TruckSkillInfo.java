package com.chuangyou.xianni.entity.truck;

import com.chuangyou.xianni.entity.DataObject;

public class TruckSkillInfo extends DataObject {
	/** 镖车技能 */
	public static final int SKILL_TRUCK = 1;
	/** 镖师技能 */
	public static final int SKILL_TRUCKER = 2;
	/** 镖师天赋 */
	public static final int SKILL_GIFT = 3;
	
	
	/** 从属者Id */
	private long ownerId;
	/** 镖车类型 */
	private int trucktype;
	/** 等级 */
	private int level;
	/** 镖车类型 */
	private int type;
	/** 技能大Id */
	private int skillType;
	/** 技能Id */
	private int skillId;
	
	public long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getSkillType() {
		return skillType;
	}
	public void setSkillType(int skillType) {
		this.skillType = skillType;
	}
	public int getSkillId() {
		return skillId;
	}
	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}
	public int getTrucktype() {
		return trucktype;
	}
	public void setTrucktype(int trucktype) {
		this.trucktype = trucktype;
	}
}
