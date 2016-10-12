package com.chuangyou.xianni.sql.dao;

import java.util.Map;

import com.chuangyou.xianni.entity.spawn.SpawnInfo;

public interface SpawnInfoDao {
	Map<Integer, SpawnInfo> getAll();
}
