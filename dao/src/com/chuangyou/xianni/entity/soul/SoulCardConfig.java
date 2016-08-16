package com.chuangyou.xianni.entity.soul;

public class SoulCardConfig {
	private int id;//` int(11) NOT NULL AUTO_INCREMENT,
	private String name;//` varchar(30) NOT NULL DEFAULT '',
	private int quality;//` tinyint(3) NOT NULL DEFAULT '0' COMMENT '品质',
	private int attrInit1;//` int(11) NOT NULL DEFAULT '0',
	private int attrAdd1;//` int(11) NOT NULL DEFAULT '0',
	private int attrInit2;//` int(11) NOT NULL DEFAULT '0',
	private int attrAdd2;//` int(11) NOT NULL DEFAULT '0',
	private int attrInit3;//` int(11) DEFAULT '0',
	private int attrAdd3;//` varchar(255) DEFAULT NULL,
	private int attrInit4;//` int(11) NOT NULL DEFAULT '0',
	private int attrAdd4;//` int(11) NOT NULL DEFAULT '0',
	private int combo1;//` int(11) NOT NULL DEFAULT '0' COMMENT '组合',
	private int combo2;//` int(11) NOT NULL DEFAULT '0' COMMENT '组合',
	private int combo3;//` int(11) NOT NULL DEFAULT '0' COMMENT '组合',
	private int combo4;//` int(11) NOT NULL DEFAULT '0' COMMENT '组合',
	private int needClip;//` int(11) NOT NULL DEFAULT '0' COMMENT '需求碎片数量',
	private int skill;//` int(11) NOT NULL DEFAULT '0' COMMENT '技能',
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
	public int getQuality() {
		return quality;
	}
	public void setQuality(int quality) {
		this.quality = quality;
	}
	public int getAttrInit1() {
		return attrInit1;
	}
	public void setAttrInit1(int attrInit1) {
		this.attrInit1 = attrInit1;
	}
	public int getAttrAdd1() {
		return attrAdd1;
	}
	public void setAttrAdd1(int attrAdd1) {
		this.attrAdd1 = attrAdd1;
	}
	public int getAttrInit2() {
		return attrInit2;
	}
	public void setAttrInit2(int attrInit2) {
		this.attrInit2 = attrInit2;
	}
	public int getAttrAdd2() {
		return attrAdd2;
	}
	public void setAttrAdd2(int attrAdd2) {
		this.attrAdd2 = attrAdd2;
	}
	public int getAttrInit3() {
		return attrInit3;
	}
	public void setAttrInit3(int attrInit3) {
		this.attrInit3 = attrInit3;
	}
	public int getAttrAdd3() {
		return attrAdd3;
	}
	public void setAttrAdd3(int attrAdd3) {
		this.attrAdd3 = attrAdd3;
	}
	public int getAttrInit4() {
		return attrInit4;
	}
	public void setAttrInit4(int attrInit4) {
		this.attrInit4 = attrInit4;
	}
	public int getAttrAdd4() {
		return attrAdd4;
	}
	public void setAttrAdd4(int attrAdd4) {
		this.attrAdd4 = attrAdd4;
	}
	public int getCombo1() {
		return combo1;
	}
	public void setCombo1(int combo1) {
		this.combo1 = combo1;
	}
	public int getCombo2() {
		return combo2;
	}
	public void setCombo2(int combo2) {
		this.combo2 = combo2;
	}
	public int getCombo3() {
		return combo3;
	}
	public void setCombo3(int combo3) {
		this.combo3 = combo3;
	}
	public int getCombo4() {
		return combo4;
	}
	public void setCombo4(int combo4) {
		this.combo4 = combo4;
	}
	public int getNeedClip() {
		return needClip;
	}
	public void setNeedClip(int needClip) {
		this.needClip = needClip;
	}
	public int getSkill() {
		return skill;
	}
	public void setSkill(int skill) {
		this.skill = skill;
	}
	

}
