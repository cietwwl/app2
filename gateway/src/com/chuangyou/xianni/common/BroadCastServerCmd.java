package com.chuangyou.xianni.common;

import com.chuangyou.common.protobuf.pb.common.BroadcastMsgProto.BroadcastMsg;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.socket.Command;
import com.chuangyou.xianni.user.UserMgr;

import io.netty.channel.Channel;

@Cmd(code = Protocol.G_BROADCAST_SERVER, desc = "广播包")
public class BroadCastServerCmd implements Command {

	@Override
	public void execute(Channel channel, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		BroadcastMsg bmsg = BroadcastMsg.parseFrom(packet.getBytes());
		short protocol = (short) bmsg.getProtocol();
		PBMessage message = new PBMessage(protocol);
		message.setBytes(bmsg.getPacket().toByteArray());
		UserMgr.broadcastMessage(message);
	}

}
