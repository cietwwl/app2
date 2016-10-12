package com.chuangyou.xianni.guild.action;

import java.util.List;
import java.util.Set;

import com.chuangyou.common.protobuf.pb.guild.GuildInfoRespProto.GuildInfoRespMsg;
import com.chuangyou.common.protobuf.pb.guild.GuildMemberInfoProto.GuildMemberInfoMsg;
import com.chuangyou.common.protobuf.pb.guild.GuildReqProto.GuildReqMsg;
import com.chuangyou.common.protobuf.pb.guild.GuildRespProto.GuildRespMsg;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.constant.GuildConstant;
import com.chuangyou.xianni.entity.guild.GuildApplyInfo;
import com.chuangyou.xianni.entity.guild.GuildJobPowerCfg;
import com.chuangyou.xianni.entity.guild.GuildMemberInfo;
import com.chuangyou.xianni.guild.action.baseAction.GuildIsGuildMemberAction;
import com.chuangyou.xianni.guild.manager.GuildManager;
import com.chuangyou.xianni.guild.struct.Guild;
import com.chuangyou.xianni.guild.template.GuildTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.player.PlayerState;
import com.chuangyou.xianni.proto.BroadcastUtil;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.word.WorldMgr;

public class GuildApplyResponseAction extends GuildIsGuildMemberAction {

	public GuildApplyResponseAction(GamePlayer player, PBMessage packet) {
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
		
		if(jobPowerCfg.getApplyHandle() == 0){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_POWER_UNENOUGH, packet.getCode(), "权限不足");
			return;
		}
		
		if(guild.getMemberMap().size() >= guild.getMaxMemberNum()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_MEMBER_MAX, packet.getCode(), "帮派成员已满");
			return;
		}
		
		GuildReqMsg req = GuildReqMsg.parseFrom(packet.getBytes());
		long targetId = req.getParam1();
		int option = (int)req.getParam2();
		
		GuildApplyInfo applyInfo = guild.getApply(targetId);
		if(applyInfo == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_APPLY_NOT_EXIST, packet.getCode(), "玩家没有申请");
			return;
		}
		guild.removeApply(targetId);
		
		Guild targetPlayerGuild = GuildManager.getIns().getPlayerGuild(targetId);
		if(targetPlayerGuild != null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_APPLY_ALREADY_JOIN, packet.getCode(), "玩家已经加入帮派");
			
			//同步给有权限审核申请的成员更新申请列表
			GuildRespMsg.Builder notifyMsg = GuildRespMsg.newBuilder();
			guild.writeResponseApplyMsg(notifyMsg, targetId, GuildConstant.ResponseApplyType.NOTIFY_REMOVE_APPLY);
			Set<Long> ids = guild.getApplyCheckPlayers();
			BroadcastUtil.sendBroadcastPacket(ids, Protocol.U_GUILD_ACTION_RESP, notifyMsg.build());
			return;
		}
		
		
		GamePlayer targetPlayer = WorldMgr.getPlayer(targetId);
		if(option == 1){
			//申请人加入帮派
			GuildManager.getIns().playerJoinGuild(targetPlayer, guild.getGuildInfo().getGuildId());
			
			
			//广播给帮派成员有玩家加入帮派
			List<Long> memberIds = guild.getMemberIds();
			GuildMemberInfoMsg.Builder joinMsg = GuildMemberInfoMsg.newBuilder();
			guild.writeMemberProto(joinMsg, targetId);
			BroadcastUtil.sendBroadcastPacket(memberIds, Protocol.U_GUILD_MEMBER_INFO, joinMsg.build());
			
			//返回给审核者
			GuildRespMsg.Builder responseMsg = GuildRespMsg.newBuilder();
			guild.writeResponseApplyMsg(responseMsg, targetId, GuildConstant.ResponseApplyType.ACCEPT_APPLY);
			
			PBMessage responsePkg = MessageUtil.buildMessage(Protocol.U_GUILD_ACTION_RESP, responseMsg);
			player.sendPbMessage(responsePkg);
			
			if(targetPlayer.getPlayerState() == PlayerState.ONLINE){
				//返回给申请人(如果在线)
				GuildRespMsg.Builder applyMsg = GuildRespMsg.newBuilder();
				guild.writeResponseApplyMsg(applyMsg, targetId, GuildConstant.ResponseApplyType.SELF_ACCEPT);
				PBMessage applyPkg = MessageUtil.buildMessage(Protocol.U_GUILD_ACTION_RESP, applyMsg);
				targetPlayer.sendPbMessage(applyPkg);
				
				//给刚加入帮派的玩家推帮派详情
				GuildInfoRespMsg.Builder guildInfoMsg = GuildInfoRespMsg.newBuilder();
				guild.writeProto(guildInfoMsg, targetPlayer.getPlayerId());
				PBMessage guildInfoPkg = MessageUtil.buildMessage(Protocol.U_GUILD_INFO, guildInfoMsg);
				targetPlayer.sendPbMessage(guildInfoPkg);
			}
		}else{
			//返回给申请人(如果在线)
			if(targetPlayer.getPlayerState() == PlayerState.ONLINE){
				GuildRespMsg.Builder applyMsg = GuildRespMsg.newBuilder();
				guild.writeResponseApplyMsg(applyMsg, targetId, GuildConstant.ResponseApplyType.SELF_REFUSE);
				
				PBMessage applyPkg = MessageUtil.buildMessage(Protocol.U_GUILD_ACTION_RESP, applyMsg);
				targetPlayer.sendPbMessage(applyPkg);
			}
			
			//返回给审核者
			GuildRespMsg.Builder responseMsg = GuildRespMsg.newBuilder();
			guild.writeResponseApplyMsg(responseMsg, targetId, GuildConstant.ResponseApplyType.REFUSE_APPLY);
			PBMessage responsePkg = MessageUtil.buildMessage(Protocol.U_GUILD_ACTION_RESP, responseMsg);
			player.sendPbMessage(responsePkg);
			
		}
		
		//同步给其他有权限审核申请的成员更新申请列表
		GuildRespMsg.Builder notifyMsg = GuildRespMsg.newBuilder();
		guild.writeResponseApplyMsg(notifyMsg, targetId, GuildConstant.ResponseApplyType.NOTIFY_REMOVE_APPLY);
		Set<Long> ids = guild.getApplyCheckPlayers();
		ids.remove(player.getPlayerId());
		BroadcastUtil.sendBroadcastPacket(ids, Protocol.U_GUILD_ACTION_RESP, notifyMsg.build());
	}

}
