package com.chuangyou.xianni.team.reaction;

import com.chuangyou.common.protobuf.pb.team.ChangeLeaderReqProto.ChangeLeaderReqMsg;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.constant.PlayerState;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.team.TeamMgr;
import com.chuangyou.xianni.team.reaction.abstractAction.TeamLeaderAction;
import com.chuangyou.xianni.team.struct.TeamMember;
import com.chuangyou.xianni.word.WorldMgr;

/**
 * 改变队长
 * @author laofan
 *
 */
public class ChangeLeaderAction extends TeamLeaderAction {

	protected TeamMember member;
	protected TeamMember newLeaderMember;
	
	public ChangeLeaderAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void teamLeaderExec(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		ChangeLeaderReqMsg req = ChangeLeaderReqMsg.parseFrom(packet.getBytes());
		long newLeader = req.getNewLeaderId();
		if(newLeader == player.getPlayerId())return;
		GamePlayer leader = WorldMgr.getPlayer(newLeader);
		if(leader==null || leader.getPlayerState()==PlayerState.OFFLINE){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, getProtocol(),"不在线");
			return;
		}
		newLeaderMember = TeamMgr.getPlayerTeamMap().get(newLeader);
		if(newLeaderMember ==null || newLeaderMember.getTeamId()!=t.getId()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, getProtocol(),"数据错误");
			return;
		}
		t.changeLeader(newLeaderMember);
		//ChangeLeaderAction action = new ChangeLeaderAction(player, t, member);
		//action.getActionQueue().enqueue(action);
	}

}
