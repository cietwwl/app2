package com.chuangyou.xianni.entity.soul;

import java.util.Date;
import com.chuangyou.common.protobuf.pb.soul.SoulMakeProto.SoulMakeMsg;
import com.chuangyou.xianni.entity.DataObject;

public class SoulMake extends DataObject {
	
	/**
	 * 初始
	 */
	public static final int STATE_INIT = 0;
	/**
	 * 提交了QTE
	 */
	public static final int STATE_QTE = 1;
	/**
	 * 任务进行中
	 */
	public static final int STATE_ING = 2;
	/**
	 * 任务已经完成
	 */
	public static final int STATE_COMPLETE = 3;
	
	private long playerId;
	private int state;
	private Date startTime = new Date();
	private int killNum;
	private int totalTime;
	private int itemId;
	private long lastQteTime;
	private int qteIndex;
		
	public SoulMakeMsg.Builder getMsg(){
		SoulMakeMsg.Builder msg = SoulMakeMsg.newBuilder();
		msg.setState(state);
		msg.setStartTime(startTime.getTime());
		msg.setKillNum(killNum);
		msg.setTotalTime(totalTime);
		msg.setItemId(itemId);
		msg.setLastQteTime(lastQteTime);
		msg.setQte(qteIndex);
		return msg;
	}
	
	public long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public int getKillNum() {
		return killNum;
	}
	public void setKillNum(int killNum) {
		this.killNum = killNum;
	}
	public int getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public long getLastQteTime() {
		return lastQteTime;
	}

	public void setLastQteTime(long lastQteTime) {
		this.lastQteTime = lastQteTime;
	}

	public int getQteIndex() {
		return qteIndex;
	}

	public void setQteIndex(int qteIndex) {
		this.qteIndex = qteIndex;
	}
	
}
