package com.chuangyou.xianni.sql.dao;

import java.util.Map;

import com.chuangyou.xianni.entity.npcShop.NpcShopServerCache;
import com.chuangyou.xianni.entity.npcShop.NpcShopUserCache;

public interface NpcShopDao {
	
	/**
	 * 获取NPC商店用户信息
	 * @param playerId
	 * @return
	 */
	public Map<Short,NpcShopUserCache> getUserAll(long playerId);
	
	/** 添加 */
	public boolean addNpcShopUserCache(NpcShopUserCache info);
	/**删除 */
	public boolean deleteNpcShopUserCache(NpcShopUserCache info);
	/** 更新  */
	public boolean updateNpcShopUserCache(NpcShopUserCache info);
	
	/**
	 * 获取全服商品信息
	 * @return
	 */
	public Map<Short, NpcShopServerCache> getAll();
	
	/** 添加 */
	public boolean addNpcShopServerCache(NpcShopServerCache info);
	/**删除 */
	public boolean deleteNpcShopServerCache(NpcShopServerCache info);
	/** 更新  */
	public boolean updateNpcShopServerCache(NpcShopServerCache info);
  	
}
