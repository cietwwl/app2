package com.chuangyou.xianni.team.reaction;

import com.chuangyou.common.protobuf.pb.team.ClearMatchRespProto.ClearMatchRespMsg;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.team.TeamMgr;
import com.chuangyou.xianni.team.reaction.abstractAction.TeamLeaderAction;

/**
 * 队长清除队伍的自动匹配
 * 
 * @author laofan
 *
 */
public class LeaderClearMatchAction extends TeamLeaderAction {

	public LeaderClearMatchAction(GamePlayer player, PBMessage packet, short p) {
		super(player, packet);
		this.protocol = p;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void teamLeaderExec(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		TeamMgr.removeTeamTargetMatch(t.getTargetId(), t.getId());

		ClearMatchRespMsg.Builder resp = ClearMatchRespMsg.newBuilder();
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_TEAM_CLEAR_MATCH, resp);
		player.sendPbMessage(pkg);
	}

}
