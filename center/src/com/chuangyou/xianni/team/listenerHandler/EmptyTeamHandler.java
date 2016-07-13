package com.chuangyou.xianni.team.listenerHandler;

import com.chuangyou.xianni.team.TeamMgr;
import com.chuangyou.xianni.team.event.TeamEvent;

/**
 * 队伍为空
 * @author laofan
 *
 */
public class EmptyTeamHandler extends TeamListener<TeamEvent> {


	@Override
	public void onTeamEvent(TeamEvent event) {
		// TODO Auto-generated method stub
		TeamMgr.destroyTeam(event.getT());
		event.getT().clear();
	}

}
