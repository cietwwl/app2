package com.chuangyou.xianni.entity.guild;

import com.chuangyou.xianni.entity.DataObject;

public class GuildApplyInfo extends DataObject {

	private int guildId;
	private long playerId;
	private long applyTime;
	public int getGuildId() {
		return guildId;
	}
	public void setGuildId(int guildId) {
		this.guildId = guildId;
	}
	public long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}
	public long getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(long applyTime) {
		this.applyTime = applyTime;
	}
}
