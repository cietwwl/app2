package com.chuangyou.xianni.guild.manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.chuangyou.common.protobuf.pb.guild.GuildRespProto.GuildRespMsg;
import com.chuangyou.xianni.constant.CampaignConstant;
import com.chuangyou.xianni.constant.GuildConstant;
import com.chuangyou.xianni.constant.GuildConstant.GuildLogType;
import com.chuangyou.xianni.constant.GuildConstant.GuildType;
import com.chuangyou.xianni.constant.PlayerState;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.guild.GuildApplyInfo;
import com.chuangyou.xianni.entity.guild.GuildInfo;
import com.chuangyou.xianni.entity.guild.GuildMemberInfo;
import com.chuangyou.xianni.entity.guild.GuildSystemCfg;
import com.chuangyou.xianni.entity.robot.RobotTemplate;
import com.chuangyou.xianni.exec.AbstractActionQueue;
import com.chuangyou.xianni.exec.ThreadManager;
import com.chuangyou.xianni.guild.GuildOperateAction;
import com.chuangyou.xianni.guild.action.GuildMemberStateAction;
import com.chuangyou.xianni.guild.action.baseAction.GuildBaseAction;
import com.chuangyou.xianni.guild.struct.Guild;
import com.chuangyou.xianni.guild.template.GuildTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.player.PlayerInfoSendCmd;
import com.chuangyou.xianni.proto.BroadcastUtil;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.robot.RobotManager;
import com.chuangyou.xianni.sql.dao.DBManager;
import com.chuangyou.xianni.truck.helper.TruckDataHelper;
import com.chuangyou.xianni.word.WorldMgr;

public class GuildManager {

	private static GuildManager ins = new GuildManager();
	
	private GuildManager(){
		
	}
	
	public static GuildManager getIns(){
		return ins;
	}
	
	/**
	 * 帮派处理队列
	 */
	private AbstractActionQueue actionQueue = new AbstractActionQueue(ThreadManager.actionExecutor);
	
	/**
	 * 帮派信息
	 */
	private ConcurrentHashMap<Integer, Guild> guildMap = null;
	
	/**
	 * 玩家所在帮派ID
	 */
	private ConcurrentHashMap<Long, Integer> playerGuildMap = null;

	public AbstractActionQueue getActionQueue() {
		return actionQueue;
	}
	
	/**
	 * 初始化，加载数据
	 * @return
	 */
	public boolean init(){
		List<GuildInfo> guildList = DBManager.getGuildInfoDao().getAll();
		List<GuildMemberInfo> memberList = DBManager.getGuildMemberDao().getAll();
		
		//帮派信息
		guildMap = new ConcurrentHashMap<Integer, Guild>();
		for(GuildInfo guildInfo: guildList){
			Guild guild = new Guild();
			guild.setGuildInfo(guildInfo);
			
			guildMap.put(guildInfo.getGuildId(), guild);
			
			guild.loadFromDatabase();
		}
		
		//成员信息
		playerGuildMap = new ConcurrentHashMap<Long, Integer>();
		for(GuildMemberInfo memberInfo: memberList){
			Guild guild = guildMap.get(memberInfo.getGuildId());
			if(guild == null) continue;
			guild.addMember(memberInfo);
			
			playerGuildMap.put(memberInfo.getPlayerId(), memberInfo.getGuildId());
		}
		
		//检查系统门派是否创建，如果未创建，自动创建系统门派
		List<GuildSystemCfg> tempGuidList = GuildTemplateMgr.getGuildList();
		for(GuildSystemCfg cfg: tempGuidList){
			if(guildMap.containsKey(cfg.getGuildId()) == false){
				Guild guild = new Guild();
				guild.createSystemGuild(cfg);
				
				guildMap.put(cfg.getGuildId(), guild);
				
				//系统门派管理层初始化为机器人
				Map<Integer, List<RobotTemplate>> jobRobotMap = RobotManager.getGuildRobots(cfg.getGuildId());
				for(int job: jobRobotMap.keySet()){
					List<RobotTemplate> robotList = jobRobotMap.get(job);
					for(RobotTemplate memberRobot: robotList){
						GuildMemberInfo member = guild.buildRobotMember(memberRobot);
						guild.addMember(member);
						
						playerGuildMap.put((long)memberRobot.getId(), cfg.getGuildId());
					}
				}
			}
		}
		
		return true;
	}
	/**
	 * 入库
	 * @return
	 */
	public boolean saveToDatabase(){
		Iterator<Guild> iterator = guildMap.values().iterator();
		while(iterator.hasNext()){
			Guild guild = iterator.next();
			guild.saveToDatabase();
		}
		return true;
	}
	
