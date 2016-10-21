package com.chuangyou.xianni.guild.struct;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.chuangyou.common.protobuf.pb.guild.GuildInfoRespProto.GuildInfoRespMsg;
import com.chuangyou.common.protobuf.pb.guild.GuildMemberInfoProto.GuildMemberInfoMsg;
import com.chuangyou.common.protobuf.pb.guild.GuildRespProto.GuildRespMsg;
import com.chuangyou.common.protobuf.pb.guild.GuildSnapProto.GuildSnapMsg;
import com.chuangyou.common.util.TimeUtil;
import com.chuangyou.xianni.common.template.SystemConfigTemplateMgr;
import com.chuangyou.xianni.constant.GuildConstant;
import com.chuangyou.xianni.constant.GuildConstant.GuildJob;
import com.chuangyou.xianni.constant.GuildConstant.GuildType;
import com.chuangyou.xianni.constant.PlayerState;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.guild.GuildApplyInfo;
import com.chuangyou.xianni.entity.guild.GuildInfo;
import com.chuangyou.xianni.entity.guild.GuildJobPowerCfg;
import com.chuangyou.xianni.entity.guild.GuildLogInfo;
import com.chuangyou.xianni.entity.guild.GuildMemberInfo;
import com.chuangyou.xianni.entity.guild.GuildSystemCfg;
import com.chuangyou.xianni.entity.guild.GuildWarehouseCfg;
import com.chuangyou.xianni.entity.guild.GuildWarehouseItemInfo;
import com.chuangyou.xianni.entity.robot.RobotTemplate;
import com.chuangyou.xianni.entity_id.EntityIdBuilder;
import com.chuangyou.xianni.guild.GuildInventory;
import com.chuangyou.xianni.guild.GuildOperateAction;
import com.chuangyou.xianni.guild.template.GuildTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.player.PlayerInfoSendCmd;
import com.chuangyou.xianni.robot.RobotManager;
import com.chuangyou.xianni.sql.dao.DBManager;
import com.chuangyou.xianni.word.WorldMgr;

public class Guild {

	private GuildInfo guildInfo;
	
	/**
	 * 成员
	 */
	private ConcurrentHashMap<Long, GuildMemberInfo> memberMap = new ConcurrentHashMap<Long, GuildMemberInfo>();
	
	/**
	 * 职位索引
	 */
	private ConcurrentHashMap<Integer, Set<Long>> jobMap = new ConcurrentHashMap<Integer, Set<Long>>();
	
	/**
	 * 申请
	 */
	private ConcurrentHashMap<Long, GuildApplyInfo> applyMap = new ConcurrentHashMap<Long, GuildApplyInfo>();
	private List<GuildApplyInfo> applyList = new ArrayList<>();
	
	/**
	 * 仓库物品信息
	 */
	private ConcurrentHashMap<Integer, GuildWarehouseItemInfo> itemMap = new ConcurrentHashMap<Integer, GuildWarehouseItemInfo>();
	
	/**
	 * 日志
	 */
	private List<GuildLogInfo> logList = new ArrayList<>();
	
	/** 上次计算战力的时间 */
	private long lastComputeFight = 0;
	
	/** 正在夺位的玩家ID */
	private long seizePlayerId = 0;
	
	/** 正在被夺位的玩家ID */
	private long seizeTargetId = 0;
	
	public Guild() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 创建帮派
	 * @param playerId
	 * @param guildName
	 * @param createLevel
	 */
	public void createGuild(long playerId, String guildName, int createLevel){
		int guildId = EntityIdBuilder.guildIdBuild();
		guildInfo = new GuildInfo(playerId, guildId, GuildConstant.GuildType.PLAYER_GUILD, guildName, createLevel);
		guildInfo.setOp(Option.Insert);
		
		memberMap = new ConcurrentHashMap<Long, GuildMemberInfo>();
		jobMap = new ConcurrentHashMap<Integer, Set<Long>>();
		
		applyMap = new ConcurrentHashMap<Long, GuildApplyInfo>();
		applyList = new ArrayList<>();
		
		itemMap = new ConcurrentHashMap<Integer, GuildWarehouseItemInfo>();
		
		logList = new ArrayList<>();
	}
	
