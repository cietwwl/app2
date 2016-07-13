package com.chuangyou.xianni.team.reaction;

import com.chuangyou.common.protobuf.pb.team.ClearApplyListRespProto.ClearApplyListRespMsg;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.team.reaction.abstractAction.TeamLeaderAction;

/**
 *  队长清空申请列表
 * @author laofan
 *
 */
public class ClearApplyListAction extends TeamLeaderAction {

	public ClearApplyListAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void teamLeaderExec(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		t.getApplyPools().clear();

		ClearApplyListRespMsg.Builder msg = ClearApplyListRespMsg.newBuilder();
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_TEAM_CLEAR_APPLY_LIST, msg);
		player.sendPbMessage(pkg);
	}

}
