package com.chuangyou.xianni.guild.action;

import java.util.List;

import com.chuangyou.common.protobuf.pb.guild.GuildReqProto.GuildReqMsg;
import com.chuangyou.common.protobuf.pb.guild.GuildRespProto.GuildRespMsg;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.constant.GuildConstant;
import com.chuangyou.xianni.constant.GuildConstant.GuildLogType;
import com.chuangyou.xianni.constant.GuildConstant.GuildType;
import com.chuangyou.xianni.entity.guild.GuildJobPowerCfg;
import com.chuangyou.xianni.entity.guild.GuildMemberInfo;
import com.chuangyou.xianni.guild.GuildOperateAction;
import com.chuangyou.xianni.guild.action.baseAction.GuildIsGuildMemberAction;
import com.chuangyou.xianni.guild.struct.Guild;
import com.chuangyou.xianni.guild.template.GuildTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.BroadcastUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.word.WorldMgr;

public class GuildJobAppointAction extends GuildIsGuildMemberAction {

	public GuildJobAppointAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(GamePlayer player, PBMessage packet, Guild guild, GuildMemberInfo memberInfo) throws Exception {
		// TODO Auto-generated method stub

		//系统门派不能任命成员
		if(guild.getGuildInfo().getType() == GuildType.SYSTEM_GUILD) return;
		
		GuildJobPowerCfg jobPowerCfg = GuildTemplateMgr.getPowerMap().get(memberInfo.getJob());
		if(jobPowerCfg == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, packet.getCode(), "未知错误");
			return;
		}
		
		if(jobPowerCfg.getAppointJob() == 0){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_POWER_UNENOUGH, packet.getCode(), "权限不足");
			return;
		}
		
		GuildReqMsg req = GuildReqMsg.parseFrom(packet.getBytes());
		long targetId = req.getParam1();
		int targetJob = (int)req.getParam2();
		
		List<Long> memberIds = null;
		if(targetJob == GuildConstant.GuildJob.LEADER){
			if(memberInfo.getJob() != GuildConstant.GuildJob.LEADER){
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_POWER_UNENOUGH, packet.getCode(), "权限不足");
				return;
			}
			if(guild.isDoSeize() == true){
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_APPOINT_LEADER_SEIZE, packet.getCode(), "成员正在夺权，不能转让掌门");
				return;
			}
			
			guild.changeMemberJob(memberInfo.getPlayerId(), GuildConstant.GuildJob.MEMBER);
			
			guild.log(memberInfo.getPlayerId(), GuildLogType.GUILD_JOB_APPOINT, GuildConstant.GuildJob.MEMBER, 0);
			
			memberIds = guild.getMemberIds();
			GuildRespMsg.Builder respMsg = GuildRespMsg.newBuilder();
			respMsg.setAction(GuildOperateAction.APPOINT_JOB);
			respMsg.setParam1(memberInfo.getPlayerId());
			respMsg.setParam2(memberInfo.getJob());
			respMsg.setParamStr(player.getNickName());
			BroadcastUtil.sendBroadcastPacket(memberIds, Protocol.U_GUILD_ACTION_RESP, respMsg.build());
		}else{
			GuildMemberInfo targetMember = guild.getMember(targetId);
			if(targetJob <= memberInfo.getJob() || targetMember.getJob() <= memberInfo.getJob()){
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_POWER_UNENOUGH, packet.getCode(), "权限不足");
				return;
			}
			
			GuildJobPowerCfg targetJobPowerCfg = GuildTemplateMgr.getPowerMap().get(targetJob);
			if(targetJobPowerCfg.getJobCount() > 0){
				if(guild.getJobCount(targetJob) >= targetJobPowerCfg.getJobCount()){
					ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_JOB_FULL, packet.getCode(), "职位已满");
					return;
				}
			}
		}
		
		guild.changeMemberJob(targetId, targetJob);
		
		guild.log(targetId, GuildLogType.GUILD_JOB_APPOINT, targetJob, 0);
		
		if(memberIds == null) memberIds = guild.getMemberIds();
		
		GuildRespMsg.Builder respMsg = GuildRespMsg.newBuilder();
		respMsg.setAction(GuildOperateAction.APPOINT_JOB);
		respMsg.setParam1(targetId);
		respMsg.setParam2(targetJob);
		GamePlayer targetPlayer = WorldMgr.getPlayer(targetId);
		if(targetPlayer != null){
			respMsg.setParamStr(targetPlayer.getNickName());
		}
		BroadcastUtil.sendBroadcastPacket(memberIds, Protocol.U_GUILD_ACTION_RESP, respMsg.build());
	}

}
