package com.chuangyou.xianni.shop.logic;

import java.util.Calendar;
import java.util.Date;

import com.chuangyou.common.util.TimeUtil;
import com.chuangyou.xianni.entity.shop.ShopCfg;
import com.chuangyou.xianni.player.GamePlayer;

/**
 * 重置逻辑
 * @author laofan
 *
 */
public abstract class BaseResetLogic implements IShopResetLogic{

	@Override
	public void reset(GamePlayer player, ShopCfg cfg) {
		if(cfg.getPersonLimitNum() <= 0 && cfg.getServerLimitNum() <= 0)return;
		// TODO Auto-generated method stub
		switch (cfg.getTimeType()) {
		case ShopCfg.TIME_TYPE_MONTH:
			monthReset(cfg, player);
			break;
		case ShopCfg.TIME_TYPE_NOLIMIT:
			break;
		case ShopCfg.TIME_TYPE_WEEK:
			weekReset(cfg, player);
			break;
		default:
			if (cfg.getResetTime() > 0) {
				// 是否需要重置
				resetTime(cfg, player);
			}
			break;
		}
	}
	
	
	protected abstract void resetTime(ShopCfg cfg,GamePlayer player);
	protected abstract void weekReset(ShopCfg cfg,GamePlayer player);
	protected abstract void monthReset(ShopCfg cfg,GamePlayer player);
	
	/**
	 * 是否是同一周
	 * @param d1
	 * @param d2
	 * @return
	 */
	protected boolean isSameWeek(Date d1,Date d2){
		Calendar c1 = Calendar.getInstance(); 
		c1.setTime(d1); 
		int nowWeek = c1.get(Calendar.WEEK_OF_YEAR); 
		
		Calendar c2 = Calendar.getInstance(); 
		c2.setTime(d2); 
		int lastUpdateWeek = c2.get(Calendar.WEEK_OF_YEAR); 
		return nowWeek==lastUpdateWeek;
	}
	
	/**
	 * 是否是同一个月份
	 * @param d1
	 * @param d2
	 * @return
	 */
	protected boolean isSameMonth(Date d1,Date d2){
		Calendar c1 = Calendar.getInstance(); 
		c1.setTime(d1); 
		int now = c1.get(Calendar.MONTH); 
		
		Calendar c2 = Calendar.getInstance(); 
		c2.setTime(d2); 
		int lastUpdate = c2.get(Calendar.MONTH); 
		return now==lastUpdate;
	}
	 
	/**
	 * 获取重置次数
	 * @param cfg
	 * @return
	 */
	protected long getTime(ShopCfg cfg){
		long mm = new Date().getTime() - TimeUtil.getDateByString(cfg.getStartTime(),1).getTime();
		long minute = mm/(1000*60);
		long time = minute/cfg.getResetTime();
		return time;
	}
	
}
