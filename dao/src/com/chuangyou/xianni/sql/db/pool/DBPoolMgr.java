package com.chuangyou.xianni.sql.db.pool;

import java.sql.Connection;

import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.NetConfigXml;

/**
 * 数据库连接池管理类
 * 
 */
public class DBPoolMgr {
	public static String		STRATEGY_POOL_NAME	= "mysql";
	public static String		LOG_POOL_NAME		= "log";
	public static final String	dbUrl				= "jdbc:mysql://%s:%s/%s?characterEncoding=utf-8&autoReconnect=true";
	private static IDBPool		strategyPool;
	private static IDBPool		logPool;

	private static NetConfigXml	mainDBXml;
	private static NetConfigXml	logDBXml;
	private static int			mainDBMaxConn		= 30;
	private static int			mainDBFllow			= 3;
	private static int			logDBMaxConn		= 30;
	private static int			logDBFllow			= 3;

	public static boolean init(NetConfigXml dbXml, NetConfigXml logXml, int dbMaxconn, int dbFallow, int logMaxConn,
			int logFallow) {
		mainDBXml = dbXml;
		logDBXml = logXml;
		mainDBMaxConn = dbMaxconn;
		mainDBFllow = dbFallow;
		logDBMaxConn = logMaxConn;
		logDBFllow = logFallow;
		return initPool(true, true);
	}

	private static String getMainDBUrl() {
		if (mainDBXml != null) {
			return String.format(dbUrl, mainDBXml.getAddress(), mainDBXml.getPort(), mainDBXml.getName());
		}
		return "";
	}

	private static String getLogDBUrl() {
		if (logDBXml != null) {
			return String.format(dbUrl, logDBXml.getAddress(), logDBXml.getPort(), logDBXml.getName());
		}
		return "";
	}

	public static boolean initPool(boolean isInitMainDB, boolean isInitLogDB) {
		if (isInitMainDB) {
			strategyPool = createPools(STRATEGY_POOL_NAME, getMainDBUrl(), mainDBXml.getUser(),
					mainDBXml.getPassword());
			if (strategyPool == null) {
				return false;
			}
		}
		if (isInitLogDB) {
			logPool = createPools(LOG_POOL_NAME, getLogDBUrl(), logDBXml.getUser(), logDBXml.getPassword());
			if (logPool == null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 检查连接池状态是否挂掉，如挂了重新初始化
	 * 
	 * @param checkStrategy
	 *            是否检查游戏库
	 * @param checkLog
	 *            是否检查日志库
	 */
	public static void checkConnectionPool(boolean checkStrategy, boolean checkLog) {
		boolean initStrategy = false;
		boolean initLog = false;
		if (checkStrategy) {
			if (strategyPool == null || strategyPool.getCurConns() <= 0) {
				initStrategy = true;
				Log.error("检查strategyPool发现异常,strategyPool:" + getStrategyPoolState());
			}
		}
		if (checkLog) {
			if (logPool == null || logPool.getCurConns() <= 0) {
				initLog = true;
				Log.error("检查logPool发现异常,logPool:" + getLogPoolState());
			}
		}

		if (initStrategy || initLog) {
			boolean result = initPool(initStrategy, initLog);
			StringBuilder sb = new StringBuilder("重新初始化连接池");
			sb.append(result ? "成功" : "失败").append(",strategyPoolState:").append(getStrategyPoolState())
					.append(",logPoolState：").append(getLogPoolState());
			Log.error(sb);
		}
	}

	/**
	 * 根据指定属性创建连接池实例.
	 * 
	 * @param props
	 *            连接池属性
	 */
	private static IDBPool createPools(String poolName, String url, String user, String password) {
		int maxconn = 30;
		int maxfllow = 3;
		if (poolName.equals(STRATEGY_POOL_NAME) && mainDBMaxConn > 0 && mainDBFllow > 0) {
			maxconn = mainDBMaxConn;
			maxfllow = mainDBFllow;
		} else if (poolName.equals(LOG_POOL_NAME) && logDBMaxConn > 0 && logDBFllow > 0) {
			maxconn = logDBMaxConn;
			maxfllow = logDBFllow;
		}
		try {
			IDBPool pool = new C3p0Pool(url, user, password, maxconn, maxfllow);
			String msg = String.format("加载配置连接池:%s,URL:%s完成！", poolName, url);
			Log.info(msg);
			return pool;
		} catch (Exception e) {
			Log.error("创建db连接池失败 poolName : " + poolName, e);
			return null;
		}
	}

	public static void closePools() {
		if (strategyPool != null) {
			strategyPool.shutdown();
			strategyPool = null;
		}

		if (logPool != null) {
			logPool.shutdown();
			logPool = null;
		}
		Log.info("数据库连接池关闭成功");
	}

	public static void closeStrategyPools() {
		if (strategyPool != null) {
			strategyPool.shutdown();
			strategyPool = null;
		}
		Log.info("Strategy数据库连接池关闭成功");
	}

	public static Connection getStrategyConn() {
		Connection conn = strategyPool.getConnection();
		return conn;
	}

	public static Connection getLogConn() {
		Connection conn = logPool.getConnection();
		return conn;
	}

	public static String getStrategyPoolState() {
		if (strategyPool != null) {
			return strategyPool.getState();
		}
		return null;
	}

	public static String getLogPoolState() {
		if (logPool != null) {
			return logPool.getState();
		}
		return null;
	}
}
