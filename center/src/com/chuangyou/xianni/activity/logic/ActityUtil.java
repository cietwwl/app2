package com.chuangyou.xianni.activity.logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.TimeUtil;
import com.chuangyou.xianni.activity.action.TimingCheckActivityAction;
import com.chuangyou.xianni.entity.activity.ActivityConfig;
import com.chuangyou.xianni.exec.ThreadManager;

public class ActityUtil {

	public static boolean inTime(ActivityConfig config) {
		// 是否是在限制时间上
		if (config.getTimeType() == ActivityConfig.TIMETYPE_EVERYDAY) {
			try {
				Date startDate = getDate(config.getStartTime());
				Date endDate = getDate(config.getEndTime());
				if (!inTime(config, startDate, endDate)) {
					return false;
				}
			} catch (Exception e) {
				Log.error("配置时间错误,config:" + config.getId(), e);
				return false;
			}
		}

		if (config.getTimeType() == ActivityConfig.TIMETYPE_EVERYWEEK) {
			int[] days = config.getWeekDays();
			String[] starts = config.getWeekStarts();
			String[] ends = config.getWeekStarts();
			if (days.length != starts.length || days.length != ends.length) {
				Log.error("配置表数据配置错误：ActivityConfig：id" + config.getId());
				return false;
			}
			int index = useLoop(days, TimeUtil.getDayOfWeekIndex());
			if (index == -1) {
				return false;
			}
			try {
				Date startDate = getDate(starts[index]);
				Date endDate = getDate(ends[index]);
				if (!inTime(config, startDate, endDate)) {
					return false;
				}
			} catch (ParseException e) {
				Log.error("配置时间错误：ActivityConfig：id" + config.getId(), e);
				return false;
			}
		}
		return true;
	}

	/**
	 * 查看数组中是否有指定元素
	 * 
	 * @param arr
	 * @param targetValue
	 * @return
	 */
	private static int useLoop(int[] arr, int targetValue) {
		int len = arr.length;
		for (int i = 0; i < len; i++) {
			if (arr[i] == targetValue) {
				return i;
			}
		}
		return -1;
	}

	public static boolean inTime(ActivityConfig config, Date startDate, Date endDate) {
		long now = System.currentTimeMillis();
		long delay = 0;
		if (startDate.getTime() - now <= 6 * 60 * 1000 && startDate.getTime() - now > 0) {
			delay = startDate.getTime() - now;
		}
		if (endDate.getTime() - now <= 6 * 60 * 1000 && endDate.getTime() - now > 0) {
			delay = endDate.getTime() - now;
		}
		if (delay > 0 && config.isDelayChecking() == false) {
			config.setDelayChecking(true);
			TimingCheckActivityAction check = new TimingCheckActivityAction(ThreadManager.actionExecutor.getDefaultQueue(), (int) delay + 1000, config);
			ThreadManager.actionExecutor.enDelayQueue(check);
		}
		return TimeUtil.isInDate(System.currentTimeMillis(), startDate, endDate);
	}

	/**
	 * 获取时间
	 * 
	 * @param timeStr
	 * @return
	 * @throws ParseException
	 */
	private static Date getDate(String timeStr) throws ParseException {
		Date now = new Date();
		SimpleDateFormat cf = new SimpleDateFormat("yyyy-MM-dd");
		String str = cf.format(now);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-ddmmss");
		Date startDate = df.parse(str + timeStr);
		return startDate;
	}
}
