package com.chuangyou.xianni.sql.logdao;

import java.util.List;

import com.chuangyou.xianni.entity.log.MonetaryLogInfo;

public interface MonetaryLogInfoDao {
	public boolean addList(List<MonetaryLogInfo> logs);
}