	/**
	 * 每天早上5点检查玩家帮派离线情况
	 * 系统门派成员超过3天未上线的踢出门派
	 * 玩家门派所有成员7天内都没上过线的帮派自动解散
	 */
	public void checkGuildOffline(){
		//玩家门派需要自动解散的帮派ID列表
		List<Integer> guildList = new ArrayList<>();
		//系统门派需要自动退出的玩家ID列表
		List<Long> exitList = new ArrayList<>();
		
		Iterator<Guild> iterator = guildMap.values().iterator();
		while(iterator.hasNext()){
			Guild guild = iterator.next();
			
			if(guild.getGuildInfo().getType() == GuildType.PLAYER_GUILD){
				boolean needDissolve = true;
				Iterator<GuildMemberInfo> memberIterator = guild.getMemberMap().values().iterator();
				while (memberIterator.hasNext()) {
					GuildMemberInfo memberInfo = memberIterator.next();
					GamePlayer memberPlayer = WorldMgr.getPlayer(memberInfo.getPlayerId());
					
					if(memberPlayer == null) continue;
					//有玩家在线，不需要解散
					if(memberPlayer.getPlayerState() == PlayerState.ONLINE){
						needDissolve = false;
						break;
					}
					
					if(memberPlayer.getPlayerOfflineTime() == null) continue;
					
					//有玩家离线时间小于7天，不需要解散
					long leaveTime = System.currentTimeMillis() - memberPlayer.getPlayerOfflineTime().getTime();
					if(leaveTime < 1000 * 60 * 60 * 24 * 7){
						needDissolve = false;
						break;
					}
				}
				//帮派全部成员离线都超过7天，加入自动解散的帮派列表
				if(needDissolve == true){
					guildList.add(guild.getGuildInfo().getGuildId());
				}
			}else if(guild.getGuildInfo().getType() == GuildType.SYSTEM_GUILD){
				Iterator<GuildMemberInfo> memberIterator = guild.getMemberMap().values().iterator();
				while (memberIterator.hasNext()) {
					GuildMemberInfo memberInfo = memberIterator.next();
					
					//机器人不算
					if(RobotManager.isRobot(memberInfo.getPlayerId()) == true) continue;
					
					GamePlayer memberPlayer = WorldMgr.getPlayer(memberInfo.getPlayerId());
					if(memberPlayer == null) continue;
					//玩家在线，不需要退出
					if(memberPlayer.getPlayerState() == PlayerState.ONLINE){
						continue;
					}
					
					if(memberPlayer.getPlayerOfflineTime() == null) continue;
					
					long leaveTime = System.currentTimeMillis() - memberPlayer.getPlayerOfflineTime().getTime();
					if(leaveTime > 1000 * 60 * 60 * 24 * 3){
						//三天没上线，加入自动退出列表
						exitList.add(memberInfo.getPlayerId());
					}
				}
			}
		}
		//解散帮派
		for(int guildId: guildList){
			this.dissolveGuild(guildId);
		}
		//让玩家退出帮派
		for(long playerId: exitList){
			this.playerExit(playerId, GuildOperateAction.GUILD_SYSTEM_AUTO_EXIT);
		}
	}
	
	/**
	 * 获取玩家所在帮派ID
	 * @param playerId
	 * @return
	 */
	public int getPlayerGuildId(long playerId){
		if(!this.playerGuildMap.containsKey(playerId)){
			return 0;
		}
		return this.playerGuildMap.get(playerId);
	}
	
