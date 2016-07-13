package com.chuangyou.xianni.team.reaction.abstractAction;

import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.team.TeamMgr;
import com.chuangyou.xianni.team.struct.TeamMember;

/**
 * 队伍不确定action基类
 * @author laofan
 *
 */
public abstract class TeamOrAction extends AbstractTeamAction {

	protected TeamMember m;
	
	public TeamOrAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(GamePlayer player, PBMessage packet) {
		// TODO Auto-generated method stub
		TeamMember member = TeamMgr.getPlayerTeamMap().get(player.getPlayerId());
		if(member==null){
			getTeamNoAction().execute(player, packet);
		}else{
			this.m = member;
			getTeamHasAction().execute(player, packet);
		}
	}
	
	/**
	 * 获取队长执行action
	 * @return
	 */
	public abstract TeamHasAction getTeamHasAction();
	
	/**
	 * 获取无队的人执行的action
	 * @return
	 */
	public abstract TeamNoAction getTeamNoAction();

}
