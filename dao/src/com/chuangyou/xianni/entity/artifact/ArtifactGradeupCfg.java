package com.chuangyou.xianni.entity.artifact;

public class ArtifactGradeupCfg {

	/** 神器ID*1000+星级*100+星数 */
	private long id;
	
	/** 神器id */
	private int artifactId;
	
	/** 星级 */
	private int starLvl;
	
	/** 星数 */
	private int starNum;
	
	/** 颜色 */
	private int color;
	
	/** 增加属性万分比 */
	private int attr;
	
	/** 消耗道具 */
	private int costItem;
	
	/** 消耗道具数量 */
	private int costNum;
	
	/** 升星符ID */
	private int starAmuletId;
	
	/** 升星符数量 */
	private int starAmuletNum;
	
	/** 幸运值上限 */
	private int blessMax;
	
	/** 增加幸运值的几率 */
	private int blessrate;
	
	/** 升星成功率 */
	private int rate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(int artifactId) {
		this.artifactId = artifactId;
	}

	public int getStarLvl() {
		return starLvl;
	}

	public void setStarLvl(int starLvl) {
		this.starLvl = starLvl;
	}

	public int getStarNum() {
		return starNum;
	}

	public void setStarNum(int starNum) {
		this.starNum = starNum;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getAttr() {
		return attr;
	}

	public void setAttr(int attr) {
		this.attr = attr;
	}

	public int getCostItem() {
		return costItem;
	}

	public void setCostItem(int costItem) {
		this.costItem = costItem;
	}

	public int getCostNum() {
		return costNum;
	}

	public void setCostNum(int costNum) {
		this.costNum = costNum;
	}

	public int getStarAmuletId() {
		return starAmuletId;
	}

	public void setStarAmuletId(int starAmuletId) {
		this.starAmuletId = starAmuletId;
	}

	public int getStarAmuletNum() {
		return starAmuletNum;
	}

	public void setStarAmuletNum(int starAmuletNum) {
		this.starAmuletNum = starAmuletNum;
	}

	public int getBlessMax() {
		return blessMax;
	}

	public void setBlessMax(int blessMax) {
		this.blessMax = blessMax;
	}

	public int getBlessrate() {
		return blessrate;
	}

	public void setBlessrate(int blessrate) {
		this.blessrate = blessrate;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}
}