	/**
	 * 创建系统帮派
	 * @param cfg
	 */
	public boolean createSystemGuild(GuildSystemCfg cfg){
		RobotTemplate robot = RobotManager.getGuildLeaderTemp(cfg.getGuildId());
		if(robot == null) return false;
		
		guildInfo = new GuildInfo();
		guildInfo.setGuildId(cfg.getGuildId());
		guildInfo.setType(GuildConstant.GuildType.SYSTEM_GUILD);
		guildInfo.setName(cfg.getName());
		guildInfo.setCreateTime(System.currentTimeMillis());
		guildInfo.setLevel(cfg.getLevel());
		guildInfo.setBuildExp(0);
		guildInfo.setTotalBuildExp(0);
		guildInfo.setSupply(0);
		guildInfo.setMainBuildLevel(cfg.getMainBuildLevel());
		guildInfo.setSkillShopLevel(cfg.getSkillShopLevel());
		guildInfo.setShopLevel(cfg.getShopLevel());
		guildInfo.setWarehouseLevel(cfg.getWarehouseLevel());
		guildInfo.setNotice(cfg.getNotice());
		guildInfo.setLeaderId(robot.getId());
		guildInfo.setJoinType(GuildConstant.JoinType.AUTO_CONDITION);
		guildInfo.setLevelLimit(0);
		guildInfo.setFightLimit(0);
		guildInfo.setSupplyCheckTime(System.currentTimeMillis());
		guildInfo.setOp(Option.Insert);
		
		memberMap = new ConcurrentHashMap<Long, GuildMemberInfo>();
		jobMap = new ConcurrentHashMap<Integer, Set<Long>>();
		
		applyMap = new ConcurrentHashMap<Long, GuildApplyInfo>();
		applyList = new ArrayList<>();
		
		itemMap = new ConcurrentHashMap<Integer, GuildWarehouseItemInfo>();
		
		logList = new ArrayList<>();
		
		return true;
	}
	/**
	 * 创建机器人成员
	 * @param robotCfg
	 * @return
	 */
	public GuildMemberInfo buildRobotMember(RobotTemplate robotCfg){
		GuildMemberInfo member = new GuildMemberInfo();
		member.setPlayerId(robotCfg.getId());
		member.setGuildId(guildInfo.getGuildId());
		member.setJob(robotCfg.getGuildJob());
		member.setContributionTotal(0);
		member.setContribution(0);
		member.setFortune(0);
		member.setConsumeSupply(0);
		member.setOp(Option.Insert);
		
		return member;
	}
	/**
	 * 创建玩家成员
	 * @param playerId
	 * @param job
	 * @return
	 */
	public GuildMemberInfo buildPlayerMember(long playerId, int job){
		GuildMemberInfo member = new GuildMemberInfo();
		member.setPlayerId(playerId);
		member.setGuildId(guildInfo.getGuildId());
		member.setJob(job);
		member.setContributionTotal(0);
		member.setContribution(0);
		member.setFortune(0);
		member.setConsumeSupply(0);
		member.setOp(Option.Insert);
		
		return member;
	}
	
	/**
	 * 获取成员列表
	 * @return
	 */
	public List<Long> getMemberIds(){
		List<Long> ids = new ArrayList<>();
		ids.addAll(memberMap.keySet());
		return ids;
	}
	/**
	 * 获取成员列表
	 * @return
	 */
	public Set<Long> getMemberIdsSet(){
		Set<Long> ids = new HashSet<>();
		ids.addAll(memberMap.keySet());
		return ids;
	}
	
