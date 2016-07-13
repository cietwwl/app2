package com.chuangyou.xianni.entity.pet;

public class PetSkillSlotCfg {

	/** 宠物技能格id */
	private int id;
	/** 解锁所需道具 */
	private int needItem;
	/** 解锁所需道具数 */
	private int needItemNum;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	@Override
	public String toString() {
		return "PetSkillSlot [id=" + id + ", needItem=" + needItem + ", needItemNum=" + needItemNum + "]";
	}
}
