package com.chuangyou.xianni.sql.dao;

import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.entity.truck.TruckCheckPointConfig;
import com.chuangyou.xianni.entity.truck.TruckFun;
import com.chuangyou.xianni.entity.truck.TruckInfo;
import com.chuangyou.xianni.entity.truck.TruckLevelConfig;
import com.chuangyou.xianni.entity.truck.TruckSkillConfig;
import com.chuangyou.xianni.entity.truck.TruckSkillInfo;


public interface TruckDao {
	public List<TruckCheckPointConfig> getCheckPoints();
	public List<TruckLevelConfig> getLevelConfig();
	public List<TruckSkillConfig> getSkillConfig();
	public List<TruckFun> getSkillFunc();
	
	public boolean addTruckSkill(TruckSkillInfo info);
	public boolean updateTruckSkill(TruckSkillInfo info);
	public boolean delTruckSkill(TruckSkillInfo info);
	public List<TruckSkillInfo> getTruckSkills(long playerId, int trucktype);
	
	public boolean addTruckInfo(TruckInfo info);
	public boolean updateTruckInfo(TruckInfo info);
	public boolean delTruckInfo(TruckInfo info);
	public TruckInfo getTruckInfos(long playerId, int type);
}
