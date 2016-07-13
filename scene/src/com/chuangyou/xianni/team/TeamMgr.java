package com.chuangyou.xianni.team;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TeamMgr {
	/**
	 * 所有队伍池
	 */
	private static ConcurrentHashMap<Integer, Team> allTeams = new ConcurrentHashMap<Integer, Team>(64);

	/**
	 * 队员信息<队员ID，队伍ID>
	 */
	private static ConcurrentMap<Long, Integer> playerTeamMap = new ConcurrentHashMap<Long, Integer>(256);

	/**
	 * 获取队伍
	 * 
	 * @param playerId
	 * @return 没有队伍返回null
	 */
	public static Team getTeam(long playerId) {
		if (playerTeamMap.containsKey(playerId)) {
			return allTeams.get(playerTeamMap.get(playerId));
		}
		return null;
	}

	/**
	 * 添加队伍
	 * 
	 * @param t
	 */
	public static void addTeam(Team t) {
		allTeams.put(t.getTeamid(), t);
	}

	/**
	 * 删除队伍
	 * 
	 * @param t
	 */
	public static void removeTeam(int teamId) {
		Team t = allTeams.get(teamId);
		if (t != null) {
			t.clear();
			allTeams.remove(teamId);
		}
	}

	public static ConcurrentMap<Long, Integer> getPlayerTeamMap() {
		return playerTeamMap;
	}

	public static ConcurrentHashMap<Integer, Team> getAllTeams() {
		return allTeams;
	}


	
	

}
