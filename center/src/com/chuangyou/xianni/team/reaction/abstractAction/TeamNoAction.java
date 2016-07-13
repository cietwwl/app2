package com.chuangyou.xianni.team.reaction.abstractAction;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.team.TeamMgr;

/**
 * 没有队伍action基类
 * @author laofan
 *
 */
public abstract class TeamNoAction extends AbstractTeamAction {

	public TeamNoAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(GamePlayer player, PBMessage packet){
		// TODO Auto-generated method stub
		if(TeamMgr.getPlayerTeamMap().containsKey(player.getPlayerId())){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Team_Is_Existed, getProtocol(),"队伍已经存在");
			return;
		}
		try {
			teamExec(player,packet);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.error(e.getMessage(),e);
		}
	}
	
	

	
	public abstract void teamExec(GamePlayer player, PBMessage packet)throws Exception;


}
