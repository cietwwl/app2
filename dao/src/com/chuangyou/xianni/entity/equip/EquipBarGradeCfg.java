package com.chuangyou.xianni.entity.equip;

import java.util.HashMap;
import java.util.Map;

public class EquipBarGradeCfg {

	/**
	 * 栏位索引
	 */
	private short position;
	
	/**
	 * 加持等级
	 */
	private int level;
	
	/**
	 * 升级材料
	 */
	private int upgradeItem1;
	
	/**
	 * 材料数量
	 */
	private int upgradeItemNum1;
	
	/**
	 * 升级材料
	 */
	private int upgradeItem2;
	
	/**
	 * 材料数量
	 */
	private int upgradeItemNum2;
	
	/**
	 * 升级材料
	 */
	private int upgradeItem3;
	
	/**
	 * 材料数量
	 */
	private int upgradeItemNum3;
	
	/**
	 * 进阶机率
	 */
	private int rate;
	
	/**
	 * 祝福值上限
	 */
	private int blessMax;
	
	/**
	 * 失败增加的祝福值下限
	 */
	private int failBlessMin;
	
	/**
	 * 失败增加的祝福值上限
	 */
	private int failBlessMax;
	
	/**
	 * 当前等级祝福值小于该值时，加持必定失败
	 */
	private int blessValve;
	
	/**
	 * 增加装备基础属性的万分比
	 */
	private int addProperty;
	
	/**
	 * 加持需要的材料
	 */
	private Map<Integer, Integer> needItemMap = null;

	public short getPosition() {
		return position;
	}

	public void setPosition(short position) {
		this.position = position;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getUpgradeItem1() {
		return upgradeItem1;
	}

	public void setUpgradeItem1(int upgradeItem1) {
		this.upgradeItem1 = upgradeItem1;
	}

	public int getUpgradeItemNum1() {
		return upgradeItemNum1;
	}

	public void setUpgradeItemNum1(int upgradeItemNum1) {
		this.upgradeItemNum1 = upgradeItemNum1;
	}

	public int getUpgradeItem2() {
		return upgradeItem2;
	}

	public void setUpgradeItem2(int upgradeItem2) {
		this.upgradeItem2 = upgradeItem2;
	}

	public int getUpgradeItemNum2() {
		return upgradeItemNum2;
	}

	public void setUpgradeItemNum2(int upgradeItemNum2) {
		this.upgradeItemNum2 = upgradeItemNum2;
	}

	public int getUpgradeItem3() {
		return upgradeItem3;
	}

	public void setUpgradeItem3(int upgradeItem3) {
		this.upgradeItem3 = upgradeItem3;
	}

	public int getUpgradeItemNum3() {
		return upgradeItemNum3;
	}

	public void setUpgradeItemNum3(int upgradeItemNum3) {
		this.upgradeItemNum3 = upgradeItemNum3;
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

	public int getAddProperty() {
		return addProperty;
	}

	public void setAddProperty(int addProperty) {
		this.addProperty = addProperty;
	}
	
	public void setNeedItems(){
		this.needItemMap = new HashMap<>();
		if(upgradeItem1 > 0){
			this.needItemMap.put(this.upgradeItem1, this.upgradeItemNum1);
		}
		if(upgradeItem2 > 0){
			this.needItemMap.put(this.upgradeItem2, this.upgradeItemNum2);
		}
		if(upgradeItem3 > 0){
			this.needItemMap.put(this.upgradeItem3, this.upgradeItemNum3);
		}
	}
	
	public Map<Integer, Integer> getNeedItems(){
		return this.needItemMap;
	}
}