	/**
	 * 添加成员
	 * @param info
	 */
	public void addMember(GuildMemberInfo info){
		this.memberMap.put(info.getPlayerId(), info);
		
		Set<Long> playerIds = jobMap.get(info.getJob());
		if(playerIds == null){
			playerIds = new HashSet<>();
			jobMap.put(info.getJob(), playerIds);
		}
		playerIds.add(info.getPlayerId());
		
		if(info.getJob() == GuildJob.LEADER){
			guildInfo.setLeaderId(info.getPlayerId());
		}
	}
	/**
	 * 删除成员
	 * @param playerId
	 */
	public GuildMemberInfo removeMember(long playerId){
		GuildMemberInfo info = memberMap.remove(playerId);
		if(info == null) return null;
		
		if(this.getSeizePlayerId() == playerId){
			this.endSeize(playerId);
		}
		if(!RobotManager.isRobot(playerId)){
			GamePlayer removePlayer = WorldMgr.getPlayer(playerId);
			GuildInventory guildInventory = removePlayer.getGuildInventory();
			if(guildInventory != null){
				GuildMemberInfo historyMember = guildInventory.getHistoryMember();
				historyMember.setGuildId(info.getGuildId());
				historyMember.setJob(info.getJob());
				historyMember.setContributionTotal(info.getContributionTotal());
				historyMember.setContribution(info.getContribution());
				historyMember.setFortune(info.getFortune());
				historyMember.setConsumeSupply(info.getConsumeSupply());
			}
		}
		
		Set<Long> playerIds = jobMap.get(info.getJob());
		if(playerIds != null){
			playerIds.remove(playerId);
		}
		
		if(info.getOp() != Option.Insert){
			DBManager.getGuildMemberDao().remove(info.getPlayerId());
		}
		
		return info;
	}
	
	/**
	 * 添加申请记录
	 * @param info
	 */
	public void addApply(GuildApplyInfo info){
		this.applyMap.put(info.getPlayerId(), info);
		if(!this.applyList.contains(info)){
			this.applyList.add(info);
		}
		
		if(applyList.size() > 50){
			this.removeApply(this.applyList.get(0).getPlayerId());
		}
	}
	/**
	 * 申请列表按时间排序
	 */
	public void sortApplyList(){
		this.applyList.sort(new Comparator<GuildApplyInfo>() {
			@Override
			public int compare(GuildApplyInfo o1, GuildApplyInfo o2) {
				// TODO Auto-generated method stub
				return Long.compare(o1.getApplyTime(), o2.getApplyTime());
			}
		});
	}
	
	/**
	 * 删除申请记录
	 * @param playerId
	 */
	public void removeApply(long playerId){
		GuildApplyInfo info = this.applyMap.remove(playerId);
		this.applyList.remove(info);
		
		if(info.getOp() != Option.Insert){
			DBManager.getGuildApplyDao().remove(guildInfo.getGuildId(), playerId);
		}
	}
	/**
	 * 删除所有申请
	 */
	public void removeAllApply(){
		DBManager.getGuildApplyDao().removeAll(guildInfo.getGuildId());
		
		this.applyMap.clear();
		this.applyList.clear();
		this.applyMap = new ConcurrentHashMap<Long, GuildApplyInfo>();
		this.applyList = new ArrayList<>();
	}
	
	/**
	 * 仓库添加物品
	 * @param itemTempId
	 * @param addNum
	 */
	public GuildWarehouseItemInfo addItem(int itemTempId, int addNum){
		GuildWarehouseItemInfo itemInfo = this.getItem(itemTempId);
		if(itemInfo == null){
			itemInfo = new GuildWarehouseItemInfo();
			itemInfo.setGuildId(this.getGuildInfo().getGuildId());
			itemInfo.setItemTempId(itemTempId);
			itemInfo.setAmount(0);
			itemInfo.setOp(Option.Insert);
			
			this.itemMap.put(itemInfo.getItemTempId(), itemInfo);
		}
		itemInfo.setAmount(itemInfo.getAmount() + addNum);
		return itemInfo;
	}
	
	/**
	 * 帮派删除物品
	 * @param itemTempId
	 * @param removeNum
	 * @return
	 */
	public GuildWarehouseItemInfo removeItem(int itemTempId, int removeNum){
		GuildWarehouseItemInfo itemInfo = this.itemMap.get(itemTempId);
		if(itemInfo == null){
			return null;
		}
		if(itemInfo.getAmount() < removeNum){
			return null;
		}
		itemInfo.setAmount(itemInfo.getAmount() - removeNum);
		
		if(itemInfo.getAmount() <= 0){
			this.itemMap.remove(itemInfo.getItemTempId());
			
			if(itemInfo.getOp() != Option.Insert){
				DBManager.getGuildWarehouseDao().remove(guildInfo.getGuildId(), itemInfo.getItemTempId());
			}
		}
		return itemInfo;
	}
	
