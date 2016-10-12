package com.chuangyou.xianni.sql.dao;

import java.util.Map;

import com.chuangyou.xianni.entity.state.ConsumSystemConfig;
import com.chuangyou.xianni.entity.state.StateConditionConfig;
import com.chuangyou.xianni.entity.state.StateConditionInfo;
import com.chuangyou.xianni.entity.state.StateConfig;

public interface StateDao {	
	
	public Map<Integer, StateConditionConfig> getStateConditionConfig();
	public Map<Integer, StateConfig> getStateConfig();
	public Map<Integer, ConsumSystemConfig> getConsumConfig();
	
	public boolean addInfo(StateConditionInfo info);
	public boolean updateInfo(StateConditionInfo info);
	public Map<Integer, StateConditionInfo> getStateConditions(long playerId);
	
	
	
}
