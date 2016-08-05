package com.chuangyou.xianni.entity_id;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import com.chuangyou.xianni.common.template.SystemConfigTemplateMgr;
import com.chuangyou.xianni.sql.dao.DBManager;

public class EntityIdBuilder {
	private static AtomicLong		USER_ID;
	private static AtomicLong		PLAYER_ID;
	private static AtomicLong		ITEM_INFO_ID;
	private static AtomicInteger	CAMPAIGN_RECORD_ID;
	private static AtomicInteger	TEAM_ID;
	private static AtomicInteger    SPACE_MSG_ID;
	
	public static boolean init() {
		USER_ID = new AtomicLong(DBManager.getUserDao().getMaxId());
		PLAYER_ID = new AtomicLong(DBManager.getPlayerInfoDao().getMaxPlayerId());
		ITEM_INFO_ID = new AtomicLong(DBManager.getItemInfoDao().getMaxItemId());
		CAMPAIGN_RECORD_ID = new AtomicInteger(DBManager.getCampaignRecordInfoDao().getMaxId());
		TEAM_ID = new AtomicInteger(1);
		SPACE_MSG_ID = new AtomicInteger(DBManager.getSpaceDao().getMaxId());
		return true;
	}

	public static long userIdBuilder() {
		synchronized (USER_ID) {
			if (SystemConfigTemplateMgr.getIdBuiderWay() != 0) {
				return DBManager.getUserDao().getMaxId();
			}
			return USER_ID.getAndIncrement();
		}
	}

	public static long playerIdBuilder() {
		synchronized (PLAYER_ID) {
			if (SystemConfigTemplateMgr.getIdBuiderWay() != 0) {
				return DBManager.getPlayerInfoDao().getMaxPlayerId();
			}
			return PLAYER_ID.getAndIncrement();
		}
	}

	public static long itemIdBuilder() {
		synchronized (ITEM_INFO_ID) {
			if (SystemConfigTemplateMgr.getIdBuiderWay() != 0) {
				return DBManager.getItemInfoDao().getMaxItemId();
			}
			return ITEM_INFO_ID.getAndIncrement();
		}
	}

	public static int cmRecordIdBuilder() {
		synchronized (CAMPAIGN_RECORD_ID) {
			if (SystemConfigTemplateMgr.getIdBuiderWay() != 0) {
				return DBManager.getCampaignRecordInfoDao().getMaxId();
			}
			return CAMPAIGN_RECORD_ID.getAndIncrement();
		}
	}

	/**
	 *  获取
	 * @return
	 */
	public static int spaceMsgIdBuilder() {
		synchronized (SPACE_MSG_ID) {
			if (SystemConfigTemplateMgr.getIdBuiderWay() != 0) {
				return DBManager.getSpaceDao().getMaxId();
			}
			return SPACE_MSG_ID.getAndIncrement();
		}
	}
	/**
	 * 队伍ID生成器
	 * 
	 * @return
	 */
	public static int teamIdBuilder() {
		synchronized (TEAM_ID) {
			return TEAM_ID.getAndIncrement();
		}
	}
}