	/**
	 * 获取仓库物品
	 * @param itemTempId
	 * @return
	 */
	public GuildWarehouseItemInfo getItem(int itemTempId){
		return this.getItemMap().get(itemTempId);
	}
	
	/**
	 * 获取玩家帮派成员信息
	 * @param playerId
	 * @return
	 */
	public GuildMemberInfo getMember(long playerId){
		return memberMap.get(playerId);
	}
	
	/**
	 * 获取申请信息
	 * @param playerId
	 * @return
	 */
	public GuildApplyInfo getApply(long playerId){
		return applyMap.get(playerId);
	}
	
	/**
	 * 获取有权限审核申请的玩家
	 */
	public Set<Long> getApplyCheckPlayers(){
		Set<Long> ids = new HashSet<>();
		for(int job: jobMap.keySet()){
			GuildJobPowerCfg jobPowerCfg = GuildTemplateMgr.getPowerMap().get(job);
			if(jobPowerCfg == null) continue;
			if(jobPowerCfg.getApplyHandle() > 0){
				ids.addAll(jobMap.get(job));
			}
		}
		return ids;
	}
	
	/**
	 * 解散门派
	 */
	public void disposeGuild(){
		List<Long> ids = getMemberIds();
		for(long playerId: ids){
			this.removeMember(playerId);
		}
		DBManager.getGuildMemberDao().removeAll(guildInfo.getGuildId());
		this.memberMap.clear();
		this.memberMap = null;
		
		DBManager.getGuildApplyDao().removeAll(guildInfo.getGuildId());
		this.applyMap.clear();
		this.applyMap = null;
		this.applyList.clear();
		this.applyList = null;
		
		DBManager.getGuildInfoDao().remove(this.guildInfo);
		this.guildInfo = null;
		
		jobMap.clear();
		jobMap = null;
		
		this.seizePlayerId = 0;
		this.seizeTargetId = 0;
	}
	
	/**
	 * 修改玩家职位
	 * @param playerId
	 * @param job
	 */
	public void changeMemberJob(long playerId, int job){
		GuildMemberInfo member = getMember(playerId);
		Set<Long> ids = jobMap.get(member.getJob());
		if(ids != null){
			ids.remove(playerId);
		}
		
		member.setJob(job);
		
		ids = jobMap.get(job);
		if(ids == null){
			ids = new HashSet<>();
			jobMap.put(job, ids);
		}
		ids.add(playerId);
		
		if(job == GuildJob.LEADER){
			this.getGuildInfo().setLeaderId(playerId);
		}
		
		PlayerInfoSendCmd.sendGuildUpdatePacket(playerId);
	}
	
	/** 开始夺权 */
	public void startSeize(long seizePlayerId, long targetId){
		this.seizePlayerId = seizePlayerId;
		this.seizeTargetId = targetId;
	}
	
	/** 结束夺权 */
	public void endSeize(long playerId){
		if(seizePlayerId == playerId){
			this.seizePlayerId = 0;
			this.seizeTargetId = 0;
		}
	}
	
	/**
	 * 是否正在进行夺权
	 * @return
	 */
	public boolean isDoSeize(){
		if(this.seizePlayerId > 0 && this.seizePlayerId > 0){
			return true;
		}
		return false;
	}
	
	
	/**
	 * 获取当前职位的人数
	 * @param job
	 * @return
	 */
	public int getJobCount(int job){
		Set<Long> jobIds = jobMap.get(job);
		if(jobIds == null){
			return 0;
		}
		return jobIds.size();
	}
	
	public void writeMemberProto(GuildMemberInfoMsg.Builder msg, long playerId){
		GuildMemberInfo info = memberMap.get(playerId);
		if(info == null) return;
		msg.setPlayerId(info.getPlayerId());
		msg.setGuildId(info.getGuildId());
		if(RobotManager.isRobot(playerId) == true){
			RobotTemplate robot = RobotManager.getRobotTemp((int)playerId);
			
			msg.setPlayerName(robot.getNickName());
			msg.setFight(robot.getFluctuate());
			msg.setOnlineState(PlayerState.ONLINE);
			msg.setOfflineTime(0);
			msg.setLevel(robot.getLevel());
		}else{
			GamePlayer player = WorldMgr.getPlayer(info.getPlayerId());
			if(player == null) return;
			msg.setPlayerName(player.getNickName());
			msg.setFight(player.getBasePlayer().getPlayerInfo().getFight());
			msg.setOnlineState(player.getPlayerState());
			msg.setOfflineTime(player.getBasePlayer().getPlayerTimeInfo().getOfflineTime().getTime());
			msg.setLevel(player.getLevel());
		}
		msg.setJob(info.getJob());
		msg.setContributionTotal(info.getContributionTotal());
		msg.setContribution(info.getContribution());
		msg.setFortune(info.getFortune());
		msg.setIconId(1031001);
		msg.setConsumeSupply(info.getConsumeSupply());
		msg.setLastSeizeTime(info.getLastSeizeWinTime());
	}
	
