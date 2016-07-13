package com.chuangyou.xianni.team.cmd;

import com.chuangyou.common.protobuf.pb.team.TeamInfoRespProto.TeamInfoRespMsg;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.socket.Command;
import com.chuangyou.xianni.team.Team;
import com.chuangyou.xianni.team.TeamMgr;
import com.chuangyou.xianni.world.ArmyProxy;
import com.chuangyou.xianni.world.WorldMgr;

import io.netty.channel.Channel;

@Cmd(code=Protocol.S_TEAM_INFO,desc="建队")
public class CreateTeamCmd implements Command {

	
	@Override
	public void execute(Channel channel, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		TeamInfoRespMsg msg = TeamInfoRespMsg.parseFrom(packet.getBytes());
		Team t = new Team(msg.getTeamId());
		t.setLeader(msg.getLeaderPlayerId());
		t.addMember(msg.getLeaderPlayerId());
		TeamMgr.addTeam(t);
		
		//todo同步player数据
		ArmyProxy player = WorldMgr.getArmy(msg.getLeaderPlayerId());
		if(player!=null){			
			player.getPlayer().updateProperty(EnumAttr.TEAM_ID,t.getTeamid());
		}
		
	}

}
