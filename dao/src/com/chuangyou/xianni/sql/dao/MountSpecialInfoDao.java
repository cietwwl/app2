package com.chuangyou.xianni.sql.dao;

import java.util.Map;

import com.chuangyou.xianni.entity.mount.MountSpecialGet;

public interface MountSpecialInfoDao {

	public Map<Integer, MountSpecialGet> getAll(long playerId);
	
	public boolean add(MountSpecialGet info);
	
	public boolean delete(long playerId, int mountId);
}
