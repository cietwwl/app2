package com.chuangyou.xianni.relation.cmd;

import java.util.List;

import com.chuangyou.common.protobuf.pb.friend.GetRecommendFriendsRespProto.GetRecommendFriendsRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.constant.PlayerRelationConstant;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.relation.manager.RelationProtoUtil;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.word.WorldMgr;

@Cmd(code = Protocol.C_REQ_GETRECOMMENDFRIENDS, desc = "获取推荐好友")
public class GetRecommendFriendsCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		if (player.getRelationInventory() == null)
			return;
		if (System.currentTimeMillis() - player.getRelationInventory().lastQueryTime < 3 * 1000) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.CD_IS_NOT_ENOUGTH, Protocol.C_REQ_GETRECOMMENDFRIENDS);
			return;
		}
		player.getRelationInventory().lastQueryTime = System.currentTimeMillis();

		GetRecommendFriendsRespMsg.Builder resp = GetRecommendFriendsRespMsg.newBuilder();
		List<GamePlayer> list = WorldMgr.getOnLinePlayers();
		for (GamePlayer gamePlayer : list) {
			if(gamePlayer.getPlayerId() == player.getPlayerId()) continue;
			if(player.getRelationInventory().isRelationTypeTargetToSelf(gamePlayer.getPlayerId(), PlayerRelationConstant.FRIEND)) continue;
			resp.addRoles(RelationProtoUtil.change(gamePlayer));
		}
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_REQ_GETRECOMMENDFRIENDS, resp);
		player.sendPbMessage(pkg);

	}

}
