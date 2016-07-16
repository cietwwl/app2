package com.chuangyou.xianni.common.timer;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.TimeUtil;

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


