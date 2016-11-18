package com.chuangyou.xianni.common.timer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.TimeUtil;
import com.chuangyou.xianni.battle.snare.SnareCreateFilter;
import com.chuangyou.xianni.campaign.CampaignMgr;
import com.chuangyou.xianni.role.PrivateMonsterMgr;
import com.chuangyou.xianni.warfield.spawn.TimeControlerNodeMgr;
import com.chuangyou.xianni.world.HeartbeatWorldMgr;

public class TimerTaskMgr {

	// 最小时间隔1分钟
	protected static final int	MINTIME	= 60 * 1000;

	/*-------- 清理失效副本--------------- */
	private static Timer		clearCampaignTimer;		// 定时器
	private static Task			clearCampaignTask;		// 任务

	/*----------清理过期任务怪------------*/
	private static Timer		clearTaskMonsterTimer;	// 定时器
	private static Task			clearMonsterTask;; // 任务

	/* 到点数据清理 */
	private static Timer		day_reset_clearTimer;	// 定时器
	private static Task			day_reset_Data;			// 任务
	private static Task			timeControlerNode;		// 控制时间刷新副本

	public static boolean init() {
		// 设置启动时间(在当前时间基础上向后推2分10秒)
		Date beginDate = TimeUtil.addSystemCurTime(Calendar.MILLISECOND, MINTIME);

		// 清理过期副本数据
		clearCampaignTimer = new Timer("clearCampaignTimer");
		clearCampaignTask = new ClearCampaignData();
		clearCampaignTimer.schedule(clearCampaignTask, beginDate, MINTIME * 5);

		// 清理过期任务怪
		clearTaskMonsterTimer = new Timer("clearTaskMonsterTimer");
		clearMonsterTask = new ClearPrivateMonster();
		clearTaskMonsterTimer.schedule(clearMonsterTask, beginDate, MINTIME * 5);

		// 凌晨5点定时清理
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeStr = sdf.format(new Date());
		Date startTime = TimeUtil.parseDate(timeStr.substring(0, timeStr.length() - 4) + "0:01");

		day_reset_clearTimer = new Timer("scence_day_reset_clearTimer");
		day_reset_Data = new Day_5ClearData();
		timeControlerNode = new TimeControlerNode();

		day_reset_clearTimer.scheduleAtFixedRate(day_reset_Data, startTime, MINTIME * 60);
		day_reset_clearTimer.scheduleAtFixedRate(timeControlerNode, startTime, MINTIME * 5);

		day_reset_clearTimer.scheduleAtFixedRate(new AITask(), startTime, 1000);
		return true;
	}
}

/**
 * 定时任务抽象基类
 * 
 * @author laofan
 *
 */
abstract class Task extends TimerTask {
	private String name;

	public Task(String name) {
		this.name = "定时器任务-" + name;
	}

	@Override
	public void run() {
		try {
			exec();
		} catch (Exception e) {
			Log.error(name + "错误", e);
		}
	}

	public abstract void exec();

}

/////////////////////////////////////////////////////
// ==============》清理失效副本《========================
class ClearCampaignData extends Task {

	public ClearCampaignData() {
		super("保存用户数据");
	}

	@Override
	public void exec() {
		// Log.error(TimeUtil.getDateFormat(new Date()));
		CampaignMgr.clearInvalid();

	}
}

// ==============》清理私有怪物《========================
class ClearPrivateMonster extends Task {

	public ClearPrivateMonster() {
		super("保存用户数据");
	}

	@Override
	public void exec() {
		PrivateMonsterMgr.clearExpired();
	}
}

// =================>每天5点重置玩家数据<======================================
class Day_5ClearData extends Task {
	public Day_5ClearData() {
		super("每天5点重置数据");
	}

	@Override
	public void exec() {
		SnareCreateFilter.clearExiped();
	}
}

class TimeControlerNode extends Task {

	public TimeControlerNode() {
		super("控制根据时间来刷新的节点状态变更");
	}

	@Override
	public void exec() {
		TimeControlerNodeMgr.check();
	}

}

class AITask extends Task {

	public AITask() {
		super("控制根据时间来刷新的节点状态变更");
	}

	@Override
	public void exec() {
		HeartbeatWorldMgr.exe();
		HeartbeatWorldMgr.exePlayer();
		HeartbeatWorldMgr.exeFpolling();

	}

}
