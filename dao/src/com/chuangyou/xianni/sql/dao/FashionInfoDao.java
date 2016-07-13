package com.chuangyou.xianni.sql.dao;

import java.util.Map;

import com.chuangyou.xianni.entity.fashion.FashionInfo;

public interface FashionInfoDao {

	public Map<Integer, FashionInfo> getAll(long playerId);
	
	public boolean add(FashionInfo info);
	
	public boolean update(FashionInfo info);
}
