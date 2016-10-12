package com.chuangyou.xianni.entity.guild;

import com.chuangyou.xianni.entity.DataObject;

public class GuildSkillInfo extends DataObject {

	/** 玩家ID */
	private long playerId;
	
	/** 帮派技能ID */
	private int guildSkillId;
	
	/** 技能等级 */
	private int level;
	
	public GuildSkillInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public GuildSkillInfo(long playerId, int guildSkillId) {
		// TODO Auto-generated constructor stub
		this.playerId = playerId;
		this.guildSkillId = guildSkillId;
		this.level = 0;
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public int getGuildSkillId() {
		return guildSkillId;
	}

	public void setGuildSkillId(int guildSkillId) {
		this.guildSkillId = guildSkillId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
