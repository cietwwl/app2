package com.chuangyou.xianni.sql.dao;

import java.util.Map;
import com.chuangyou.xianni.entity.npcShop.NpcShopCfg;

public interface NpcShopConfigDao {
	
	/**
	 * 获取NPC商店配置表 
	 *
	 **/
	public Map<Short,NpcShopCfg> getAll();
}
