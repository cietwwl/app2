package com.chuangyou.xianni.entity.npcShop;

import com.chuangyou.xianni.entity.DataObject;

/*
 * 用户当前商品的购买数
 */
public class NpcShopUserCache extends DataObject{
	
	private long playerId;
	private short privateId;
	private short buyNum;
	/** 重置次数   */
	private short resetTime;
	
	public short getPrivateId() {
		return privateId;
	}
	public void setPrivateId(short privateId) {
		this.privateId = privateId;
	}
	public short getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(short buyNum) {
		this.buyNum = buyNum;
	}
	
	public long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}
	public short getResetTime() {
		return resetTime;
	}
	public void setResetTime(short resetTime) {
		this.resetTime = resetTime;
	}
	@Override
	public String toString() {
		return "NpcShopUserCache [playerId=" + playerId + ", privateId=" + privateId + ", buyNum=" + buyNum
				+ ", resetTime=" + resetTime + "]";
	}



}
