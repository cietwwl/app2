package com.chuangyou.xianni.welfare;

import javax.management.timer.Timer;

import com.chuangyou.xianni.common.template.SystemConfigTemplateMgr;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.sevenDaysGiftPacks.WelfareConditionRecordInfo;
import com.chuangyou.xianni.event.AbstractEvent;
import com.chuangyou.xianni.interfaces.IInventory;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.sql.dao.DBManager;

public class WelfareConditionRecordInventory extends AbstractEvent implements IInventory {

	private GamePlayer					player;

	/** 福利条件信息 */
	private WelfareConditionRecordInfo	info;

	/** 新手开始记录在线时间的时间点 */
	private long						onlineStartTime	= -1;

	public WelfareConditionRecordInventory(GamePlayer player) {
		this.player = player;
	}

	@Override
	public boolean loadFromDataBase() {
		info = DBManager.getWelfareConditionRecordDao().getInfoByPlayerId(player.getPlayerId());
		if (info == null) {// 无记录信息
			// 需要初始化，并及时入库
			info = init();
			return saveToDatabase();
		}
		return true;
	}

	// 初始化福利条件信息
	private WelfareConditionRecordInfo init() {
		info = new WelfareConditionRecordInfo();
		info.setPlayerId(player.getPlayerId());
		info.setDays(0);
		info.setOnLineTime(0);
		info.setTime(0);
		info.setOp(Option.Insert);
		return info;
	}

	// 当玩家退出游戏时需要释放不共享的数据的内存空间
	@Override
	public boolean unloadData() {
		this.info = null;
		return true;
	}

	@Override
	public boolean saveToDatabase() {
		// 同步数据库时需要同步一下在线时间
		if (onlineStartTime != -1) {
			long now = System.currentTimeMillis();
			info.setOnLineTime((int) (info.getOnLineTime() + (now - onlineStartTime) / 1000));
			onlineStartTime = now;
		}
		int op = info.getOp();
		if (op == Option.Update) {
			info.setOp(Option.None);
			return DBManager.getWelfareConditionRecordDao().update(info);
		} else if (op == Option.Insert) {
			info.setOp(Option.None);
			return DBManager.getWelfareConditionRecordDao().add(info);
		}
		return true;
	}

	public WelfareConditionRecordInfo getInfo() {
		return info;
	}

	public void setInfo(WelfareConditionRecordInfo info) {
		this.info = info;
	}

	private boolean checkIsNewDay() {
		int now = (int) ((System.currentTimeMillis() + 8 * Timer.ONE_HOUR) / Timer.ONE_DAY);
		if (now > info.getTime()) {
			return true;
		}
		return false;
	}

	/**
	 * 累计登陆天数加1
	 */
	public void addDay() {
		info.setDays(info.getDays() + 1);
		info.setTime((int) ((System.currentTimeMillis() + Timer.ONE_HOUR * 8) / Timer.ONE_DAY));
		player.getWelfareInventory().reset();
		player.getWelfareInventory().getWelfareConditionHandleMap().get(WelfareConditionHandleFactory.LOGIN_DAYS).listen();
		player.getWelfareInventory().getWelfareConditionHandleMap().get(WelfareConditionHandleFactory.SIGN).listen();
		player.getWelfareInventory().getWelfareConditionHandleMap().get(WelfareConditionHandleFactory.MIN_TIME).listen();
		player.getWelfareInventory().getWelfareConditionHandleMap().get(WelfareConditionHandleFactory.MAX_TIME).listen();
	}

	public long getOnlineStartTime() {
		return onlineStartTime;
	}

	public void setOnlineStartTime(long onlineStartTime) {
		this.onlineStartTime = onlineStartTime;
		info.setOp(Option.Update);
	}

	/**
	 * 玩家登录时需要处理福利条件相关逻辑
	 */
	public void login() {
		// 初始化开始记录在线时间的时间点
		if (player.getLevel() >= SystemConfigTemplateMgr.getSystemTemps().get("welfare.newPlayerOpenLevel").getValue())
			onlineStartTime = System.currentTimeMillis();
		// 检查是否是新的一天
		if (checkIsNewDay()) {
			addDay();
		}
	}
}
