package com.chuangyou.xianni.entity.drop;

import com.chuangyou.common.util.random.IWeight;

public class DropItemInfo implements IWeight {

	/**
	 * 掉落物ID
	 */
	private int id;
	
	/**
	 * 所在掉落池ID,对应dropinfo中的id
	 */
	private int poolId;
	
	/**
	 * 物品ID或货币数量
	 */
	private int itemId;
	
	/**
	 * 数量
	 */
	private int count;
	
	/**
	 * 权重
	 */
	private int weight;
	
	/**
	 * 获取物品时发送全服公告
	 */
	private int sendNotice;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPoolId() {
		return poolId;
	}

	public void setPoolId(int poolId) {
		this.poolId = poolId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getSendNotice() {
		return sendNotice;
	}

	public void setSendNotice(int sendNotice) {
		this.sendNotice = sendNotice;
	}
}
