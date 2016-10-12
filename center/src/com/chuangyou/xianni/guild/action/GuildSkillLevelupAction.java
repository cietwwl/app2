package com.chuangyou.xianni.guild.action;

import com.chuangyou.common.protobuf.pb.guild.GuildReqProto.GuildReqMsg;
import com.chuangyou.common.protobuf.pb.guild.GuildRespProto.GuildRespMsg;
import com.chuangyou.common.protobuf.pb.guild.GuildSkillInfoProto.GuildSkillInfoMsg;
import com.chuangyou.common.protobuf.pb.guild.GuildSkillUpdateProto.GuildSkillUpdateMsg;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.constant.GuildConstant.GuildType;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.guild.GuildMemberInfo;
import com.chuangyou.xianni.entity.guild.GuildSkillCfg;
import com.chuangyou.xianni.entity.guild.GuildSkillInfo;
import com.chuangyou.xianni.guild.GuildOperateAction;
import com.chuangyou.xianni.guild.action.baseAction.GuildIsGuildMemberAction;
import com.chuangyou.xianni.guild.struct.Guild;
import com.chuangyou.xianni.guild.template.GuildTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;

public class GuildSkillLevelupAction extends GuildIsGuildMemberAction {

	public GuildSkillLevelupAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(GamePlayer player, PBMessage packet, Guild guild, GuildMemberInfo memberInfo) throws Exception {
		// TODO Auto-generated method stub
		
		GuildReqMsg req = GuildReqMsg.parseFrom(packet.getBytes());
		int guildSkillId = (int)req.getParam1();
		
		boolean addSkill = false;
		GuildSkillInfo guildSkill = player.getGuildInventory().getSkillMap().get(guildSkillId);
		if(guildSkill == null){
			guildSkill = new GuildSkillInfo(player.getPlayerId(), guildSkillId);
			guildSkill.setOp(Option.Insert);
			addSkill = true;
		}
		
		GuildSkillCfg cfg = GuildTemplateMgr.getGuildSkill(guildSkillId, guildSkill.getLevel());
		if(cfg == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, packet.getCode(), "帮派技能配置错误");
			return;
		}
		
		//玩家门派判断建筑等级
		if(guild.getGuildInfo().getType() == GuildType.PLAYER_GUILD){
			if(guild.getGuildInfo().getSkillShopLevel() < cfg.getSkillShopLevel()){
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_SKILLSHOP_UNENOUGH, packet.getCode(), "蒧经阁等级不足");
				return;
			}
		}
		//系统门派判断玩家境界
		if(guild.getGuildInfo().getType() == GuildType.SYSTEM_GUILD){
			if(player.getBasePlayer().getPlayerInfo().getStateLv() < cfg.getPlayerStateLevel()){
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.PLAYER_STATELV_UNENOUGH, packet.getCode(), "玩家境界不足");
				return;
			}
		}
		//检查消耗
		if(guild.getSupply() < cfg.getNeedSupply()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_SUPPLY_UNENOUGH, packet.getCode(), "帮派物资不足");
			return;
		}
		if(memberInfo.getContribution() < cfg.getNeedContribution()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_CONTRIBUTION_UNENOUGH, packet.getCode(), "帮派贡献不足");
			return;
		}
		//扣除消耗
		if(!guild.consumeSupply(cfg.getNeedSupply(), memberInfo.getPlayerId()) || !memberInfo.consumeContribution(cfg.getNeedContribution())){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, packet.getCode(), "未知错误");
			return;
		}
		
		if(addSkill == true){
			player.getGuildInventory().addGuildSkill(guildSkill);
		}
		guildSkill.setLevel(guildSkill.getLevel() + 1);
		
		//更新技能信息
		GuildSkillUpdateMsg.Builder skillMsg = GuildSkillUpdateMsg.newBuilder();
		GuildSkillInfoMsg.Builder skillInfoMsg = GuildSkillInfoMsg.newBuilder();
		skillInfoMsg.setGuildSkillId(guildSkill.getGuildSkillId());
		skillInfoMsg.setLevel(guildSkill.getLevel());
		skillMsg.addSkillInfo(skillInfoMsg);
		PBMessage skillPkg = MessageUtil.buildMessage(Protocol.U_GUILD_SKILL_UPDATE, skillMsg);
		player.sendPbMessage(skillPkg);
		
		//返回升级结果
		GuildRespMsg.Builder respMsg = GuildRespMsg.newBuilder();
		respMsg.setAction(GuildOperateAction.GUILD_SKILL_LEVELUP);
		respMsg.setResult(0);
		respMsg.setParam1(guildSkillId);
		PBMessage respPkg = MessageUtil.buildMessage(Protocol.U_GUILD_ACTION_RESP, respMsg);
		player.sendPbMessage(respPkg);
		
		//帮派贡献更新
		GuildRespMsg.Builder contrMsg = GuildRespMsg.newBuilder();
		contrMsg.setAction(GuildOperateAction.CONTRIBUTION_UPDATE);
		contrMsg.setParam1(memberInfo.getContributionTotal());
		contrMsg.setParam2(memberInfo.getContribution());
		contrMsg.setParam3(memberInfo.getConsumeSupply());
		PBMessage contrPkg = MessageUtil.buildMessage(Protocol.U_GUILD_ACTION_RESP, contrMsg);
		player.sendPbMessage(contrPkg);
		
		//帮派物资更新通知
		GuildRespMsg.Builder supplyMsg = GuildRespMsg.newBuilder();
		supplyMsg.setAction(GuildOperateAction.GUILD_SUPPLY_UPDATE);
		supplyMsg.setParam1(guild.getSupply());
//		BroadcastUtil.sendBroadcastPacket(guild.getMemberIds(), Protocol.U_GUILD_ACTION_RESP, supplyMsg.build());
		PBMessage supplyPkg = MessageUtil.buildMessage(Protocol.U_GUILD_ACTION_RESP, supplyMsg);
		player.sendPbMessage(supplyPkg);
		
		player.getGuildInventory().updateSkillProperty();
	}

}
