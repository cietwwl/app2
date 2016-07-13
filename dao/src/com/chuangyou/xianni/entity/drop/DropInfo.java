package com.chuangyou.xianni.entity.drop;

public class DropInfo {

	/**
	 * 掉落池ID
	 */
	private int id;
	
	/**
	 * 掉落池类型，1机率掉落，2权重掉落
	 */
	private short type;
	
	/**
	 * 循环掉落本掉落池的次数
	 */
	private int repeat;
	
	/**
	 * 限时掉落时间类型
	 */
	private short limitType;
	
	/**
	 * 掉落开始时间
	 */
	private String startTime;
	
	/**
	 * 掉落结束时间
	 */
	private String endTime;
	
	/**
	 * 可见类型
	 */
	private short visibleType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

	public int getRepeat() {
		return repeat;
	}

	public void setRepeat(int repeat) {
		this.repeat = repeat;
	}

	public short getLimitType() {
		return limitType;
	}

	public void setLimitType(short limitType) {
		this.limitType = limitType;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public short getVisibleType() {
		return visibleType;
	}

	public void setVisibleType(short visibleType) {
		this.visibleType = visibleType;
	}
}
