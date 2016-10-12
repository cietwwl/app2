package com.chuangyou.xianni.guild.action;

import com.chuangyou.common.protobuf.pb.guild.GuildReqProto.GuildReqMsg;
import com.chuangyou.common.protobuf.pb.guild.GuildRespProto.GuildRespMsg;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.guild.GuildOperateAction;
import com.chuangyou.xianni.guild.action.baseAction.GuildBaseAction;
import com.chuangyou.xianni.guild.manager.GuildManager;
import com.chuangyou.xianni.guild.struct.Guild;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;

public class GuildNoticeGetAction extends GuildBaseAction {

	public GuildNoticeGetAction(GamePlayer player, PBMessage packet) {
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
		
		GuildRespMsg.Builder respMsg = GuildRespMsg.newBuilder();
		respMsg.setAction(GuildOperateAction.GUILD_NOTICE_GET);
		respMsg.setParam1(guildId);
		respMsg.setParamStr(guild.getGuildInfo().getNotice());
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_GUILD_ACTION_RESP, respMsg);
		player.sendPbMessage(pkg);
	}

}
