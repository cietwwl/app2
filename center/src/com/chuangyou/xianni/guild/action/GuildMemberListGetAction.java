package com.chuangyou.xianni.guild.action;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import com.chuangyou.common.protobuf.pb.guild.GuildMemberInfoProto.GuildMemberInfoMsg;
import com.chuangyou.common.protobuf.pb.guild.GuildMemberListRespProto.GuildMemberListRespMsg;
import com.chuangyou.xianni.entity.guild.GuildMemberInfo;
import com.chuangyou.xianni.guild.action.baseAction.GuildIsGuildMemberAction;
import com.chuangyou.xianni.guild.struct.Guild;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;

public class GuildMemberListGetAction extends GuildIsGuildMemberAction {

	public GuildMemberListGetAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(GamePlayer player, PBMessage packet, Guild guild, GuildMemberInfo memberInfo) {
		// TODO Auto-generated method stub

		ConcurrentHashMap<Long, GuildMemberInfo> memberMap = guild.getMemberMap();
		Iterator<Long> iterator = memberMap.keySet().iterator();
		
		GuildMemberListRespMsg.Builder msg = GuildMemberListRespMsg.newBuilder();
		int count = 0;
		boolean hasStartPkg = false;
		while(iterator.hasNext()){
			long memberId = iterator.next();
			
			GuildMemberInfoMsg.Builder memberMsg = GuildMemberInfoMsg.newBuilder();
			guild.writeMemberProto(memberMsg, memberId);
			msg.addMember(memberMsg.build());
			count ++;
			
			if(count%100 == 0 && iterator.hasNext()){
				if(count <= 100){
					sendListMsg(player, 0, msg);
					hasStartPkg = true;
				}else{
					sendListMsg(player, 1, msg);
				}
				msg = GuildMemberListRespMsg.newBuilder();
			}
		}
		
		if(hasStartPkg == false){
			sendListMsg(player, 0, msg);
			msg = GuildMemberListRespMsg.newBuilder();
		}
		
		sendListMsg(player, 2, msg);
	}
	
	private void sendListMsg(GamePlayer player, int flag, GuildMemberListRespMsg.Builder msg){
		msg.setFlag(flag);
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_GUILD_MEMBER_LIST, msg);
		player.sendPbMessage(pkg);
	}

}
