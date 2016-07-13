package com.chuangyou.xianni.sql.dao;

import java.util.Map;

import com.chuangyou.xianni.entity.task.TaskCfg;

public interface TaskTemplateDao {
	
	public Map<Integer, TaskCfg> getAll();
}
