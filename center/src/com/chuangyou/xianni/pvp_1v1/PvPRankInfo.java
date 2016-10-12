package com.chuangyou.xianni.pvp_1v1;

import com.chuangyou.common.protobuf.pb.pvp1v1.PvP1v1RankinfoProto.PvP1v1RankinfoMsg;

public class PvPRankInfo implements Comparable<PvPRankInfo> {
	private long				playerId;			// 用户ID
	private int					rank;				// 排名
	private int					score;				// 积分
	private int					winCount;			// 胜场
	private int					fight;				// 战斗力
	private int					winningStreak;		// 连胜场次
	private int					maxWinningStreak;	// 最大连胜场次
	private int					toalCount;			// 战斗总场次
	private short				statu;				// 当前状态 0 正常状态 1 开始匹配 2 匹配完毕
													// 读条中
	private String				name;

	public static final short	NOMOR	= 0;
	public static final short	BEGIN	= 1;
	public static final short	READY	= 2;

	public PvPRankInfo(long playerId, int fight, int rank, String name) {
		this.playerId = playerId;
		this.rank = rank;
		this.score = 0;
		this.fight = fight;
		this.winCount = 0;
		this.winningStreak = 0;
		this.toalCount = 0;
		this.statu = 0;
		this.name = name;
	}

	public void writeProto(PvP1v1RankinfoMsg.Builder builder) {
		builder.setPlayerId(playerId);
		builder.setRank(rank);
		builder.setScore(score);
		builder.setWinCount(winCount);
		builder.setFight(fight);
		builder.setWinningStreak(winningStreak);
		builder.setMaxWinningStreak(maxWinningStreak);
		builder.setToalCount(toalCount);
		builder.setStatu(statu);
		builder.setName(name);

	}

	public void writeSimpeProto(PvP1v1RankinfoMsg.Builder builder) {
		builder.setPlayerId(playerId);
		builder.setRank(rank);
		builder.setScore(score);
		builder.setFight(fight);
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getFight() {
		return fight;
	}

	public void setFight(int fight) {
		this.fight = fight;
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void addScore(int score) {
		this.score += score;
	}

	public int getWinCount() {
		return winCount;
	}

	public void setWinCount(int winCount) {
		this.winCount = winCount;
	}

	public int getWinningStreak() {
		return winningStreak;
	}

	public void setWinningStreak(int winningStreak) {
		this.winningStreak = winningStreak;
	}

	public int getToalCount() {
		return toalCount;
	}

	public void setToalCount(int toalCount) {
		this.toalCount = toalCount;
	}

	public short getStatu() {
		return statu;
	}

	public void setStatu(short statu) {
		this.statu = statu;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxWinningStreak() {
		return maxWinningStreak;
	}

	public void setMaxWinningStreak(int maxWinningStreak) {
		this.maxWinningStreak = maxWinningStreak;
	}

	@Override
	public int compareTo(PvPRankInfo o) {
		if (this.score == o.score) {
			return o.fight - this.fight;
		}
		return o.score - this.score;
	}

}
