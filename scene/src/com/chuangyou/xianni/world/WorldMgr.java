package com.chuangyou.xianni.world;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.chuangyou.xianni.constant.PlayerState;

/**
 * 世界管理类，管理用户以及用户连接
 */
public class WorldMgr {
	public static final Map<Long, ArmyProxy> players = new ConcurrentHashMap<Long, ArmyProxy>();

	public static void addOnline(ArmyProxy army) {
		players.put(army.getPlayerId(), army);
		army.setOnlineStatu(PlayerState.ONLINE);
	}

	// TODO 此处部队信息，应做缓存处理
	public static void unLine(long playerId) {
		if (players.containsKey(playerId)) {
			synchronized (players) {
				ArmyProxy army = players.get(playerId);
				players.remove(playerId);
				army.unload();
				army.setOnlineStatu(PlayerState.OFFLINE);
			}
		}
	}

	public static ArmyProxy getArmy(long playerId) {
		if (players.containsKey(playerId)) {
			return players.get(playerId);
		}
		return null;
	}

	public static boolean isExist(long playerId) {
		return players.containsKey(playerId);
	}
}
