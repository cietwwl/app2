package com.chuangyou.xianni.sql.dao;

import java.util.Map;

import com.chuangyou.xianni.entity.activity.ActivityConfig;
import com.chuangyou.xianni.entity.activity.ActivityInfo;

public interface ActivityDao {

	public Map<Integer, ActivityConfig> getActivityConfig();
	
	public boolean addInfo(ActivityInfo info);
	public boolean updateInfo(ActivityInfo info);
	public Map<Integer, ActivityInfo> getInfos(long playerId);
	
}
