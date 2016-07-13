package com.chuangyou.xianni.login;

import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.socket.Command;
import com.chuangyou.xianni.user.UserMgr;

import io.netty.channel.Channel;

@Cmd(code = Protocol.G_PLAYER_LOGIN_OUT, desc = "用户退出")
public class LogOutCmd implements Command{

	@Override
	public void execute(Channel channel, PBMessage packet) throws Exception {
		long playerID = packet.getPlayerId();
		UserMgr.removeOnline(playerID, channel);
		
	}

}
