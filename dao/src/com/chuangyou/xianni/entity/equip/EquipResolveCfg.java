package com.chuangyou.xianni.entity.equip;

import java.util.HashMap;
import java.util.Map;

public class EquipResolveCfg {
	/**
	 * 装备ID
	 */
	private int equipId;
	
	/**
	 * 分解材料1
	 */
	private int item1;
	
	/**
	 * 分解材料1数量
	 */
	private int num1;
	
	/**
	 * 分解材料2
	 */
	private int item2;
	
	/**
	 * 分解材料2数量
	 */
	private int num2;
	
	/**
	 * 分解材料3
	 */
	private int item3;
	
	/**
	 * 分解材料3数量
	 */
	private int num3;
	
	/**
	 * 分解材料4
	 */
	private int item4;
	
	/**
	 * 分解材料4数量
	 */
	private int num4;
	
	/**
	 * 分解材料5
	 */
	private int item5;
	
	/**
	 * 分解材料5数量
	 */
	private int num5;
	
	/**
	 * 分解材料
	 */
	private Map<Integer, Integer> items = null;
	
	/**
	 * 分解获得经验
	 */
	private long exp;

	public int getEquipId() {
		return equipId;
	}

	public void setEquipId(int equipId) {
		this.equipId = equipId;
	}

	public int getItem1() {
		return item1;
	}

	public void setItem1(int item1) {
		this.item1 = item1;
	}

	public int getNum1() {
		return num1;
	}

	public void setNum1(int num1) {
		this.num1 = num1;
	}

	public int getItem2() {
		return item2;
	}

	public void setItem2(int item2) {
		this.item2 = item2;
	}

	public int getNum2() {
		return num2;
	}

	public void setNum2(int num2) {
		this.num2 = num2;
	}

	public int getItem3() {
		return item3;
	}

	public void setItem3(int item3) {
		this.item3 = item3;
	}

	public int getNum3() {
		return num3;
	}

	public void setNum3(int num3) {
		this.num3 = num3;
	}

	public int getItem4() {
		return item4;
	}

	public void setItem4(int item4) {
		this.item4 = item4;
	}

	public int getNum4() {
		return num4;
	}

	public void setNum4(int num4) {
		this.num4 = num4;
	}

	public int getItem5() {
		return item5;
	}

	public void setItem5(int item5) {
		this.item5 = item5;
	}

	public int getNum5() {
		return num5;
	}

	public void setNum5(int num5) {
		this.num5 = num5;
	}
	
	public Map<Integer, Integer> getItems(){
		if(this.items == null){
			this.items = new HashMap<>();
			if(this.item1 > 0){
				this.items.put(item1, num1);
			}
			if(this.item2 > 0){
				this.items.put(item2, num2);
			}
			if(this.item3 > 0){
				this.items.put(item3, num3);
			}
			if(this.item4 > 0){
				this.items.put(item4, num4);
			}
			if(this.item5 > 0){
				this.items.put(item5, num5);
			}
		}
		return this.items;
	}

	public long getExp() {
		return exp;
	}

	public void setExp(long exp) {
		this.exp = exp;
	}

}
