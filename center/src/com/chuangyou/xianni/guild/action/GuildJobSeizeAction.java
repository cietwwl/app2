package com.chuangyou.xianni.guild.action;

import java.util.List;

import com.chuangyou.common.protobuf.pb.PlayerInfoMsgProto.PlayerInfoMsg;
import com.chuangyou.common.protobuf.pb.army.RobotInfoProto.RobotInfoMsg;
import com.chuangyou.common.protobuf.pb.guild.GuildReqProto.GuildReqMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.constant.CampaignConstant;
import com.chuangyou.xianni.constant.GuildConstant.GuildJob;
import com.chuangyou.xianni.constant.GuildConstant.GuildType;
import com.chuangyou.xianni.constant.PlayerState;
import com.chuangyou.xianni.entity.avatar.AvatarInfo;
import com.chuangyou.xianni.entity.guild.GuildJobCfg;
import com.chuangyou.xianni.entity.guild.GuildMemberInfo;
import com.chuangyou.xianni.entity.item.BagType;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.guild.action.baseAction.GuildIsGuildMemberAction;
import com.chuangyou.xianni.guild.manager.GuildManager;
import com.chuangyou.xianni.guild.struct.Guild;
import com.chuangyou.xianni.guild.template.GuildTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.robot.RobotManager;
import com.chuangyou.xianni.word.WorldMgr;

public class GuildJobSeizeAction extends GuildIsGuildMemberAction {

	public GuildJobSeizeAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(GamePlayer player, PBMessage packet, Guild guild, GuildMemberInfo memberInfo) throws Exception {
		// TODO Auto-generated method stub

		if (guild.isDoSeize() == true) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_SEIZE_DOING, packet.getCode(), "帮派中正在进行夺权，只能同时进行一场夺权挑战");
			return;
		}
		GuildReqMsg req = GuildReqMsg.parseFrom(packet.getBytes());
		long targetId = req.getParam1();
		int targetJob = (int) req.getParam2();

		GuildMemberInfo targetMember = guild.getMember(targetId);

		if (targetJob != targetMember.getJob()) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, packet.getCode(), "未知错误");
			return;
		}

		// 不能挑战比自己低的职位
		if (targetJob >= memberInfo.getJob()) {
			return;
		}

		if (guild.getGuildInfo().getType() == GuildType.PLAYER_GUILD) {
			if (targetJob != GuildJob.LEADER) {
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_JOB_NO_SEIZE, packet.getCode(), "该职位不能夺权");
				return;
			}
			GamePlayer targetPlayer = WorldMgr.getPlayer(targetId);
			if (targetPlayer == null) {
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, packet.getCode(), "未知错误");
				return;
			}
			if (targetPlayer.getPlayerState() == PlayerState.ONLINE) {
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_TARGET_NOT_SEIZE, packet.getCode(), "目标未达到夺权条件");
				return;
			}
			GuildJobCfg jobCfg = GuildTemplateMgr.getJobMap().get(memberInfo.getJob());
			if (jobCfg == null) {
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, packet.getCode(), "tb_z_guild_job 配置错误");
				return;
			}
			long leaveTime = 1000 * 60 * jobCfg.getSeizeDay();
			// long leaveTime = 1000 * 60 * 60 * 24 * jobCfg.getSeizeDay();
			if (System.currentTimeMillis() - targetPlayer.getPlayerOfflineTime().getTime() < leaveTime) {
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_TARGET_NOT_SEIZE, packet.getCode(), "目标未达到夺权条件");
				return;
			}
		} else if (guild.getGuildInfo().getType() == GuildType.SYSTEM_GUILD) {
			if (targetJob == GuildJob.MEMBER) {
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_JOB_NO_SEIZE, packet.getCode(), "该职位不能夺权");
				return;
			}
			if (!RobotManager.isRobot(targetId)) {
				if (System.currentTimeMillis() - targetMember.getLastSeizeWinTime() < 1000 * 60 * 5) {
					ErrorMsgUtil.sendErrorMsg(player, ErrorCode.GUILD_TARGET_SEIZE_WIN_CD, packet.getCode(), "目标玩家上位时间不到5分钟");
					return;
				}
			}
		}
		// 检查背包物品是否足够
		GuildJobCfg jobCfg = GuildTemplateMgr.getJobMap().get(targetJob);
		int needItem = jobCfg.getCostItem();
		int needNum = jobCfg.getCostCount();
		if (player.getBagInventory().getItemCount(BagType.Play, needItem) < needNum) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Item_UnEnough, packet.getCode(), "物品不足");
			return;
		}
		// 消耗物品
		if (!player.getBagInventory().removeItem(BagType.Play, needItem, needNum, ItemRemoveType.GUILD_SEIZE)) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, packet.getCode(), "未知错误");
			return;
		}

		// 挑战
		guild.startSeize(player.getPlayerId(), targetId);

		// 玩家门派不用挑战
		if (guild.getGuildInfo().getType() == GuildType.PLAYER_GUILD) {
			GuildManager.getIns().guildSeizeResult(player, guild, CampaignConstant.ChallengeResult.WIN);
			return;
		}

		RobotInfoMsg.Builder builder = RobotInfoMsg.newBuilder();
		if (RobotManager.isRobot(targetId)) {
			RobotManager.writeRobotData(player, targetId, builder);

			PlayerInfoMsg.Builder playerInfoMsgBuilder = builder.getSimpInfoBuilder();
			playerInfoMsgBuilder.setGuildId(guild.getGuildInfo().getGuildId());
			playerInfoMsgBuilder.setGuildName(guild.getGuildInfo().getName());
			playerInfoMsgBuilder.setGuildJob(targetMember.getJob());
		} else {
			GamePlayer target = WorldMgr.getPlayer(targetId);
			if (target == null) {
				Log.error("GuildJobSeizeAction challenge not find player-" + player.getPlayerId() + "  targetId : " + targetId);
				return;
			}
			if (target.getArmyInventory() != null) {
				target.getArmyInventory().getArmy().getHero().writeRobotInfo(target, builder);
			}
			if (target.getAvatarInventory() != null) {
				List<AvatarInfo> avatars = player.getAvatarInventory().getFinghtingInfos();
				for (AvatarInfo avatar : avatars) {
					builder.addAvatarInfos(player.getAvatarInventory().writeProto(avatar));
				}
			}
		}
		PBMessage message = MessageUtil.buildMessage(Protocol.S_GUILD_SEIZE_CHALLENGE, builder);
		player.sendPbMessage(message);
	}

}
