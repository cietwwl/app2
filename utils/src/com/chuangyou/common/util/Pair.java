package com.chuangyou.common.util;

/**
 * key - value形式数据保存对象
 * 
 */
public class Pair {

	private int param1;
	private int param2;
	private int param3;
	private int param4;

	public Pair(int param1, int param2) {
		this.param1 = param1;
		this.param2 = param2;
	}
	
	public Pair(int param1, int param2, int param3) {
		this.param1 = param1;
		this.param2 = param2;
		this.param3 = param3;
	}

	public Pair(int param1, int param2, int param3, int param4) {
		this.param1 = param1;
		this.param2 = param2;
		this.param3 = param3;
		this.param4 = param4;
	}

	public int getParam1() {
		return param1;
	}

	public int getParam2() {
		return param2;
	}

	public int getParam3() {
		return param3;
	}

	public int getParam4() {
		return param4;
	}
	
	public String getParam4Desc() {
		if (param4 == 0) {
			return "00";
		} else {
			return param4 + "";
		}
	}

	public String getParam2Desc() {
		if (param2 == 0) {
			return "00";
		} else {
			return param2 + "";
		}
	}
}
