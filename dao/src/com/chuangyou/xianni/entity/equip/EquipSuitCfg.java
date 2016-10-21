package com.chuangyou.xianni.entity.equip;

import java.util.HashMap;
import java.util.Map;

public class EquipSuitCfg {

	/**
	 * 套装ID
	 */
	private int id;
	
	/**
	 * 套件1
	 */
	private int stone1;
	
	/**
	 * 套件2
	 */
	private int stone2;
	
	/**
	 * 套件3
	 */
	private int stone3;
	
	/**
	 * 套件4
	 */
	private int stone4;
	
	/**
	 * 套件5
	 */
	private int stone5;
	
	/**
	 * 套件6
	 */
	private int stone6;
	
	/**
	 * 套件7
	 */
	private int stone7;
	
	/**
	 * 套件8
	 */
	private int stone8;
	
	/**
	 * 1件激活属性
	 */
	private int att1;
	
	/**
	 * 2件激活属性
	 */
	private int att2;
	
	/**
	 * 3件激活属性
	 */
	private int att3;
	
	/**
	 * 4件激活属性
	 */
	private int att4;
	
	/**
	 * 5件激活属性
	 */
	private int att5;
	
	/**
	 * 6件激活属性
	 */
	private int att6;
	
	/**
	 * 7件激活属性
	 */
	private int att7;
	
	/**
	 * 8件激活属性
	 */
	private int att8;
	
	/**
	 * 分解物品
	 */
	private int resolveItem;
	
	/**
	 * 分解数量
	 */
	private int resolveNum;
	
	private Map<Integer, Integer> attMap = null;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStone1() {
		return stone1;
	}

	public void setStone1(int stone1) {
		this.stone1 = stone1;
	}

	public int getStone2() {
		return stone2;
	}

	public void setStone2(int stone2) {
		this.stone2 = stone2;
	}

	public int getStone3() {
		return stone3;
	}

	public void setStone3(int stone3) {
		this.stone3 = stone3;
	}

	public int getStone4() {
		return stone4;
	}

	public void setStone4(int stone4) {
		this.stone4 = stone4;
	}

	public int getStone5() {
		return stone5;
	}

	public void setStone5(int stone5) {
		this.stone5 = stone5;
	}

	public int getStone6() {
		return stone6;
	}

	public void setStone6(int stone6) {
		this.stone6 = stone6;
	}

	public int getStone7() {
		return stone7;
	}

	public void setStone7(int stone7) {
		this.stone7 = stone7;
	}

	public int getStone8() {
		return stone8;
	}

	public void setStone8(int stone8) {
		this.stone8 = stone8;
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

	public int getResolveItem() {
		return resolveItem;
	}

	public void setResolveItem(int resolveItem) {
		this.resolveItem = resolveItem;
	}

	public int getResolveNum() {
		return resolveNum;
	}

	public void setResolveNum(int resolveNum) {
		this.resolveNum = resolveNum;
	}
	
	public void setAttMap(){
		attMap = new HashMap<>();
		if(this.att1 > 0){
			attMap.put(1, att1);
		}
		if(this.att2 > 0){
			attMap.put(2, att2);
		}
		if(this.att3 > 0){
			attMap.put(3, att3);
		}
		if(this.att4 > 0){
			attMap.put(4, att4);
		}
		if(this.att5 > 0){
			attMap.put(5, att5);
		}
		if(this.att6 > 0){
			attMap.put(6, att6);
		}
		if(this.att7 > 0){
			attMap.put(7, att7);
		}
		if(this.att8 > 0){
			attMap.put(8, att8);
		}
	}
	
	public Map<Integer, Integer> getAttMap(){
		return this.attMap;
	}

}
