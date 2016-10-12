package com.chuangyou.xianni.guild.action;

import com.chuangyou.common.protobuf.pb.guild.GuildRespProto.GuildRespMsg;
import com.chuangyou.xianni.guild.GuildOperateAction;
import com.chuangyou.xianni.guild.action.baseAction.GuildBaseAction;
import com.chuangyou.xianni.guild.manager.GuildManager;
import com.chuangyou.xianni.guild.struct.Guild;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.BroadcastUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;

public class GuildMemberStateAction extends GuildBaseAction {

	public GuildMemberStateAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		long playerId = player.getPlayerId();
		Guild guild = GuildManager.getIns().getPlayerGuild(playerId);
		if(guild == null) return;
		
		//通知帮派成员
		GuildRespMsg.Builder notifyMsg = GuildRespMsg.newBuilder();
		notifyMsg.setAction(GuildOperateAction.GUILD_PLAYER_STATE);
		notifyMsg.setParam1(playerId);
		notifyMsg.setParam2(player.getPlayerState());
		notifyMsg.setParam3(player.getPlayerOfflineTime().getTime());
		notifyMsg.setParamStr(player.getNickName());
		BroadcastUtil.sendBroadcastPacket(guild.getMemberIds(), Protocol.U_GUILD_ACTION_RESP, notifyMsg.build());
	}

}
