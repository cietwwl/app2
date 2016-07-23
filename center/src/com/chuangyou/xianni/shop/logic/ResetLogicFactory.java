package com.chuangyou.xianni.shop.logic;

import com.chuangyou.xianni.entity.shop.ShopCfg;
import com.chuangyou.xianni.player.GamePlayer;

public class ResetLogicFactory {

	/**
	 * 获取相对应的重置逻辑器
	 * @param player
	 * @param cfg
	 * @return
	 */
	public static IShopResetLogic getResetLogic(GamePlayer player, ShopCfg cfg) {
		//如果已经过期，不需要任何重置
		if(cfg.isExpired())return new NoResetLogic();
		
		if(cfg.getServerLimitNum()>0 && cfg.getPersonLimitNum()>0){
			return new PandSResetLogic();
		}else if(cfg.getServerLimitNum()>0){
			return new ServerResetLogic();
		}else if(cfg.getPersonLimitNum()>0){
			return new PersonResetLogic();
		}else{
			return new NoResetLogic();
		}
	}

}
