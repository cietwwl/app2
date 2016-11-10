package com.chuangyou.xianni.map.cmd;

import com.chuangyou.common.protobuf.pb.PostionMsgProto.PostionMsg;
import com.chuangyou.common.protobuf.pb.ReqChangeMapMsgProto.ReqChangeMapMsg;
import com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.entity.player.PlayerPositionInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_CHANGE_MAP, desc = "用户请求变更地图")
public class ChangeMapCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// 玩家当前位置信息
		PlayerPositionInfo pinfo = player.getBasePlayer().getPlayerPositionInfo();

		// 玩家目标位置信息
		ReqChangeMapMsg msg = ReqChangeMapMsg.parseFrom(packet.getBytes());

		// 记录玩家在副本，但是玩家下线后副本已经结束
		if (msg.getPostionMsg().getMapId() > 1000000 && player.getCurCampaign() == 0) {
			ReqChangeMapMsg.Builder builder = ReqChangeMapMsg.newBuilder();
			PostionMsg.Builder pos = PostionMsg.newBuilder();
			pos.setMapId(pinfo.getPreMapId());
			pos.setMapKey(pinfo.getPreMapTempId());
			PBVector3.Builder v3 = PBVector3.newBuilder();
			v3.setX(pinfo.getPreX());
			v3.setY(pinfo.getPreY());
			v3.setZ(pinfo.getPreZ());
			pos.setPostion(v3);
			builder.setPostionMsg(pos);
			msg = builder.build();
		}

		// TODO 判断，是否允许玩家切换场景

		// TODO 向场景服务器申请场景进入
		PBMessage message = MessageUtil.buildMessage(Protocol.S_ENTERSCENE, player.getPlayerId(), msg);
		player.sendPbMessage(message);
	}

}
