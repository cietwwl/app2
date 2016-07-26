package com.chuangyou.xianni.entity.shop;

import com.chuangyou.common.util.StringUtils;
import com.chuangyou.common.util.TimeUtil;

/**
 * 商店模板配置表
 * @author laofan
 *
 */
public class ShopCfg {
	
	/**  没有时间限制类型商品  */
	public static final byte TIME_TYPE_NOLIMIT  = 1;
	/** 按天循环上下架商品        */
	public static final byte TIME_TYPE_DAY_LOOP = 3;
	/**  固定时间上下架商品    */
	public static final byte TIME_TYPE_FIXED    = 2;
	/** 按周循环上下架商品       */
	public static final byte TIME_TYPE_WEEK     = 4;
	/**  每月  */
	public static final byte TIME_TYPE_MONTH    = 5;
	///////////////////////////////////////////////////////
	/** 常用商城  */
	public static final int SHOP_NORMAL = 90;
	/** 时装商城  */
	public static final int SHOP_FASION = 91;
	/**  每周限购　　 */
	public static final int SHOP_LIMIT_BUY = 92;
	/**  VIP专区 */
	public static final int SHOP_VIP = 93;
	/**  积分   */
	public static final int SHOP_POINTS = 94;

	//////////////////////////////////////////////////////
	private int id;
	private int npcid;
	private int shopid;
	private int itemType;
	private byte bind;
	private int moneyType;
	private long price;
	private byte discount;
	private int serverLimitNum;
	private int personLimitNum;
	/**
	 * 1:永久 2：固定时间 3：每天 4：每周 5：每月                     11-17：周一到周日                        101-131：1号到31号
	 */
	private short timeType;
	/** 上架时间   */
	private String shelvesTime;
	/** 下架时间    */
	private String shelfTime;
	/**  每日的开始时间   */
	private String startTime;
	/**  每日的结束时间   */
	private String endTime;
	/** 重置时间   */
	private int resetTime;
	private int tab;
	private int sort;
	/** 是否预显示  */
	private byte isPreview;
	/** 是否快速购买  */
	private byte easyBuy;
	/** 购买需要的VIP等级 */
	private int vipLv;
	

	/**
	 * 是否过期 
	 * @return true:已经过期 
	 */
	public boolean isExpired(){
		if(this.timeType == TIME_TYPE_NOLIMIT){  //永久的。不会过期
			return false;
		}
		if(this.shelvesTime.equals("0") && this.shelfTime.equals("0"))return false;
		//其它类型都判断一下，当前时间是否在上下架时间范围内
		return !TimeUtil.isInDate(System.currentTimeMillis(), 
				TimeUtil.getDateByString(this.shelvesTime,2),
				TimeUtil.getDateByString(this.shelfTime,2));
		
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




	public String getStartTime() {
		return startTime;
	}



	public void setStartTime(String startTime) {
		if(StringUtils.isNullOrEmpty(startTime)){
			startTime = "0";
		}
		this.startTime = startTime;
	}



	public String getEndTime() {
		return endTime;
	}



	public void setEndTime(String endTime) {
		if(StringUtils.isNullOrEmpty(startTime)){
			startTime = "0";
		}
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






	public byte getDiscount() {
		return discount;
	}



	public void setDiscount(byte discount) {
		this.discount = discount;
	}



	public int getServerLimitNum() {
		return serverLimitNum;
	}



	public void setServerLimitNum(int serverLimitNum) {
		this.serverLimitNum = serverLimitNum;
	}



	public int getPersonLimitNum() {
		return personLimitNum;
	}



	public void setPersonLimitNum(int personLimitNum) {
		this.personLimitNum = personLimitNum;
	}



	public int getTab() {
		return tab;
	}



	public void setTab(int tab) {
		this.tab = tab;
	}



	public int getSort() {
		return sort;
	}



	public void setSort(int sort) {
		this.sort = sort;
	}





	public int getId() {
		return id;
	}





	public void setId(int id) {
		this.id = id;
	}





	public String getShelvesTime() {
		return shelvesTime;
	}





	public void setShelvesTime(String shelvesTime) {
		if(StringUtils.isNullOrEmpty(shelvesTime)){
			shelvesTime = "0";
		}
		this.shelvesTime = shelvesTime;
	}





	public String getShelfTime() {
		return shelfTime;
	}





	public void setShelfTime(String shelfTime) {
		if(StringUtils.isNullOrEmpty(shelfTime)){
			shelfTime = "0";
		}
		this.shelfTime = shelfTime;
	}





	public byte getIsPreview() {
		return isPreview;
	}





	public void setIsPreview(byte isPreview) {
		this.isPreview = isPreview;
	}





	public short getTimeType() {
		return timeType;
	}





	public void setTimeType(short timeType) {
		this.timeType = timeType;
	}


	public byte getEasyBuy() {
		return easyBuy;
	}


	public void setEasyBuy(byte easyBuy) {
		this.easyBuy = easyBuy;
	}


	public int getVipLv() {
		return vipLv;
	}


	public void setVipLv(int vipLv) {
		this.vipLv = vipLv;
	}
	

}
