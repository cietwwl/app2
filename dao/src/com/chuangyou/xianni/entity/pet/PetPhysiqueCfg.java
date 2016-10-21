package com.chuangyou.xianni.entity.pet;

import java.util.ArrayList;
import java.util.List;

public class PetPhysiqueCfg {

	/** 宠物id后3位为炼体等级 */
	private int id;
	/** 炼体等级 */
	private int lv;
	/** 前缀名 */
	private String name;
	/** 星级 */
	private int star;
	/** 消耗道具 */
	private int needItem;
	/** 消耗道具数量 */
	private int needNum;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public int getNeedItem() {
		return needItem;
	}
	public void setNeedItem(int needItem) {
		this.needItem = needItem;
	}
	public int getNeedNum() {
		return needNum;
	}
	public void setNeedNum(int needNum) {
		this.needNum = needNum;
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
	
	@Override
	public String toString() {
		return "PetPhysique [id=" + id + ", lv=" + lv + ", name=" + name + ", star=" + star + ", needItem=" + needItem
				+ ", needNum=" + needNum + ", att1=" + att1 + ", att2=" + att2 + ", att3=" + att3 + ", att4=" + att4
				+ ", att5=" + att5 + ", att6=" + att6 + ", att7=" + att7 + ", att8=" + att8 + "]";
	}
}
