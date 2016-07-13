package com.chuangyou.xianni.entity.player;

import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.property.BaseProperty;

public class PlayerJoinInfo extends BaseProperty {

	/** 玩家ID */
	private long	playerId;

	/** 当前血量 */
	private int		curSoul;

	/** 当前魔法 */
	private int		curBlood;

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public int getCurSoul() {
		return curSoul;
	}

	public void setCurSoul(int curSoul) {
		setOp(Option.Update);
		this.curSoul = curSoul;
	}

	public int getCurBlood() {
		return curBlood;
	}

	public void setCurBlood(int curBlood) {
		setOp(Option.Update);
		this.curBlood = curBlood;
	}

}
