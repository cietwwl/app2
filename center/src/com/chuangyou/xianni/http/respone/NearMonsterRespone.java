package com.chuangyou.xianni.http.respone;

import java.util.Map;

import com.chuangyou.common.protobuf.pb.PostionMsgProto.PostionMsg;
import com.chuangyou.common.protobuf.pb.ReqChangeMapMsgProto.ReqChangeMapMsg;
import com.chuangyou.xianni.constant.PlayerState;
import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;
import com.chuangyou.xianni.http.HttpResult;
import com.chuangyou.xianni.http.HttpResult.Code;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.word.WorldMgr;

@HttpCmd(command = "nearMonster", desc = "移动到附近怪旁边")
public class NearMonsterRespone implements BaseRespone {
	@Override
	public String getResult(Map<String, String> params) throws Exception {
		long playerId = Long.valueOf(params.get("playerId"));
		GamePlayer player = WorldMgr.getPlayerFromCache(playerId);
		int monsterType = Integer.valueOf(params.get("monsterType"));

		if (player != null && player.getPlayerState() == PlayerState.ONLINE) {

			ReqChangeMapMsg.Builder builder = ReqChangeMapMsg.newBuilder();
			PostionMsg.Builder pos = PostionMsg.newBuilder();
			pos.setMapId(monsterType);
			builder.setPostionMsg(pos);

			PBMessage message = MessageUtil.buildMessage(Protocol.S_NEARMONSTER, player.getPlayerId(), builder.build());
			player.sendPbMessage(message);

			return HttpResult.getResult(Code.SUCCESS, "成功");
		}
		return HttpResult.getResult(Code.ERROR, "失败");
	}
}
