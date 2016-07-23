package com.chuangyou.xianni.shop.action.limit;

import com.chuangyou.xianni.entity.shop.ShopCfg;
import com.chuangyou.xianni.player.GamePlayer;

/**
 * 不限购 商品购买
 * @author laofan
 *
 */
public class NoLimitBuyGoodsAction extends BaseLimitBuyAction{

	public NoLimitBuyGoodsAction(GamePlayer player, ShopCfg cfg) {
		super(player, cfg);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public boolean limitCheck(int num, long totalPrice) {
		// TODO Auto-generated method stub
		return true;
	}

}
