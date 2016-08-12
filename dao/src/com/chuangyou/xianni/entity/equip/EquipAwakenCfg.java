package com.chuangyou.xianni.entity.equip;

public class EquipAwakenCfg {

	/**
	 * 武器ID*10000+觉醒等级*100+突破点
	 */
	private long id;
	
	/**
	 * 武器ID
	 */
	private int weaponId;
	
	/**
	 * 觉醒等级
	 */
	private int level;
	
	/**
	 * 突破点
	 */
	private int point;
	
	/**
	 * 突破点上限
	 */
	private int maxPoint;
	
	/**
	 * 消耗道具
	 */
	private int needItem;
	
	/**
	 * 消耗道具数量
	 */
	private int needItemNum;
	
	/**
	 * 觉醒技能
	 */
	private int skillId;
	
	/**
	 * 成功机率
	 */
	private int rate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getWeaponId() {
		return weaponId;
	}

	public void setWeaponId(int weaponId) {
		this.weaponId = weaponId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getMaxPoint() {
		return maxPoint;
	}

	public void setMaxPoint(int maxPoint) {
		this.maxPoint = maxPoint;
	}

	public int getNeedItem() {
		return needItem;
	}

	public void setNeedItem(int needItem) {
		this.needItem = needItem;
	}

	public int getNeedItemNum() {
		return needItemNum;
	}

	public void setNeedItemNum(int needItemNum) {
		this.needItemNum = needItemNum;
	}

	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}
}
