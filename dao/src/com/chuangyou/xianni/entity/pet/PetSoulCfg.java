package com.chuangyou.xianni.entity.pet;

import java.util.ArrayList;
import java.util.List;

public class PetSoulCfg {

	/** 炼魂等级 */
	private int lv;
	/** 消耗道具 */
	private int needItem;
	/** 普通经验 */
	private int normalExp;
	/** 暴击经验 */
	private int criticalExp;
	/** 暴击几率 */
	private short criticalPer;
	/** 炼魂所需经验 */
	private int exp;
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
	/** 称号名 */
	private String name;
	/** 称号属性 */
	private int att9;
	/** 称号属性 */
	private int att10;
	/** 称号属性 */
	private int att11;
	public int getLv() {
		return lv;
	}
	public void setLv(int lv) {
		this.lv = lv;
	}
	public int getNeedItem() {
		return needItem;
	}
	public void setNeedItem(int needItem) {
		this.needItem = needItem;
	}
	public int getNormalExp() {
		return normalExp;
	}
	public void setNormalExp(int normalExp) {
		this.normalExp = normalExp;
	}
	public int getCriticalExp() {
		return criticalExp;
	}
	public void setCriticalExp(int criticalExp) {
		this.criticalExp = criticalExp;
	}
	public short getCriticalPer() {
		return criticalPer;
	}
	public void setCriticalPer(short criticalPer) {
		this.criticalPer = criticalPer;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAtt9() {
		return att9;
	}
	public void setAtt9(int att9) {
		this.att9 = att9;
	}
	public int getAtt10() {
		return att10;
	}
	public void setAtt10(int att10) {
		this.att10 = att10;
	}
	public int getAtt11() {
		return att11;
	}
	public void setAtt11(int att11) {
		this.att11 = att11;
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
			if (att9 > 0) {
				list.add(att9);
			}
			if (att10 > 0) {
				list.add(att10);
			}
			if(att11 > 0){
				list.add(att11);
			}
			this.attList = list;
		}
		return this.attList;
	}
	
	@Override
	public String toString() {
		return "PetSoul [lv=" + lv + ", needItem=" + needItem + ", normalExp=" + normalExp + ", criticalExp="
				+ criticalExp + ", criticalPer=" + criticalPer + ", exp=" + exp + ", att1=" + att1 + ", att2=" + att2
				+ ", att3=" + att3 + ", att4=" + att4 + ", att5=" + att5 + ", att6=" + att6 + ", att7=" + att7
				+ ", att8=" + att8 + ", name=" + name + ", att9=" + att9 + ", att10=" + att10 + ", att11=" + att11
				+ "]";
	}
}
