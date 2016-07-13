package com.chuangyou.common.util;

import java.util.UUID;

public class UUIDCreateor {

	public static String genUUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		return str;
	}
	
	public static void main(String[] args) {
		String str = genUUID();
		System.out.println(str);
	}
}
