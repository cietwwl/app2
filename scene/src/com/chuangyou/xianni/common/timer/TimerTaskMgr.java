package com.chuangyou.xianni.common.timer;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.TimeUtil;
import com.chuangyou.xianni.campaign.CampaignMgr;
import com.chuangyou.xianni.role.PrivateMonsterMgr;

public class TimerTaskMgr {

	// 最小时间隔1分钟
	protected static final int	MINTIME	= 60 * 1000;

	/*-------- 清理失效副本--------------- */
	private static Timer		clearCampaignTimer;		// 定时器
	private static Task			clearCampaignTask;		// 任务

	/*----------清理过期任务怪------------*/
	private static Timer		clearTaskMonsterTimer;	// 定时器
	private static Task			clearMonsterTask;; // 任务

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
		clearTaskMonsterTimer.schedule(clearMonsterTask, beginDate, MINTIME * 1);

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
		System.err.println(TimeUtil.getDateFormat(new Date()));
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
