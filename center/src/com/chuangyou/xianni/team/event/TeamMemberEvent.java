package com.chuangyou.xianni.team.event;

import com.chuangyou.xianni.team.struct.Team;
import com.chuangyou.xianni.team.struct.TeamMember;

public class TeamMemberEvent extends TeamEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TeamMember member;
	public TeamMemberEvent(Object obj, Team t, int eventType,TeamMember member) {
		super(obj, t, eventType);
		this.member = member;
		// TODO Auto-generated constructor stub
	}
	public TeamMember getMember() {
		return member;
	}

}
