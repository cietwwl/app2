package com.chuangyou.xianni.job;

import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import com.chuangyou.common.util.TimeUtil;
import com.chuangyou.xianni.entity.job.JobInfo;
import com.chuangyou.xianni.sql.dao.DBManager;

public class JobMgr {
	public static Hashtable<String, JobInfo>	jobMaps;
	public static int							minuteUnit	= 1;
	public static int							hourUnit	= 2;
	public static int							dayUnit		= 3;

	// 测试
	public static String						test		= "test";

	public static boolean init() {
		return reload();
	}

	public static boolean reload() {
		List<JobInfo> temps = DBManager.getJobInfoDao().getJobInfos();
		jobMaps = new Hashtable<String, JobInfo>();
		if ((temps != null) && (temps.size() > 0)) {
			for (JobInfo info : temps) {
				jobMaps.put(info.getJobName(), info);
			}
		}
		addJobInfo(test);

		return jobMaps.size() > 0;
	}

	public static JobInfo getJobInfo(String name) {
		return jobMaps.get(name);
	}

	public static int getLeftDay(String name) {
		JobInfo info = jobMaps.get(name);
		if (info == null) {
			return 0;
		}

		int time = 60;// 60秒为单位
		if (info.getIntervalUnit() == minuteUnit)
			time = time * info.getIntervalData();
		if (info.getIntervalUnit() == hourUnit)
			time = time * 60 * info.getIntervalData();
		if (info.getIntervalUnit() == dayUnit)
			time = time * 60 * 24 * info.getIntervalData();

		long lastTime = TimeUtil.getSysCurSeconds() - TimeUtil.getDateToSeconds(info.getUpdateTime());

		long left = time - lastTime;
		left = left <= 0 ? 1 : left;
		return (int) Math.ceil(((left / 3600.00) / 24.00));
	}

	public static boolean isRun(String name) {
		JobInfo info = jobMaps.get(name);
		if (info == null)
			return false;
		int time = 60;// 60秒为单位
		if (info.getIntervalUnit() == minuteUnit) {
			time = time * info.getIntervalData();
		}
		if (info.getIntervalUnit() == hourUnit) {
			time = time * 60 * info.getIntervalData();
		}
		if (info.getIntervalUnit() == dayUnit) {
			time = time * 60 * 24 * info.getIntervalData();
		}
		if (TimeUtil.getCurrentHour() != info.getIntervalBegin()) {
			return false;
		}
		if (TimeUtil.getSysCurSeconds() - TimeUtil.getDateToSeconds(info.getUpdateTime()) > time) {
			return true;
		}
		return false;
	}

	public static boolean update(String name) {
		JobInfo info = jobMaps.get(name);
		if (info != null) {
			info.setUpdateTime(getInitDate(info.getIntervalBegin()));
			return DBManager.getJobInfoDao().updateJobInfo(info);
		} else {
			return false;
		}
	}

	public static boolean addJobInfo(String name) {
		JobInfo info = getJobInfo(name);
		if (info != null)
			return true;

		if (name.equals(test)) {
			info = new JobInfo(test, dayUnit, 7, 3, TimeUtil.getSysteCurTime(), "测试(7天执行一次，3点执行)");
		}

		if (info != null) {
			if (DBManager.getJobInfoDao().addJobInfo(info)) {
				jobMaps.put(info.getJobName(), info);
				return true;
			}
		}
		return false;
	}

	private static Date getInitDate(int startHour) {
		return getInitDate(startHour, 10);
	}

	private static Date getInitDate(int startHour, int starMinute) {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), startHour, starMinute, 0);
		Date time = cal.getTime();
		return time;
	}
}
