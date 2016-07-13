package com.chuangyou.xianni.team.cmd;

import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.team.reaction.ChangeLeaderAction;

@Cmd(code=Protocol.C_REQ_TEAM_CHANGE_LEADER,desc="队长主动移交自己队长的职位")
public class ChangeLeaderCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		ChangeLeaderAction action = new ChangeLeaderAction(player, packet);
		action.setProtocol(Protocol.C_REQ_TEAM_CHANGE_LEADER);
		action.getActionQueue().enqueue(action);
	}

//	@Override
//	public void teamLeaderExec(GamePlayer player, PBMessage packet) throws Exception {
//		// TODO Auto-generated method stub
//		ChangeLeaderReqMsg req = ChangeLeaderReqMsg.parseFrom(packet.getBytes());
//		long newLeader = req.getNewLeaderId();
//		if(newLeader == player.getPlayerId())return;
//		GamePlayer leader = WorldMgr.getPlayer(newLeader);
//		if(leader==null || leader.getPlayerState()==PlayerState.OFFLINE){
//			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, getProtocol(),"不在线");
//			return;
//		}
//		TeamMember member = TeamMgr.getPlayerTeamMap().get(newLeader);
//		if(member ==null || member.getTeamId()!=t.getId()){
//			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, getProtocol(),"数据错误");
//			return;
//		}
//		ChangeLeaderAction action = new ChangeLeaderAction(player, t, member);
//		action.getActionQueue().enqueue(action);
//		
//	}
//
//	@Override
//	public short getProtocol() {
//		// TODO Auto-generated method stub
//		return Protocol.C_REQ_TEAM_CHANGE_LEADER;
//	}

}
