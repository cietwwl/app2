package com.chuangyou.xianni.sql.dao;

import java.util.Map;

import com.chuangyou.xianni.entity.equip.EquipBarInfo;

public interface EquipBarInfoDao {

	public Map<Short, EquipBarInfo> getAll(long playerId);
	
	public boolean add(EquipBarInfo info);
	
	public boolean update(EquipBarInfo info);
}
