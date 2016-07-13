package com.chuangyou.xianni.sql.logdao;

import java.util.List;

import com.chuangyou.xianni.entity.log.ItemLogInfo;

public interface ItemLogDao {
	public boolean addList(List<ItemLogInfo> logs);
}
