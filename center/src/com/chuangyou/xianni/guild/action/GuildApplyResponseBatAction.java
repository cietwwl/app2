package com.chuangyou.xianni.guild.action;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.chuangyou.common.protobuf.pb.guild.GuildBatJoinProto.GuildBatJoinMsg;
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
import com.chuangyou.xianni.guild.GuildOperateAction;
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

public class GuildApplyResponseBatAction extends GuildIsGuildMemberAction {

	public GuildApplyResponseBatAction(GamePlayer player, PBMessage packet) {
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
		
		GuildReqMsg req = GuildReqMsg.parseFrom(packet.getBytes());
		
		if(req.getParam1() == 1){
			if(guild.getMemberMap().size() + guild.getApplyMap().size() > guild.getMaxMemberNum()){
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_MEMBER_MAX, packet.getCode(), "帮派成员已满");
				return;
			}
			//申请人加入帮派之前的成员列表ID
			List<Long> ids = guild.getMemberIds();
			
			GuildBatJoinMsg.Builder batJoinMsg = GuildBatJoinMsg.newBuilder();
			
			Iterator<GuildApplyInfo> iterator = guild.getApplyList().iterator();
			while(iterator.hasNext()){
				GuildApplyInfo apply = iterator.next();
				
				GamePlayer targetPlayer = WorldMgr.getPlayer(apply.getPlayerId());
				//申请人加入帮派
				GuildManager.getIns().playerJoinGuild(targetPlayer, guild.getGuildInfo().getGuildId());
				
				GuildMemberInfoMsg.Builder memberMsg = GuildMemberInfoMsg.newBuilder();
				guild.writeMemberProto(memberMsg, apply.getPlayerId());
				batJoinMsg.addMember(memberMsg);
				
				
				//返回给申请人(如果在线)
				if(targetPlayer.getPlayerState() == PlayerState.ONLINE){
					GuildRespMsg.Builder applyMsg = GuildRespMsg.newBuilder();
					guild.writeResponseApplyMsg(applyMsg, targetPlayer.getPlayerId(), GuildConstant.ResponseApplyType.SELF_ACCEPT);
					PBMessage applyPkg = MessageUtil.buildMessage(Protocol.U_GUILD_ACTION_RESP, applyMsg);
					targetPlayer.sendPbMessage(applyPkg);
					
					GuildInfoRespMsg.Builder guildInfoMsg = GuildInfoRespMsg.newBuilder();
					guild.writeProto(guildInfoMsg, targetPlayer.getPlayerId());
					PBMessage guildInfoPkg = MessageUtil.buildMessage(Protocol.U_GUILD_INFO, guildInfoMsg);
					targetPlayer.sendPbMessage(guildInfoPkg);
				}
			}
			//同步给帮派成员，有一批玩家加入帮派
			BroadcastUtil.sendBroadcastPacket(ids, Protocol.U_GUILD_BAT_JOIN, batJoinMsg.build());
		}else if(req.getParam1() == 2){
			Iterator<GuildApplyInfo> iterator = guild.getApplyList().iterator();
			while(iterator.hasNext()){
				GuildApplyInfo apply = iterator.next();
				
				GamePlayer targetPlayer = WorldMgr.getPlayer(apply.getPlayerId());
				//返回给申请人(如果在线)
				if(targetPlayer.getPlayerState() == PlayerState.ONLINE){
					GuildRespMsg.Builder applyMsg = GuildRespMsg.newBuilder();
					guild.writeResponseApplyMsg(applyMsg, targetPlayer.getPlayerId(), GuildConstant.ResponseApplyType.SELF_REFUSE);
					
					PBMessage applyPkg = MessageUtil.buildMessage(Protocol.U_GUILD_ACTION_RESP, applyMsg);
					targetPlayer.sendPbMessage(applyPkg);
				}
			}
		}else{
			return;
		}
		
		guild.removeAllApply();
		//同步给有审核权限的成员，清除申请列表
		Set<Long> ids = guild.getApplyCheckPlayers();
		
		GuildRespMsg.Builder respMsg = GuildRespMsg.newBuilder();
		respMsg.setAction(GuildOperateAction.BAT_RESPONSE_APPLY);
		respMsg.setParam1(player.getPlayerId());
		respMsg.setParam2(req.getParam1());
		BroadcastUtil.sendBroadcastPacket(ids, Protocol.U_GUILD_ACTION_RESP, respMsg.build());
	}

}
