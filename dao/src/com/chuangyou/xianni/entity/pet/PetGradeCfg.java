package com.chuangyou.xianni.entity.pet;

import java.util.ArrayList;
import java.util.List;

public class PetGradeCfg {

	/** 前5位为宠物id，后3位为阶级 */
	private int id;
	/** 阶级 */
	private int grade;
	/** 当前阶属性 */
	private int att1;
	/** 当前阶属性 */
	private int att2;
	/** 当前阶属性 */
	private int att3;
	/** 当前阶属性 */
	private int att4;
	/** 当前阶属性 */
	private int att5;
	/** 当前阶属性 */
	private int att6;
	/** 当前阶属性 */
	private int att7;
	/** 当前阶属性 */
	private int att8;
	/** 进阶消耗道具 */
	private int upgradeItem;
	/** 进阶消耗道具数量 */
	private int upgradeItemNum;
	/** 该级别的成功率，为万分比 */
	private short rate;
	/** 该级别对应的祝福值上限 */
	private int blessMax;
	/** 该级别每次加持失败时增加的祝福值下限 */
	private int failBlessMin;
	/** 该级别每次加持失败时增加的祝福值上限 */
	private int failBlessMax;
	/** 祝福值阀值，低于阀值时必定失败 */
	private int blessValve;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
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
		this.attList = list;
	}
	public List<Integer> getAttList() {
		return this.attList;
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
	public short getRate() {
		return rate;
	}
	public void setRate(short rate) {
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
	@Override
	public String toString() {
		return "PetGrade [id=" + id + ", grade=" + grade + ", att1=" + att1 + ", att2=" + att2 + ", att3=" + att3
				+ ", att4=" + att4 + ", att5=" + att5 + ", att6=" + att6 + ", att7=" + att7 + ", att8=" + att8
				+ ", upgradeItem=" + upgradeItem + ", upgradeItemNum=" + upgradeItemNum + ", rate=" + rate
				+ ", blessMax=" + blessMax + ", failBlessMin=" + failBlessMin + ", failBlessMax=" + failBlessMax
				+ ", blessValve=" + blessValve + "]";
	}
}
