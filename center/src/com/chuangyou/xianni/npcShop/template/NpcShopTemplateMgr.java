package com.chuangyou.xianni.npcShop.template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.entity.npcShop.NpcShopCfg;
import com.chuangyou.xianni.sql.dao.DBManager;

public class NpcShopTemplateMgr {

	/** 商品信息  */
	private static Map<Short, NpcShopCfg> shops;
	/** 商店列表   */
	private static Map<Integer,List<NpcShopCfg>> shopLists = new HashMap<Integer, List<NpcShopCfg>>();
	
	public static boolean init(){
		reloadTemplateData();
		return true;
	}
	
	public static void reloadTemplateData(){
		shopLists.clear();
		if(shops!=null)shops.clear();
		shops = DBManager.getNpcshopconfigdao().getAll();
		for(NpcShopCfg cfg:shops.values()){
			if(!shopLists.containsKey(cfg.getShopid())){
				shopLists.put(cfg.getShopid(), new ArrayList<>());
			}
			shopLists.get(cfg.getShopid()).add(cfg);
		}
	}
	
	/**
	 * 获取NPC商量配置表
	 * @param privateId
	 * @return
	 */
	public static NpcShopCfg getNpcShopCfg(short privateId){
		return shops.get(privateId);
	}
	
	/**
	 * 获取商店的所有商品信息
	 * @param shopId
	 * @return
	 */
	public static List<NpcShopCfg> getShopList(int shopId){
		return shopLists.get(shopId);
	}

	public static Map<Short, NpcShopCfg> getShops() {
		return shops;
	}
	
}
