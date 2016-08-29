package com.chuangyou.xianni.entity.artifact;

public class ArtifactJewelLevelCfg {

	/** 宝石ID*1000+宝石等级 */
	private long id;
	
	/** 宝石ID */
	private int jewelId;
	
	/** 宝石等级 */
	private int level;
	
	/** 增加属性 */
	private int attr;
	
	/** 消耗道具 */
	private int item;
	
	/** 消耗道具数量 */
	private int count;
	
	/** 增加经验值 */
	private int exp;
	
	/** 升级需要经验值 */
	private int maxExp;
	
	/** 直升消耗道具 */
	private int levelItem;
	
	/** 直升消耗道具数量 */
	private int levelCount;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getJewelId() {
		return jewelId;
	}

	public void setJewelId(int jewelId) {
		this.jewelId = jewelId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getAttr() {
		return attr;
	}

	public void setAttr(int attr) {
		this.attr = attr;
	}

	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getMaxExp() {
		return maxExp;
	}

	public void setMaxExp(int maxExp) {
		this.maxExp = maxExp;
	}

	public int getLevelItem() {
		return levelItem;
	}

	public void setLevelItem(int levelItem) {
		this.levelItem = levelItem;
	}

	public int getLevelCount() {
		return levelCount;
	}

	public void setLevelCount(int levelCount) {
		this.levelCount = levelCount;
	}
}
