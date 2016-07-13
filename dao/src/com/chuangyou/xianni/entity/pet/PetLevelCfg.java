package com.chuangyou.xianni.entity.pet;

import java.util.ArrayList;
import java.util.List;

public class PetLevelCfg {

	/** 宠物id后3位为等级 */
	private int id;
	/** 等级 */
	private int lv;
	/** 属性 */
	private int att1;
	/** 属性 */
	private int att2;
	/** 属性 */
	private int att3;
	/** 属性 */
	private int att4;
	/** 属性 */
	private int att5;
	/** 属性 */
	private int att6;
	/** 属性 */
	private int att7;
	/** 属性 */
	private int att8;
	/** 升级经验 */
	private int exp;
	/** 升级所吃道具1 */
	private int itemId1;
	/** 吃道具1增加的经验值 */
	private int itemExp1;
	/** 升级所吃道具2 */
	private int itemId2;
	/** 吃道具2增加的经验值 */
	private int itemExp2;
	/** 升级所吃道具3 */
	private int itemId3;
	/** 吃道具3增加的经验值 */
	private int itemExp3;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLv() {
		return lv;
	}
	public void setLv(int lv) {
		this.lv = lv;
	}
	public int getAtt1() {
		return att1;
	}
	public void setAtt1(int att1) {
		this.att1 = att1;
	}
	public int getAtt2() {
		return att2;
	}
	public void setAtt2(int att2) {
		this.att2 = att2;
	}
	public int getAtt3() {
		return att3;
	}
	public void setAtt3(int att3) {
		this.att3 = att3;
	}
	public int getAtt4() {
		return att4;
	}
	public void setAtt4(int att4) {
		this.att4 = att4;
	}
	public int getAtt5() {
		return att5;
	}
	public void setAtt5(int att5) {
		this.att5 = att5;
	}
	public int getAtt6() {
		return att6;
	}
	public void setAtt6(int att6) {
		this.att6 = att6;
	}
	public int getAtt7() {
		return att7;
	}
	public void setAtt7(int att7) {
		this.att7 = att7;
	}
	public int getAtt8() {
		return att8;
	}
	public void setAtt8(int att8) {
		this.att8 = att8;
	}
	private List<Integer> attList;
	public List<Integer> getAttList() {
		if (this.attList == null) {

			List<Integer> list = new ArrayList<Integer>();
			if (att1 > 0) {
				list.add(att1);
			}
			if (att2 > 0) {
				list.add(att2);
			}
			if (att3 > 0) {
				list.add(att3);
			}
			if (att4 > 0) {
				list.add(att4);
			}
			if (att5 > 0) {
				list.add(att5);
			}
			if (att6 > 0) {
				list.add(att6);
			}
			if (att7 > 0) {
				list.add(att7);
			}
			if (att8 > 0) {
				list.add(att8);
			}
			this.attList = list;
		}
		return this.attList;
	}
	
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getItemId1() {
		return itemId1;
	}
	public void setItemId1(int itemId1) {
		this.itemId1 = itemId1;
	}
	public int getItemExp1() {
		return itemExp1;
	}
	public void setItemExp1(int itemExp1) {
		this.itemExp1 = itemExp1;
	}
	public int getItemId2() {
		return itemId2;
	}
	public void setItemId2(int itemId2) {
		this.itemId2 = itemId2;
	}
	public int getItemExp2() {
		return itemExp2;
	}
	public void setItemExp2(int itemExp2) {
		this.itemExp2 = itemExp2;
	}
	public int getItemId3() {
		return itemId3;
	}
	public void setItemId3(int itemId3) {
		this.itemId3 = itemId3;
	}
	public int getItemExp3() {
		return itemExp3;
	}
	public void setItemExp3(int itemExp3) {
		this.itemExp3 = itemExp3;
	}
	@Override
	public String toString() {
		return "PetLevel [id=" + id + ", lv=" + lv + ", att1=" + att1 + ", att2=" + att2 + ", att3=" + att3 + ", att4="
				+ att4 + ", att5=" + att5 + ", att6=" + att6 + ", att7=" + att7 + ", att8=" + att8 + ", exp=" + exp
				+ ", itemId1=" + itemId1 + ", itemExp1=" + itemExp1 + ", itemId2=" + itemId2 + ", itemExp2=" + itemExp2
				+ ", itemId3=" + itemId3 + ", itemExp3=" + itemExp3 + "]";
	}
}
