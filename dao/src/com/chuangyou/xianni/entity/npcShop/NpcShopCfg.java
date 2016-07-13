package com.chuangyou.xianni.entity.npcShop;

import com.chuangyou.common.util.TimeUtil;

public class NpcShopCfg {
	
	private short id;
	private int npcid;
	private int shopid;
	private int itemType;
	private byte bind;
	private byte consumeType;
	private int moneyType;
	private long price;
	private int amount;
	private int limitBuynum;
	private byte timeType;
	private String startTime;
	private String endTime;
	private int resetTime;
	

	public boolean isExpired(){			
		if(this.timeType == 2){
			return !TimeUtil.isInDate(System.currentTimeMillis(), 
					TimeUtil.getDateByString(this.startTime,2),
					TimeUtil.getDateByString(this.endTime,2));
		}else if(this.timeType == 0){
			return false;
		}else if(this.timeType == 1){
			return !TimeUtil.isInDate(System.currentTimeMillis(), 
					TimeUtil.getDateFromNowByString(this.startTime),
					TimeUtil.getDateFromNowByString(this.endTime));
		}else{
			return false;
		}
		
	}



	public short getId() {
		return id;
	}



	public void setId(short id) {
		this.id = id;
	}



	public int getNpcid() {
		return npcid;
	}



	public void setNpcid(int npcid) {
		this.npcid = npcid;
	}



	public int getShopid() {
		return shopid;
	}



	public void setShopid(int shopid) {
		this.shopid = shopid;
	}



	public int getItemType() {
		return itemType;
	}



	public void setItemType(int itemType) {
		this.itemType = itemType;
	}


	public byte getConsumeType() {
		return consumeType;
	}



	public void setConsumeType(byte consumeType) {
		this.consumeType = consumeType;
	}



	public int getMoneyType() {
		return moneyType;
	}



	public void setMoneyType(int moneyType) {
		this.moneyType = moneyType;
	}



	public long getPrice() {
		return price;
	}



	public void setPrice(long price) {
		this.price = price;
	}



	public int getAmount() {
		return amount;
	}



	public void setAmount(int amount) {
		this.amount = amount;
	}



	public int getLimitBuynum() {
		return limitBuynum;
	}



	public void setLimitBuynum(int limitBuynum) {
		this.limitBuynum = limitBuynum;
	}




	public String getStartTime() {
		return startTime;
	}



	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}



	public String getEndTime() {
		return endTime;
	}



	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}



	public int getResetTime() {
		return resetTime;
	}



	public void setResetTime(int resetTime) {
		this.resetTime = resetTime;
	}



	public byte getBind() {
		return bind;
	}



	public void setBind(byte bind) {
		this.bind = bind;
	}



	public byte getTimeType() {
		return timeType;
	}



	public void setTimeType(byte timeType) {
		this.timeType = timeType;
	}
	

}