	/**
	 * 帮派快照消息
	 * @param msg
	 * @param playerId 请求者ID
	 * @param hasGuild 请求者是否已有帮派
	 */
	public void writeSnapProto(GuildSnapMsg.Builder msg, long playerId, boolean hasGuild){
		msg.setGuildId(guildInfo.getGuildId());
		msg.setGuildType(guildInfo.getType());
		msg.setName(guildInfo.getName());
		msg.setGangId(guildInfo.getLeaderId());
		if(RobotManager.isRobot(guildInfo.getLeaderId())){
			RobotTemplate robot = RobotManager.getRobotTemp((int)guildInfo.getLeaderId());
			if(robot != null)
				msg.setGangName(robot.getNickName());
		}else{
			GamePlayer leaderPlayer = WorldMgr.getPlayer(guildInfo.getLeaderId());
			if(leaderPlayer != null){
				msg.setGangName(leaderPlayer.getNickName());
			}
		}
		msg.setLevel(guildInfo.getLevel());
		msg.setFight(getGuildFight());
		
		msg.setPlayerNum(memberMap.size());
		msg.setMaxPlayerNum(getMaxMemberNum());
		
		msg.setJoinType(guildInfo.getJoinType());
		msg.setLevelLimit(guildInfo.getLevelLimit());
		msg.setFightLimit(guildInfo.getFightLimit());
		
		msg.setHasApply(false);
		if(hasGuild == false){
			GuildApplyInfo apply = this.getApply(playerId);
			if(apply != null){
				msg.setHasApply(true);
			}
		}
	}
	
	/**
	 * 帮派详情消息写入
	 * @param msg
	 * @param playerId
	 */
	public void writeProto(GuildInfoRespMsg.Builder msg, long playerId){
		msg.setGuildId(guildInfo.getGuildId());
		msg.setGuildType(guildInfo.getType());
		msg.setName(guildInfo.getName());
		msg.setGangId(guildInfo.getLeaderId());
		if(RobotManager.isRobot(guildInfo.getLeaderId())){
			RobotTemplate robot = RobotManager.getRobotTemp((int)guildInfo.getLeaderId());
			if(robot != null)
				msg.setGangName(robot.getNickName());
		}else{
			GamePlayer leaderPlayer = WorldMgr.getPlayer(guildInfo.getLeaderId());
			if(leaderPlayer != null){
				msg.setGangName(leaderPlayer.getNickName());
			}
		}
		msg.setLevel(guildInfo.getLevel());
		msg.setFight(getGuildFight());
		
		msg.setPlayerNum(memberMap.size());
		msg.setMaxPlayerNum(getMaxMemberNum());
		
		msg.setJoinType(guildInfo.getJoinType());
		msg.setLevelLimit(guildInfo.getLevelLimit());
		msg.setFightLimit(guildInfo.getFightLimit());
		
		msg.setNotice(guildInfo.getNotice());
		
		msg.setBuildExp(guildInfo.getBuildExp());
		msg.setSupply(this.getSupply());
		msg.setMainBuildLevel(guildInfo.getMainBuildLevel());
		msg.setSkillShopLevel(guildInfo.getSkillShopLevel());
		msg.setShopLevel(guildInfo.getShopLevel());
		msg.setWarehouseLevel(guildInfo.getWarehouseLevel());
		
		GuildMemberInfo member = memberMap.get(playerId);
		if(member != null){
			msg.setJob(member.getJob());
			msg.setContributionTotal(member.getContributionTotal());
			msg.setContribution(member.getContribution());
			msg.setFortune(member.getFortune());
			msg.setConsumeSupply(member.getConsumeSupply());
		}
	}
	
