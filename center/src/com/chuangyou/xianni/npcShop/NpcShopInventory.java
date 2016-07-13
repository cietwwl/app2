package com.chuangyou.xianni.npcShop;

import java.util.Map;

import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.npcShop.NpcShopUserCache;
import com.chuangyou.xianni.event.AbstractEvent;
import com.chuangyou.xianni.interfaces.IInventory;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.sql.dao.DBManager;

/**
 * NPC商店个人数据容器
 * @author laofan
 *
 */
public class NpcShopInventory extends AbstractEvent implements IInventory{

	/**
	 * 玩家
	 */
	private GamePlayer player;
	
	/** 商店数据   */
	private Map<Short,NpcShopUserCache> npcShops;
	
	
	public NpcShopInventory(GamePlayer player) {
		super();
		this.player = player;
	}

	
	public NpcShopUserCache get(short privateId){
		if(!npcShops.containsKey(privateId)){
			NpcShopUserCache cache = new NpcShopUserCache();
			cache.setBuyNum((short)0);
			cache.setPlayerId(player.getPlayerId());
			cache.setResetTime((short)0);
			cache.setPrivateId(privateId);
			cache.setOp(Option.Insert);
			npcShops.put(privateId, cache);
		}
		return npcShops.get(privateId);
	}
	
	/**
	 * 添加
	 * @param info
	 * @return
	 */
	public boolean add(NpcShopUserCache info){
		if(npcShops == null)return false;
		info.setOp(Option.Insert);
		npcShops.put(info.getPrivateId(), info);		
		return true;
	}
	
	/**
	 * 更新
	 * @param info
	 * @return
	 */
	public boolean update(NpcShopUserCache info){
		if(npcShops == null)return false;
		if(!npcShops.containsKey(info.getPrivateId()))return false;
		info.setOp(Option.Update);
		return true;
	}
	
	/**
	 * 删除
	 * @param info
	 * @return
	 */
	public boolean delete(NpcShopUserCache info){
		if(npcShops == null)return false;
		info.setOp(Option.Delete);
		DBManager.getNpcshopdao().deleteNpcShopUserCache(info);
		npcShops.remove(info.getPrivateId());
		return true;
	}
	
	
	//====================================>接口实现<=============================================
	@Override
	public boolean loadFromDataBase() {
		// TODO Auto-generated method stub
		npcShops =  DBManager.getNpcshopdao().getUserAll(player.getPlayerId());
		return true;
	}


	@Override
	public boolean unloadData() {
		// TODO Auto-generated method stub
		this.player = null;
		this.npcShops.clear();
		this.npcShops = null;
		return false;
	}


	@Override
	public boolean saveToDatabase() {
		// TODO Auto-generated method stub
		if(npcShops==null || npcShops.size() == 0)return true;

		for(NpcShopUserCache value:npcShops.values()){
			short option = value.getOp();
			if(option == Option.Update){
				DBManager.getNpcshopdao().updateNpcShopUserCache(value);
			}else if(option == Option.Insert){
				DBManager.getNpcshopdao().addNpcShopUserCache(value);
			}
		}
		return true;
	}
	
	
	
	
	
	
}
