package com.chuangyou.xianni.entity.activity;

import com.chuangyou.common.protobuf.pb.activity.ActivityTempMsgProto.ActivityTempMsg;

/**
 * 活动配置表
 * 
 * @author laofan
 *
 */
public class ActivityConfig {

	/**
	 * 开启中
	 */
	public static final int		ACTIVITY_OPEN		= 1;
	/**
	 * 关闲中
	 */
	public static final int		ACTIVITY_CLOSE		= 0;

	/**
	 * 活动清除
	 */
	public static final int		DELETE				= 2;

	////////////////////////////////////////////////////
	/**
	 * 永久时间开放活动
	 */
	public static final int		TIMETYPE_PERMANENT	= 1;
	/**
	 * 每天
	 */
	public static final int		TIMETYPE_EVERYDAY	= 2;
	/**
	 * 每周
	 */
	public static final int		TIMETYPE_EVERYWEEK	= 3;

	/**
	 * 间隔时间
	 */
	public static final int		TIMETYPE_INTERVAL	= 4;

	private int					id;
	private String				name;
	private int					needLevel;
	private int					timeType;
	private String				timeParam;
	private String				startTime;
	private String				endTime;
	private int					numLimit;
	/**
	 * 活动是否处于关闲状态
	 */
	private int					statu;

	/** 是否处于延迟队列检查 */
	private volatile boolean	delayChecking		= false;

	public void writeProto(ActivityTempMsg.Builder builder) {
		builder.setId(id);
		builder.setNeedLevel(needLevel);
		builder.setTimeType(timeType);
		builder.setTimeParam(timeParam);
		builder.setStartTime(startTime);
		builder.setEndTime(endTime);
		builder.setNumLimit(numLimit);
		builder.setStatu(statu);
	}

	public int[] getWeekDays() {
		String str[] = timeParam.split(",");
		int array[] = new int[str.length];
		for (int i = 0; i < str.length; i++) {
			array[i] = Integer.parseInt(str[i]);
		}
		return array;
	}

	public String[] getWeekStarts() {
		return startTime.split(",");
	}

	public String[] getWeekEnds() {
		return endTime.split(",");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNeedLevel() {
		return needLevel;
	}

	public void setNeedLevel(int needLevel) {
		this.needLevel = needLevel;
	}

	public int getTimeType() {
		return timeType;
	}

	public void setTimeType(int timeType) {
		this.timeType = timeType;
	}

	public String getTimeParam() {
		return timeParam;
	}

	public void setTimeParam(String timeParam) {
		this.timeParam = timeParam;
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

	public int getNumLimit() {
		return numLimit;
	}

	public void setNumLimit(int numLimit) {
		this.numLimit = numLimit;
	}

	public int getStatu() {
		return statu;
	}

	public void setStatu(int statu) {
		this.statu = statu;
	}

	public boolean isDelayChecking() {
		return delayChecking;
	}

	public void setDelayChecking(boolean delayChecking) {
		this.delayChecking = delayChecking;
	}

}
