package com.chuangyou.xianni.sql.dao;

import java.util.Map;

import com.chuangyou.xianni.entity.shop.ShopCfg;

/**
 * 商城模板数据接口
 * @author laofan
 *
 */
public interface ShopConfigDao {
	
	/**
	 * 获取NPC商店配置表 
	 *
	 **/
	public Map<Integer,ShopCfg> getAll();
}
