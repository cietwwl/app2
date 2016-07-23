package com.chuangyou.xianni.shop.template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.bag.ItemManager;
import com.chuangyou.xianni.entity.shop.ShopCfg;
import com.chuangyou.xianni.sql.dao.DBManager;

/**
 * 商店数据模板
 * @author laofan
 *
 */
public class ShopTemplateMgr {

	/** 商品信息  */
	private static Map<Integer, ShopCfg> shops;
	/** 商店列表   */
	private static Map<Integer,List<ShopCfg>> shopLists = new HashMap<Integer, List<ShopCfg>>();
	
	public static boolean init(){
		return reloadTemplateData();
	}
	
	public static boolean reloadTemplateData(){
		shopLists.clear();
		if(shops!=null)shops.clear();
		shops = DBManager.getShopConfigDao().getAll();
		for(ShopCfg cfg:shops.values()){
			if(ItemManager.findItemTempInfo(cfg.getItemType())==null)return false;
			if(!shopLists.containsKey(cfg.getShopid())){
				shopLists.put(cfg.getShopid(), new ArrayList<>());
			}
			shopLists.get(cfg.getShopid()).add(cfg);
		}
		return true;
	}
	
	/**
	 * 获取商量配置表
	 * @param privateId
	 * @return
	 */
	public static ShopCfg getShopCfg(int privateId){
		return shops.get(privateId);
	}
	
	/**
	 * 获取商店的所有商品信息
	 * @param shopId
	 * @return
	 */
	public static List<ShopCfg> getShopList(int shopId){
		return shopLists.get(shopId);
	}

	public static Map<Integer, ShopCfg> getShops() {
		return shops;
	}
	
}
