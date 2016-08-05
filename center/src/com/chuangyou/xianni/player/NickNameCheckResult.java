package com.chuangyou.xianni.player;

public class NickNameCheckResult {

	/**
	 * 长度超过上限
	 */
	public static final short LENGTH_LIMIT = 1;
	
	/**
	 * 含有非法字符
	 */
	public static final short ILLEGAL_CHARACTER = 2;
	
	/**
	 * 名字已存在
	 */
	public static final short NAME_EXIST = 3;

}
