package com.chuangyou.xianni.entity.guild;

public class GuildDonateCfg {

	/**
	 * 捐献类型 1道具 2仙玉
	 */
	private int type;
	/**
	 * 道具ID
	 */
	private int item;
	/**
	 * 玩家获得贡献
	 */
	private long contribution;
	/**
	 * 帮派获得建设值
	 */
	private long buildExp;
	/**
	 * 帮派获得物资
	 */
	private long supply;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getItem() {
		return item;
	}
	public void setItem(int item) {
		this.item = item;
	}
	public long getContribution() {
		return contribution;
	}
	public void setContribution(long contribution) {
		this.contribution = contribution;
	}
	public long getBuildExp() {
		return buildExp;
	}
	public void setBuildExp(long buildExp) {
		this.buildExp = buildExp;
	}
	public long getSupply() {
		return supply;
	}
	public void setSupply(long supply) {
		this.supply = supply;
	}
}
