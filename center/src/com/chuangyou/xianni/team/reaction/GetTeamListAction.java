package com.chuangyou.xianni.team.reaction;

import com.chuangyou.common.protobuf.pb.team.GetTeamListReqProto.GetTeamListReqMsg;
import com.chuangyou.common.protobuf.pb.team.GetTeamListRespProto.GetTeamListRespMsg;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.team.TeamMgr;
import com.chuangyou.xianni.team.pool.TeamMatchPool;
import com.chuangyou.xianni.team.reaction.abstractAction.TeamNoAction;
import com.chuangyou.xianni.team.struct.Team;

/**
 * 根据目标获取队伍列表
 * @author laofan
 *
 */
public class GetTeamListAction extends TeamNoAction {

	/**
	 * 目标ID
	 */
	private int targetId;
	
	public GetTeamListAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void teamExec(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		GetTeamListReqMsg msg = GetTeamListReqMsg.parseFrom(packet.getBytes());
		targetId = msg.getTargetId();
		
		TeamMatchPool pool = TeamMgr.getTargetPools().get(targetId);
		GetTeamListRespMsg.Builder resp = GetTeamListRespMsg.newBuilder();
		if(pool!=null){
			pool.sort();
			for (int id : pool.getClonePools()) {
				Team t = TeamMgr.getAllTeams().get(id);
				resp.addTeams(t.getTeamMsg());
			}
		}
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_ERSP_GET_TEAM_LIST, resp);
		player.sendPbMessage(pkg);
	}

}
