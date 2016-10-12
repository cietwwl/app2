package com.chuangyou.xianni.guild.action;

import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.constant.GuildConstant.GuildType;
import com.chuangyou.xianni.entity.guild.GuildJobPowerCfg;
import com.chuangyou.xianni.entity.guild.GuildMemberInfo;
import com.chuangyou.xianni.guild.GuildOperateAction;
import com.chuangyou.xianni.guild.action.baseAction.GuildIsGuildMemberAction;
import com.chuangyou.xianni.guild.manager.GuildManager;
import com.chuangyou.xianni.guild.struct.Guild;
import com.chuangyou.xianni.guild.template.GuildTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;

public class GuildExitAction extends GuildIsGuildMemberAction {

	public GuildExitAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(GamePlayer player, PBMessage packet, Guild guild, GuildMemberInfo memberInfo) {
		// TODO Auto-generated method stub

		GuildJobPowerCfg jobPowerCfg = GuildTemplateMgr.getPowerMap().get(memberInfo.getJob());
		if(jobPowerCfg == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, packet.getCode(), "未知错误");
			return;
		}
		
		if(jobPowerCfg.getExit() == 0){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_POWER_UNENOUGH, packet.getCode(), "权限不足");
			return;
		}
		
		if(guild.getGuildInfo().getType() == GuildType.PLAYER_GUILD){
			if(guild.getMemberMap().size() > 1){
				if(player.getPlayerId() == guild.getGuildInfo().getLeaderId()){
					ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_LEADER_EXIT, packet.getCode(), "门派中还有其他成员时，掌门不能退出");
					return;
				}
			}else{
				//帮派中只有自己一个人时，退出则直接解散帮派
				GuildManager.getIns().dissolveGuild(guild.getGuildInfo().getGuildId());
				return;
			}
		}
		
		GuildManager.getIns().playerExit(player.getPlayerId(), GuildOperateAction.GUILD_EXIT);
	}

}
