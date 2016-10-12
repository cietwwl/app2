package com.chuangyou.xianni.entity.reward;

public class RewardTemplate {
	private int		type;			// 奖励类型
	private String	name;			// 名称
	private int		param1;			// 获取参数
	private int		param2;			// 获取参数

	private int		itemTempId1;	// 奖品ID
	private int		count1;			// 奖品数量

	private int		itemTempId2;
	private int		count2;

	private int		itemTempId3;
	private int		count3;

	private int		itemTempId4;
	private int		count4;

	private int		itemTempId5;
	private int		count5;

	private String	extendParam;	// 扩展字段，备用

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getParam1() {
		return param1;
	}

	public void setParam1(int param1) {
		this.param1 = param1;
	}

	public int getParam2() {
		return param2;
	}

	public void setParam2(int param2) {
		this.param2 = param2;
	}

	public int getItemTempId1() {
		return itemTempId1;
	}

	public void setItemTempId1(int itemTempId1) {
		this.itemTempId1 = itemTempId1;
	}

	public int getCount1() {
		return count1;
	}

	public void setCount1(int count1) {
		this.count1 = count1;
	}

	public int getItemTempId2() {
		return itemTempId2;
	}

	public void setItemTempId2(int itemTempId2) {
		this.itemTempId2 = itemTempId2;
	}

	public int getCount2() {
		return count2;
	}

	public void setCount2(int count2) {
		this.count2 = count2;
	}

	public int getItemTempId3() {
		return itemTempId3;
	}

	public void setItemTempId3(int itemTempId3) {
		this.itemTempId3 = itemTempId3;
	}

	public int getCount3() {
		return count3;
	}

	public void setCount3(int count3) {
		this.count3 = count3;
	}

	public int getItemTempId4() {
		return itemTempId4;
	}

	public void setItemTempId4(int itemTempId4) {
		this.itemTempId4 = itemTempId4;
	}

	public int getCount4() {
		return count4;
	}

	public void setCount4(int count4) {
		this.count4 = count4;
	}

	public int getItemTempId5() {
		return itemTempId5;
	}

	public void setItemTempId5(int itemTempId5) {
		this.itemTempId5 = itemTempId5;
	}

	public int getCount5() {
		return count5;
	}

	public void setCount5(int count5) {
		this.count5 = count5;
	}

	public String getExtendParam() {
		return extendParam;
	}

	public void setExtendParam(String extendParam) {
		this.extendParam = extendParam;
	}

}
