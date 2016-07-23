package com.chuangyou.xianni.shop.action.limit;

import com.chuangyou.xianni.entity.shop.ShopCfg;
import com.chuangyou.xianni.player.GamePlayer;

/**
 * 个人&全服都限购的商口购买
 * @author laofan
 *
 */
public class PAndSLimitBuyGoodsAction extends ServerLimitBuyGoodsAction {

	public PAndSLimitBuyGoodsAction(GamePlayer player, ShopCfg cfg) {
		super(player, cfg);
		// TODO Auto-generated constructor stub
	}

	
	
	/**
	 * 检测是否可以购买
	 */
	@Override
	public boolean limitCheck(int num, long totalPrice) {
		// TODO Auto-generated method stub
		if(super.limitCheck(num, totalPrice)==false){
			return false;
		}else{		
			return new PersonLimitBuyGoodsAction(player, cfg).limitCheck(num, totalPrice);
		}
	}


	@Override
	public boolean updateCache(int num, long totalPrice) {
		// TODO Auto-generated method stub
		return super.updateCache(num, totalPrice) & new PersonLimitBuyGoodsAction(player, cfg).updateCache(num, totalPrice);
	}
	
	


}
