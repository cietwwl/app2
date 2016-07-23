package com.chuangyou.xianni.sql.dao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.chuangyou.xianni.entity.shop.ShopServerCache;
import com.chuangyou.xianni.entity.shop.ShopUserCache;

/**
 * 商店DAO层接口
 * @author laofan
 *
 */
public interface ShopDao {
	
	/**
	 * 获取商店用户信息
	 * @param playerId
	 * @return
	 */
	public Map<Integer,ShopUserCache> getUserAll(long playerId);
	
	/** 添加 */
	public boolean addShopUserCache(ShopUserCache info);
	/**删除 */
	public boolean deleteShopUserCache(ShopUserCache info);
	/** 更新  */
	public boolean updateShopUserCache(ShopUserCache info);
	
	/**
	 * 获取全服商品信息
	 * @return
	 */
	public ConcurrentHashMap<Integer, ShopServerCache> getAll();
	
	/** 添加 */
	public boolean addShopServerCache(ShopServerCache info);
	/**删除 */
	public boolean deleteShopServerCache(ShopServerCache info);
	/** 更新  */
	public boolean updateShopServerCache(ShopServerCache info);
  	
}
