package com.chuangyou.xianni.entity.pet;

public class PetSkillInfoCfg {

	/** 宠物技能id */
	private int skillid;
	/** 激活所需宠物 */
	private int needjihuopet;
	/** 激活所需道具 */
	private int needjihuoitem;
	/** 解封所需道具 */
	private int item;
	/** 解封所需道具数 */
	private int itemNum;
	public int getSkillid() {
		return skillid;
	}
	public void setSkillid(int skillid) {
		this.skillid = skillid;
	}
	public int getNeedjihuopet() {
		return needjihuopet;
	}
	public void setNeedjihuopet(int needjihuopet) {
		this.needjihuopet = needjihuopet;
	}
	public int getNeedjihuoitem() {
		return needjihuoitem;
	}
	public void setNeedjihuoitem(int needjihuoitem) {
		this.needjihuoitem = needjihuoitem;
	}
	public int getItem() {
		return item;
	}
	public void setItem(int item) {
		this.item = item;
	}
	public int getItemNum() {
		return itemNum;
	}
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}
	@Override
	public String toString() {
		return "PetSkillInfo [skillid=" + skillid + ", needjihuopet=" + needjihuopet + ", needjihuoitem="
				+ needjihuoitem + ", item=" + item + ", itemNum=" + itemNum + "]";
	}
}
