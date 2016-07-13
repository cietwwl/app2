package com.chuangyou.xianni.team.listenerHandler;

import com.chuangyou.xianni.team.TeamMgr;
import com.chuangyou.xianni.team.event.TeamEvent;

/**
 * 队满
 * @author laofan
 *
 */
public class FullTeamHandler extends TeamListener<TeamEvent> {

	@Override
	public void onTeamEvent(TeamEvent event) {
		// TODO Auto-generated method stub
		//从匹配池中去掉这个队伍
		TeamMgr.removeTeamTargetMatch(event.getT().getTargetId(), event.getT().getId());
		TeamMgr.removeTeamTargetPool(event.getT().getTargetId(), event.getT().getId());
		
	}

}
