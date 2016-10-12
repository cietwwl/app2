package com.chuangyou.xianni.guild.action;

import java.util.Set;

import com.chuangyou.common.protobuf.pb.guild.GuildReqProto.GuildReqMsg;
import com.chuangyou.common.protobuf.pb.guild.GuildRespProto.GuildRespMsg;
import com.chuangyou.common.util.LanguageSet;
import com.chuangyou.common.util.SensitivewordFilterUtil;
import com.chuangyou.common.util.StringUtils;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.email.manager.EmailManager;
import com.chuangyou.xianni.entity.guild.GuildJobPowerCfg;
import com.chuangyou.xianni.entity.guild.GuildMemberInfo;
import com.chuangyou.xianni.guild.GuildOperateAction;
import com.chuangyou.xianni.guild.action.baseAction.GuildIsGuildMemberAction;
import com.chuangyou.xianni.guild.struct.Guild;
import com.chuangyou.xianni.guild.template.GuildTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;

public class GuildMailAction extends GuildIsGuildMemberAction {

	public GuildMailAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(GamePlayer player, PBMessage packet, Guild guild, GuildMemberInfo memberInfo) throws Exception {
		// TODO Auto-generated method stub

		GuildJobPowerCfg jobPowerCfg = GuildTemplateMgr.getPowerMap().get(memberInfo.getJob());
		if(jobPowerCfg == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, packet.getCode(), "未知错误");
			return;
		}
		
		if(jobPowerCfg.getMassEmail() == 0){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_POWER_UNENOUGH, packet.getCode(), "权限不足");
			return;
		}
		
		GuildReqMsg req = GuildReqMsg.parseFrom(packet.getBytes());
		
		if(!StringUtils.verifyMaxByteLen(req.getParamStr(), 120)){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.MAIL_CONTENT_MAX, packet.getCode(), "邮件内容过长");
			return;
		}
		String content = LanguageSet.getResource("CenterServer.guild.emailSender") + player.getNickName() + "\n" + SensitivewordFilterUtil.getIntence().replaceSensitiveWord(req.getParamStr());
		
		String emailTitle = LanguageSet.getResource("CenterServer.guild.emailTitle");
		
		Set<Long> ids = guild.getMemberIdsSet();
		ids.remove(player.getPlayerId());
		for(long playerId: ids){
			EmailManager.insertEmail(playerId, emailTitle, content);
		}
		
		GuildRespMsg.Builder respMsg = GuildRespMsg.newBuilder();
		respMsg.setAction(GuildOperateAction.GUILD_MAIL);
		respMsg.setResult(0);
		PBMessage respPkg = MessageUtil.buildMessage(Protocol.U_GUILD_ACTION_RESP, respMsg);
		player.sendPbMessage(respPkg);
	}

}
