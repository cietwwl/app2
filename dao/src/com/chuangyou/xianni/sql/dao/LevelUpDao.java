package com.chuangyou.xianni.sql.dao;

import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.entity.level.LevelUp;

public interface LevelUpDao {

	public Map<Integer, List<LevelUp>> getAll();
}
