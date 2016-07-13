package com.chuangyou.xianni.team.listenerHandler;

import java.util.List;

import com.chuangyou.common.protobuf.pb.team.TeamAddMemberRespProto.TeamAddMemberRespMsg;
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
 * 队伍添中成员处理器
 * @author laofan
 *
 */
public class AddInTeamHandler extends TeamListener<TeamMemberEvent>{

	@Override
	public void onTeamEvent(TeamMemberEvent event) {
		// TODO Auto-generated method stub
		//todo给新加成员发送队伍消息
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_TEAM_INFO, event.getT().getTeamMsg());
		GamePlayer player = WorldMgr.getPlayer(event.getMember().getPlayerId());
		if(player!=null){
			player.sendPbMessage(pkg);
			TeamMgr.removePersonTarget(player.getBasePlayer().getTeamTarget(), player.getPlayerId());
		}
		//广播其它玩家
		TeamAddMemberRespMsg.Builder resp = TeamAddMemberRespMsg.newBuilder();
		resp.setInfo(event.getMember().getTeamMemberMsg());
		List<Long> l = event.getT().getPlayers(event.getMember().getPlayerId());
		BroadcastUtil.sendBroadcastPacket(l, Protocol.U_RESP_TEAM_ADD, resp.build());
		
		//同步scene
		resp.setTeamId(event.getT().getId());
		pkg = MessageUtil.buildMessage(Protocol.S_TEAM_ADD,resp);
		GatewayLinkedSet.send2Server(pkg);
	}

	

}