	/**
	 * 获取玩家帮派信息
	 * @param playerId
	 * @return
	 */
	public Guild getPlayerGuild(long playerId){
		if(!this.playerGuildMap.containsKey(playerId)){
			return null;
		}
		int guildId = this.playerGuildMap.get(playerId);
		return this.guildMap.get(guildId);
	}
	
	/**
	 * 创建帮派
	 * @param playerId
	 * @param guildName
	 * @param createLevel
	 * @return
	 */
	public Guild createGuild(GamePlayer player, String guildName, int createLevel){
		//创建帮派
		Guild guild = new Guild();
		guild.createGuild(player.getPlayerId(), guildName, createLevel);
		
		//存入缓存
		int guildId = guild.getGuildInfo().getGuildId();
		this.guildMap.put(guildId, guild);
		this.playerGuildMap.put(player.getPlayerId(), guildId);
		
		//创建成员并加入帮派
		GuildMemberInfo info = guild.buildPlayerMember(player.getPlayerId(), GuildConstant.GuildJob.LEADER);
		guild.addMember(info);
		
		//帮派日志
		guild.log(player.getPlayerId(), GuildLogType.GUILD_CREATE, 0, 0);
		
		//镖车处理
		TruckDataHelper.addGuildHelper(player);
		
		//帮派信息场景通知
		PlayerInfoSendCmd.sendGuildUpdatePacket(player);
		return guild;
	}
	/**
	 * 添加申请
	 * @param player
	 * @param guildId
	 */
	public GuildApplyInfo addApply(GamePlayer player, int guildId){
		Guild guild = getGuildMap().get(guildId);
		if(guild == null) return null;
		
		GuildApplyInfo apply = new GuildApplyInfo();
		apply.setPlayerId(player.getPlayerId());
		apply.setGuildId(guildId);
		apply.setApplyTime(System.currentTimeMillis());
		apply.setOp(Option.Insert);
		guild.addApply(apply);
		return apply;
	}
	
	/**
	 * 玩家加入帮派
	 * @param player
	 * @param guildId
	 * @return
	 */
	public boolean playerJoinGuild(GamePlayer player, int guildId){
		if(playerGuildMap.containsKey(player.getPlayerId())){
			return false;
		}
		
		Guild guild = getGuildMap().get(guildId);
		if(guild == null) return false;
		
		GuildMemberInfo member = guild.buildPlayerMember(player.getPlayerId(), GuildConstant.GuildJob.MEMBER);
		guild.addMember(member);
		
		//如果加入的是上次所在帮派，则保留贡献值
		GuildMemberInfo historyMemberInfo = player.getGuildInventory().getHistoryMember();
		if(historyMemberInfo.getGuildId() == guildId){
			member.setContributionTotal(historyMemberInfo.getContributionTotal());
			member.setContribution(historyMemberInfo.getContribution());
			member.setConsumeSupply(historyMemberInfo.getConsumeSupply());
		}
		
		playerGuildMap.put(player.getPlayerId(), guildId);
		
		//如果在线，刷新帮派技能属性
		if(player.getPlayerState() == PlayerState.ONLINE){
			player.getGuildInventory().updateSkillProperty();
		}
		
		guild.log(player.getPlayerId(), GuildLogType.GUILD_JOIN, 0, 0);
		
		//镖车处理
		TruckDataHelper.addGuildHelper(player);
		
		//帮派信息场景通知
		PlayerInfoSendCmd.sendGuildUpdatePacket(player);
		return true;
	}
	/**
	 * 系统门派中，玩家退出门派或者职位变更后，用机器人补充职位
	 * @param robotCfg
	 * @param guild
	 */
	private void robotJoinGuild(RobotTemplate robotCfg, Guild guild){
		if(playerGuildMap.containsKey(robotCfg.getId())){
			return;
		}
		if(guild.getGuildInfo().getType() != GuildType.SYSTEM_GUILD) return;
		GuildMemberInfo member = guild.buildRobotMember(robotCfg);
		guild.addMember(member);
		
		playerGuildMap.put(member.getPlayerId(), guild.getGuildInfo().getGuildId());
	}
	
