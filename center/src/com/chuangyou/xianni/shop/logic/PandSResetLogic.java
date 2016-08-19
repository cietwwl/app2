package com.chuangyou.xianni.shop.logic;

import com.chuangyou.xianni.entity.shop.ShopCfg;
import com.chuangyou.xianni.player.GamePlayer;

/**
 * 个人和全服都限购的商品重置检测
 * 
 * @author laofan
 *
 */
public class PandSResetLogic extends ServerResetLogic {

	@Override
	public void reset(GamePlayer player, ShopCfg cfg) {
		// TODO Auto-generated method stub
		super.reset(player, cfg);
		new PersonResetLogic().reset(player, cfg);
	}

}
