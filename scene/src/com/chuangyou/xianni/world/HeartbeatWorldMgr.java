package com.chuangyou.xianni.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.chuangyou.xianni.ai2.proxy.AI;
import com.chuangyou.xianni.ai2.proxy.FieldPolling;
import com.chuangyou.xianni.ai2.proxy.PlayerAI;

public class HeartbeatWorldMgr {
	private static List<AI>				AIS				= new ArrayList<>();
	private static List<FieldPolling>	fieldPollings	= new ArrayList<>();
	private static Map<Long, PlayerAI>	playerAIs		= new ConcurrentHashMap<>();

	public static void exe() {
		List<AI> temps = new ArrayList<>();
		synchronized (AIS) {
			temps.addAll(AIS);
		}
		for (AI ai : temps) {
			ai.exe();
		}
	}

	public static void exeFpolling() {
		List<FieldPolling> temp = new ArrayList<>();
		synchronized (fieldPollings) {
			temp.addAll(fieldPollings);
		}
	}

	public static void exePlayer() {
		List<PlayerAI> temp = null;
		synchronized (playerAIs) {
			temp = new ArrayList<>(playerAIs.values());
		}
		for (PlayerAI pai : temp) {
			pai.exe();
		}
	}

	public static void addFieldPolling(FieldPolling fp) {
		fieldPollings.add(fp);
	}

	public static void removeFieldPolling(FieldPolling fp) {
		fieldPollings.remove(fp);
	}

	public static void addAI(AI ai) {
		AIS.add(ai);
	}

	public static void removeAI(AI ai) {
		AIS.remove(ai);
	}

	public static void addPlayerAI(PlayerAI pai) {
		playerAIs.put(pai.getId(), pai);
	}

	public static void removePlayerAI(PlayerAI pai) {
		playerAIs.remove(pai.getId());
	}
}
