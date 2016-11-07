package com.chuangyou.xianni.sql.dao;

import java.util.Map;

import com.chuangyou.xianni.entity.active.ActiveConfig;
import com.chuangyou.xianni.entity.active.ActiveInfo;

public interface ActiveDao {	
	
	public Map<Integer, ActiveConfig> getActiveConfig();
	
	public boolean addInfo(ActiveInfo info);
	public boolean updateInfo(ActiveInfo info);
	public Map<Integer, ActiveInfo> getActiveInfos(long playerId);

}
