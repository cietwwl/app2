package com.chuangyou.xianni.entity.guild;

import java.util.Date;

import com.chuangyou.xianni.entity.DataObject;

public class GuildLogInfo extends DataObject {

	/** 帮派ID */
	private int guildId;
	
	/** 操作时间 */
	private Date operateTime;
	
	/** 操作类型 */
	private short operateType;
	
	/** 玩家ID */
	private long playerId;
	
	/** 操作值1 */
	private long value1;
	
	/** 操作值2 */
	private long value2;

	public int getGuildId() {
		return guildId;
	}

	public void setGuildId(int guildId) {
		this.guildId = guildId;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public short getOperateType() {
		return operateType;
	}

	public void setOperateType(short operateType) {
		this.operateType = operateType;
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public long getValue1() {
		return value1;
	}

	public void setValue1(long value1) {
		this.value1 = value1;
	}

	public long getValue2() {
		return value2;
	}

	public void setValue2(long value2) {
		this.value2 = value2;
	}
}
