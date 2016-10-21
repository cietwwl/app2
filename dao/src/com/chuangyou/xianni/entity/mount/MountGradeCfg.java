package com.chuangyou.xianni.entity.mount;

import java.util.ArrayList;
import java.util.List;

public class MountGradeCfg {

	/** 坐骑ID */
	private int id;
	/** 坐骑名字 */
	private String name;
	/** 阶数 */
	private int grade;
	/** 阶数对应的名字颜色 */
	private int color;
	/** 属性1 */
	public int att1;
	/** 属性2 */
	public int att2;
	/** 属性3 */
	public int att3;
	/** 属性4 */
	public int att4;
	/** 属性5 */
	public int att5;
	/** 属性6 */
	public int att6;
	/** 属性7 */
	public int att7;
	/** 属性8 */
	public int att8;
	/** 属性9 */
	public int att9;
	/** 属性10 */
	public int att10;
	/** 获取途径 */
	private String getMode;
	/** 升阶所需道具ID */
	private int upgradeItem;
	/** 升阶所需物品数量 */
	private int upgradeItemNum;
	/** 成功率 */
	private int rate;
	/** 该级别祝福值上限 */
	private int blessMax;
	/** 失败增加的祝福值随机下限 */
	private int failBlessMin;
	/** 失败增加的祝福值随机上限 */
	private int failBlessMax;
	/** 祝福值阀值，低于阀值时必定失败 */
	private int blessValve;
	
	
	///////////////////////程序逻辑需要，第一次使用时初始化///////////////////////////
	/** 属性列表 */
	private List<Integer> attList;
	/** 坐骑移动速度加成 */
	private int speed = 0;
	private int speedType;
	private boolean speedInit = false;
	
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
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
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
	public String getGetMode() {
		return getMode;
	}
	public void setGetMode(String getMode) {
		this.getMode = getMode;
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
	
	public int getSpeed(int type){
		if(speedInit == false || speedType != type){
			speedType = type;
			
			List<Integer> attList = getAttList();
			for(int attNum: attList){
				int attType = (int) (attNum / 1000000);
				if(attType == speedType){
					int attValue = attNum % 1000000;
					speed = attValue;
					break;
				}
			}
			
			speedInit = true;
		}
		return speed;
	}
	
	@Override
	public String toString() {
		return "MountGrade [id=" + id + ", name=" + name + ", grade=" + grade + ", color=" + color + ", att1=" + att1
				+ ", att2=" + att2 + ", att3=" + att3 + ", att4=" + att4 + ", att5=" + att5 + ", att6=" + att6
				+ ", att7=" + att7 + ", att8=" + att8 + ", att9=" + att9 + ", att10=" + att10 + ", attList=" + attList
				+ ", getMode=" + getMode + ", upgradeItem=" + upgradeItem + ", upgradeItemNum=" + upgradeItemNum
				+ ", rate=" + rate + ", blessMax=" + blessMax + ", failBlessMin=" + failBlessMin + ", failBlessMax="
				+ failBlessMax + ", blessValve=" + blessValve + "]";
	}
}
