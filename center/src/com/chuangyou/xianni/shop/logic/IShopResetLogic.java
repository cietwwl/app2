package com.chuangyou.xianni.shop.logic;

import com.chuangyou.xianni.entity.shop.ShopCfg;
import com.chuangyou.xianni.player.GamePlayer;

/**
 * 商店重置接口
 * @author laofan
 *
 */
public interface IShopResetLogic {
	
	/** 
	 * 重置
	 * @param player
	 * @param cfg
	 */
	public void reset(GamePlayer player,ShopCfg cfg);
	
}
