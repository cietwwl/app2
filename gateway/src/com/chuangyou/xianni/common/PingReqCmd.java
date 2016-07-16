package com.chuangyou.xianni.common;

import com.chuangyou.common.protobuf.pb.PingRspProto.PingRspMsg;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.socket.Command;
import com.chuangyou.xianni.user.User;
import com.chuangyou.xianni.user.UserMgr;

import io.netty.channel.Channel;

@Cmd(code = Protocol.G_PING_PACKET, desc = "ping包")
public class PingReqCmd implements Command {

	@Override
	public void execute(Channel channel, PBMessage packet) throws Exception {
		long playerId = packet.getPlayerId();
		if (playerId != 0) {
			User user = UserMgr.getOnlineUser(playerId);
			if(user==null)return;
			user.setLastSyncTime(System.currentTimeMillis());
		}
		PingRspMsg.Builder builder = PingRspMsg.newBuilder();
		builder.setTime(System.currentTimeMillis());

		PBMessage message = MessageUtil.buildMessage(Protocol.U_G_PING_PACKET, playerId, builder);
		channel.write(message);
		channel.flush();
	}

}
