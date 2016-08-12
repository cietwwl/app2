package com.chuangyou.xianni.sql.dao;

import java.util.Map;

import com.chuangyou.xianni.entity.equip.EquipAwakenCfg;
import com.chuangyou.xianni.entity.equip.EquipBarGradeCfg;
import com.chuangyou.xianni.entity.equip.EquipResolveCfg;
import com.chuangyou.xianni.entity.equip.EquipSuitCfg;

public interface EquipConfigDao {

	public Map<Short, Map<Integer, EquipBarGradeCfg>> getGrade();
	
	public Map<Long, EquipAwakenCfg> getAwaken();
	
//	public Map<Integer, EquipResolveCfg> getResolve();
	
	public Map<Integer, EquipSuitCfg> getSuit();
}
