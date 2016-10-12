package com.chuangyou.xianni.guild.action;

import com.chuangyou.common.protobuf.pb.guild.GuildReqProto.GuildReqMsg;
import com.chuangyou.common.protobuf.pb.guild.GuildRespProto.GuildRespMsg;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.common.template.LevelUpTempleteMgr;
import com.chuangyou.xianni.constant.GuildConstant.GuildLogType;
import com.chuangyou.xianni.constant.GuildConstant.GuildType;
import com.chuangyou.xianni.constant.LevelUpType;
import com.chuangyou.xianni.entity.guild.GuildDonateCfg;
import com.chuangyou.xianni.entity.guild.GuildInfo;
import com.chuangyou.xianni.entity.guild.GuildMemberInfo;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.level.LevelUp;
import com.chuangyou.xianni.guild.GuildOperateAction;
import com.chuangyou.xianni.guild.action.baseAction.GuildIsGuildMemberAction;
import com.chuangyou.xianni.guild.struct.Guild;
import com.chuangyou.xianni.guild.template.GuildTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;

public class GuildSupplyDonateAction extends GuildIsGuildMemberAction {

	public GuildSupplyDonateAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(GamePlayer player, PBMessage packet, Guild guild, GuildMemberInfo memberInfo) throws Exception {
		// TODO Auto-generated method stub

		GuildReqMsg req = GuildReqMsg.parseFrom(packet.getBytes());
		
		int donateType = (int)req.getParam1();
		int donateNum = (int)req.getParam2();
		
		GuildDonateCfg donateCfg = GuildTemplateMgr.getDonateMap().get(donateType);
		if(donateCfg == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, packet.getCode(), "未知错误");
			return;
		}
		//消耗道具或者元宝
		if(donateType == 1){
			int hasItemNum = player.getBagInventory().getItemCount(donateCfg.getItem());
			if(hasItemNum < donateNum){
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Item_UnEnough, packet.getCode(), "道具不足");
				return;
			}
			if(!player.getBagInventory().removeItem(donateCfg.getItem(), donateNum, ItemRemoveType.GUILD_DONATE)){
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, packet.getCode(), "未知错误");
				return;
			}
		}else if(donateType == 2){
			if(player.getBasePlayer().getPlayerInfo().getCash() < donateNum){
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Cash_UnEnough, packet.getCode(), "仙玉不足");
				return;
			}
			if(!player.getBasePlayer().consumeCash(donateNum, ItemRemoveType.GUILD_DONATE)){
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, packet.getCode(), "未知错误");
				return;
			}
		}else{
			return;
		}
		
		GuildInfo guildInfo = guild.getGuildInfo();
		if(guildInfo.getType() == GuildType.PLAYER_GUILD){
			//添加帮派建设值，满足帮派升级需求后自动升级
			int maxLevel = LevelUpTempleteMgr.getMaxLevel(LevelUpType.GUILD);
			if(guildInfo.getLevel() < maxLevel){
				long addBuildExp = donateCfg.getBuildExp() * donateNum;
				
				guildInfo.setBuildExp(guildInfo.getBuildExp() + addBuildExp);
				guildInfo.setTotalBuildExp(guildInfo.getTotalBuildExp() + addBuildExp);
				
				LevelUp levelUpCfg = LevelUpTempleteMgr.getLevelUp(LevelUpType.GUILD, guildInfo.getLevel());
				if(guildInfo.getBuildExp() >= levelUpCfg.getExp()){
					int level = LevelUpTempleteMgr.getLevelByTotalExp(LevelUpType.GUILD, guildInfo.getTotalBuildExp());
					
					long needTotalExp = LevelUpTempleteMgr.getLevelNeedExp(LevelUpType.GUILD, level);
					//如果超过了最高级，只能升到最高等级
					if(level >= maxLevel){
						level = maxLevel;
						needTotalExp = LevelUpTempleteMgr.getLevelNeedExp(LevelUpType.GUILD, level);
						
						guildInfo.setLevel(level);
						guildInfo.setTotalBuildExp(needTotalExp);
						guildInfo.setBuildExp(0);
					}else{
						guildInfo.setLevel(level);
						guildInfo.setBuildExp(guildInfo.getTotalBuildExp() - needTotalExp);
					}
				}
			}
		}
		
		//玩家增加贡献值
		memberInfo.addContribution(donateCfg.getContribution() * donateNum);
		
		//帮派增加物资
		guild.addSupply(donateCfg.getSupply() * donateNum);
		
		if(donateType == 1){
			guild.log(player.getPlayerId(), GuildLogType.GUILD_DONATE_ITEM, donateNum, 0);
		}else if(donateType == 2){
			guild.log(player.getPlayerId(), GuildLogType.GUILD_DONATE_MONEY, donateNum, 0);
		}
		
		//返回捐献结果-捐献者信息
		GuildRespMsg.Builder respMsg = GuildRespMsg.newBuilder();
		respMsg.setAction(GuildOperateAction.CONTRIBUTION_UPDATE);
		respMsg.setParam1(memberInfo.getContributionTotal());
		respMsg.setParam2(memberInfo.getContribution());
		respMsg.setParam3(memberInfo.getConsumeSupply());
		PBMessage respPkg = MessageUtil.buildMessage(Protocol.U_GUILD_ACTION_RESP, respMsg);
		player.sendPbMessage(respPkg);
		
		//返回帮派信息变更-帮派信息
		GuildRespMsg.Builder updateMsg = GuildRespMsg.newBuilder();
		updateMsg.setAction(GuildOperateAction.GUILD_DONATE_NOTIFY);
		updateMsg.setParam1(guildInfo.getLevel());
		updateMsg.setParam2(guildInfo.getBuildExp());
		updateMsg.setParam3(guild.getSupply());
//		BroadcastUtil.sendBroadcastPacket(guild.getMemberIds(), Protocol.U_GUILD_ACTION_RESP, updateMsg.build());
		PBMessage updatePkg = MessageUtil.buildMessage(Protocol.U_GUILD_ACTION_RESP, updateMsg);
		player.sendPbMessage(updatePkg);
		
	}

}
