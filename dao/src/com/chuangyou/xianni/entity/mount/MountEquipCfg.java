package com.chuangyou.xianni.entity.mount;

import java.util.ArrayList;
import java.util.List;

public class MountEquipCfg {

	/** 装备位置ID */
	private int id;
	/** 等级 */
	private int level;
	/** 升级所需道具ID */
	private int upLevItem;
	/** 升级所需道具数量 */
	private int upLevItemNum;
	/** 升级成功率 */
	private int rate;
	/** 属性1 */
	private int att1;
	/** 属性2 */
	private int att2;
	/** 属性3 */
	private int att3;
	
	private List<Integer> attList;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getUpLevItem() {
		return upLevItem;
	}
	public void setUpLevItem(int upLevItem) {
		this.upLevItem = upLevItem;
	}
	public int getUpLevItemNum() {
		return upLevItemNum;
	}
	public void setUpLevItemNum(int upLevItemNum) {
		this.upLevItemNum = upLevItemNum;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
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
		this.attList = list;
	}
	
	public List<Integer> getAttList() {
		return this.attList;
	}
	@Override
	public String toString() {
		return "MountEquip [id=" + id + ", level=" + level + ", upLevItem=" + upLevItem + ", upLevItemNum="
				+ upLevItemNum + ", rate=" + rate + ", att1=" + att1 + ", att2=" + att2 + ", att3=" + att3 + "]";
	}
}
