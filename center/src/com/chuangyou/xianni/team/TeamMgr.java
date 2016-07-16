package com.chuangyou.xianni.team;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.chuangyou.common.protobuf.pb.team.TeamDestroyRespProto.TeamDestroyRespMsg;
import com.chuangyou.xianni.entity_id.EntityIdBuilder;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.exec.AbstractActionQueue;
import com.chuangyou.xianni.exec.ActionQueue;
import com.chuangyou.xianni.exec.ThreadManager;
import com.chuangyou.xianni.netty.GatewayLinkedSet;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.team.listenerHandler.AddInTeamHandler;
import com.chuangyou.xianni.team.listenerHandler.AllOfflineHandler;
import com.chuangyou.xianni.team.listenerHandler.ChangeOnlineTeamHandler;
import com.chuangyou.xianni.team.listenerHandler.EmptyTeamHandler;
import com.chuangyou.xianni.team.listenerHandler.FullTeamHandler;
import com.chuangyou.xianni.team.listenerHandler.LeaderChangeTeamHandler;
import com.chuangyou.xianni.team.listenerHandler.LeaveInTeamHandler;
import com.chuangyou.xianni.team.pool.MemberMatchPool;
import com.chuangyou.xianni.team.pool.TeamMatchPool;
import com.chuangyou.xianni.team.struct.Team;
import com.chuangyou.xianni.team.struct.TeamMember;

/**
 * 组队管理
 * @author laofan
 *
 */
public class TeamMgr{
		
	/**
	 * 队伍处理队列
	 */
	private static AbstractActionQueue actionQueue = new AbstractActionQueue(ThreadManager.actionExecutor);
	/**
	 * 所有队伍池
	 */
	private static ConcurrentHashMap<Integer, Team> allTeams = new ConcurrentHashMap<Integer, Team>(64);
	
	/**
	 * 队员信息
	 */
	private static ConcurrentHashMap<Long, TeamMember> playerTeamMap = new ConcurrentHashMap<Long, TeamMember>(256);


	/**
	 * 队伍目标池<目标，池>
	 */
	private static Map<Integer, TeamMatchPool> targetPools = new ConcurrentHashMap<>(); 
	
	/**
	 * 个人目标匹配查找字典<目标，池>
	 */
	private static Map<Integer, MemberMatchPool> memeberTargetMatch = new ConcurrentHashMap<>();
	
	/**
	 * 队伍目标匹配查找字典<目标，池>
	 */
	private static Map<Integer, TeamMatchPool> teamTargetMatch     = new ConcurrentHashMap<>();
	
	/** 队伍上限  */
	public static final int TEAM_MAX = 4;
	/** 最大申请列表  */
	public static final int TEAM_APPLY_LIST_MAX = 8;
	/** 无目标虚拟目标ID */
	public static final int TEAM_NO_TARGET = 100000;
	
	
	private static AddInTeamHandler addInTeamHandler = new AddInTeamHandler();
	private static EmptyTeamHandler emptyTeamHandler = new EmptyTeamHandler();
	private static AllOfflineHandler allOfflineHandler = new AllOfflineHandler();
	private static LeaveInTeamHandler leaveInTeamHandler = new LeaveInTeamHandler();
	private static FullTeamHandler fullTeamHandler = new FullTeamHandler();
	private static LeaderChangeTeamHandler leaderChangeTeamHandler = new LeaderChangeTeamHandler();
	private static ChangeOnlineTeamHandler changeOnlineTeamHandler = new ChangeOnlineTeamHandler();
	
	
	/**
	 * 建队
	 */
	public static Team createTeam(GamePlayer player,int targetId){
		Team t = new Team(EntityIdBuilder.teamIdBuilder(),player.getPlayerId(),targetId);
		playerTeamMap.put(player.getPlayerId(), t.getLeader());
		removePersonTarget(player.getBasePlayer().getTeamTarget(), player.getPlayerId());
		t.addListener(addInTeamHandler, EventNameType.TEAM_ADD_MEMBER);
		t.addListener(emptyTeamHandler, EventNameType.TEAM_IS_EMPTY);
		t.addListener(allOfflineHandler, EventNameType.TEAM_IS_ALLOFFLINE);
		t.addListener(leaveInTeamHandler, EventNameType.TEAM_REMOVE_MEMBER);
		t.addListener(fullTeamHandler, EventNameType.TEAM_IS_FULL);
		t.addListener(leaderChangeTeamHandler, EventNameType.TEAM_LEADER_CHANGE);
		t.addListener(changeOnlineTeamHandler, EventNameType.TEAM_CHNAGE_ONLINE);
		
		allTeams.put(t.getId(), t);
		return t;	
	}
	
	
	/**
	 * 消毁队伍
	 */
	public static void destroyTeam(Team t){
		
		t.clearListener();  //取消所有监听
		for (TeamMember member : t.getMembers()) {
			playerTeamMap.remove(member.getPlayerId());
		}
		removeTeamTargetMatch(t.getTargetId(),t.getId());
		removeTeamTargetPool(t.getTargetId(),t.getId());
		allTeams.remove(t.getId());
		
		//todo同步scnene服务器
		TeamDestroyRespMsg.Builder resp = TeamDestroyRespMsg.newBuilder();
		resp.setTeamId(t.getId());
		PBMessage pkg = MessageUtil.buildMessage(Protocol.S_TEAM_DESTROY,resp);
		GatewayLinkedSet.send2Server(pkg);
	}

