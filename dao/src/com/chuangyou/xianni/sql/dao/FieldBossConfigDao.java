package com.chuangyou.xianni.sql.dao;

import java.util.Map;

import com.chuangyou.xianni.entity.fieldBoss.FieldBossCfg;

public interface FieldBossConfigDao {

	public Map<Integer, FieldBossCfg> getAll();
}
