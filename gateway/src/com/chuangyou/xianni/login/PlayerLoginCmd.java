package com.chuangyou.xianni.login;

import com.chuangyou.common.protobuf.pb.GatewayMsgProto.GatewayMsg;
import com.chuangyou.common.protobuf.pb.LoginGameReqMsgProto.LoginGameReqMsg;
import com.chuangyou.common.protobuf.pb.LoginResultMsgProto.LoginResultMsg;
import com.chuangyou.common.protobuf.pb.PlayerLoginMsgProto.PlayerLoginMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.conn.ClientSet;
import com.chuangyou.xianni.netty.AttributeKeySet;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.socket.Command;
import com.chuangyou.xianni.user.User;
import com.chuangyou.xianni.user.UserMgr;

import io.netty.channel.Channel;

@Cmd(code = Protocol.G_PLAYER_LOGIN, desc = "角色登录")
public class PlayerLoginCmd implements Command {

	@Override
	public void execute(Channel channel, PBMessage packet) throws Exception {
		System.err.println("登录第四步，用户直接使用角色登录========执行角色登录=========");
		LoginGameReqMsg lgmsg = LoginGameReqMsg.parseFrom(packet.getBytes());
		long playerId = lgmsg.getPlayerID();
		// 用户是否处于账号已登录状态
		if (channel.attr(AttributeKeySet.LOGIN_STATU) == null) {
			Log.warn("player login but login statu is null,playerId:" + playerId);
			return;
		}
		if (channel.attr(AttributeKeySet.LOGIN_STATU).get() == false) {
			Log.warn("player login but login statu is false,playerId:" + playerId);
			return;
		}
		// 从在线列表中挤人
		if (UserMgr.checkOnline(playerId)) {
			User user = UserMgr.getOnlineUser(playerId);
			if (user != null) {
				try {
					// 挤老用户下线
					GatewayMsg.Builder gatewayMsg = GatewayMsg.newBuilder();
					// "您的账号在别处登录！"
					gatewayMsg.setNotifyMsg("你的用户在别处登录");
					PBMessage resp = MessageUtil.buildMessage(Protocol.U_G_LOGIN_OTHER, gatewayMsg);
					user.sendToUser(resp);
					Log.info("被其他玩家挤下线 , serverName : " + user.getServerName() + " ,userId : " + playerId);
				} catch (Exception e) {
					Log.error("挤在线玩家错误 userId : " + playerId + ", nickName : " + user.getServerName(), e);
				} finally {
					UserMgr.removeOnline(playerId, user.getChannel());
				}
			}
		}
		UserMgr.addOnline(playerId, channel);

		// 通知center服务器，用户登录角色
		PlayerLoginMsg.Builder ploginMsg = PlayerLoginMsg.newBuilder();
		ploginMsg.setPlayerId(playerId);
		PBMessage loadInfoMessage = MessageUtil.buildMessage(Protocol.C_PLAYER_LOGIN, ploginMsg);
		ClientSet.routeCenter(loadInfoMessage);
	}

}