	public void writeResponseApplyMsg(GuildRespMsg.Builder respMsg, long targetId, int operate){
		respMsg.setAction(GuildOperateAction.RESPONSE_APPLY);
		respMsg.setResult(operate);
		respMsg.setParam1(targetId);
		respMsg.setParam2(this.getGuildInfo().getGuildId());
	}
	
	/**
	 * 检查物资上限
	 */
	private void checkSupplyMax(){
		if(TimeUtil.dateCompare(guildInfo.getSupplyCheckTime())){
			return;
		}
		guildInfo.setSupplyCheckTime(System.currentTimeMillis());
		GuildWarehouseCfg warehouseCfg = GuildTemplateMgr.getWarehouseMap().get(guildInfo.getWarehouseLevel());
		if(warehouseCfg == null) return;
		if(guildInfo.getSupply() >= warehouseCfg.getGuildMaxSupply()){
			guildInfo.setSupply(warehouseCfg.getGuildMaxSupply());
		}
	}
	/**
	 * 获取帮派物资
	 */
	public long getSupply(){
		this.checkSupplyMax();
		return guildInfo.getSupply();
	}
	/**
	 * 设置帮派物资
	 * @param supply
	 */
	public void setSupply(long supply){
		this.checkSupplyMax();
		guildInfo.setSupply(supply);
	}
	/**
	 * 添加帮派物资
	 * @param addValue
	 * @return
	 */
	public boolean addSupply(long addValue){
		this.checkSupplyMax();
		if(addValue <= 0) return false;
		guildInfo.setSupply(guildInfo.getSupply() + addValue);
		return true;
	}
	/**
	 * 消耗帮派物资
	 * @param value
	 * @return
	 */
	public boolean consumeSupply(long value,long playerId){
		this.checkSupplyMax();
		if(value < 0) return false;
		if(value > guildInfo.getSupply()) return false;
		guildInfo.setSupply(guildInfo.getSupply() - value);
		if(playerId > 0){
			//记录玩家消耗
			GuildMemberInfo memberInfo = this.getMember(playerId);
			memberInfo.setConsumeSupply(memberInfo.getConsumeSupply() + value);
		}
		return true;
	}
	
	/**
	 * 获取门派战力
	 * @return
	 */
	public long getGuildFight(){
		if(System.currentTimeMillis() - lastComputeFight > 20 * 1000){
			int guildFight = 0;
			for(long memberId: memberMap.keySet()){
				if(RobotManager.isRobot(memberId)){
					RobotTemplate robot = RobotManager.getRobotTemp((int)memberId);
					guildFight += robot.getFluctuate();
					continue;
				}
				GamePlayer memberPlayer = WorldMgr.getPlayer(memberId);
				if(memberPlayer != null){
					guildFight += memberPlayer.getBasePlayer().getPlayerInfo().getFight();
				}
			}
			this.guildInfo.setFight(guildFight);
			
			lastComputeFight = System.currentTimeMillis();
		}
		return this.guildInfo.getFight();
	}
	
	/**
	 * 获取门派人数上限
	 * @return
	 */
	public int getMaxMemberNum(){
		int maxMemberNum = 0;
		if(this.getGuildInfo().getType() == GuildType.SYSTEM_GUILD){
			maxMemberNum = SystemConfigTemplateMgr.getIntValue("guild.member.max.system");
		}else{
			int maxMemberInit = SystemConfigTemplateMgr.getIntValue("guild.member.max.init");
			maxMemberNum = maxMemberInit + guildInfo.getMainBuildLevel();
		}
		return maxMemberNum;
	}
	
	/**
	 * 写日志
	 * @param playerId
	 * @param operateType
	 * @param value1
	 * @param value2
	 */
	public void log(long playerId, short operateType, long value1, long value2){
		GuildLogInfo logInfo = new GuildLogInfo();
		logInfo.setGuildId(guildInfo.getGuildId());
		logInfo.setOperateTime(new Date());
		logInfo.setOperateType(operateType);
		logInfo.setPlayerId(playerId);
		logInfo.setValue1(value1);
		logInfo.setValue2(value2);
		
		this.addLog(logInfo);
	}
	
