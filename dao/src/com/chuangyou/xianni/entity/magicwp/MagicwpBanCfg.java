package com.chuangyou.xianni.entity.magicwp;

public class MagicwpBanCfg {

	/** 禁制id */
	private int id;
	/** 名字 */
	private String name;
	/** 图标 */
	private int icon;
	/** 1号位置激活碎片 */
	private int activeItem1;
	/** 2号位置激活碎片 */
	private int activeItem2;
	/** 3号位置激活碎片 */
	private int activeItem3;
	/** 4号位置激活碎片 */
	private int activeItem4;
	/** 激活技能 */
	private int skillId;
	/** 吃碎片经验 */
	private int exp;
	/** 描述 */
	private String des;
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
	public int getIcon() {
		return icon;
	}
	public void setIcon(int icon) {
		this.icon = icon;
	}
	public int getActiveItem1() {
		return activeItem1;
	}
	public void setActiveItem1(int activeItem1) {
		this.activeItem1 = activeItem1;
	}
	public int getActiveItem2() {
		return activeItem2;
	}
	public void setActiveItem2(int activeItem2) {
		this.activeItem2 = activeItem2;
	}
	public int getActiveItem3() {
		return activeItem3;
	}
	public void setActiveItem3(int activeItem3) {
		this.activeItem3 = activeItem3;
	}
	public int getActiveItem4() {
		return activeItem4;
	}
	public void setActiveItem4(int activeItem4) {
		this.activeItem4 = activeItem4;
	}
	public int getSkillId() {
		return skillId;
	}
	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	@Override
	public String toString() {
		return "MagicwpBan [id=" + id + ", name=" + name + ", icon=" + icon + ", activeItem1=" + activeItem1
				+ ", activeItem2=" + activeItem2 + ", activeItem3=" + activeItem3 + ", activeItem4=" + activeItem4
				+ ", skillId=" + skillId + ", exp=" + exp + ", des=" + des + "]";
	}
}
