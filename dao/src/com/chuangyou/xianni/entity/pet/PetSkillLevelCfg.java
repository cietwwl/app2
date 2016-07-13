package com.chuangyou.xianni.entity.pet;

public class PetSkillLevelCfg {

	/** 前5位为宠物技能id，后3位为技能等级 */
	private int skillid;
	/** 技能等级 */
	private int skillLv;
	/** 升级所需灵石 */
	private int lvupGold;
	public int getSkillid() {
		return skillid;
	}
	public void setSkillid(int skillid) {
		this.skillid = skillid;
	}
	public int getSkillLv() {
		return skillLv;
	}
	public void setSkillLv(int skillLv) {
		this.skillLv = skillLv;
	}
	public int getLvupGold() {
		return lvupGold;
	}
	public void setLvupGold(int lvupGold) {
		this.lvupGold = lvupGold;
	}
	@Override
	public String toString() {
		return "PetSkillLevel [skillid=" + skillid + ", skillLv=" + skillLv + ", lvupGold=" + lvupGold + "]";
	}
}
