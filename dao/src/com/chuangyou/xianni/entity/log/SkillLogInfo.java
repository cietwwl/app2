package com.chuangyou.xianni.entity.log;

import java.util.Date;

/** 技能日志表 */
public class SkillLogInfo {
	private long id;			// 日志ID
	private long playerId;	// 玩家ID
	private int perSkillId;	// 上一个技能ID
	private int skillId;	// 技能ID
	private int skillLV;	// 技能等级
	private Date createDate;	// 创建时间

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public int getPerSkillId() {
		return perSkillId;
	}

	public void setPerSkillId(int perSkillId) {
		this.perSkillId = perSkillId;
	}

	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

	public int getSkillLV() {
		return skillLV;
	}

	public void setSkillLV(int skillLV) {
		this.skillLV = skillLV;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
