package com.chuangyou.xianni.team.cmd;

import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.team.reaction.KickTeamAction;

@Cmd(code=Protocol.C_REQ_TEAM_KICK,desc="队长踢人")
public class KickTeamCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		KickTeamAction action = new KickTeamAction(player, packet);
		action.setProtocol(Protocol.C_REQ_TEAM_KICK);
		action.getActionQueue().enqueue(action);
	}

//	@Override
//	public void teamLeaderExec(GamePlayer player, PBMessage packet) throws Exception {
//		// TODO Auto-generated method stub
//		KickTeamReqMsg req = KickTeamReqMsg.parseFrom(packet.getBytes());
//		long id = req.getKickPlayerId();
//		TeamMember member = TeamMgr.getPlayerTeamMap().get(id);
//		if(member==null || member == t.getLeader() || t.getMembers().indexOf(member)==-1){
//			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, getProtocol(),"数据错误");
//			return;
//		}
//		KickTeamAction action = new KickTeamAction(player, t, member);
//		action.getActionQueue().enqueue(action);
//	}
//
//	@Override
//	public short getProtocol() {
//		// TODO Auto-generated method stub
//		return Protocol.C_REQ_TEAM_KICK;
//	}

}
