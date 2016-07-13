package com.chuangyou.xianni.team.reaction;

import com.chuangyou.common.protobuf.pb.team.AgreeInviteReqProto.AgreeInviteReqMsg;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.team.TeamMgr;
import com.chuangyou.xianni.team.reaction.abstractAction.TeamNoAction;
import com.chuangyou.xianni.team.reaction.check.TeamCheck;
import com.chuangyou.xianni.team.struct.Team;

/**
 * 同意某个队伍的邀请
 * 
 * @author laofan
 *
 */
public class AgreeInviteAction extends TeamNoAction {

	protected Team t;
	protected long agreeId;

	public AgreeInviteAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void teamExec(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		AgreeInviteReqMsg req;
		req = AgreeInviteReqMsg.parseFrom(packet.getBytes());
		t = TeamMgr.getAllTeams().get(req.getTeamId());
		agreeId = player.getPlayerId();

		if (t == null) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Team_Is_Not_Existed, getProtocol(), "队伍不存在");
			return;
		}

		if(new TeamCheck().check(player, agreeId, getProtocol(), t) == false)
			return;
		t.addMember(agreeId);

	}



	

}
