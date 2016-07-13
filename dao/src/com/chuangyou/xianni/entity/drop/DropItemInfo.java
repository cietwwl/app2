package com.chuangyou.xianni.entity.drop;

public class DropItemInfo {

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
}
