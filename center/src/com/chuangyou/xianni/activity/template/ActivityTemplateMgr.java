package com.chuangyou.xianni.activity.template;

import java.util.List;
import java.util.Map;

import com.chuangyou.common.protobuf.pb.activity.ActivityTempMsgProto.ActivityTempMsg;
import com.chuangyou.common.protobuf.pb.activity.GetActivityTempRespProto.GetActivityTempRespMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.activity.ActivityType;
import com.chuangyou.xianni.activity.logic.ActityUtil;
import com.chuangyou.xianni.entity.activity.ActivityConfig;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.player.PlayerState;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.pvp_1v1.PvP1v1Manager;
import com.chuangyou.xianni.sql.dao.DBManager;
import com.chuangyou.xianni.word.WorldMgr;

/**
 * 日常活动模板数据
 * 
 * @author laofan
 *
 */
public class ActivityTemplateMgr {

	private static Map<Integer, ActivityConfig> activitConfigMap;

	public static boolean init() {
		return reloadActivityTemp();
	}

	public static boolean reloadActivityTemp() {
		activitConfigMap = DBManager.getActivityDao().getActivityConfig();
		if (activitConfigMap == null) {
			return false;
		}
		List<GamePlayer> all = WorldMgr.getAllPlayers();
		if (all != null) {
			for (GamePlayer player : all) {
				sendTempsMsg(player);
			}
		}
		activityCheck();
		return true;
	}

	/** 活动开闭状态发生变更时 */
	public static void sendActivityConfigChange(ActivityConfig config) {
		ActivityTempMsg.Builder activityMsg = ActivityTempMsg.newBuilder();
		config.writeProto(activityMsg);
		PBMessage message = MessageUtil.buildMessage(Protocol.U_RESP_SINGLE_TEMP_ACTIVITY_SYNC, activityMsg);
		List<GamePlayer> all = WorldMgr.getAllPlayers();
		if (all != null) {
			for (GamePlayer player : all) {
				if (player.getPlayerState() == PlayerState.ONLINE) {
					player.sendPbMessage(message);
				}
			}
		}
	}

	public static void sendTempsMsg(GamePlayer player) {
		if (activitConfigMap == null) {
			return;
		}
		GetActivityTempRespMsg.Builder builder = GetActivityTempRespMsg.newBuilder();
		for (ActivityConfig temp : activitConfigMap.values()) {
			ActivityTempMsg.Builder amsg = ActivityTempMsg.newBuilder();
			temp.writeProto(amsg);
			builder.addInfos(amsg);
		}
		PBMessage message = MessageUtil.buildMessage(Protocol.U_RESP_TEMP_ACTIVITY_SYNC, builder);
		player.sendPbMessage(message);
	}

	public static Map<Integer, ActivityConfig> getActivitConfigMap() {
		return activitConfigMap;
	}

	/** 活动开闭配置 */
	public static void activityCheck() {
		for (ActivityConfig config : activitConfigMap.values()) {
			boolean inTime = ActityUtil.inTime(config);
			// 在时间范围内，但是活动未开启
			if (inTime && config.getStatu() == ActivityConfig.ACTIVITY_CLOSE) {
				config.setStatu(ActivityConfig.ACTIVITY_OPEN);
				openActivity(config);
				sendActivityConfigChange(config);
				continue;
			}
			if (!inTime && config.getStatu() == ActivityConfig.ACTIVITY_OPEN) {
				config.setStatu(ActivityConfig.ACTIVITY_CLOSE);
				sendActivityConfigChange(config);
				closedActivity(config);
				continue;
			}
		}
	}

	public static void openActivity(ActivityConfig active) {
		ActivityType type = ActivityType.get(active.getId());
		if (type == null) {
			Log.error("the active type is erro" + active.getId());
			return;
		}
		switch (type) {
			case ARENA:
				return;
			case PEAK_OF_THE_WAR:
				PvP1v1Manager.start();
			default:
				break;
		}
	}

	public static void closedActivity(ActivityConfig active) {
		ActivityType type = ActivityType.get(active.getId());
		if (type == null) {
			Log.error("the active type is erro" + active.getId());
			return;
		}
		switch (type) {
			case ARENA:
				return;
			case PEAK_OF_THE_WAR:
				PvP1v1Manager.over();
			default:
				break;
		}
	}

}
