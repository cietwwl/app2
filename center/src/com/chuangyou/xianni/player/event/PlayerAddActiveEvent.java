package com.chuangyou.xianni.player.event;

import com.chuangyou.xianni.event.ObjectEvent;

public class PlayerAddActiveEvent extends ObjectEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int addValue;
	public PlayerAddActiveEvent(Object obj, int addValue, int eventType) {
		super(obj, null, eventType);
		// TODO Auto-generated constructor stub
		this.addValue = addValue;
	}
	public int getAddValue() {
		return addValue;
	}

}
