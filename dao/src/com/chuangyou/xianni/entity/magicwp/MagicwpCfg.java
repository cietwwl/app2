package com.chuangyou.xianni.entity.magicwp;

public class MagicwpCfg {

	/** 法宝id */
	private int id;
	/** 名字 */
	private String name;
	/** 名字颜色 */
	private byte color;
	/** 图标 */
	private int icon;
	/** 法宝模型 */
	private String model;
	/** 特殊法宝 0普通 1特殊 */
	private byte isSpecial;
	/** 激活所需天数 */
	private int needDay;
	/** 激活所需vip等级 */
	private int needVip;
	/** 激活禁制格子 */
	private int activeCheck;
	/** 特殊法宝激活需要道具 */
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
	public byte getColor() {
		return color;
	}
	public void setColor(byte color) {
		this.color = color;
	}
	public int getIcon() {
		return icon;
	}
	public void setIcon(int icon) {
		this.icon = icon;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public byte getIsSpecial() {
		return isSpecial;
	}
	public void setIsSpecial(byte isSpecial) {
		this.isSpecial = isSpecial;
	}
	public int getNeedDay() {
		return needDay;
	}
	public void setNeedDay(int needDay) {
		this.needDay = needDay;
	}
	public int getNeedVip() {
		return needVip;
	}
	public void setNeedVip(int needVip) {
		this.needVip = needVip;
	}
	public int getActiveCheck() {
		return activeCheck;
	}
	public void setActiveCheck(int activeCheck) {
		this.activeCheck = activeCheck;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	@Override
	public String toString() {
		return "Magicwp [id=" + id + ", name=" + name + ", color=" + color + ", icon=" + icon + ", model=" + model
				+ ", special=" + isSpecial + ", needDay=" + needDay + ", needVip=" + needVip + ", activeCheck="
				+ activeCheck + ", itemId=" + itemId + "]";
	}
}
