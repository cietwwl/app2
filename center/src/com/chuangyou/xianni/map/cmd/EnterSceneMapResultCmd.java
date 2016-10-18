package com.chuangyou.xianni.map.cmd;


import java.util.Date;

import com.chuangyou.common.protobuf.pb.ChangeMapResultMsgProto.ChangeMapResultMsg;
import com.chuangyou.common.protobuf.pb.PostionMsgProto.PostionMsg;
import com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3;
import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.TimeUtil;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.constant.EnterMapResult;
import com.chuangyou.xianni.entity.field.FieldInfo;
import com.chuangyou.xianni.entity.player.PlayerPositionInfo;
import com.chuangyou.xianni.map.MapProxyManager;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_ENTER_SENCE_MAP_RESULT, desc = "场景服务器地图变更结果")
public class EnterSceneMapResultCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		ChangeMapResultMsg msg = ChangeMapResultMsg.parseFrom(packet.getBytes());
		int result = msg.getResult();

		if (result == EnterMapResult.SUCCESS) {
			// 更新用户当前地图场景,并且,如果是固定地图(大地图),则更新保留节点
			PlayerPositionInfo pinfo = player.getBasePlayer().getPlayerPositionInfo();
			FieldInfo mapTemp = MapProxyManager.getFieldTempInfo(pinfo.getMapTempId());
			PostionMsg postionMsg = msg.getPostion();
			if (mapTemp != null && mapTemp.getType() == 1) {
				pinfo.setPreMapId(pinfo.getMapId());
				pinfo.setPreMapTempId(pinfo.getMapTempId());
				pinfo.setPreX(pinfo.getX());
				pinfo.setPreY(pinfo.getY());
				pinfo.setPreZ(pinfo.getZ());
			}

			PBVector3 v3 = postionMsg.getPostion();
			if (postionMsg.getMapId() != 0 && postionMsg.getMapKey() != 0 && (v3.getX() != 0 || v3.getY() != 0 || v3.getZ() != 0)) {
				pinfo.setMapId(postionMsg.getMapId());
				pinfo.setMapTempId(postionMsg.getMapKey());
				pinfo.setX(postionMsg.getPostion().getX());
				pinfo.setY(postionMsg.getPostion().getY());
				pinfo.setZ(postionMsg.getPostion().getZ());
			}

			PBMessage message = MessageUtil.buildMessage(Protocol.U_CHANGE_MAP, msg);
			player.sendPbMessage(message);
			Log.error((TimeUtil.getDateFormat(new Date()) + "---------通知客户端修改地图-------playerId:-----"+ player.getPlayerId() +" msg:" +msg.toString()));
			player.setCurMapId(postionMsg.getMapId());
		} else {
			// 当用户登录请求地图失败，则直接回到出生点
			player.backBornPoint();
		}
	}

}
