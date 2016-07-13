package com.chuangyou.xianni.team.event;

import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.team.struct.Team;

/**
 * 组队事件对象
 * @author laofan
 *
 */
public class TeamEvent extends ObjectEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Team t;
	public TeamEvent(Object obj, Team t, int eventType) {
		super(obj, null, eventType);
		this.t = t;
		// TODO Auto-generated constructor stub
	}
	public Team getT() {
		return t;
	}

}
