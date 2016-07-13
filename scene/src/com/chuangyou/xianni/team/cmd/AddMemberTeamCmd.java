package com.chuangyou.xianni.team.cmd;

import com.chuangyou.common.protobuf.pb.team.TeamAddMemberRespProto.TeamAddMemberRespMsg;
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

/**
 * 有人进队
 * 
 * @author laofan
 *
 */
@Cmd(code = Protocol.S_TEAM_ADD, desc = "加入")
public class AddMemberTeamCmd implements Command {

	@Override
	public void execute(Channel channel, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		TeamAddMemberRespMsg msg = TeamAddMemberRespMsg.parseFrom(packet.getBytes());
		int teamId = msg.getTeamId();
		Team t = TeamMgr.getAllTeams().get(teamId);
		t.addMember(msg.getInfo().getPlayerId());

		// todo同步player数据
		ArmyProxy player = WorldMgr.getArmy(msg.getInfo().getPlayerId());
		if (player != null) {
			player.getPlayer().updateProperty(EnumAttr.TEAM_ID, t.getTeamid());
		}

	}

}
