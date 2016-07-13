package com.chuangyou.xianni.team.cmd;

import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.team.reaction.LeaveTeamAction;

@Cmd(code=Protocol.C_REQ_TEAM_LEAVE,desc="离开队伍")
public class LeaveTeamCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		LeaveTeamAction action = new LeaveTeamAction(player, packet);
		action.setProtocol(Protocol.C_REQ_TEAM_LEAVE);
		action.getActionQueue().enqueue(action);
	}

	
	
	
//	@Override
//	public void teamExec(GamePlayer player, PBMessage packet) throws Exception {
//		// TODO Auto-generated method stub
//		TeamMember menber = TeamMgr.getPlayerTeamMap().get(player.getPlayerId());
//		LeaveTeamAction action = new LeaveTeamAction(player, t, menber);
//		action.getActionQueue().enqueue(action);
//	}
//
//	@Override
//	public short getProtocol() {
//		// TODO Auto-generated method stub
//		return Protocol.C_REQ_TEAM_LEAVE;
//	}

}
