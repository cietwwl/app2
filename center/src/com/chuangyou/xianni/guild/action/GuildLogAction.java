package com.chuangyou.xianni.guild.action;

import java.util.List;

import com.chuangyou.common.protobuf.pb.guild.GuildLogInfoProto.GuildLogInfoMsg;
import com.chuangyou.common.protobuf.pb.guild.GuildLogListProto.GuildLogListMsg;
import com.chuangyou.common.protobuf.pb.guild.GuildReqProto.GuildReqMsg;
import com.chuangyou.xianni.entity.guild.GuildLogInfo;
import com.chuangyou.xianni.entity.guild.GuildMemberInfo;
import com.chuangyou.xianni.guild.action.baseAction.GuildIsGuildMemberAction;
import com.chuangyou.xianni.guild.struct.Guild;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.word.WorldMgr;

public class GuildLogAction extends GuildIsGuildMemberAction {

	public GuildLogAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(GamePlayer player, PBMessage packet, Guild guild, GuildMemberInfo memberInfo) throws Exception {
		// TODO Auto-generated method stub

		GuildReqMsg req = GuildReqMsg.parseFrom(packet.getBytes());
		
		int startIndex = (int)req.getParam1();
		int count = (int)req.getParam2();
		
		List<GuildLogInfo> logList = guild.getLogList(startIndex, count);
		
		GuildLogListMsg.Builder respMsg = GuildLogListMsg.newBuilder();
		respMsg.setStartIndex(startIndex);
		respMsg.setTotalCount(guild.getLogList().size());
		
		for(GuildLogInfo info: logList){
			GuildLogInfoMsg.Builder infoMsg = GuildLogInfoMsg.newBuilder();
			infoMsg.setGuildId(info.getGuildId());
			infoMsg.setOperateTime(info.getOperateTime().getTime());
			infoMsg.setOperateType(info.getOperateType());
			infoMsg.setPlayerId(info.getPlayerId());
			GamePlayer logPlayer = WorldMgr.getPlayer(info.getPlayerId());
			if(logPlayer != null){
				infoMsg.setPlayerName(logPlayer.getNickName());
			}
			infoMsg.setValue1(info.getValue1());
			infoMsg.setValue2(info.getValue2());
			
			respMsg.addLog(infoMsg);
		}
		
		PBMessage respPkg = MessageUtil.buildMessage(Protocol.U_GUILD_LOG_LIST, respMsg);
		player.sendPbMessage(respPkg);
	}

}
