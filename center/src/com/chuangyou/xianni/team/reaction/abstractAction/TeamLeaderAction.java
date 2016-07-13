package com.chuangyou.xianni.team.reaction.abstractAction;

import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;

/**
 * 队长action基类
 * @author laofan
 *
 */
public abstract class TeamLeaderAction extends TeamHasAction {

	public TeamLeaderAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void teamExec(GamePlayer player, PBMessage packet)throws Exception{
		// TODO Auto-generated method stub
		if(t.getLeader().getPlayerId()!=member.getPlayerId()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Team_No_GRANT, getProtocol(),"非队长。无权限");
			return;
		}
		teamLeaderExec(player,packet);
	}

	/** 队长执行逻辑  */
	public abstract void teamLeaderExec(GamePlayer player,PBMessage packet)throws Exception;

}
