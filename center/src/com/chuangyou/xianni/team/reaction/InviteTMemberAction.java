package com.chuangyou.xianni.team.reaction;

import com.chuangyou.common.protobuf.pb.team.InviteInTeamReqProto.InviteInTeamReqMsg;
import com.chuangyou.common.protobuf.pb.team.NotifyInviteRespProto.NotifyInviteRespMsg;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.team.TeamMgr;
import com.chuangyou.xianni.team.reaction.abstractAction.TeamLeaderAction;
import com.chuangyou.xianni.word.WorldMgr;

/**
 * 队长邀请某人加入队伍
 * @author laofan
 *
 */
public class InviteTMemberAction extends TeamLeaderAction {

	public InviteTMemberAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void teamLeaderExec(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		InviteInTeamReqMsg req = InviteInTeamReqMsg.parseFrom(packet.getBytes());
		long playerId = req.getPlayerId();
		if(TeamMgr.getPlayerTeamMap().containsKey(playerId)){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Team_SomeBody_Has_Team, getProtocol(),"已经有队伍啦");
			return;
		}
		
		if(t.isFull()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Team_Members_Full, getProtocol(),"队伍人数已满");
			return;
		}
		
		NotifyInviteRespMsg.Builder resp = NotifyInviteRespMsg.newBuilder();
		resp.setTeamInfo(t.getTeamMsg());
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_NOTIFY_INVITE, resp);
		GamePlayer p = WorldMgr.getPlayer(playerId);
		if(p!=null){
			p.sendPbMessage(pkg);
		}
	}

}
