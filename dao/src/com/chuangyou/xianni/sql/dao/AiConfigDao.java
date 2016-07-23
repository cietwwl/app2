package com.chuangyou.xianni.sql.dao;

import java.util.Map;

import com.chuangyou.xianni.entity.spawn.AiConfig;

public interface AiConfigDao {
	public Map<Integer, AiConfig> getAll();
}
