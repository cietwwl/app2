package com.chuangyou.xianni.team.cmd;

import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.team.reaction.GetTeamListAction;

@Cmd(code=Protocol.C_ERQ_GET_TEAM_LIST,desc="查队伍")
public class GetTeamListCmd extends AbstractCommand {

//	@Override
//	public void teamExec(GamePlayer player, PBMessage packet) throws Exception {
//		// TODO Auto-generated method stub
//		GetTeamListReqMsg msg = GetTeamListReqMsg.parseFrom(packet.getBytes());
//		int targetId = msg.getTargetId();
//		
//		GetTeamListAction action = new GetTeamListAction(player, targetId);
//		action.getActionQueue().enqueue(action);
//		
//	}
//
//	@Override
//	public short getProtocol() {
//		// TODO Auto-generated method stub
//		return Protocol.C_ERQ_GET_TEAM_LIST;
//	}

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		GetTeamListAction action = new GetTeamListAction(player, packet);
		action.setProtocol(Protocol.C_ERQ_GET_TEAM_LIST);
		action.getActionQueue().enqueue(action);
	}

}
