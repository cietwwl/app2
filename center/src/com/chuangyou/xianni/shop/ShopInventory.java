package com.chuangyou.xianni.shop;

import java.util.Date;
import java.util.Map;

import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.shop.ShopUserCache;
import com.chuangyou.xianni.event.AbstractEvent;
import com.chuangyou.xianni.interfaces.IInventory;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.sql.dao.DBManager;

/**
 * 商店个人数据容器
 * @author laofan
 *
 */
public class ShopInventory extends AbstractEvent implements IInventory{

	/**
	 * 玩家
	 */
	private GamePlayer player;
	
	/** 商店数据   */
	private Map<Integer,ShopUserCache> shops;
	
	/** 
	 * 是否刷新过数据
	 */
	private boolean isRefreshData = true;
	
	
	public ShopInventory(GamePlayer player) {
		super();
		this.player = player;
	}

	/**
	 * 获取购买记录数据
	 * @param privateId
	 * @return
	 */
	public ShopUserCache get(int privateId){
		if(!shops.containsKey(privateId)){
			ShopUserCache cache = new ShopUserCache();
			cache.setBuyNum((short)0);
			cache.setPlayerId(player.getPlayerId());
			cache.setResetTime((short)0);
			cache.setPrivateId(privateId);
			cache.setLastUpdateTime(new Date());
			cache.setOp(Option.Insert);
			shops.put(privateId, cache);
		}
		return shops.get(privateId);
	}
	
	
	/**
	 * 添加
	 * @param info
	 * @return
	 */
	public boolean add(ShopUserCache info){
		if(shops == null)return false;
		info.setOp(Option.Insert);
		shops.put(info.getPrivateId(), info);		
		return true;
	}
	
	/**
	 * 更新
	 * @param info
	 * @return
	 */
	public boolean update(ShopUserCache info){
		if(shops == null)return false;
		if(!shops.containsKey(info.getPrivateId()))return false;
		info.setOp(Option.Update);
		return true;
	}
	
	/**
	 * 删除
	 * @param info
	 * @return
	 */
	public boolean delete(ShopUserCache info){
		if(shops == null)return false;
		info.setOp(Option.Delete);
		DBManager.getShopDao().deleteShopUserCache(info);
		shops.remove(info.getPrivateId());
		return true;
	}
	
	
	//====================================>接口实现<=============================================
	@Override
	public boolean loadFromDataBase() {
		// TODO Auto-generated method stub
		shops =  DBManager.getShopDao().getUserAll(player.getPlayerId());
		return true;
	}


	@Override
	public boolean unloadData() {
		// TODO Auto-generated method stub
		saveToDatabase();
		this.player = null;
		this.shops.clear();
		this.shops = null;
		return false;
	}


	@Override
	public boolean saveToDatabase() {
		// TODO Auto-generated method stub
		if(shops==null || shops.size() == 0)return true;

		for(ShopUserCache value:shops.values()){
			short option = value.getOp();
			if(option == Option.Update){
				DBManager.getShopDao().updateShopUserCache(value);
			}else if(option == Option.Insert){
				DBManager.getShopDao().addShopUserCache(value);
			}
		}
		return true;
	}

	public boolean isRefreshData() {
		return isRefreshData;
	}

	public void setRefreshData(boolean isRefreshData) {
		this.isRefreshData = isRefreshData;
	}
	
	
	
	
	
	
}
