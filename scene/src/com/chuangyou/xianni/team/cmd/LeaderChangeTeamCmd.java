package com.chuangyou.xianni.team.cmd;

import com.chuangyou.common.protobuf.pb.team.TeamUpdateLeaderRespProto.TeamUpdateLeaderRespMsg;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.socket.Command;
import com.chuangyou.xianni.team.Team;
import com.chuangyou.xianni.team.TeamMgr;

import io.netty.channel.Channel;

@Cmd(code=Protocol.S_TEAM_LEADER_CHANE,desc="队长改变")
public class LeaderChangeTeamCmd implements Command{


	@Override
	public void execute(Channel channel, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		TeamUpdateLeaderRespMsg msg = TeamUpdateLeaderRespMsg.parseFrom(packet.getBytes());
		int teamId = msg.getTeamId();
		Team t = TeamMgr.getAllTeams().get(teamId);
		t.setLeader(msg.getNewLeader());
	}

}