	/**
	 * 添加日志
	 * @param info
	 */
	private void addLog(GuildLogInfo info){
		info.setOp(Option.Insert);
		this.logList.add(0, info);
	}
	
	/**
	 * 获取当前页列表
	 * @param lastIndex
	 * @param count
	 */
	public List<GuildLogInfo> getLogList(int startIndex, int count){
		this.clearOldLog();
		
		List<GuildLogInfo> resultList = new ArrayList<>();
		
		if(startIndex >= logList.size()){
			return resultList;
		}
		int endIndex = startIndex + count;
		if(endIndex > logList.size()){
			endIndex = logList.size();
		}
		
		resultList.addAll(logList.subList(startIndex, endIndex));
		return resultList;
	}
	
	/**
	 * 清理超过7天的日志
	 */
	private void clearOldLog(){
		if(logList == null || logList.size() <= 0) return;
		GuildLogInfo lastInfo = logList.get(logList.size() - 1);
		
		long maxTime = 1000l * 60 * 60 * 24 * 7;
		while(System.currentTimeMillis() - lastInfo.getOperateTime().getTime() > maxTime){
			logList.remove(lastInfo);
			DBManager.getGuildLogDao().remove(lastInfo);
			
			if(logList.size() <= 0) break;
			lastInfo = logList.get(logList.size() - 1);
		}
	}
	
	/**
	 * 加载数据
	 * @return
	 */
	public boolean loadFromDatabase(){
		//申请列表
		this.applyMap = DBManager.getGuildApplyDao().getGuildAll(guildInfo.getGuildId());
		this.applyList = new ArrayList<>();
		this.applyList.addAll(applyMap.values());
		this.sortApplyList();
		
		//仓库物品
		this.itemMap = DBManager.getGuildWarehouseDao().getAll(guildInfo.getGuildId());
		
		//日志
		this.logList = DBManager.getGuildLogDao().getAll(guildInfo.getGuildId());
		
		return true;
	}
	
	/**
	 * 入库
	 * @return
	 */
	public boolean saveToDatabase(){
		if(guildInfo != null){
//			this.getGuildFight();
			short option = guildInfo.getOp();
			if(option == Option.Insert){
				DBManager.getGuildInfoDao().add(guildInfo);
			}else if(option == Option.Update){
				DBManager.getGuildInfoDao().update(guildInfo);
			}
		}
		if(memberMap.size() > 0){
			for(GuildMemberInfo info: memberMap.values()){
				short option = info.getOp();
				if(option == Option.Insert){
					DBManager.getGuildMemberDao().add(info);
				}else if(option == Option.Update){
					DBManager.getGuildMemberDao().update(info);
				}
			}
		}
		
		if(applyMap.size() > 0){
			for(GuildApplyInfo info: applyMap.values()){
				short option = info.getOp();
				if(option == Option.Insert){
					DBManager.getGuildApplyDao().add(info);
				}
			}
		}
		
		if(logList != null && logList.size() > 0){
			this.clearOldLog();
			
			for(GuildLogInfo info: logList){
				short option = info.getOp();
				if(option == Option.Insert){
					DBManager.getGuildLogDao().add(info);
				}
			}
		}
		
		return true;
	}

	public GuildInfo getGuildInfo() {
		return guildInfo;
	}

	public void setGuildInfo(GuildInfo guildInfo) {
		this.guildInfo = guildInfo;
	}

	public ConcurrentHashMap<Long, GuildMemberInfo> getMemberMap() {
		return memberMap;
	}

	public ConcurrentHashMap<Long, GuildApplyInfo> getApplyMap() {
		return applyMap;
	}

	public List<GuildApplyInfo> getApplyList() {
		return applyList;
	}

	public ConcurrentHashMap<Integer, GuildWarehouseItemInfo> getItemMap() {
		return itemMap;
	}

	public List<GuildLogInfo> getLogList() {
		this.clearOldLog();
		return logList;
	}

	public long getSeizePlayerId() {
		return seizePlayerId;
	}

	public long getSeizeTargetId() {
		return seizeTargetId;
	}

	public void setSeizeTargetId(long seizeTargetId) {
		this.seizeTargetId = seizeTargetId;
	}
	
}
