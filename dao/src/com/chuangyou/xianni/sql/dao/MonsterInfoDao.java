package com.chuangyou.xianni.sql.dao;

import java.util.Map;

import com.chuangyou.xianni.entity.spawn.MonsterInfo;

public interface MonsterInfoDao {
	public Map<Integer, MonsterInfo> getAll();
}
