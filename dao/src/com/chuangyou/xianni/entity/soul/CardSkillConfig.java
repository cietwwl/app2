package com.chuangyou.xianni.entity.soul;

public class CardSkillConfig {
	private int id;
	private String name;
	/**
	 * 权重
	 */
	private int weight;
	/**
	 *  洗技能消耗物品
	 */
	private int itemId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
	
}
