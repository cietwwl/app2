package com.chuangyou.xianni.map.cmd;

import com.chuangyou.common.protobuf.pb.ReqChangeMapMsgProto.ReqChangeMapMsg;
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
		
		// TODO 判断，是否允许玩家切换场景

		// TODO 向场景服务器申请场景进入
		PBMessage message = MessageUtil.buildMessage(Protocol.S_ENTERSCENE, player.getPlayerId(), msg);
		player.sendPbMessage(message);
	}

}
