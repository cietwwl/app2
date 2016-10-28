package com.chuangyou.xianni.entity.welfare;

import com.chuangyou.xianni.entity.DataObject;

public class WelfareInfo extends DataObject {

	private long	playerId;	// 人物ID
	private int		welfareId;	// 福利配置表ID
	private int		status;		// 福利领取奖励状态（1：未领取，2：可领取，3：已领取）

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public int getWelfareId() {
		return welfareId;
	}

	public void setWelfareId(int welfareId) {
		this.welfareId = welfareId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
