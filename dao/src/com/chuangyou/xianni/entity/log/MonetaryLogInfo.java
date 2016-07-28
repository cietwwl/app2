package com.chuangyou.xianni.entity.log;

/** 虚拟货币日志：包括所有数字类型日志记录 */
public class MonetaryLogInfo {
	private int		id;				// ID
	private long	playerId;		// 角色ID
	private int		moneyType;		// 货币类
	private int		changeWay;		// 修改途径
	private long	initCount;		// 初始数量
	private long	changeCount;	// 改变后的数量
	private long	changeTime;		// 改变时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(int moneyType) {
		this.moneyType = moneyType;
	}

	public int getChangeWay() {
		return changeWay;
	}

	public void setChangeWay(int changeWay) {
		this.changeWay = changeWay;
	}

	public long getInitCount() {
		return initCount;
	}

	public void setInitCount(long initCount) {
		this.initCount = initCount;
	}

	public long getChangeCount() {
		return changeCount;
	}

	public void setChangeCount(long changeCount) {
		this.changeCount = changeCount;
	}

	public long getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(long changeTime) {
		this.changeTime = changeTime;
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

}
