package com.chuangyou.xianni.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import com.chuangyou.common.protobuf.pb.battle.AttackBroadcastMsgProto.AttackBroadcastMsg;
import com.chuangyou.common.protobuf.pb.common.BroadcastMsgProto.BroadcastMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.TimeUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.socket.Command;
import com.chuangyou.xianni.user.User;
import com.chuangyou.xianni.user.UserMgr;

import io.netty.channel.Channel;

@Cmd(code = Protocol.G_BROADCAST_PACKET, desc = "广播包")
public class BroadCastPacketCmd implements Command {

	@Override
	public void execute(Channel channel, PBMessage packet) throws Exception {
		BroadcastMsg bmsg = BroadcastMsg.parseFrom(packet.getBytes());
		List<Long> ids = bmsg.getPlayersList();
		short protocol = (short) bmsg.getProtocol();
		PBMessage message = new PBMessage(protocol);
		message.setBytes(bmsg.getPacket().toByteArray());

		for (long id : ids) {
			User user = UserMgr.getOnlineUser(id);
			if (user != null) {
				message.setPlayerId(id);
				user.sendToUser(message);
			}
		}
	}
	
}
