package com.chuangyou.xianni.team.reaction;

import com.chuangyou.common.protobuf.pb.team.ApplyTeamReqProto.ApplyTeamReqMsg;
import com.chuangyou.common.protobuf.pb.team.ApplyTeamRespProto.ApplyTeamRespMsg;
import com.chuangyou.common.protobuf.pb.team.NotifyLeaderApplyRespProto.NotifyLeaderApplyRespMsg;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.team.TeamMgr;
import com.chuangyou.xianni.team.reaction.abstractAction.TeamNoAction;
import com.chuangyou.xianni.team.struct.Team;
import com.chuangyou.xianni.word.WorldMgr;

/**
 * 申请入队
 * @author laofan
 *
 */
public class ApplyTeamAction extends TeamNoAction {

	private Team t;
	
	public ApplyTeamAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void teamExec(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		ApplyTeamReqMsg msg = ApplyTeamReqMsg.parseFrom(packet.getBytes());

		t = TeamMgr.getAllTeams().get(msg.getTeamId());
		if (t == null) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Team_Is_Not_Existed, getProtocol(), "队伍不存在");
			return;
		}
		if (t.getApplyPools().getPools().size() >= TeamMgr.TEAM_APPLY_LIST_MAX) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.TEAM_APPLY_LIST_MAX, getProtocol(), "队伍申请列表已满");
			return;
		}
		if (t.isFull()) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Team_Members_Full, getProtocol(), "队伍人数已满。不能再申请");
			return;
		}

		t.getApplyPools().addMember(player.getPlayerId());
		sendMsg();
	}
	
	
	private void sendMsg() {
		// TODO Auto-generated method stub
		ApplyTeamRespMsg.Builder resp = ApplyTeamRespMsg.newBuilder();
		resp.setTeamId(t.getId());
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_APPLY_TO_TEAM,resp);
		player.sendPbMessage(pkg);
		
		NotifyLeaderApplyRespMsg.Builder notify = NotifyLeaderApplyRespMsg.newBuilder();
		notify.setMember(this.getTeamMemberMsg(player.getPlayerId()));
		pkg = MessageUtil.buildMessage(Protocol.U_RESP_TEAM_APPLY_LEADER,notify);
		WorldMgr.getPlayer(t.getLeader().getPlayerId()).sendPbMessage(pkg);
		
	}

}
