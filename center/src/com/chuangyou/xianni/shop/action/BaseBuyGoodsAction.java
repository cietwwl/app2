package com.chuangyou.xianni.shop.action;

import com.chuangyou.xianni.entity.shop.ShopCfg;
import com.chuangyou.xianni.player.GamePlayer;

/**
 * 购买基类
 * @author laofan
 *
 */
public class BaseBuyGoodsAction {

	protected GamePlayer player;
	protected ShopCfg cfg;
	

	public BaseBuyGoodsAction(GamePlayer player, ShopCfg cfg) {
		this.player = player;
		this.cfg = cfg;
	}
	

}
