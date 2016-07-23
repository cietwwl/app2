package com.chuangyou.xianni.team.cmd;

import com.chuangyou.common.protobuf.pb.team.TeamOptionMsgProto.TeamOptionMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.team.TeamMgr;
import com.chuangyou.xianni.team.struct.Team;
import com.chuangyou.xianni.team.struct.TeamMember;

@Cmd(code = Protocol.C_MEMBER_COMMON_OPTION, desc = "队伍一般操作")
public class TeamCommonOptionCmd extends AbstractCommand {
	static final int	GO_TARGET		= 101;	// 前往目标
	static final int	PREPARE			= 201;	// 成员准备
	static final int	DE_PREPARE		= 202;	// 成员取消准备
	static final int	PERSON_GOING	= 203;	// 成员独自前往目标

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		TeamOptionMsg msg = TeamOptionMsg.parseFrom(packet.getBytes());

		Team team = TeamMgr.getPlayerTeam(player.getPlayerId());
		if (team == null) {
			Log.error("TeamCommonOptionCmd, team is null ,playerId : " + player.getPlayerId());
			return;
		}

		if (msg.getOption() == GO_TARGET) {
			if (team.getLeader() == null || team.getLeader().getPlayerId() != player.getPlayerId()) {
				Log.error("option gotarget but is not leader");
				return;
			}
			team.changeTeamStatu(Team.PREPARE);
			return;
		}

		if (msg.getOption() == PREPARE) {
			team.changeMemberStatu(TeamMember.PREPARE, player.getPlayerId());
			return;
		}

		if (msg.getOption() == DE_PREPARE) {
			team.changeMemberStatu(TeamMember.DE_PREPARE, player.getPlayerId());
			return;
		}

		if (msg.getOption() == PERSON_GOING) {
			
		}
	}

}
