package com.chuangyou.xianni.team.cmd;

import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.team.reaction.InviteTMemberAction;

@Cmd(code=Protocol.C_REQ_INVITE_TO_TEAM,desc="邀请入队")
public class InviteMemberCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		InviteTMemberAction action = new InviteTMemberAction(player, packet);
		action.setProtocol(Protocol.C_REQ_INVITE_TO_TEAM);
		action.getActionQueue().enqueue(action);
	}

//	@Override
//	public void teamLeaderExec(GamePlayer player, PBMessage packet) throws Exception {
//		// TODO Auto-generated method stub
//		InviteInTeamReqMsg req = InviteInTeamReqMsg.parseFrom(packet.getBytes());
//		long playerId = req.getPlayerId();
//		if(TeamMgr.getPlayerTeamMap().containsKey(playerId)){
//			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Team_SomeBody_Has_Team, getProtocol(),"已经有队伍啦");
//			return;
//		}
//		
//		if(t.isFull()){
//			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Team_Members_Full, getProtocol(),"队伍人数已满");
//			return;
//		}
//		
//		NotifyInviteRespMsg.Builder resp = NotifyInviteRespMsg.newBuilder();
//		resp.setTeamInfo(t.getTeamMsg());
//		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_NOTIFY_INVITE, resp);
//		GamePlayer p = WorldMgr.getPlayer(playerId);
//		if(p!=null){
//			p.sendPbMessage(pkg);
//		}
//		
//	}
//
//	@Override
//	public short getProtocol() {
//		// TODO Auto-generated method stub
//		return Protocol.C_REQ_INVITE_TO_TEAM;
//	}

}
