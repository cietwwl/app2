package com.chuangyou.xianni.shop.action.pay;

import com.chuangyou.xianni.entity.shop.ShopCfg;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.shop.action.BaseBuyGoodsAction;
import com.chuangyou.xianni.shop.action.IPayBuyGoods;

/**
 * 积分兑换
 * @author laofan
 *
 */
public class PointsPayBuyAction extends BaseBuyGoodsAction implements IPayBuyGoods {

	public PointsPayBuyAction(GamePlayer player, ShopCfg cfg) {
		super(player, cfg);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean payBuyGoods(int num, long totalPrice) {
		// TODO Auto-generated method stub
		return false;
	}

}
