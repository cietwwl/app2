package com.chuangyou.xianni.sql.dao;

import java.util.Map;

import com.chuangyou.xianni.entity.drop.DropInfo;
import com.chuangyou.xianni.entity.drop.DropItemInfo;

public interface DropConfigDao {

	public Map<Integer, DropInfo> getDropInfo();
	
	public Map<Integer, Map<Integer, DropItemInfo>> getDropItem();
}
