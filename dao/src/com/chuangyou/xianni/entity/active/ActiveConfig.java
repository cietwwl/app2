package com.chuangyou.xianni.entity.active;

import com.chuangyou.xianni.entity.task.ITaskCfg;

public class ActiveConfig implements ITaskCfg {

	/**
	 * 日常 
	 */
	public static final int DAY = 1;
	/**
	 * 周常 
	 */
	public static final int WEEK = 2;
	
	public static final int TOTAL_DAY = 3;
	
	public static final int TOTAL_WEEK = 4;
	
	/**
	 *  所有类型
	 */
	public static final int ALL = 0;
	
	private int id;
	private int type;
	private int needLv;
	
	private byte taskTarget;
	private int targetId;
	private int targetId1;
	private int targetNum;
	private int targetTrigger;
	private int reward;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public byte getTaskTarget() {
		return taskTarget;
	}
	public void setTaskTarget(byte taskTarget) {
		this.taskTarget = taskTarget;
	}
	public int getTargetId() {
		return targetId;
	}
	public void setTargetId(int targetId) {
		this.targetId = targetId;
	}
	public int getTargetId1() {
		return targetId1;
	}
	public void setTargetId1(int targetId1) {
		this.targetId1 = targetId1;
	}
	public int getTargetNum() {
		return targetNum;
	}
	public void setTargetNum(int targetNum) {
		this.targetNum = targetNum;
	}
	public int getReward() {
		return reward;
	}
	public void setReward(int reward) {
		this.reward = reward;
	}
	public int getTargetTrigger() {
		return targetTrigger;
	}
	public void setTargetTrigger(int targetTrigger) {
		this.targetTrigger = targetTrigger;
	}
	public int getNeedLv() {
		return needLv;
	}
	public void setNeedLv(int needLv) {
		this.needLv = needLv;
	}
	
	


}
