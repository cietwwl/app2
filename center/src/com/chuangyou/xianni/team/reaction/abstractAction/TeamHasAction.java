package com.chuangyou.xianni.team.reaction.abstractAction;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.team.TeamMgr;
import com.chuangyou.xianni.team.struct.Team;
import com.chuangyou.xianni.team.struct.TeamMember;

/**
 * 有队伍action基类
 * @author laofan
 *
 */
public abstract class TeamHasAction extends AbstractTeamAction {

	/**
	 * 队伍
	 */
	protected Team t;
	/**
	 * 队员
	 */
	protected TeamMember member;
		
	public TeamHasAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void execute(GamePlayer player, PBMessage packet){
		// TODO Auto-generated method stub
		if(!TeamMgr.getPlayerTeamMap().containsKey(player.getPlayerId())){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Team_Is_Not_Existed, getProtocol(),"队伍不存在");
			return;
		}
		member = TeamMgr.getPlayerTeamMap().get(player.getPlayerId());
		if(!TeamMgr.getAllTeams().containsKey(member.getTeamId())){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Team_Is_Not_Existed,getProtocol(),"队伍不存在");
			return;
		}
		t = TeamMgr.getAllTeams().get(member.getTeamId());
		try {
			teamExec(player,packet);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.error(e.getMessage(),e);
		}
	}
	
	/**  队伍执行虚方法 */
	public abstract void teamExec(GamePlayer player, PBMessage packet)throws Exception;



}
