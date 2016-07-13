package com.chuangyou.xianni.team.cmd;

import com.chuangyou.common.protobuf.pb.team.TeamDestroyRespProto.TeamDestroyRespMsg;
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

@Cmd(code=Protocol.S_TEAM_DESTROY,desc="")
public class DestroyTeamCmd implements Command {

	

	@Override
	public void execute(Channel channel, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		TeamDestroyRespMsg msg =  TeamDestroyRespMsg.parseFrom(packet.getBytes());
		
		Team t = TeamMgr.getAllTeams().get(msg.getTeamId()); 
		TeamMgr.removeTeam(msg.getTeamId());
		
		// todo同步player数据
		if(t!=null){
			for (long playerId : t.getMembers()) {
				ArmyProxy player = WorldMgr.getArmy(playerId);
				if (player != null) {
					player.getPlayer().updateProperty(EnumAttr.TEAM_ID, 0);
				}
			}
		}
	}

}