	/**
	 * 解散帮派
	 * @param guildId
	 */
	public void dissolveGuild(int guildId){
		Guild guild = this.getGuildMap().remove(guildId);
		if(guild == null) return;
		
		//清理帮派信息，实际上仅有一个成员的时候才能解散帮派
		List<Long> memberIds = guild.getMemberIds();
		
		guild.disposeGuild();
		
		for(long memberId: memberIds){
			this.playerGuildMap.remove(memberId);
			
			GamePlayer player = WorldMgr.getPlayer(memberId);
			if(player != null){
				
				//镖车处理
				TruckDataHelper.quitGuildHelper(player);
				
				//帮派信息场景通知
				PlayerInfoSendCmd.sendGuildUpdatePacket(player);
			}
		}
		
		GuildRespMsg.Builder notifyMsg = GuildRespMsg.newBuilder();
		notifyMsg.setAction(GuildOperateAction.GUILD_DISSOLVE);
		//通知帮派内所有成员帮派解散(实际上只有一个人时才能解散)
		BroadcastUtil.sendBroadcastPacket(memberIds, Protocol.U_GUILD_ACTION_RESP, notifyMsg.build());
	}
	
	/**
	 * 玩家退出帮派
	 * @param playerId
	 */
	public void playerExit(long playerId, int option){
		Guild guild = getPlayerGuild(playerId);
		
		List<Long> memberIds = guild.getMemberIds();
		
		playerGuildMap.remove(playerId);
		GuildMemberInfo removeMember = guild.removeMember(playerId);
		
		if(option == GuildOperateAction.GUILD_EXIT){
			guild.log(playerId, GuildLogType.GUILD_EXIT, 0, 0);
		}else if(option == GuildOperateAction.REMOVE_MEMBER){
			guild.log(playerId, GuildLogType.GUILD_REMOVE, 0, 0);
		}
		
		//通知所有成员玩家退出帮派
		GuildRespMsg.Builder notifyMsg = GuildRespMsg.newBuilder();
		notifyMsg.setAction(option);
		notifyMsg.setResult(0);
		notifyMsg.setParam1(playerId);
		GamePlayer player = WorldMgr.getPlayer(playerId);
		if(player != null){
			notifyMsg.setParamStr(player.getNickName());
		}
		BroadcastUtil.sendBroadcastPacket(memberIds, Protocol.U_GUILD_ACTION_RESP, notifyMsg.build());
		
		//如果在线，刷新帮派技能属性
		if(player.getPlayerState() == PlayerState.ONLINE){
			player.getGuildInventory().updateSkillProperty();
		}
		
		//系统门派玩家退出时，如果玩家有职务，找机器人代替，如果此玩家正在被挑战夺权，也用机器人代替
		if(guild.getGuildInfo().getType() == GuildType.SYSTEM_GUILD){
			List<RobotTemplate> jobList = RobotManager.getGuildJobRobots(guild.getGuildInfo().getGuildId(), removeMember.getJob());
			if(jobList != null && jobList.size() > 0){
				for(RobotTemplate robot: jobList){
					GuildMemberInfo robotMember =  guild.getMember(robot.getId());
					if(robotMember == null){
						this.robotJoinGuild(robot, guild);
						if(removeMember.getPlayerId() == guild.getSeizeTargetId()){
							guild.setSeizeTargetId(robot.getId());
						}
						break;
					}
				}
			}
		}
		
		//镖车处理
		TruckDataHelper.quitGuildHelper(player);
		
		//帮派信息场景通知
		PlayerInfoSendCmd.sendGuildUpdatePacket(player);
	}
	
	/**
	 * 玩家上下线，通知帮派成员
	 * @param playerId
	 */
	public void playerStateUpdate(GamePlayer player){
		GuildBaseAction action = new GuildMemberStateAction(player, null);
		action.getActionQueue().enqueue(action);
	}
	
