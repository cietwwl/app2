package com.chuangyou.xianni.sql.dao;

import com.chuangyou.xianni.entity.fieldBoss.FieldBossDieInfo;

public interface FieldBossInfoDao {

	public FieldBossDieInfo get(int monsterId);
	
	public boolean saveOrUpdate(FieldBossDieInfo info);
}
