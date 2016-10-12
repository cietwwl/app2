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

public class GuildShopLevelupAction extends GuildIsGuildMemberAction {

	public GuildShopLevelupAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(GamePlayer player, PBMessage packet, Guild guild, GuildMemberInfo memberInfo) {
		// TODO Auto-generated method stub

		if(guild.getGuildInfo().getType() == GuildType.SYSTEM_GUILD) return;
		
		int levelLimit = (int)Math.floor(guild.getGuildInfo().getLevel() / 10) + 1;
		if(guild.getGuildInfo().getShopLevel() >= levelLimit){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_LEVEL_UNENOUGH, packet.getCode(), "请先升级门派等级");
			return;
		}
		
		GuildJobPowerCfg jobPowerCfg = GuildTemplateMgr.getPowerMap().get(memberInfo.getJob());
		if(jobPowerCfg == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, packet.getCode(), "未知错误");
			return;
		}
		
		if(jobPowerCfg.getBuildLevelup() == 0){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_POWER_UNENOUGH, packet.getCode(), "权限不足");
			return;
		}
		
		int maxLevel = LevelUpTempleteMgr.getMaxLevel(LevelUpType.GUILD_SHOP);
		if(guild.getGuildInfo().getShopLevel() >= maxLevel){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_SHOP_MAXLEVEL, packet.getCode(), "帮派商店已达到最高等级");
			return;
		}
		
		LevelUp levelUpCfg = LevelUpTempleteMgr.getLevelUp(LevelUpType.GUILD_SHOP, guild.getGuildInfo().getShopLevel());
		if(guild.getSupply() < levelUpCfg.getExp()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_SUPPLY_UNENOUGH, packet.getCode(), "帮派物资不足");
			return;
		}
		
		if(!guild.consumeSupply(levelUpCfg.getExp(), 0)) return;
		
		guild.getGuildInfo().setShopLevel(guild.getGuildInfo().getShopLevel() + 1);
		
		GuildRespMsg.Builder respMsg = GuildRespMsg.newBuilder();
		respMsg.setAction(GuildOperateAction.SHOP_LEVELUP);
		respMsg.setParam1(guild.getGuildInfo().getShopLevel());
		respMsg.setParam2(guild.getSupply());
//		BroadcastUtil.sendBroadcastPacket(guild.getMemberIds(), Protocol.U_GUILD_ACTION_RESP, respMsg.build());
		PBMessage respPkg = MessageUtil.buildMessage(Protocol.U_GUILD_ACTION_RESP, respMsg);
		player.sendPbMessage(respPkg);
	}

}
