package com.chuangyou.xianni.sql.dao;

import java.util.Map;

import com.chuangyou.xianni.entity.mount.MountEquipInfo;

public interface MountEquipDao {

	public Map<Integer, MountEquipInfo> getAll(long playerId);
	
	public boolean add(MountEquipInfo info);
	
	public boolean update(MountEquipInfo info);
}
