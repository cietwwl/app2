package com.chuangyou.xianni.team.cmd;

import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.team.reaction.MatchTeamAction;

@Cmd(code=Protocol.C_ERQ_MATCH_TEAM_TARGET,desc="匹配")
public class MatchCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		MatchTeamAction action = new MatchTeamAction(player, packet);
		action.setProtocol(Protocol.C_ERQ_MATCH_TEAM_TARGET);
		action.getActionQueue().enqueue(action);
				
	}

//	@Override
//	public TeamLeaderCommand getTeamLeaderCommand() {
//		// TODO Auto-generated method stub
//		return new LeaderMatchCmd();
//	}
//
//	@Override
//	public TeamNoCommand getTeamNoCommand() {
//		// TODO Auto-generated method stub
//		return new NoTeamMatchCmd();
//	}
//
//	@Override
//	public short getProtocol() {
//		// TODO Auto-generated method stub
//		return Protocol.C_ERQ_MATCH_TEAM_TARGET;
//	}
	
	

}
