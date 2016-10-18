package com.chuangyou.xianni.activity;

import java.util.Iterator;
import java.util.Map;
import com.chuangyou.common.protobuf.pb.activity.GetActivityInfosRespProto.GetActivityInfosRespMsg;
import com.chuangyou.xianni.activity.template.ActivityTemplateMgr;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.activity.ActivityConfig;
import com.chuangyou.xianni.entity.activity.ActivityInfo;
import com.chuangyou.xianni.event.AbstractEvent;
import com.chuangyou.xianni.interfaces.IInventory;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.sql.dao.DBManager;

public class ActivityInventory extends AbstractEvent implements IInventory {

	private GamePlayer					player;
	/**
	 * 活动字典
	 */
	private Map<Integer, ActivityInfo>	activityMap;

	public ActivityInventory(GamePlayer player) {
		this.player = player;
	}

	@Override
	public boolean loadFromDataBase() {
		activityMap = DBManager.getActivityDao().getInfos(player.getPlayerId());
		return true;
	}

	@Override
	public boolean unloadData() {
		player = null;
		if (activityMap != null) {
			activityMap.clear();
			activityMap = null;
		}
		return true;
	}

	@Override
	public boolean saveToDatabase() {
		if (activityMap != null) {
			Iterator<ActivityInfo> it = activityMap.values().iterator();
			while (it.hasNext()) {
				ActivityInfo info = it.next();
				if (info.getOp() == Option.Insert) {
					DBManager.getActivityDao().addInfo(info);
				} else if (info.getOp() == Option.Update) {
					DBManager.getActivityDao().updateInfo(info);
				}
			}
		}
		return true;
	}

	public void sendAllActivityInfos() {
		GetActivityInfosRespMsg.Builder infosRespMsg = GetActivityInfosRespMsg.newBuilder();
		for (ActivityType type : ActivityType.values()) {
			ActivityInfo ainfo = getActivityInfo(type);
			if (ainfo != null) {
				infosRespMsg.addInfos(ainfo.getMsg());
			}
		}
		PBMessage message = MessageUtil.buildMessage(Protocol.U_RESP_ACTIVITY_INFOS, infosRespMsg);
		player.sendPbMessage(message);
	}

	/**
	 * 获取个人的日常活动信息
	 * 
	 * @param activityId
	 * @return
	 */
	public ActivityInfo getActivityInfo(ActivityType type) {
		ActivityInfo info = activityMap.get(type.getValue());
		if (info == null) {
			ActivityConfig config = ActivityTemplateMgr.getActivitConfigMap().get(type.getValue());
			if (config != null) {
				info = new ActivityInfo();
				info.setId(type.getValue());
				info.setPlayerId(player.getPlayerId());
				info.setRemainTime(config.getNumLimit());
				info.setParam(0);
				info.setOp(Option.Insert);
				activityMap.put(info.getId(), info);
			}
		}
		return info;
	}

	/**
	 * 修改活动数据
	 * 
	 * @param activityId
	 * @param state
	 * @param remainTime
	 */
	public void changeActivityInfo(ActivityType type, int remainTime) {
		ActivityInfo info = getActivityInfo(type);
		info.setRemainTime(remainTime);
		info.setOp(Option.Update);
		syncActivityInfo(info);
	}

	/** 是否还有剩余次数 */
	public boolean hasLeftCount(ActivityType type) {
		ActivityInfo info = getActivityInfo(type);
		return info.getRemainTime() > 0;
	}

	///////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 同步最新的活动详情给客户端
	 * 
	 * @param info
	 */
	private void syncActivityInfo(ActivityInfo info) {
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_ACTIVITY_SYNC, info.getMsg());
		player.sendPbMessage(pkg);
	}
}
