package com.chuangyou.xianni.entity.job;

import java.util.Date;

public class JobInfo {
	// 作业名
	private String	jobName;
	// 时间单位
	private int		intervalUnit;
	// 间隔时间
	private int		intervalData;
	// 指定几点开始
	private int		intervalBegin;
	// 最后一次执行时间
	private Date	updateTime;

	private String	desc;

	public JobInfo() {

	}

	/**
	 * @param jobName
	 *            作业名
	 * @param intervalUnit
	 *            单位
	 * @param intervalData
	 *            间隔时长
	 * @param intervalBegin
	 *            指定几点开始
	 * @param updateTime
	 *            最后一次更新时间
	 * @param desc
	 *            描述
	 */
	public JobInfo(String jobName, int intervalUnit, int intervalData, int intervalBegin, Date updateTime, String desc) {
		this.jobName = jobName;
		this.intervalUnit = intervalUnit;
		this.intervalData = intervalData;
		this.intervalBegin = intervalBegin;
		this.updateTime = updateTime;
		this.desc = desc;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setIntervalUnit(int intervalUnit) {
		this.intervalUnit = intervalUnit;
	}

	public int getIntervalUnit() {
		return intervalUnit;
	}

	public void setIntervalData(int intervalData) {
		this.intervalData = intervalData;
	}

	public int getIntervalData() {
		return intervalData;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public void setIntervalBegin(int intervalBegin) {
		this.intervalBegin = intervalBegin;
	}

	public int getIntervalBegin() {
		return intervalBegin;
	}

}
