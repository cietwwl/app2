package com.chuangyou.xianni.bag;

import com.chuangyou.xianni.entity.item.ItemInfo;

/**
 * 
 * <pre>
 * 背包信息
 * </pre>
 */
public class BagMessage {
	private int			changeBagType	= 0;	// 背包类型
	private int			changePos		= 0;	// 位置
	private long		changeObjectId	= 0;	//
	private ItemInfo	itemInfo;				// 物品信息

	public BagMessage() {

	}

	public void addMessage(int changeBagType, int changePos, long changeObjectId, ItemInfo itemInfo) {
		this.changeBagType = changeBagType;
		this.changePos = changePos;
		this.changeObjectId = changeObjectId;
		this.itemInfo = itemInfo;

	}

	public int getChangeBagType() {
		return changeBagType;
	}

	public void setChangeBagType(int changeBagType) {
		this.changeBagType = changeBagType;
	}

	public int getChangePos() {
		return changePos;
	}

	public void setChangePos(int changePos) {
		this.changePos = changePos;
	}

	public long getChangeObjectId() {
		return changeObjectId;
	}

	public void setChangeObjectId(int changeObjectId) {
		this.changeObjectId = changeObjectId;
	}

	public ItemInfo getItemInfo() {
		return itemInfo;
	}

	public void setItemInfo(ItemInfo itemInfo) {
		this.itemInfo = itemInfo;
	}

}
