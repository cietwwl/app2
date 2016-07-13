package com.chuangyou.xianni.sql.db.pool;


import java.sql.Connection;

/**
 * 数据连接池
 */
public interface IDBPool {

	public Connection getConnection();

	public void shutdown();

	public String getState();

	public int getCurConns();
	
	public String getPoolName();

}
