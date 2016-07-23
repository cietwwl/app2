package com.chuangyou.xianni.shop.action;

/**
 * 物品购买接口
 * @author laofan
 *
 */
public interface IBuyGoods {
	/** 购买  */
	public void buy(int num,long totalPrice);
	
	/** 获取购买器  */
	public ILimitBuyGoods getLimitBuy();
}
