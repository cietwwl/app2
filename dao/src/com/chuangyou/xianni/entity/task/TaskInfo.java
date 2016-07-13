package com.chuangyou.xianni.entity.task;

import java.util.Date;

import com.chuangyou.xianni.entity.DataObject;

public class TaskInfo extends DataObject {
	

	////////////////////////////////////////////////////
	/** 未接 */
	public static final byte UN_ACCEPT = 0;
	/**  已接进行中   */
	public static final byte ACCEPT = 1;
	/**  完成   */
	public static final byte FINISH = 2;
	/** 提交  */
	public static final byte COMMIT = 3;
	
	
	
	
	private int taskId;
	private long playerId;
	private int process;
	private byte state;
	private Date updateTime;
	private Date createTime;
	
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}
	public int getProcess() {
		return process;
	}
	public void setProcess(int process) {
		this.process = process;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public byte getState() {
		return state;
	}
	public void setState(byte state) {
		this.state = state;
	}
	
	
}
