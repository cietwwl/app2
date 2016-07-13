package com.chuangyou.xianni.sql.db;

import java.sql.Connection;
import com.chuangyou.xianni.sql.db.pool.DBPoolMgr;

public class LogBaseDao extends BaseDao {
	public static final int EXEC_COUNT_PRE_BAT = 1000;

	protected Connection openConn() {
		return DBPoolMgr.getLogConn();
	}

}
