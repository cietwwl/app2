package com.chuangyou.xianni.campaign.cmd;

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

@Cmd(code = Protocol.C_QUIT_CAMPAIGN, desc = "退出副本")
public class QuitCampaignCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		PlayerPositionInfo pos = player.getBasePlayer().getPlayerPositionInfo();

		if (pos == null || pos.getPreMapId() == 0) {
			player.backBornPoint();
			return;
		}
		ReqChangeMapMsg.Builder req = ReqChangeMapMsg.newBuilder();
		PostionMsg.Builder pmsg = PostionMsg.newBuilder();
		pmsg.setMapId(pos.getPreMapId());
		pmsg.setMapKey(pos.getPreMapTempId());
		PBVector3.Builder v3 = PBVector3.newBuilder();
		v3.setX(pos.getPreX());
		v3.setY(pos.getPreY());
		v3.setZ(pos.getPreZ());
		pmsg.setPostion(v3);
		req.setPostionMsg(pmsg);
		PBMessage message = MessageUtil.buildMessage(Protocol.S_ENTERSCENE, player.getPlayerId(), req);
		player.sendPbMessage(message);
	}

}
