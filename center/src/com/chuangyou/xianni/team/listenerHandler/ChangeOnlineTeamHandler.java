package com.chuangyou.xianni.team.listenerHandler;

import com.chuangyou.common.protobuf.pb.team.TeamUpdateLineRespProto.TeamUpdateLineRespMsg;
import com.chuangyou.xianni.proto.BroadcastUtil;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.team.event.TeamMemberEvent;

/**
 * 在线状态改变
 * @author laofan
 *
 */
public class ChangeOnlineTeamHandler extends TeamListener<TeamMemberEvent> {

	@Override
	public void onTeamEvent(TeamMemberEvent event) {
		// TODO Auto-generated method stub
		TeamUpdateLineRespMsg.Builder resp = TeamUpdateLineRespMsg.newBuilder();
		resp.setIsOnline(event.getMember().isOnline());
		resp.setMemberPlayerId(event.getMember().getPlayerId());
		BroadcastUtil.sendBroadcastPacket(event.getT().getPlayers(event.getMember().getPlayerId()), Protocol.U_RESP_TEAM_LINE_CHANGE, resp.build());
	}

}
