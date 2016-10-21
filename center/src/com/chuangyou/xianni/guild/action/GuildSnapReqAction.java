package com.chuangyou.xianni.guild.action;

import com.chuangyou.common.protobuf.pb.guild.GuildReqProto.GuildReqMsg;
import com.chuangyou.common.protobuf.pb.guild.GuildSnapProto.GuildSnapMsg;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.guild.action.baseAction.GuildBaseAction;
import com.chuangyou.xianni.guild.manager.GuildManager;
import com.chuangyou.xianni.guild.struct.Guild;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;

public class GuildSnapReqAction extends GuildBaseAction {

	public GuildSnapReqAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		GuildReqMsg req = GuildReqMsg.parseFrom(packet.getBytes());
		int guildId = (int)req.getParam1();
		
		Guild guild = GuildManager.getIns().getGuildMap().get(guildId);
		if(guild == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_NOT_EXIST, packet.getCode(), "帮派不存在");
			return;
		}
		
		int playerGuildId = GuildManager.getIns().getPlayerGuildId(player.getPlayerId());
		boolean hasGuild = false;
		if(playerGuildId > 0){
			hasGuild = true;
		}
		
		GuildSnapMsg.Builder respMsg = GuildSnapMsg.newBuilder();
		guild.writeSnapProto(respMsg, player.getPlayerId(), hasGuild);
		
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_GUILD_SNAP, respMsg);
		player.sendPbMessage(pkg);
	}

}
