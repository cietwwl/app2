package com.chuangyou.xianni.shop.logic;

import java.util.Date;

import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.shop.ShopCfg;
import com.chuangyou.xianni.entity.shop.ShopServerCache;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.shop.ShopServerManager;

public class ServerResetLogic extends BaseResetLogic {

	
	/**
	 * 重置数据
	 * @param cfg
	 * @param player
	 */
	protected void resetTime(ShopCfg cfg,GamePlayer player){
		ShopServerCache cache = ShopServerManager.get(cfg.getId());
		long time = this.getTime(cfg);
		synchronized (cache) {			
			if(cache.getResetTime()<time){
				clearCache(cache,time);
			}
		}
	}
	
	
	/**
	 * 执行数据清理
	 * @param userCache
	 * @param time
	 */
	private void clearCache(ShopServerCache cache,long time){
		cache.setBuyNum((short)0);
		cache.setResetTime((short)time);
		cache.setLastUpdateTime(new Date());
		cache.setOp(Option.Update);
	}
	
	
	
	/**
	 * 按周重置
	 * @param cfg
	 * @param palyer
	 */
	protected void weekReset(ShopCfg cfg,GamePlayer player){
		ShopServerCache cache = ShopServerManager.get(cfg.getId());
		synchronized (cache) {
			if(!isSameWeek(new Date(),cache.getLastUpdateTime())){
				clearCache(cache, 0);
			}
		}
	}
	
	/**
	 * 按月重置
	 * @param cfg
	 * @param player
	 */
	protected void monthReset(ShopCfg cfg,GamePlayer player){
		ShopServerCache cache = ShopServerManager.get(cfg.getId());
		synchronized (cache) {
			if(!isSameMonth(new Date(),cache.getLastUpdateTime())){
				clearCache(cache, 0);
			}
		}
	}
	

}
