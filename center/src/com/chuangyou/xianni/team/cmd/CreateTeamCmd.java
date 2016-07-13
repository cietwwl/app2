package com.chuangyou.xianni.team.cmd;

import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.team.reaction.CreateTeamAction;

@Cmd(code=Protocol.C_REQ_CREATE_TEAM,desc="建队")
public class CreateTeamCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		CreateTeamAction action = new CreateTeamAction(player, packet);
		action.setProtocol(Protocol.C_REQ_CREATE_TEAM);
		action.getActionQueue().enqueue(action);
	}

	
	
	
//	@Override
//	public void teamExec(GamePlayer player, PBMessage packet) throws Exception {
//		// TODO Auto-generated method stub
//		int targetId = 0;
//		CreateTeamReqMsg msg = CreateTeamReqMsg.parseFrom(packet.getBytes());
//		if(msg.hasTargetId()){
//			targetId = msg.getTargetId();
//		}
//		CreateTeamAction action = new CreateTeamAction(player,targetId);
//		action.getActionQueue().enqueue(action);
//	}
//
//	@Override
//	public short getProtocol() {
//		// TODO Auto-generated method stub
//		return Protocol.C_REQ_CREATE_TEAM;
//	}

}
