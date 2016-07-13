package com.chuangyou.xianni.team.cmd;

import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.team.reaction.SetTargetAction;

@Cmd(code=Protocol.C_REQ_TEAM_SET_TARGET,desc="队长设置队伍目标")
public class SetTargetTeamCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		SetTargetAction action = new SetTargetAction(player, packet);
		action.setProtocol(Protocol.C_REQ_TEAM_SET_TARGET);
		action.getActionQueue().enqueue(action);
	}

	
//	@Override
//	public void teamLeaderExec(GamePlayer player, PBMessage packet) throws Exception {
//		// TODO Auto-generated method stub
//		SetTargetTeamReqMsg msg = SetTargetTeamReqMsg.parseFrom(packet.getBytes());
//		int targetid = msg.getTargetId();
//		SetTargetAction action = new SetTargetAction(player, t, targetid, getProtocol());
//		action.getActionQueue().enqueue(action);
//	}
//
//	@Override
//	public short getProtocol() {
//		// TODO Auto-generated method stub
//		return Protocol.C_REQ_TEAM_SET_TARGET;
//	}

}