	/**
	 * 门派夺权挑战结果
	 * @param player
	 * @param guild
	 * @param result
	 */
	public void guildSeizeResult(GamePlayer player, Guild guild, int result){
		if(player.getPlayerId() != guild.getSeizePlayerId()){
			return;
		}
		
		if(result == CampaignConstant.ChallengeResult.FAIL){
			//夺权结束，清除夺权标记
			guild.endSeize(player.getPlayerId());
			
			//返回给夺权发起者夺权失败
			GuildRespMsg.Builder respMsg = GuildRespMsg.newBuilder();
			respMsg.setAction(GuildOperateAction.GUILD_JOB_SEIZE);
			respMsg.setResult(1);
			PBMessage respPkg = MessageUtil.buildMessage(Protocol.U_GUILD_ACTION_RESP, respMsg);
			player.sendPbMessage(respPkg);
		}else if(result == CampaignConstant.ChallengeResult.WIN){
			GuildMemberInfo memberInfo = guild.getMember(player.getPlayerId());
			
			GuildMemberInfo targetMember = guild.getMember(guild.getSeizeTargetId());
			if(targetMember == null){
				guild.endSeize(player.getPlayerId());
				return;
			}
			
			int targetJob = targetMember.getJob();
			
			List<Long> memberIds = guild.getMemberIds();
			if(RobotManager.isRobot(guild.getSeizeTargetId())){
				//机器人被抢，把机器人踢出门派
				guild.removeMember(guild.getSeizeTargetId());
				
				GuildRespMsg.Builder targetMsg = GuildRespMsg.newBuilder();
				targetMsg.setAction(GuildOperateAction.GUILD_JOB_SEIZE);
				targetMsg.setResult(3);
				targetMsg.setParam1(targetMember.getPlayerId());
				RobotTemplate robot = RobotManager.getRobotTemp((int)guild.getSeizeTargetId());
				if(robot != null){
					targetMsg.setParamStr(robot.getNickName());
				}
				BroadcastUtil.sendBroadcastPacket(memberIds, Protocol.U_GUILD_ACTION_RESP, targetMsg.build());
			}else{
				//被夺权者降级为普通成员
				guild.changeMemberJob(guild.getSeizeTargetId(), GuildConstant.GuildJob.MEMBER);
				GuildRespMsg.Builder targetMsg = GuildRespMsg.newBuilder();
				targetMsg.setAction(GuildOperateAction.GUILD_JOB_SEIZE);
				targetMsg.setResult(2);
				targetMsg.setParam1(targetMember.getPlayerId());
				targetMsg.setParam2(targetMember.getJob());
				targetMsg.setParam3(targetMember.getLastSeizeWinTime());
				GamePlayer targetPlayer = WorldMgr.getPlayer(targetMember.getPlayerId());
				if(targetPlayer != null){
					targetMsg.setParamStr(targetPlayer.getNickName());
				}
				BroadcastUtil.sendBroadcastPacket(memberIds, Protocol.U_GUILD_ACTION_RESP, targetMsg.build());
			}
			
			//夺权发起者升级为目标的职位
			guild.changeMemberJob(guild.getSeizePlayerId(), targetJob);
			memberInfo.setLastSeizeWinTime(System.currentTimeMillis());
			
			GuildRespMsg.Builder seizeMsg = GuildRespMsg.newBuilder();
			seizeMsg.setAction(GuildOperateAction.GUILD_JOB_SEIZE);
			seizeMsg.setResult(0);
			seizeMsg.setParam1(memberInfo.getPlayerId());
			seizeMsg.setParam2(memberInfo.getJob());
			seizeMsg.setParam3(memberInfo.getLastSeizeWinTime());
			seizeMsg.setParamStr(player.getNickName());
			BroadcastUtil.sendBroadcastPacket(memberIds, Protocol.U_GUILD_ACTION_RESP, seizeMsg.build());
			
			guild.endSeize(player.getPlayerId());
		}
	}

	/**
	 * 获取所有帮派
	 * @return
	 */
	public ConcurrentHashMap<Integer, Guild> getGuildMap() {
		return guildMap;
	}
}
