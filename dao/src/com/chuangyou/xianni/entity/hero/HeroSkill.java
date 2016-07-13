package com.chuangyou.xianni.entity.hero;

import com.chuangyou.xianni.entity.DataObject;

/**
 * 英雄技能 entity
 * 
 * @author Administrator
 *
 */
public class HeroSkill extends DataObject {
	private long playerId;// 玩家id
	// private int heroId;//英雄id
	private int skillId;// 技能id
	private int type;// 技能主类型
	private int subType;// 技能子类型
	private int grandsonType;// 三级技能
	private int skillLV;// 技能等級

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSubType() {
		return subType;
	}

	public void setSubType(int subType) {
		this.subType = subType;
	}

	public int getGrandsonType() {
		return grandsonType;
	}

	public void setGrandsonType(int grandsonType) {
		this.grandsonType = grandsonType;
	}

	public int getSkillLV() {
		return skillLV;
	}

	public void setSkillLV(int skillLV) {
		this.skillLV = skillLV;
	}

}
