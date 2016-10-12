package com.chuangyou.xianni.entity.arena;

public class FightData implements Comparable<FightData> {
	private long	playerId;
	private int		fight;

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public int getFight() {
		return fight;
	}

	public void setFight(int fight) {
		this.fight = fight;
	}

	@Override
	public int compareTo(FightData o) {
		return this.getFight() - o.getFight();
	}

}
