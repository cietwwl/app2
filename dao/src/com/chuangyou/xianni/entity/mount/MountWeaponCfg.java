package com.chuangyou.xianni.entity.mount;

import java.util.ArrayList;
import java.util.List;

public class MountWeaponCfg {

	/** 阶数 */
	private int grade;
	/** 模型 */
	private int model;
	/** 属性1 */
	private int att1;
	/** 属性2 */
	private int att2;
	/** 属性3 */
	private int att3;
	/** 属性4 */
	private int att4;
	/** 属性5 */
	private int att5;
	/** 属性6 */
	private int att6;
	/** 属性7 */
	private int att7;
	/** 属性8 */
	private int att8;
	/** 属性9 */
	private int att9;
	/** 属性10 */
	private int att10;
	
	private List<Integer> attList;
	/** 升阶所需道具id */
	private int upgradeItem;
	/** 升阶所需道具数量 */
	private int upgradeItemNum;
	/** 成功率 */
	private int rate;
	/** 祝福值上限 */
	private int blessMax;
	/** 每次失败时增加的祝福值随机下限 */
	private int failBlessMin;
	/** 每次失败时增加的祝福值随机上限 */
	private int failBlessMax;
	/** 祝福值增加的成功率 */
	private int blessValve;
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getModel() {
		return model;
	}
	public void setModel(int model) {
		this.model = model;
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
	public int getUpgradeItem() {
		return upgradeItem;
	}
	public void setUpgradeItem(int upgradeItem) {
		this.upgradeItem = upgradeItem;
	}
	public int getUpgradeItemNum() {
		return upgradeItemNum;
	}
	public void setUpgradeItemNum(int upgradeItemNum) {
		this.upgradeItemNum = upgradeItemNum;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public int getBlessMax() {
		return blessMax;
	}
	public void setBlessMax(int blessMax) {
		this.blessMax = blessMax;
	}
	public int getFailBlessMin() {
		return failBlessMin;
	}
	public void setFailBlessMin(int failBlessMin) {
		this.failBlessMin = failBlessMin;
	}
	public int getFailBlessMax() {
		return failBlessMax;
	}
	public void setFailBlessMax(int failBlessMax) {
		this.failBlessMax = failBlessMax;
	}
	public int getBlessValve() {
		return blessValve;
	}
	public void setBlessValve(int blessValve) {
		this.blessValve = blessValve;
	}
	
	public void setAttList(){
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
		this.attList = list;
	}
	public List<Integer> getAttList() {
		return this.attList;
	}
	@Override
	public String toString() {
		return "MountWeapon [grade=" + grade + ", model=" + model + ", att1=" + att1 + ", att2=" + att2 + ", att3="
				+ att3 + ", att4=" + att4 + ", att5=" + att5 + ", att6=" + att6 + ", att7=" + att7 + ", att8=" + att8
				+ ", att9=" + att9 + ", att10=" + att10 + ", attList=" + attList + ", upgradeItem=" + upgradeItem
				+ ", upgradeItemNum=" + upgradeItemNum + ", rate=" + rate + ", blessMax=" + blessMax + ", failBlessMin="
				+ failBlessMin + ", failBlessMax=" + failBlessMax + ", blessValve=" + blessValve + "]";
	}
}
