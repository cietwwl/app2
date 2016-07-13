package com.chuangyou.xianni.army.cmd;

import com.chuangyou.common.protobuf.pb.battle.PlayerRevivalMsgProto.PlayerRevivalMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_PLAYER_REVIVAL, desc = "复活")
public class PlayerRevivalCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		PlayerRevivalMsg msg = PlayerRevivalMsg.parseFrom(packet.getBytes());

		// 扣除复活费用
		PBMessage message = MessageUtil.buildMessage(Protocol.S_PLAYER_REVIVAL, msg);
		player.sendPbMessage(message);
	}

}
