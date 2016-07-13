package com.chuangyou.xianni.team.reaction;

import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.team.reaction.abstractAction.TeamHasAction;
import com.chuangyou.xianni.team.reaction.abstractAction.TeamLeaderAction;

/**
 * 离开队伍
 * @author laofan
 *
 */
public class LeaveTeamAction extends TeamHasAction {

	public LeaveTeamAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	
	
	@Override
	public void teamExec(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		if(member.getPlayerId() == t.getLeader().getPlayerId()){
			getLeaderLeaveAction().execute();
		}else{
			getPersonLeaveAction().execute();
		}
	}
	
	/**
	 * 成员离开action
	 * @return
	 */
	protected TeamHasAction getPersonLeaveAction(){
		return new MemberLeaveAction(player,packet);
	}
	
	/**
	 * 队长离开action
	 * @return
	 */
	protected TeamLeaderAction getLeaderLeaveAction(){
		return new LeaderLeaveTeamAction(player,packet);
	}

}
