package com.chuangyou.xianni.login;

import com.chuangyou.common.protobuf.pb.PlayerCreateInnerProto.PlayerCreateInnerMsg;
import com.chuangyou.common.protobuf.pb.PlayerCreateProto.PlayerCreateMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.conn.ClientSet;
import com.chuangyou.xianni.netty.AttributeKeySet;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.socket.Command;
import com.chuangyou.xianni.user.UserMgr;

import io.netty.channel.Channel;

@Cmd(code = Protocol.G_PLAYER_CREATE, desc = "请求创建角色")
public class PlayerCreateCmd implements Command {

	@Override
	public void execute(Channel channel, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		if (channel.attr(AttributeKeySet.TEMP_USER_ID).toString().equals("null")) {
			Log.error("can't find user session.");
			return;
		}

		long userId = channel.attr(AttributeKeySet.TEMP_USER_ID).get();

		String key = channel.attr(AttributeKeySet.LOGIN_KEY).get();
		UserMgr.addTempSession(userId, key, channel);

		PlayerCreateMsg req = PlayerCreateMsg.parseFrom(packet.getBytes());

		PlayerCreateInnerMsg.Builder playerCreateMsg = PlayerCreateInnerMsg.newBuilder();
		playerCreateMsg.setUserId(userId);
		playerCreateMsg.setRoleConfigId(req.getSkin());
		playerCreateMsg.setNickName(req.getNickName());
		PBMessage p = MessageUtil.buildMessage(Protocol.C_PLAYER_CREATE, playerCreateMsg);
		ClientSet.routeCenter(p);
	}

}
