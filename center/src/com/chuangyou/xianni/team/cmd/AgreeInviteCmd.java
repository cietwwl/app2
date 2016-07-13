package com.chuangyou.xianni.team.cmd;

import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.team.reaction.AgreeInviteAction;

@Cmd(code=Protocol.C_REQ_AGREE_INVITE,desc="接受队长的邀请")
public class AgreeInviteCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		AgreeInviteAction action = new AgreeInviteAction(player, packet);
		action.setProtocol(Protocol.C_REQ_AGREE_INVITE);
		action.getActionQueue().enqueue(action);
	}

//	@Override
//	public void teamExec(GamePlayer player, PBMessage packet) throws Exception {
//		// TODO Auto-generated method stub
//		AgreeInviteReqMsg req = AgreeInviteReqMsg.parseFrom(packet.getBytes());
//		Team t = TeamMgr.getAllTeams().get(req.getTeamId());
//		if(t==null){
//			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Team_Is_Not_Existed, getProtocol(),"队伍不存在");
//			return;
//		}
//		
//		AgreeInviteAction action = new AgreeInviteAction(player, t, player.getPlayerId(), getProtocol());
//		action.getActionQueue().enqueue(action);
//	}
//
//	@Override
//	public short getProtocol() {
//		// TODO Auto-generated method stub
//		return Protocol.C_REQ_AGREE_INVITE;
//	}

}
