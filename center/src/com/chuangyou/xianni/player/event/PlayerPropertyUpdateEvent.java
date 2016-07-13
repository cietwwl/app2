package com.chuangyou.xianni.player.event;

import java.util.Map;

import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;

public class PlayerPropertyUpdateEvent extends ObjectEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<Integer, Long> changeMap;

	public PlayerPropertyUpdateEvent(Object obj, Map<Integer, Long> changeMap) {
		// TODO Auto-generated constructor stub
		super(obj, null, EventNameType.UPDATE_PLAYER_PROPERTY);
		this.changeMap = changeMap;
	}

	public Map<Integer, Long> getChangeMap() {
		return changeMap;
	}
}
