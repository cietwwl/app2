package com.chuangyou.xianni.shop.logic;

import java.util.Date;

import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.shop.ShopCfg;
import com.chuangyou.xianni.entity.shop.ShopUserCache;
import com.chuangyou.xianni.player.GamePlayer;

/**
 * 个人购买记录数据重置
 * 
 * @author laofan
 *
 */

public class PersonResetLogic extends BaseResetLogic {

	/**
	 * 重置数据
	 * 
	 * @param cfg
	 * @param player
	 */
	protected void resetTime(ShopCfg cfg, GamePlayer player) {
		//Log.error("cfg:"+cfg+"*****"+player.getShopInventory());
		ShopUserCache userCache = player.getShopInventory().get(cfg.getId());
		long time = this.getTime(cfg);
		if (userCache.getResetTime() < time) {
			clearCache(userCache, time);
		}
	}

	/**
	 * 执行数据清理
	 * 
	 * @param userCache
	 * @param time
	 */
	private void clearCache(ShopUserCache userCache, long time) {
		userCache.setBuyNum((short) 0);
		userCache.setResetTime((short) time);
		userCache.setLastUpdateTime(new Date());
		userCache.setOp(Option.Update);
	}

	/**
	 * 按周重置
	 * 
	 * @param cfg
	 * @param palyer
	 */
	protected void weekReset(ShopCfg cfg, GamePlayer player) {
		ShopUserCache userCache = player.getShopInventory().get(cfg.getId());
		if (!isSameWeek(new Date(), userCache.getLastUpdateTime())) {
			clearCache(userCache, 0);
		}
	}

	/**
	 * 按月重置
	 * 
	 * @param cfg
	 * @param player
	 */
	protected void monthReset(ShopCfg cfg, GamePlayer player) {
		ShopUserCache userCache = player.getShopInventory().get(cfg.getId());
		if (!isSameMonth(new Date(), userCache.getLastUpdateTime())) {
			clearCache(userCache, 0);
		}
	}

}
