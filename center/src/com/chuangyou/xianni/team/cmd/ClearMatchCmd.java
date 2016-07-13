package com.chuangyou.xianni.team.cmd;

import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.team.reaction.ClearMatchAction;

@Cmd(code=Protocol.C_REQ_TEAM_CLEAR_MATCH,desc="取消自动匹配")
public class ClearMatchCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		ClearMatchAction action = new ClearMatchAction(player, packet);
		action.setProtocol(Protocol.C_REQ_TEAM_CLEAR_MATCH);
		action.getActionQueue().enqueue(action);
	}

	
//	@Override
//	public TeamLeaderCommand getTeamLeaderCommand() {
//		// TODO Auto-generated method stub
//		return new LeaderClearMatchCmd();
//	}
//
//	
//	@Override
//	public TeamNoCommand getTeamNoCommand() {
//		// TODO Auto-generated method stub
//		return new NoTeamClearMatchCmd();
//	}
//
//	@Override
//	public short getProtocol() {
//		// TODO Auto-generated method stub
//		return Protocol.C_REQ_TEAM_CLEAR_MATCH;
//	}

}
