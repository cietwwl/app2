package com.chuangyou.xianni.common.timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.TimeUtil;
import com.chuangyou.xianni.log.LogManager;
import com.chuangyou.xianni.npcShop.manager.NpcShopServerManager;
import com.chuangyou.xianni.task.manager.TaskManager;
import com.chuangyou.xianni.word.WorldMgr;

public class TimerTaskMgr {

	// 最小时间隔1分钟
	protected static final int	MINTIME	= 60 * 1000;

	/** 保存用户数据定时器 */
	private static Timer		saveUserDataTimer;
	/** 更新NPC商店状态数据 */
	private static Timer		updateNpcShopDataTimer;
	/** 定时器 */
	private static Timer		taskDayClearTimer;

	/** 保存用户数据定时任务 */
	private static TimerTask	saveUserData;
	/** 更新NPC商店状态数据 */
	private static TimerTask	updateNpcShopData;
	/** 日常任务清理器 */
	private static TimerTask	taskDayClearData;
	/** 保存日志 */
	private static TimerTask	savalogData;

	public static boolean init() {
		// 设置启动时间(在当前时间基础上向后推2分10秒)
		Date beginDate = TimeUtil.addSystemCurTime(Calendar.MILLISECOND, MINTIME);

		// 保存用户数据
		saveUserDataTimer = new Timer("SaveDataTimer");
		saveUserData = new SaveUserData();
		saveUserDataTimer.schedule(saveUserData, beginDate, MINTIME * 2);

		savalogData = new SavaLogData();
		saveUserDataTimer.schedule(savalogData, beginDate, MINTIME * 5);

		// 更新NPC商店状态数据
		updateNpcShopDataTimer = new Timer("updateNpcShopDataTimer");
		updateNpcShopData = new UpdateNpcShopData();
		updateNpcShopDataTimer.schedule(updateNpcShopData, beginDate, MINTIME);

		// 每天6点执行以下定时器
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd '6:00:00'");
		taskDayClearTimer = new Timer("taskDayClearTimer");
		taskDayClearData = new DayTaskData();

		try {
			Date startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sdf.format(new Date()));
			taskDayClearTimer.scheduleAtFixedRate(taskDayClearData, startTime, MINTIME * 60 * 24);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			Log.error("定时器 taskDayClearTimer,taskDayClearTimer 异常", e);
			e.printStackTrace();
		}

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
// ==============》 保存用户数据 《========================
class SaveUserData extends Task {

	public SaveUserData() {
		super("保存用户数据");
	}

	@Override
	public void exec() {
		WorldMgr.save();
	}
}

// =================>更新NPC商店货物状态<================================
class UpdateNpcShopData extends Task {
	public UpdateNpcShopData() {
		super("更新NPC商店货物状态");
	}

	@Override
	public void exec() {
		NpcShopServerManager.update();
	}
}

// ==================>重置日常任务状态<====================================
class DayTaskData extends Task {
	public DayTaskData() {
		super("更新日常任务状态");
	}

	@Override
	public void exec() {
		TaskManager.clearAllDayTask();
	}
}

// ==================>保存日志信息<====================================
class SavaLogData extends Task {
	public SavaLogData() {
		super("保存系统日志信息");
	}

	@Override
	public void exec() {
		LogManager.saveLog();
	}
}
