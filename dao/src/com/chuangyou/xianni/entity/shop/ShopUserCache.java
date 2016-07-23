package com.chuangyou.xianni.entity.shop;


import java.util.Date;

import com.chuangyou.xianni.entity.DataObject;
import com.chuangyou.xianni.entity.Option;

/*
 * 用户当前商品的购买数
 */
public class ShopUserCache extends DataObject{
	
	private long playerId;
	private int privateId;
	private short buyNum;
	/** 重置次数   */
	private short resetTime;
	/**  最后一次更新时间   */
	private Date lastUpdateTime;
	
	public short getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(short buyNum) {
		if(this.buyNum!=buyNum){
			this.setOp(Option.Update);
			this.buyNum = buyNum;
		}
	}
	
	public long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(long playerId) {
		if(this.playerId != playerId){
			this.setOp(Option.Update);
			this.playerId = playerId;
		}
	}
	public short getResetTime() {
		return resetTime;
	}
	public void setResetTime(short resetTime) {
		if(this.resetTime != resetTime){
			this.setOp(Option.Update);
			this.resetTime = resetTime;
		}
	}
	@Override
	public String toString() {
		return "NpcShopUserCache [playerId=" + playerId + ", privateId=" + privateId + ", buyNum=" + buyNum
				+ ", resetTime=" + resetTime + "]";
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
		if(this.lastUpdateTime != lastUpdateTime){
			this.setOp(Option.Update);
			this.lastUpdateTime = lastUpdateTime;
		}
	}



}
