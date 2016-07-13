package com.chuangyou.xianni.sql.dao;

import java.util.Map;

import com.chuangyou.xianni.entity.fashion.FashionCfg;
import com.chuangyou.xianni.entity.fashion.FashionLevelCfg;
import com.chuangyou.xianni.entity.fashion.FashionQualityCfg;

public interface FashionConfigDao {

	public Map<Integer, FashionCfg> getFashion();
	
	public Map<Integer, FashionLevelCfg> getLevel();
	
	public Map<Integer, FashionQualityCfg> getQuality();
}
