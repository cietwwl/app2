package com.chuangyou.xianni.guild.action;

import com.chuangyou.common.protobuf.pb.guild.GuildReqProto.GuildReqMsg;
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

public class GuildMemberRemoveAction extends GuildIsGuildMemberAction {

	public GuildMemberRemoveAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(GamePlayer player, PBMessage packet, Guild guild, GuildMemberInfo memberInfo) throws Exception {
		// TODO Auto-generated method stub

		//系统门派不能踢人
		if(guild.getGuildInfo().getType() == GuildType.SYSTEM_GUILD) return;
		
		GuildJobPowerCfg jobPowerCfg = GuildTemplateMgr.getPowerMap().get(memberInfo.getJob());
		if(jobPowerCfg == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, packet.getCode(), "未知错误");
			return;
		}
		
		if(jobPowerCfg.getRemoveMember() == 0){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_POWER_UNENOUGH, packet.getCode(), "权限不足");
			return;
		}
		
		GuildReqMsg req = GuildReqMsg.parseFrom(packet.getBytes());
		long targetId = req.getParam1();
		
		if(targetId == player.getPlayerId()){
			return;
		}
		
		if(targetId == guild.getSeizePlayerId()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_REMOVE_MEMBER_SEIZE, packet.getCode(), "该玩家正在夺权，不能踢出门派");
			return;
		}
		
		GuildMemberInfo targetMember = guild.getMember(targetId);
		if(targetMember == null) return;
		if(memberInfo.getJob() >= targetMember.getJob()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_POWER_UNENOUGH, packet.getCode(), "权限不足");
			return;
		}
		
		GuildManager.getIns().playerExit(targetId, GuildOperateAction.REMOVE_MEMBER);
	}

}
