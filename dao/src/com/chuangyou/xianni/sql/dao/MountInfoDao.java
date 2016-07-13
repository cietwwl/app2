package com.chuangyou.xianni.sql.dao;

import com.chuangyou.xianni.entity.mount.MountInfo;

public interface MountInfoDao {

	public MountInfo get(long playerId);
	
	public boolean add(MountInfo info);
	
	public boolean update(MountInfo info);
}
