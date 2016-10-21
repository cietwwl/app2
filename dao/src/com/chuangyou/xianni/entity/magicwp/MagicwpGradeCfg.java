package com.chuangyou.xianni.entity.magicwp;

import java.util.HashMap;
import java.util.Map;

public class MagicwpGradeCfg {

	/** 前5位为法宝id，后3位为阶级 */
	private int id;
	/** 阶级 */
	private int jieji;
	/** 需求法宝等级 */
	private int needLv;
	/** 进阶道具 */
	private int jinjieItem;
	/** 进阶道具数量 */
	private int itemNum;
	
	private int att1;
	private int att2;
	private int att3;
	private int att4;
	private int att5;
	private int att6;
	private int att7;
	private int att8;
	
	private Map<Integer, Integer> maxAttMap;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getJieji() {
		return jieji;
	}
	public void setJieji(int jieji) {
		this.jieji = jieji;
	}
	public int getNeedLv() {
		return needLv;
	}
	public void setNeedLv(int needLv) {
		this.needLv = needLv;
	}
	public int getJinjieItem() {
		return jinjieItem;
	}
	public void setJinjieItem(int jinjieItem) {
		this.jinjieItem = jinjieItem;
	}
	public int getItemNum() {
		return itemNum;
	}
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
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
	
	public void setMaxAttMap(){
		this.maxAttMap = new HashMap<Integer, Integer>();
		putAttNum(maxAttMap, getAtt1());
		putAttNum(maxAttMap, getAtt2());
		putAttNum(maxAttMap, getAtt3());
		putAttNum(maxAttMap, getAtt4());
		putAttNum(maxAttMap, getAtt5());
		putAttNum(maxAttMap, getAtt6());
		putAttNum(maxAttMap, getAtt7());
		putAttNum(maxAttMap, getAtt8());
	}
	
	public Map<Integer, Integer> getMaxAttMap(){
		return this.maxAttMap;
	}
	
	private void putAttNum(Map<Integer, Integer> attMap, int attNum){
		if(attNum > 0){
			int attType = (int)(attNum/1000000);
			int attValue = attNum%1000000;
			attMap.put(attType, attValue);
		}
	}
	
	@Override
	public String toString() {
		return "MagicwpGrade [id=" + id + ", jieji=" + jieji + ", needLv=" + needLv + ", jinjieItem=" + jinjieItem
				+ ", itemNum=" + itemNum + ", att1=" + att1 + ", att2=" + att2 + ", att3=" + att3 + ", att4=" + att4
				+ ", att5=" + att5 + ", att6=" + att6 + ", att7=" + att7 + ", att8=" + att8 + "]";
	}
}
