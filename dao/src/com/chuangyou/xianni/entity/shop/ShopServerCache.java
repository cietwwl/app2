package com.chuangyou.xianni.entity.shop;

import java.util.Date;

import com.chuangyou.xianni.entity.DataObject;

/**
 *  全服的商品购买数
 * @author raodun
 *
 */
public class ShopServerCache extends DataObject{
	

	private int privateId;
	private int buyNum;
	/**
	 * 已经重置次数
	 */
	private short resetTime;
	
	/**  最后一次更新时间   */
	private Date lastUpdateTime;
	
	public synchronized int getBuyNum() {
		return buyNum;
	}
	public synchronized void setBuyNum(int buyNum) {
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
	public int getPrivateId() {
		return privateId;
	}
	public void setPrivateId(int privateId) {
		this.privateId = privateId;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
	



	
	
	
}
