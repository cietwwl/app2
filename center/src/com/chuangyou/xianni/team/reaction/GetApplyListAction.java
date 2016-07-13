package com.chuangyou.xianni.team.reaction;

import com.chuangyou.common.protobuf.pb.team.GetApplyListRespProto.GetApplyListRespMsg;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.team.reaction.abstractAction.TeamLeaderAction;

/**
 * 队长获取申请列表
 * @author laofan
 *
 */
public class GetApplyListAction extends TeamLeaderAction {

	public GetApplyListAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void teamLeaderExec(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		sendMsg();
	}
	

	
	
	private void sendMsg() {
		// TODO Auto-generated method stub
		GetApplyListRespMsg.Builder resp = GetApplyListRespMsg.newBuilder();
		for (long playerId : t.getApplyPools().getPools()) {
			resp.addMembers(getTeamMemberMsg(playerId));
		}
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_GET_APPLY_LIST,resp);
		player.sendPbMessage(pkg);
	}

}
