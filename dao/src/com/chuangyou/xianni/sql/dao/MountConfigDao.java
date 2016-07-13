package com.chuangyou.xianni.sql.dao;

import java.util.Map;

import com.chuangyou.xianni.entity.mount.MountEquipCfg;
import com.chuangyou.xianni.entity.mount.MountGradeCfg;
import com.chuangyou.xianni.entity.mount.MountLevelCfg;
import com.chuangyou.xianni.entity.mount.MountWeaponCfg;

public interface MountConfigDao {
	public Map<Integer, MountLevelCfg> getLevel();
	
	public Map<Integer, MountGradeCfg> getGrade();
	
	public Map<Integer, Map<Integer, MountEquipCfg>> getEquip();
	
	public Map<Integer, MountWeaponCfg> getWeapon();
}
