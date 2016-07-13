package com.chuangyou.xianni.team.reaction;

import com.chuangyou.common.protobuf.pb.team.KickTeamReqProto.KickTeamReqMsg;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.team.TeamMgr;
import com.chuangyou.xianni.team.reaction.abstractAction.TeamLeaderAction;
import com.chuangyou.xianni.team.struct.TeamMember;

/**
 * 队长踢人
 * @author laofan
 *
 */
public class KickTeamAction extends TeamLeaderAction {

	public KickTeamAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void teamLeaderExec(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		KickTeamReqMsg req = KickTeamReqMsg.parseFrom(packet.getBytes());
		long id = req.getKickPlayerId();
		TeamMember kickMember = TeamMgr.getPlayerTeamMap().get(id);
		if(kickMember==null ||kickMember.getPlayerId() == t.getLeader().getPlayerId() || t.getMembers().indexOf(kickMember)==-1){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, getProtocol(),"数据错误");
			return;
		}
		t.removeMember(kickMember);
		
	}

}
