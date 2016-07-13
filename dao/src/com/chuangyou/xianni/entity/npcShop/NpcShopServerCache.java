package com.chuangyou.xianni.entity.npcShop;

import com.chuangyou.xianni.entity.DataObject;

/**
 *  全服的商品购买数
 * @author raodun
 *
 */
public class NpcShopServerCache extends DataObject{
	

	private short privateId;
	private int buyNum;
	/**
	 * 已经重置次数
	 */
	private short resetTime;
	
	public short getPrivateId() {
		return privateId;
	}
	public void setPrivateId(short privateId) {
		this.privateId = privateId;
	}
	public int getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}
	public short getResetTime() {
		return resetTime;
	}
	public void setResetTime(short resetTime) {
		this.resetTime = resetTime;
	}
	
	@Override
	public String toString() {
		return "NpcShopServerCache [privateId=" + privateId + ", buyNum=" + buyNum + ", resetTime=" + resetTime + "]";
	}
	
	



	
	
	
}
