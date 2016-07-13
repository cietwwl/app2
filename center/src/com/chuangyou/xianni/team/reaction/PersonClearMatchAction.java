package com.chuangyou.xianni.team.reaction;

import com.chuangyou.common.protobuf.pb.team.ClearMatchRespProto.ClearMatchRespMsg;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.team.TeamMgr;
import com.chuangyou.xianni.team.reaction.abstractAction.TeamNoAction;

/**
 * 个人清除自己的自动匹配
 * 
 * @author laofan
 *
 */
public class PersonClearMatchAction extends TeamNoAction {

	public PersonClearMatchAction(GamePlayer player, PBMessage packet, short p) {
		super(player, packet);
		this.protocol = p;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void teamExec(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		TeamMgr.removePersonTarget(player.getBasePlayer().getTeamTarget(), player.getPlayerId());

		ClearMatchRespMsg.Builder resp = ClearMatchRespMsg.newBuilder();
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_TEAM_CLEAR_MATCH, resp);
		player.sendPbMessage(pkg);
	}

}
