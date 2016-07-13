package com.chuangyou.xianni.player.event;

import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;

public class PlayerProperEvent extends ObjectEvent {
	private static final long	serialVersionUID	= -3206865305785789111L;
	public static int			CURRENTTYPE			= EventNameType.UPDATE_PLAYER_PROPERTY_ALL;

	public PlayerProperEvent(Object obj) {
		super(obj, null, CURRENTTYPE);
	}
	
	
}
