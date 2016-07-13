package com.chuangyou.xianni.team.reaction;

import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.team.TeamMgr;
import com.chuangyou.xianni.team.reaction.abstractAction.TeamHasAction;
import com.chuangyou.xianni.team.reaction.abstractAction.TeamNoAction;
import com.chuangyou.xianni.team.reaction.abstractAction.TeamOrAction;
import com.chuangyou.xianni.team.struct.Team;

/**
 * 改变在线/离线状态
 * @author laofan
 *
 */
public class ChangeLineAction extends TeamOrAction {

	private boolean isLine;
	
	public ChangeLineAction(GamePlayer player, PBMessage packet,boolean isLine) {
		super(player, packet);
		// TODO Auto-generated constructor stub
		this.isLine = isLine;
	}
	
	
	@Override
	public TeamHasAction getTeamHasAction() {
		// TODO Auto-generated method stub
		Team team = TeamMgr.getAllTeams().get(m.getTeamId());
		if(m.getPlayerId() == team.getLeader().getPlayerId()){
			return new LeaderChangeLineAction(player, packet, isLine);
		}else{
			return new MemberChangeLineAction(player, packet, isLine);
		}
	}

	@Override
	public TeamNoAction getTeamNoAction() {
		// TODO Auto-generated method stub
		return new PersonChangeLineAction(player, packet, isLine);
	}
	

}
