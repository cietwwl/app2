package com.chuangyou.xianni.sql.dao;

import java.util.Map;

import com.chuangyou.xianni.entity.task.TaskInfo;

public interface TaskDao {
	
	public boolean add(TaskInfo info);
	public boolean update(TaskInfo info);
	public boolean del(TaskInfo info);
	public Map<Integer, TaskInfo> get(long playerId);
	
	
}
