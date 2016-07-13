package com.chuangyou.xianni.login;

import java.util.List;

import com.chuangyou.common.protobuf.pb.LoginReqMsgProto.LoginReqMsg;
import com.chuangyou.common.protobuf.pb.PlayerCheckMsgProto.PlayerCheckMsg;
import com.chuangyou.common.protobuf.pb.PlayerInfoListMsgProto.PlayerInfoListMsg;
import com.chuangyou.common.protobuf.pb.PlayerInfoMsgProto.PlayerInfoMsg;
import com.chuangyou.xianni.common.template.SystemConfigTemplateMgr;
import com.chuangyou.xianni.entity.player.PlayerInfo;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.socket.Command;
import com.chuangyou.xianni.sql.dao.DBManager;

import io.netty.channel.Channel;

@Cmd(code = Protocol.C_LOGIN_KEY, desc = "登录第二步：验证用户KEY,通过，则返回角色列表")
public class UserValidateCmd implements Command {

	@Override
	public void execute(Channel channel, PBMessage packet) throws Exception {
		System.err.println("登录第二步：验证用户KEY，并且返回角色列表");
		LoginReqMsg loginReq = LoginReqMsg.parseFrom(packet.getBytes());
		String key = loginReq.getKey();
		long userId = loginReq.getUserId();

		boolean result = LoginTokenCache.check(userId, key);

		PlayerCheckMsg.Builder checkMsg = PlayerCheckMsg.newBuilder();
		checkMsg.setResult(result);
		checkMsg.setKey(key);
		checkMsg.setUserId(userId);

		// TODO 添加角色列表
		PlayerInfoListMsg.Builder plistMsg = PlayerInfoListMsg.newBuilder();
		List<PlayerInfo> playerInfos = DBManager.getPlayerInfoDao().getByUserId(userId);
		if (playerInfos != null && playerInfos.size() != 0) {
			for (PlayerInfo pinfo : playerInfos) {
				PlayerInfoMsg.Builder pmsg = PlayerInfoMsg.newBuilder();
				pinfo.writeProto(pmsg, SystemConfigTemplateMgr.getIntValue("bag.initGridNum"));
				plistMsg.addPlayers(pmsg.build());
			}
		}
		checkMsg.setPlayers(plistMsg.build());

		PBMessage pkg = MessageUtil.buildMessage(Protocol.G_USER_WAITE, checkMsg);
		channel.writeAndFlush(pkg);
	}

}
