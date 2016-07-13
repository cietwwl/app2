package com.chuangyou.xianni.login;

import com.chuangyou.common.protobuf.pb.LoginReqMsgProto.LoginReqMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.conn.ClientSet;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.socket.Command;
import com.chuangyou.xianni.user.UserMgr;
import io.netty.channel.Channel;

@Cmd(code = Protocol.G_LOGIN_GATEWAY, desc = "登录第一步：登录网关")
public class UserLoginCmd implements Command {

	@Override
	public void execute(Channel channel, PBMessage packet) throws Exception {
		try {
			System.err.println("==登录第一步：========执行登录网关=========");
			LoginReqMsg loginReq = LoginReqMsg.parseFrom(packet.toByteArray());
			String key = loginReq.getKey();
			long userId = loginReq.getUserId();

			// 保存未经过验证channel
			UserMgr.addTempSession(userId, key, channel);

			// 发送验证信息到center
			LoginReqMsg.Builder keyVerifyReq = LoginReqMsg.newBuilder();
			keyVerifyReq.setKey(key);
			keyVerifyReq.setUserId(userId);
			PBMessage pkg = MessageUtil.buildMessage(Protocol.C_LOGIN_KEY, -1, keyVerifyReq);
			ClientSet.routeCenter(pkg);
			
		} catch (Exception e) {
			Log.error("UserLoginCmd", e);
		}
	}

}
