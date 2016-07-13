package com.chuangyou.xianni.team.listenerHandler;

import com.chuangyou.common.protobuf.pb.team.TeamUpdateLeaderRespProto.TeamUpdateLeaderRespMsg;
import com.chuangyou.xianni.netty.GatewayLinkedSet;
import com.chuangyou.xianni.proto.BroadcastUtil;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.team.TeamMgr;
import com.chuangyou.xianni.team.event.TeamMemberEvent;

/**
 * 队长发生改变
 * @author laofan
 *
 */
public class LeaderChangeTeamHandler extends TeamListener<TeamMemberEvent> {

	@Override
	public void onTeamEvent(TeamMemberEvent event) {
		// TODO Auto-generated method stub
		//todo 队长变更要取消队伍自动匹配
		TeamMgr.removeTeamTargetMatch(event.getT().getTargetId(), event.getT().getId());
		
		//广播
		TeamUpdateLeaderRespMsg.Builder resp = TeamUpdateLeaderRespMsg.newBuilder();
		resp.setNewLeader(event.getMember().getPlayerId());
		resp.setTeamId(event.getT().getId());
		BroadcastUtil.sendBroadcastPacket(event.getT().getPlayers(0), Protocol.U_RESP_TEAM_LEADER_CHANGE, resp.build());
		
		//同步scene
		PBMessage pkg = MessageUtil.buildMessage(Protocol.S_TEAM_LEADER_CHANE, resp);
		GatewayLinkedSet.send2Server(pkg);
	}

}
