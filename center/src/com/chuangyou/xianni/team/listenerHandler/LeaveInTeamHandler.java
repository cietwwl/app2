package com.chuangyou.xianni.team.listenerHandler;

import java.util.List;

import com.chuangyou.common.protobuf.pb.team.TeamDestroyRespProto.TeamDestroyRespMsg;
import com.chuangyou.common.protobuf.pb.team.TeamMemberLeaveRespProto.TeamMemberLeaveRespMsg;
import com.chuangyou.xianni.netty.GatewayLinkedSet;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.BroadcastUtil;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.team.TeamMgr;
import com.chuangyou.xianni.team.event.TeamMemberEvent;
import com.chuangyou.xianni.word.WorldMgr;

/**
 * 离开队伍
 * @author laofan
 *
 */
public class LeaveInTeamHandler extends TeamListener<TeamMemberEvent> {

	@Override
	public void onTeamEvent(TeamMemberEvent event) {
		// TODO Auto-generated method stub
		
		TeamMgr.addTeamTargetPool(event.getT().getTargetId(), event.getT().getId());
		//通知队伍消毁
		TeamDestroyRespMsg.Builder resp = TeamDestroyRespMsg.newBuilder();
		resp.setTeamId(event.getT().getId());
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_TEAM_DESTROY,resp);
		GamePlayer player = WorldMgr.getPlayer(event.getMember().getPlayerId());
		if(player!=null){
			player.sendPbMessage(pkg);
		}
		
		//广播其它玩家
		TeamMemberLeaveRespMsg.Builder leaveResp = TeamMemberLeaveRespMsg.newBuilder();
		leaveResp.setMemberPlayerId(event.getMember().getPlayerId());
		leaveResp.setTeamId(event.getMember().getTeamId());
		List<Long> l = event.getT().getPlayers(event.getMember().getPlayerId());
		BroadcastUtil.sendBroadcastPacket(l, Protocol.U_RESP_TEAM_LEAVE, leaveResp.build());
		
		//同步scene有人离开
		pkg = MessageUtil.buildMessage(Protocol.S_TEAM_LEAVE,leaveResp);
		GatewayLinkedSet.send2Server(pkg);
	}

}
