package com.chuangyou.xianni.team.reaction;

import com.chuangyou.xianni.constant.PlayerState;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.team.reaction.abstractAction.TeamLeaderAction;
import com.chuangyou.xianni.team.struct.TeamMember;
import com.chuangyou.xianni.word.WorldMgr;

public class LeaderLeaveTeamAction extends TeamLeaderAction{

	public LeaderLeaveTeamAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void teamLeaderExec(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		//队长离开.要先移交队长
		changeLeader();
		t.removeMember(member);
	}
	
	
	/**
	 * 改变队长
	 */
	protected void changeLeader(){
		TeamMember newL= searchNewLeader();
		if(newL!=null){
			t.changeLeader(newL);
			//ChangeLeaderAction action = new ChangeLeaderAction(player, t, newL);
			//action.execute();
		}
	}
	
	/**
	 * 随机找新队长.
	 * @return :如果找不到返回空
	 */
	protected TeamMember searchNewLeader(){
		for (TeamMember member : t.getMembers()) {
			if(member.getPlayerId() == t.getLeader().getPlayerId())continue;
			if(WorldMgr.getPlayer(member.getPlayerId()).getPlayerState() == PlayerState.ONLINE){
				return member;
			}
		}
		return null;
	}

}
