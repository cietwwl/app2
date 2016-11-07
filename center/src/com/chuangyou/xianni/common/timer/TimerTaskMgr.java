package com.chuangyou.xianni.common.timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.TimeUtil;
import com.chuangyou.xianni.activity.template.ActivityTemplateMgr;
import com.chuangyou.xianni.chat.manager.ChatManager;
import com.chuangyou.xianni.guild.action.GuildCheckOfflineAction;
import com.chuangyou.xianni.guild.manager.GuildManager;
import com.chuangyou.xianni.log.LogManager;
import com.chuangyou.xianni.rank.logic.RankRewardLogic;
import com.chuangyou.xianni.rank.logic.UpdateRankLogic;
import com.chuangyou.xianni.task.manager.TaskManager;
import com.chuangyou.xianni.welfare.WelfareManager;
import com.chuangyou.xianni.word.WorldMgr;

public class TimerTaskMgr {

	// 最小时间隔1分钟
	protected static final int	MINTIME	= 60 * 1000;

	/** 保存用户数据定时器 */
	private static Timer		saveUserDataTimer;
	/** 定时执行器 */
	private static Timer		commonTimer;

	/** 5点重置日常任务 */
	private static Timer		taskDayClearTimer;

	/** 5点重置玩家参数 */
	private static Timer		day_reset_clearTimer;
	/**
	 * 排行榜刷新定时器
	 */
	private static Timer		rankTimer;

	/**
	 * 定时0点发排行榜奖励定时器
	 */
	private static Timer		rankRewardTimer;

	/** 保存用户数据定时任务 */
	private static TimerTask	saveUserData;

	/** 日常任务清理器 */
	private static TimerTask	taskDayClearData;
	/** 保存日志 */
	private static TimerTask	savalogData;
	/** 保存聊天离线消息 */
	private static TimerTask	saveChatOfflineData;
	/** 保存帮派数据 */
	private static TimerTask	saveGuildData;

	/** 活动以及所有定时开闭状态检查 */
	private static TimerTask	checkEverySwitch;

	/** 5点重置 */
	private static TimerTask	day_reset_Data;
	/**
	 * 排行榜每两小时更新
	 */
	private static TimerTask	rankUpdateData;

	/**
	 * 0点排行榜发奖
	 */
	private static TimerTask	rankRewardData;

	public static boolean init() {
		// 设置启动时间(在当前时间基础上向后推2分10秒)
		Date beginDate = TimeUtil.addSystemCurTime(Calendar.MILLISECOND, MINTIME);

		// 保存用户数据
		saveUserDataTimer = new Timer("SaveDataTimer");
		saveUserData = new SaveUserData();
		saveUserDataTimer.schedule(saveUserData, beginDate, MINTIME * 2);

		savalogData = new SavaLogData();
		saveUserDataTimer.schedule(savalogData, beginDate, MINTIME * 5);

		saveChatOfflineData = new SaveChatOfflineData();
		saveUserDataTimer.schedule(saveChatOfflineData, beginDate, MINTIME * 6);

		saveGuildData = new SaveGuildData();
		saveUserDataTimer.schedule(saveGuildData, beginDate, MINTIME * 7);

		// 数据检查
		commonTimer = new Timer("commonTimer");
		checkEverySwitch = new CheckEverySwitch();
		commonTimer.schedule(checkEverySwitch, beginDate, MINTIME * 2);

		// 每天5点执行以下定时器
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd '5:00:00'");
		taskDayClearTimer = new Timer("taskDayClearTimer");
		taskDayClearData = new DayTaskData();

		day_reset_clearTimer = new Timer("day_reset_clearTimer");
		day_reset_Data = new Day_5ClearData();
		try {
			Date startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sdf.format(new Date()));
			// 如果是5点后启动服务器，执行时间加一天
			if (System.currentTimeMillis() > startTime.getTime()) {
				startTime = TimeUtil.addTime(startTime, Calendar.DATE, 1);
			}
			taskDayClearTimer.scheduleAtFixedRate(taskDayClearData, startTime, MINTIME * 60 * 24);
			day_reset_clearTimer.scheduleAtFixedRate(day_reset_Data, startTime, MINTIME * 60 * 24);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			Log.error("定时器 taskDayClearTimer,taskDayClearTimer 异常", e);
			e.printStackTrace();
		}

		// 排行榜
		rankTimer = new Timer("RankTimer");
		rankUpdateData = new RankUpdateData();
		rankTimer.schedule(rankUpdateData, beginDate, MINTIME * 120);

		// 排行傍发奖
		SimpleDateFormat rankRewardSdf = new SimpleDateFormat("yyyy-MM-dd '00:01:00'");
		rankRewardTimer = new Timer("0点排行榜发奖");
		rankRewardData = new RankRewardData();
		try {
			Date startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rankRewardSdf.format(new Date()));
			// 如果是0点后启动服务器，执行时间加一天
			if (System.currentTimeMillis() > startTime.getTime()) {
				startTime = TimeUtil.addTime(startTime, Calendar.DATE, 1);
			}
			rankRewardTimer.scheduleAtFixedRate(rankRewardData, startTime, MINTIME * 60 * 24);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			Log.error("定时器 rankRewardTimer异常", e);
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

// =================>保存玩家离线消息<======================================
class SaveChatOfflineData extends Task {
	public SaveChatOfflineData() {
		// TODO Auto-generated constructor stub
		super("保存聊天离线消息");
	}

	@Override
	public void exec() {
		// TODO Auto-generated method stub
		ChatManager.saveOfflineMsg();
	}
}

class SaveGuildData extends Task {
	public SaveGuildData() {
		// TODO Auto-generated constructor stub
		super("保存帮派信息");
	}

	@Override
	public void exec() {
		// TODO Auto-generated method stub
		GuildManager.getIns().saveToDatabase();
	}
}

// =================>每天5点重置玩家数据<======================================
class Day_5ClearData extends Task {
	public Day_5ClearData() {
		super("每天5点重置数据");
	}

	@Override
	public void exec() {
		WorldMgr.resetTimeInfo();
		// 检查需要解散的玩家帮派，检查需要退出帮派的系统帮派成员
		GuildCheckOfflineAction guildAction = new GuildCheckOfflineAction();
		guildAction.getActionQueue().enqueue(guildAction);
	}
}

// ==================>定时检测所有开闭状态信息<==========================
class CheckEverySwitch extends Task {
	public CheckEverySwitch() {
		super("检查所有开关类型重置状态");
	}

	@Override
	public void exec() {
		ActivityTemplateMgr.activityCheck();
	}

}

// ==================>每2小时候，刷新排行榜数据<=====================================================
class RankUpdateData extends Task {

	public RankUpdateData() {
		super("每隔两个小时。刷新一下排行榜数据");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void exec() {
		// TODO Auto-generated method stub
		new UpdateRankLogic().updateRank();
	}
}

// ==================>每天0点，发排行榜奖励<=====================================================
class RankRewardData extends Task {

	public RankRewardData() {
		super("每天0点，发排行榜奖励");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void exec() {
		new UpdateRankLogic().updateRank();
		new RankRewardLogic().reward();
		try {
			WelfareManager.newDay();
		} catch (Exception e) {
			System.err.println("福利登录天数增加逻辑执行错误！");
			e.printStackTrace();
		}
	}
}
