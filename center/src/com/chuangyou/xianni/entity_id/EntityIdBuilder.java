package com.chuangyou.xianni.entity_id;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import com.chuangyou.xianni.sql.dao.DBManager;

public class EntityIdBuilder {
	private static AtomicLong		USER_ID;
	private static AtomicLong		PLAYER_ID;
	private static AtomicLong		ITEM_INFO_ID;
	private static AtomicInteger	CAMPAIGN_RECORD_ID;
	private static AtomicInteger    TEAM_ID;
	

	public static boolean init() {
		USER_ID = new AtomicLong(DBManager.getUserDao().getMaxId());
		PLAYER_ID = new AtomicLong(DBManager.getPlayerInfoDao().getMaxPlayerId());
		ITEM_INFO_ID = new AtomicLong(DBManager.getItemInfoDao().getMaxItemId());
		CAMPAIGN_RECORD_ID = new AtomicInteger(DBManager.getCampaignRecordInfoDao().getMaxId());
		TEAM_ID = new AtomicInteger(1);
		return true;
	}

	public static long userIdBuilder() {
		synchronized (USER_ID) {
			return USER_ID.getAndIncrement();
		}
	}

	public static long playerIdBuilder() {
		synchronized (PLAYER_ID) {
			return PLAYER_ID.getAndIncrement();
		}
	}

	public static long itemIdBuilder() {
		synchronized (ITEM_INFO_ID) {
			return ITEM_INFO_ID.getAndIncrement();
		}
	}

	public static int cmRecordIdBuilder() {
		synchronized (CAMPAIGN_RECORD_ID) {
			return CAMPAIGN_RECORD_ID.getAndIncrement();
		}
	}
	
	/**
	 * 队伍ID生成器
	 * @return
	 */
	public static int teamIdBuilder(){
		synchronized(TEAM_ID){
			return TEAM_ID.getAndIncrement();
		}
	}
}
