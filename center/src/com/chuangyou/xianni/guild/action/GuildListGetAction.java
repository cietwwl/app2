package com.chuangyou.xianni.guild.action;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import com.chuangyou.common.protobuf.pb.guild.GuildListRespProto.GuildListRespMsg;
import com.chuangyou.common.protobuf.pb.guild.GuildSnapProto.GuildSnapMsg;
import com.chuangyou.xianni.guild.action.baseAction.GuildBaseAction;
import com.chuangyou.xianni.guild.manager.GuildManager;
import com.chuangyou.xianni.guild.struct.Guild;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;

public class GuildListGetAction extends GuildBaseAction {

	public GuildListGetAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(GamePlayer player, PBMessage packet) {
		// TODO Auto-generated method stub

		Guild playerGuild = GuildManager.getIns().getPlayerGuild(player.getPlayerId());
		boolean hasGuild = (playerGuild != null);
		ConcurrentHashMap<Integer, Guild> guildMap = GuildManager.getIns().getGuildMap();
		
		GuildListRespMsg.Builder msg = GuildListRespMsg.newBuilder();
		Iterator<Guild> iterator = guildMap.values().iterator();
		int count = 0;
		boolean hasStartPkg = false;
		while(iterator.hasNext()){
			Guild guild = iterator.next();
			
			GuildSnapMsg.Builder snapMsg = GuildSnapMsg.newBuilder();
			guild.writeSnapProto(snapMsg, player.getPlayerId(), hasGuild);
			msg.addGuild(snapMsg.build());
			count ++;
			
			if(count%100 == 0 && iterator.hasNext()){
				if(count <= 100){
					sendListMsg(player, 0, msg);
					hasStartPkg = true;
				}else{
					sendListMsg(player, 1, msg);
				}
				msg = GuildListRespMsg.newBuilder();
			}
		}
		
		if(hasStartPkg == false){
			sendListMsg(player, 0, msg);
			msg = GuildListRespMsg.newBuilder();
		}
		
		sendListMsg(player, 2, msg);
	}
	
	private void sendListMsg(GamePlayer player, int flag, GuildListRespMsg.Builder msg){
		msg.setFlag(flag);
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_GUILD_LIST, msg);
		player.sendPbMessage(pkg);
	}

}
