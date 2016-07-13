package com.chuangyou.xianni.login;

import com.chuangyou.common.protobuf.pb.PlayerLoginMsgProto.PlayerLoginMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.player.PlayerInfoSendCmd;
import com.chuangyou.xianni.player.PlayerState;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.socket.Command;
import com.chuangyou.xianni.team.reaction.ChangeLineAction;
import com.chuangyou.xianni.word.WorldMgr;

import io.netty.channel.Channel;

@Cmd(code = Protocol.C_PLAYER_LOGIN, desc = "第五步：角色真正登入业务服，加载用户数据，返回用户基础数据")
public class PlayerLoginReceiveCmd implements Command {
	@Override
	public void execute(Channel channel, PBMessage packet) throws Exception {
		System.err.println("登录第五步：返回用户基本数据");
		PlayerLoginMsg plmsg = PlayerLoginMsg.parseFrom(packet.toByteArray());
		long playerId = plmsg.getPlayerId();
		GamePlayer player = WorldMgr.getPlayer(playerId);
		// TODO 加载用户
		if (player == null) {
			Log.error("player login error in PlayerLoginReceiveCmd，playerId:" + playerId);
			return;
		}
		player.setChannel(channel);
		player.loadPersonData();
		player.setPlayerState(PlayerState.ONLINE);

		ChangeLineAction action = new ChangeLineAction(player, null, true);
		action.getActionQueue().enqueue(action);

		// TODO 返回角色数据
		player.sendPbMessage(
				MessageUtil.buildMessage(Protocol.U_G_PLAYERINFO, PlayerInfoSendCmd.getProperPacket(player)));

		// 通知sence服务器用户登入（同时传递用户数据）
		PBMessage sencesReq = MessageUtil.buildMessage(Protocol.S_LOGIN_IN, -1,
				PlayerInfoSendCmd.getArmyPacket(player));
		channel.writeAndFlush(sencesReq);

	}
}
