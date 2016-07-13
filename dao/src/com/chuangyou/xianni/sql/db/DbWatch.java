package com.chuangyou.xianni.sql.db;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.sql.db.pool.DBPoolMgr;

public class DbWatch {

	private long	first	= 0;
	private long	second	= 0;

	public DbWatch() {
		first = System.currentTimeMillis();
	}

	public void getPool() {
		second = System.currentTimeMillis();
	}

	public void commit(String procName) {
		long end = System.currentTimeMillis();

		long spendTime = end - first;
		if (spendTime > 1000) {
			Log.error(String.format("执行语句%s花耗时间总时间 超过:%sms,获取连接：%sms,执行sql:%sms", procName, spendTime, second - first,
					end - second));
			Log.error("StrategyPool:" + DBPoolMgr.getStrategyPoolState() + "  LogPool:" + DBPoolMgr.getLogPoolState());
		}
	}
}
