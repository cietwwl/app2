package com.chuangyou.xianni.shop.action;

/**
 * 限购购买接口
 * @author laofan
 *
 */
public interface ILimitBuyGoods {

	public void limitBuy(int num,long totalPrice);
	
	public boolean limitCheck(int num,long totalPrice);
	
	public IPayBuyGoods getPayBuyGoods();
}
