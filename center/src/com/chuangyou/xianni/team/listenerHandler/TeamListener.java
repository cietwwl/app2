package com.chuangyou.xianni.team.listenerHandler;

import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.event.ObjectListener;

public abstract class TeamListener<T extends ObjectEvent> implements ObjectListener {

	@SuppressWarnings("unchecked")
	@Override
	public void onEvent(ObjectEvent event) {
		// TODO Auto-generated method stub
		onTeamEvent((T) event);
	}
	
	public abstract void onTeamEvent(T event);
		
}
