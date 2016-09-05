package com.chuangyou.xianni.entity.soul;

import java.util.ArrayList;
import java.util.List;

public class CardComboConfig {
	
	/**
	 * 上阵激活
	 */
	public static final int TYPE_UP = 1;
	
	/**
	 * 获得激活（ 只要有一张卡上阵就可以啦）
	 */
	public static final int TYPE_GET = 2;
	
	
	private int id;
	private String name;
	private int type;
	private int effectAttr1;
	private int effectAttr2;
	private int mate1;
	private int mate2;
	private int mate3;
	private int mate4;
	
	private List<Integer> mateList = new ArrayList<>();
	private List<Integer> effectList = new ArrayList<>();
	
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getEffectAttr1() {
		return effectAttr1;
	}
	public void setEffectAttr1(int effectAttr1) {
		this.effectAttr1 = effectAttr1;
	}
	public int getEffectAttr2() {
		return effectAttr2;
	}
	public void setEffectAttr2(int effectAttr2) {
		this.effectAttr2 = effectAttr2;
	}
	public int getMate1() {
		return mate1;
	}
	public void setMate1(int mate1) {
		this.mate1 = mate1;
	}
	public int getMate2() {
		return mate2;
	}
	public void setMate2(int mate2) {
		this.mate2 = mate2;
	}
	public int getMate3() {
		return mate3;
	}
	public void setMate3(int mate3) {
		this.mate3 = mate3;
	}
	public int getMate4() {
		return mate4;
	}
	public void setMate4(int mate4) {
		this.mate4 = mate4;
	}
	public List<Integer> getMateList() {
		return mateList;
	}
	public void setMateList(List<Integer> mateList) {
		this.mateList = mateList;
	}
	public List<Integer> getEffectList() {
		return effectList;
	}
	public void setEffectList(List<Integer> effectList) {
		this.effectList = effectList;
	}
	
}
