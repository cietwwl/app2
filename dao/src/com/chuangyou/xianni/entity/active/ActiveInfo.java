package com.chuangyou.xianni.entity.active;

import java.util.Date;

import com.chuangyou.common.protobuf.pb.activeSystem.ActiveInfoProto.ActiveInfoMsg;
import com.chuangyou.xianni.entity.DataObject;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.task.ITaskInfo;

public class ActiveInfo extends DataObject implements ITaskInfo {

	private int activeId;
	
	private long playerId;
	
	private int process;
	
	private Date updateTime;
	
	private Date createTime;
	
	private int status;
	
	private Date rewardTime = new Date(0);
	
	public ActiveInfoMsg.Builder getMsg(){
		ActiveInfoMsg.Builder msg = ActiveInfoMsg.newBuilder();
		msg.setActive(activeId);
		msg.setProcess(process);
		if(status>0){
			msg.setReward(status);
		}
		return msg;
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
		if(this.process != process){			
			this.process = process;
			this.updateTime = new Date();
			this.setOp(Option.Update);
		}
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

	public int getActiveId() {
		return activeId;
	}

	public void setActiveId(int activeId) {
		this.activeId = activeId;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public Date getRewardTime() {
		return rewardTime;
	}


	public void setRewardTime(Date rewardTime) {
		this.rewardTime = rewardTime;
	}

}
