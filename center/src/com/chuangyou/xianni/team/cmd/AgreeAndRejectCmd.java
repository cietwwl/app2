package com.chuangyou.xianni.team.cmd;

import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.team.reaction.AgreeToTeamAction;

@Cmd(code=Protocol.C_REQ_AGREE_TO_TEAM,desc="同意/拒绝入队")
public class AgreeAndRejectCmd extends AbstractCommand {

//	@Override
//	public void teamLeaderExec(GamePlayer player, PBMessage packet) throws Exception {
//		// TODO Auto-generated method stub
//		AgreeAndRejectReqMsg req = AgreeAndRejectReqMsg.parseFrom(packet.getBytes());
//		boolean isAgree = req.getIsAgree();
//		AgreeToTeamAction action = new AgreeToTeamAction(player, t, req.getPlayerId(), getProtocol(), isAgree);
//		action.getActionQueue().enqueue(action);
//	}
//
//	@Override
//	public short getProtocol() {
//		// TODO Auto-generated method stub
//		return Protocol.C_REQ_AGREE_TO_TEAM;
//	}

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		AgreeToTeamAction action = new AgreeToTeamAction(player, packet);
		action.setProtocol(Protocol.C_REQ_AGREE_TO_TEAM);
		action.getActionQueue().enqueue(action);
	}

}
