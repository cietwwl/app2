package com.chuangyou.xianni.entity.state;

import java.util.Date;

import com.chuangyou.common.protobuf.pb.state.StateInfoProto.StateInfoMsg;
import com.chuangyou.xianni.entity.DataObject;
import com.chuangyou.xianni.entity.Option;

/**
 * 境界任务静态数据
 * @author laofan
 *
 */
public class StateConditionInfo extends DataObject {

	private int stateId;
	
	private long playerId;
	
	private int process;
	
	private Date updateTime;
	
	private Date createTime;
	
	public StateInfoMsg.Builder getMsg(){
		StateInfoMsg.Builder msg = StateInfoMsg.newBuilder();
		msg.setStateId(stateId);
		msg.setProcess(process);
		return msg;
	}
	
	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
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
	
	
	
	
}
