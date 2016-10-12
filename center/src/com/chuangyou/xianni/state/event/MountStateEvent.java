package com.chuangyou.xianni.state.event;

import com.chuangyou.xianni.event.ObjectEvent;

public class MountStateEvent extends ObjectEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int targetId;
	private int targetNum;
	private int targetNum1;
	
	
	
	public MountStateEvent(Object obj, int targetId, int targetNum, int targetNum1,int eventType) {
		super(obj, null, eventType);
		this.targetId = targetId;
		this.targetNum = targetNum;
		this.targetNum1 = targetNum1;
	}


	public int getTargetId() {
		return targetId;
	}

	public void setTargetId(int targetId) {
		this.targetId = targetId;
	}

	public int getTargetNum() {
		return targetNum;
	}

	public void setTargetNum(int targetNum) {
		this.targetNum = targetNum;
	}

	public int getTargetNum1() {
		return targetNum1;
	}

	public void setTargetNum1(int targetNum1) {
		this.targetNum1 = targetNum1;
	}

}
