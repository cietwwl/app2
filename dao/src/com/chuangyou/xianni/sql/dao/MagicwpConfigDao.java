package com.chuangyou.xianni.sql.dao;

import java.util.Map;

import com.chuangyou.xianni.entity.magicwp.MagicwpBanCfg;
import com.chuangyou.xianni.entity.magicwp.MagicwpBanLevelCfg;
import com.chuangyou.xianni.entity.magicwp.MagicwpCfg;
import com.chuangyou.xianni.entity.magicwp.MagicwpGradeCfg;
import com.chuangyou.xianni.entity.magicwp.MagicwpLevelCfg;
import com.chuangyou.xianni.entity.magicwp.MagicwpRefineCfg;

public interface MagicwpConfigDao {
	public Map<Integer, MagicwpCfg> getMagic();
	
	public Map<Integer, MagicwpLevelCfg> getLevel();
	
	public Map<Integer, MagicwpGradeCfg> getGrade();
	
	public Map<Integer, MagicwpRefineCfg> getRefine();
	
	public Map<Integer, MagicwpBanCfg> getBan();
	
	public Map<Integer, MagicwpBanLevelCfg> getBanLevel();
}
