package com.chuangyou.xianni.guild.action;

import com.chuangyou.common.protobuf.pb.guild.GuildInfoRespProto.GuildInfoRespMsg;
import com.chuangyou.common.protobuf.pb.guild.GuildRespProto.GuildRespMsg;
import com.chuangyou.xianni.guild.GuildOperateAction;
import com.chuangyou.xianni.guild.action.baseAction.GuildBaseAction;
import com.chuangyou.xianni.guild.manager.GuildManager;
import com.chuangyou.xianni.guild.struct.Guild;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;

public class GuildInfoGetAction extends GuildBaseAction {

	public GuildInfoGetAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(GamePlayer player, PBMessage packet) {
		// TODO Auto-generated method stub

		Guild guild = GuildManager.getIns().getPlayerGuild(player.getPlayerId());
		GuildRespMsg.Builder respMsg = GuildRespMsg.newBuilder();
		respMsg.setAction(GuildOperateAction.REQUEST_GUILD_INFO);
		if(guild == null){
			respMsg.setResult(1);
		}else{
			GuildInfoRespMsg.Builder msg = GuildInfoRespMsg.newBuilder();
			guild.writeProto(msg, player.getPlayerId());
			PBMessage pkg = MessageUtil.buildMessage(Protocol.U_GUILD_INFO, msg);
			player.sendPbMessage(pkg);
			
			respMsg.setResult(0);
		}
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_GUILD_ACTION_RESP, respMsg);
		player.sendPbMessage(pkg);
	}

}
