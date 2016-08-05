package com.chuangyou.xianni.entity.space;

import java.util.Date;

import com.chuangyou.common.protobuf.pb.space.ActionLogProto.ActionLogMsg;
import com.chuangyou.xianni.entity.DataObject;
/**
 * 空间操作日志 
 * @author laofan
 *
 */
public class SpaceActionLogInfo extends DataObject {
	
	private long sendPlayerId;
	private long receivePlayerId;
	private Date createTime;
	private int action;
	
	
	public ActionLogMsg.Builder getMsg(){
		ActionLogMsg.Builder msg = ActionLogMsg.newBuilder();
		msg.setSendPlayerId(sendPlayerId);
		msg.setCreateDate(createTime.getTime());
		msg.setOp(action);
		return msg;
	}
	
	
	public long getSendPlayerId() {
		return sendPlayerId;
	}
	public void setSendPlayerId(long sendPlayerId) {
		this.sendPlayerId = sendPlayerId;
	}
	public long getReceivePlayerId() {
		return receivePlayerId;
	}
	public void setReceivePlayerId(long receivePlayerId) {
		this.receivePlayerId = receivePlayerId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
}