	/**
	 * 添加或者重设匹配池中的队伍目标 
	 * @param targetId
	 * @param teamId
	 */
	public static void addTeamTargetMatch(int targetId,int teamId){
		if(!teamTargetMatch.containsKey(targetId)){
			TeamMatchPool pools = new TeamMatchPool();
			teamTargetMatch.put(targetId, pools);
		}
		teamTargetMatch.get(targetId).addMember(teamId);
	}
	
	/**
	 * 去掉匹配池中队伍的ID
	 * @param targetId
	 * @param teamId
	 */
	public static void removeTeamTargetMatch(int targetId,int teamId){
		if(teamTargetMatch.containsKey(targetId)){
			teamTargetMatch.get(targetId).removeMember(teamId);
		}
	}
	
	/**
	 * 添加或者重设匹配池中的个人目标
	 * @param targetId
	 * @param playerId
	 */
	public static void addPersonTarget(int targetId,long playerId){
		if(!memeberTargetMatch.containsKey(targetId)){
			MemberMatchPool pools = new MemberMatchPool();
			memeberTargetMatch.put(targetId, pools);
		}
		memeberTargetMatch.get(targetId).addMember(playerId);
	}
	
	
	/**
	 * 去掉匹配池中个人的ID
	 * @param targetId
	 * @param playerId
	 */
	public static void removePersonTarget(int targetId,long playerId){
		if(memeberTargetMatch.containsKey(targetId)){
			memeberTargetMatch.get(targetId).removeMember(playerId);
		}
	}
	
	
	/**
	 * 添加/重设 队伍进按队伍目标划分的池
	 * @param targetId
	 * @param teamId
	 */
	public static void addTeamTargetPool(int targetId,int teamId){
		if(!targetPools.containsKey(targetId)){
			TeamMatchPool pools = new TeamMatchPool();
			targetPools.put(targetId, pools);
		}
		targetPools.get(targetId).addMember(teamId);
	}
	
	/**
	 * 删除队伍进按队伍目标划分的池
	 * @param targetId
	 * @param teamId
	 */
	public static void removeTeamTargetPool(int targetId,int teamId){
		if(targetPools.containsKey(targetId)){
			targetPools.get(targetId).removeMember(teamId);
		}
	}
	

	
	public static void main(String[] args) {
		TeamMatchPool pool = new TeamMatchPool();
		pool.addMember(100);
		pool.addMember(1);
		pool.addMember(2);
		pool.addMember(3);
		pool.addMember(4);
		pool.addMember(101);
		pool.addMember(102);
		pool.addMember(103);
		pool.addMember(104);
		
		System.out.println("start:"+pool.getPools());
		pool.removeMember(100);
		pool.removeMember(100);
		System.out.println("end:"+pool.getPools());
		
	}
	
	//==============================================================================
	
	public static ConcurrentMap<Long, TeamMember> getPlayerTeamMap() {
		return playerTeamMap;
	}


	public static ConcurrentHashMap<Integer, Team> getAllTeams() {
		return allTeams;
	}


	public static Map<Integer, TeamMatchPool> getTeamTargetMatch() {
		return teamTargetMatch;
	}


	public static Map<Integer, MemberMatchPool> getMemeberTargetMatch() {
		return memeberTargetMatch;
	}


	public static Map<Integer, TeamMatchPool> getTargetPools() {
		return targetPools;
	}


	public static ActionQueue getActionQueue() {
		// TODO Auto-generated method stub
		return actionQueue;
	}


}
