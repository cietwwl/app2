package com.chuangyou.xianni.guild.action;

import com.chuangyou.common.protobuf.pb.arena.ArenaResultProto.ArenaResultMsg;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.constant.CampaignConstant;
import com.chuangyou.xianni.entity.guild.GuildMemberInfo;
import com.chuangyou.xianni.guild.action.baseAction.GuildIsGuildMemberAction;
import com.chuangyou.xianni.guild.manager.GuildManager;
import com.chuangyou.xianni.guild.struct.Guild;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;

public class GuildJobSeizeResultAction extends GuildIsGuildMemberAction {

	public GuildJobSeizeResultAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(GamePlayer player, PBMessage packet, Guild guild, GuildMemberInfo memberInfo) throws Exception {
		// TODO Auto-generated method stub

		ArenaResultMsg req = ArenaResultMsg.parseFrom(packet.getBytes());
		if(req.getResult() == CampaignConstant.ChallengeResult.ALREADY_IN_CAMPAIGN){
			guild.endSeize(player.getPlayerId());
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_SEIZE_ALREADY_INCAMPAIGN, packet.getCode(), "你已经在副本中，不能进行夺权");
			return;
		}
		if(req.getResult() == CampaignConstant.ChallengeResult.START_FAIL){
			guild.endSeize(player.getPlayerId());
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_SEIZE_UNKNOWN_ERROR, packet.getCode(), "夺权无法开始");
			return;
		}
		GuildManager.getIns().guildSeizeResult(player, guild, req.getResult());
	}

}
