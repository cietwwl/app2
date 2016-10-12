package com.chuangyou.xianni.guild.action;

import com.chuangyou.common.protobuf.pb.guild.GuildRespProto.GuildRespMsg;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.common.template.LevelUpTempleteMgr;
import com.chuangyou.xianni.constant.LevelUpType;
import com.chuangyou.xianni.constant.GuildConstant.GuildType;
import com.chuangyou.xianni.entity.guild.GuildJobPowerCfg;
import com.chuangyou.xianni.entity.guild.GuildMemberInfo;
import com.chuangyou.xianni.entity.level.LevelUp;
import com.chuangyou.xianni.guild.GuildOperateAction;
import com.chuangyou.xianni.guild.action.baseAction.GuildIsGuildMemberAction;
import com.chuangyou.xianni.guild.struct.Guild;
import com.chuangyou.xianni.guild.template.GuildTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;

public class GuildMainBuildLevelupAction extends GuildIsGuildMemberAction {

	public GuildMainBuildLevelupAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(GamePlayer player, PBMessage packet, Guild guild, GuildMemberInfo memberInfo) {
		// TODO Auto-generated method stub

		if(guild.getGuildInfo().getType() == GuildType.SYSTEM_GUILD) return;
		
		GuildJobPowerCfg jobPowerCfg = GuildTemplateMgr.getPowerMap().get(memberInfo.getJob());
		if(jobPowerCfg == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, packet.getCode(), "未知错误");
			return;
		}
		
		if(jobPowerCfg.getBuildLevelup() == 0){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_POWER_UNENOUGH, packet.getCode(), "权限不足");
			return;
		}
		
		int maxLevel = LevelUpTempleteMgr.getMaxLevel(LevelUpType.GUILD_MAINBUILD);
		if(guild.getGuildInfo().getMainBuildLevel() >= maxLevel){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_MAINGUILD_MAXLEVEL, packet.getCode(), "主殿已达到最高等级");
			return;
		}
		
		LevelUp levelUpCfg = LevelUpTempleteMgr.getLevelUp(LevelUpType.GUILD_MAINBUILD, guild.getGuildInfo().getMainBuildLevel());
		if(guild.getSupply() < levelUpCfg.getExp()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_SUPPLY_UNENOUGH, packet.getCode(), "帮派物资不足");
			return;
		}
		
		if(!guild.consumeSupply(levelUpCfg.getExp(), 0)) return;
		
		guild.getGuildInfo().setMainBuildLevel(guild.getGuildInfo().getMainBuildLevel() + 1);
		
		GuildRespMsg.Builder respMsg = GuildRespMsg.newBuilder();
		respMsg.setAction(GuildOperateAction.MAINBUILD_LEVELUP);
		respMsg.setParam1(guild.getGuildInfo().getMainBuildLevel());
		respMsg.setParam2(guild.getSupply());
//		BroadcastUtil.sendBroadcastPacket(guild.getMemberIds(), Protocol.U_GUILD_ACTION_RESP, respMsg.build());
		PBMessage respPkg = MessageUtil.buildMessage(Protocol.U_GUILD_ACTION_RESP, respMsg);
		player.sendPbMessage(respPkg);
	}

}
