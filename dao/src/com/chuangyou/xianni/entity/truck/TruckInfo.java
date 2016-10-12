package com.chuangyou.xianni.entity.truck;

import com.chuangyou.xianni.entity.DataObject;

public class TruckInfo extends DataObject {

	/** 个人镖车 */
	public static final int PERSONAL_TRUCK = 1;
	/** 镖师 */
	public static final int TRUCKER = 3;
	/** 帮派镖车 */
	public static final int GUILD_TRUCK = 2;
	
	/** 从属者Id */
	private long ownerId;
	public long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getSkillPoint() {
		return skillPoint;
	}
	public void setSkillPoint(int skillPoint) {
		this.skillPoint = skillPoint;
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
	/** 经验 */
	private int exp;
	/** 当前技能点 */
	private int skillPoint;
	/** 等级 */
	private int level;
	/** 镖车类型 */
	private int type;
	
}
