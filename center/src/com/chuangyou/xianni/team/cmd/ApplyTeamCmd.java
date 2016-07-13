package com.chuangyou.xianni.team.cmd;

import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.team.reaction.ApplyTeamAction;

@Cmd(code=Protocol.C_REQ_APPLY_TO_TEAM,desc="申请入队")
public class ApplyTeamCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		ApplyTeamAction action = new ApplyTeamAction(player, packet);
		action.setProtocol(Protocol.C_REQ_APPLY_TO_TEAM);
		action.getActionQueue().enqueue(action);
	}

//	@Override
//	public void teamExec(GamePlayer player, PBMessage packet) throws Exception {
//		// TODO Auto-generated method stub
//		ApplyTeamReqMsg msg = ApplyTeamReqMsg.parseFrom(packet.getBytes());
//		
//		Team t = TeamMgr.getAllTeams().get(msg.getTeamId());
//		if(t == null){
//			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Team_Is_Not_Existed, getProtocol(),"队伍不存在");
//			return;
//		}
//		if(t.getApplyPools().getPools().size()>=TeamMgr.TEAM_APPLY_LIST_MAX){
//			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.TEAM_APPLY_LIST_MAX, getProtocol(),"队伍申请列表已满");
//			return;
//		}
//		if(t.isFull()){
//			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Team_Members_Full, getProtocol(),"队伍人数已满。不能再申请");
//			return;
//		}
//		
//		ApplyToTeamAction action = new ApplyToTeamAction(player,t);
//		action.getActionQueue().enqueue(action);
//	}
//
//	@Override
//	public short getProtocol() {
//		// TODO Auto-generated method stub
//		return Protocol.C_REQ_APPLY_TO_TEAM;
//	}

	
}
