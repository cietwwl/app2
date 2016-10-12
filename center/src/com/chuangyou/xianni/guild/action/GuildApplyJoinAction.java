package com.chuangyou.xianni.guild.action;

import java.util.List;
import java.util.Set;

import com.chuangyou.common.protobuf.pb.guild.GuildApplyInfoProto.GuildApplyInfoMsg;
import com.chuangyou.common.protobuf.pb.guild.GuildInfoRespProto.GuildInfoRespMsg;
import com.chuangyou.common.protobuf.pb.guild.GuildMemberInfoProto.GuildMemberInfoMsg;
import com.chuangyou.common.protobuf.pb.guild.GuildReqProto.GuildReqMsg;
import com.chuangyou.common.protobuf.pb.guild.GuildRespProto.GuildRespMsg;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.constant.GuildConstant;
import com.chuangyou.xianni.constant.GuildConstant.GuildType;
import com.chuangyou.xianni.entity.guild.GuildApplyInfo;
import com.chuangyou.xianni.guild.GuildOperateAction;
import com.chuangyou.xianni.guild.action.baseAction.GuildNotGuildMemberAction;
import com.chuangyou.xianni.guild.manager.GuildManager;
import com.chuangyou.xianni.guild.struct.Guild;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.BroadcastUtil;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;

public class GuildApplyJoinAction extends GuildNotGuildMemberAction {

	public GuildApplyJoinAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void executeNoGuild(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		if(GuildManager.getIns().getPlayerGuild(player.getPlayerId()) != null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.PLAYER_ALREAD_IN_GUILD, packet.getCode(), "玩家已经在帮派里了");
			return;
		}
		
		GuildReqMsg req = GuildReqMsg.parseFrom(packet.getBytes());
		int guildId = (int)req.getParam1();
		
		Guild guild = GuildManager.getIns().getGuildMap().get(guildId);
		if(guild == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_NOT_EXIST, packet.getCode(), "帮派不存在");
			return;
		}
		
		if(guild.getApply(player.getPlayerId()) != null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_APPLY_REPEAT, packet.getCode(), "重复申请");
			return;
		}
		int result = 0;
		if(guild.getGuildInfo().getType() == GuildType.SYSTEM_GUILD){
			//系统门派，只判断是否满员
			guild.getGuildInfo().setJoinType(GuildConstant.JoinType.AUTO_CONDITION);
			if(guild.getMemberMap().size() >= guild.getMaxMemberNum()){
				result = GuildConstant.JoinFailCode.GUILD_FULL;
			}
		}else if(guild.getGuildInfo().getType() == GuildType.PLAYER_GUILD){
			//判断是否达到帮派设置的加入条件，未达到直接返回给申请人
			if(guild.getGuildInfo().getJoinType() == GuildConstant.JoinType.BAN){
				result = GuildConstant.JoinFailCode.GUILD_BAN;
			}else{
				if(guild.getGuildInfo().getLevelLimit() > player.getLevel()){
					result = GuildConstant.JoinFailCode.LEVEL_UNENOUGH;
				}else if(guild.getGuildInfo().getFightLimit() > player.getBasePlayer().getPlayerInfo().getFight()){
					result = GuildConstant.JoinFailCode.FIGHT_UNENOUGH;
				}else if(guild.getGuildInfo().getJoinType() == GuildConstant.JoinType.AUTO_CONDITION){
					if(guild.getMemberMap().size() >= guild.getMaxMemberNum()){
						result = GuildConstant.JoinFailCode.GUILD_FULL;
					}
				}
			}
		}
		if(result > 0){
			GuildRespMsg.Builder respMsg = GuildRespMsg.newBuilder();
			respMsg.setAction(GuildOperateAction.REQUEST_APPLY_JOIN);
			respMsg.setResult(result);
			PBMessage respPkg = MessageUtil.buildMessage(Protocol.U_GUILD_ACTION_RESP, respMsg);
			player.sendPbMessage(respPkg);
			return;
		}
		
		//如果帮派设置为满足条件自动加入，则直接加入帮派
		if(guild.getGuildInfo().getJoinType() == GuildConstant.JoinType.AUTO_CONDITION){
			//玩家加入帮派
			GuildManager.getIns().playerJoinGuild(player, guildId);
			
			//回应申请人加入帮派成功
			GuildRespMsg.Builder responseMsg = GuildRespMsg.newBuilder();
			guild.writeResponseApplyMsg(responseMsg, player.getPlayerId(), GuildConstant.ResponseApplyType.SELF_ACCEPT);
			PBMessage responsePkg = MessageUtil.buildMessage(Protocol.U_GUILD_ACTION_RESP, responseMsg);
			player.sendPbMessage(responsePkg);
			
			//广播给帮派成员有玩家加入帮派
			List<Long> memberIds = guild.getMemberIds();
			GuildMemberInfoMsg.Builder joinMsg = GuildMemberInfoMsg.newBuilder();
			guild.writeMemberProto(joinMsg, player.getPlayerId());
			BroadcastUtil.sendBroadcastPacket(memberIds, Protocol.U_GUILD_MEMBER_INFO, joinMsg.build());
			
			//玩家新加入帮派，给玩家推送帮派详情
			GuildInfoRespMsg.Builder guildInfoMsg = GuildInfoRespMsg.newBuilder();
			guild.writeProto(guildInfoMsg, player.getPlayerId());
			PBMessage guildInfoPkg = MessageUtil.buildMessage(Protocol.U_GUILD_INFO, guildInfoMsg);
			player.sendPbMessage(guildInfoPkg);
			return;
		}
		
		//申请成功
		GuildRespMsg.Builder respMsg = GuildRespMsg.newBuilder();
		respMsg.setAction(GuildOperateAction.REQUEST_APPLY_JOIN);
		respMsg.setResult(0);
		PBMessage respPkg = MessageUtil.buildMessage(Protocol.U_GUILD_ACTION_RESP, respMsg);
		player.sendPbMessage(respPkg);
		
		//加入帮派的申请列表
		GuildApplyInfo apply = GuildManager.getIns().addApply(player, guildId);
		
		//通知有权限审核的成员
		GuildApplyInfoMsg.Builder notifyMsg = GuildApplyInfoMsg.newBuilder();
		notifyMsg.setPlayerId(player.getPlayerId());
		notifyMsg.setPlayerName(player.getNickName());
		notifyMsg.setLevel(player.getLevel());
		notifyMsg.setFight(player.getBasePlayer().getPlayerInfo().getFight());
		notifyMsg.setIconId(1031001);
		notifyMsg.setApplyTime(apply.getApplyTime());
		
		Set<Long> ids = guild.getApplyCheckPlayers();
		BroadcastUtil.sendBroadcastPacket(ids, Protocol.U_GUILD_APPLY_INFO, notifyMsg.build());
	}

}
