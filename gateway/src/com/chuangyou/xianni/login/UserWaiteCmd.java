package com.chuangyou.xianni.login;

import com.chuangyou.common.protobuf.pb.LoginResultMsgProto.LoginResultMsg;
import com.chuangyou.common.protobuf.pb.PlayerCheckMsgProto.PlayerCheckMsg;
import com.chuangyou.common.protobuf.pb.PlayerInfoListMsgProto.PlayerInfoListMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.common.LoginResult;
import com.chuangyou.xianni.netty.AttributeKeySet;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.socket.Command;
import com.chuangyou.xianni.user.UserMgr;
import io.netty.channel.Channel;

@Cmd(code = Protocol.G_USER_WAITE, desc = "准备登陆")
public class UserWaiteCmd implements Command {

	@Override
	public void execute(Channel channel, PBMessage packet) throws Exception {
		System.err.println("==登录第三步========如果登录失败，告诉用户，如果成功，给予客户端角色列表=========");
		PlayerCheckMsg checkReq = PlayerCheckMsg.parseFrom(packet.toByteArray());
		long userId = checkReq.getUserId();
		boolean result = checkReq.getResult();
		// 找不到玩家session
		Channel tempSession = UserMgr.removeTempSession(userId);
		if (tempSession == null) {
			Log.error("can't find user session. userId : " + userId);
			return;
		}

		// key值验证错误
		if (!result) {
			LoginResultMsg.Builder lr = LoginResultMsg.newBuilder();
			lr.setResult(LoginResult.FAIL);
			lr.setTime(System.currentTimeMillis());
			PBMessage loginResult = MessageUtil.buildMessage(Protocol.U_G_LOGIN_RESULT, lr);
			tempSession.writeAndFlush(loginResult);
			tempSession.close();
			return;
		}
		tempSession.attr(AttributeKeySet.LOGIN_STATU).set(true);

		// 转发用户角色列表
		PlayerInfoListMsg plmsg = checkReq.getPlayers();
		PBMessage message = MessageUtil.buildMessage(Protocol.U_G_PLAYER_LIST, plmsg);
		tempSession.writeAndFlush(message);
	}
}
