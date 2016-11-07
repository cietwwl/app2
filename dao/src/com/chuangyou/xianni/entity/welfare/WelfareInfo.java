package com.chuangyou.xianni.entity.welfare;

import com.chuangyou.xianni.entity.DataObject;
import com.chuangyou.xianni.entity.Option;

public class WelfareInfo extends DataObject {

	private long	playerId;	// 人物ID
	private int		welfareId;	// 福利配置表ID
	private int		status;		// 福利领取奖励状态（1：未领取，2：可领取，3：已领取）

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		if (this.playerId != playerId) {
			this.playerId = playerId;
			setOp(Option.Update);
		}
	}

	public int getWelfareId() {
		return welfareId;
	}

	public void setWelfareId(int welfareId) {
		if (this.welfareId != welfareId) {
			this.welfareId = welfareId;
			setOp(Option.Update);
		}
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		if (this.status != status) {
			this.status = status;
			setOp(Option.Update);
		}
	}
}
