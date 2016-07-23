package com.chuangyou.xianni.shop;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.shop.ShopServerCache;
import com.chuangyou.xianni.sql.dao.DBManager;


/**
 * 全服购买商品管理器  
 * @author laofan
 * PS:重置都是由用户每次风购买时触发
 *
 */
public class ShopServerManager {

	/** 初始化完成标记 */
	private static boolean isInited = false;
	
	/** 商店数据 */
	private static ConcurrentHashMap<Integer, ShopServerCache> shops;
	
	/** 初始化 */
	public static boolean init() {
		loadFromDatabase();
		isInited = true;
		return true;
	}

	/** 获取相关信息 */
	public static ShopServerCache get(int privateId) {
		if (isInited == false) {
			init();
		}
		if(!shops.containsKey(privateId)){
			ShopServerCache cache = new ShopServerCache();
			cache.setPrivateId(privateId);
			cache.setResetTime((short) 0);
			cache.setBuyNum(0);
			cache.setLastUpdateTime(new Date());
			cache.setOp(Option.Insert);
			shops.put(privateId, cache);
		}
		return shops.get(privateId);
	}
	

	/**
	 * 添加全服购买记数器
	 * @param info
	 */
	public static void addShopServerCache(ShopServerCache info){
		shops.put(info.getPrivateId(), info);		
	}

	////////////////////////////////////////////////////////////////////////
	//
	/**
	 * 加载DB数据
	 * 
	 * @return
	 */
	private static void loadFromDatabase() {
		shops = DBManager.getShopDao().getAll();
	}

	
	/**
	 * 保存数据入DB
	 */
	public static void saveToDatabase() {
		if (isInited == false)
			return;
		Iterator<Map.Entry<Integer, ShopServerCache>> entries = shops.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<Integer, ShopServerCache> entry = entries.next();
			ShopServerCache shop = entry.getValue();
			short option = shop.getOp();
			if (option == Option.Update) {
				DBManager.getShopDao().updateShopServerCache(shop);
			} else if (option == Option.Insert) {
				DBManager.getShopDao().addShopServerCache(shop);
			}
		}
	}

	

}
