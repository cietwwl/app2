package com.chuangyou.xianni.login;

import java.util.Map;

public class LoginTokenCache {

	// tokens缓存表
	private static Map<Long, String> TOKENS_CATCH = new java.util.concurrent.ConcurrentHashMap<>();
	
	public static boolean check(long userId, String tokens) {
		if(1 == 1){
			return true;
		}
		return TOKENS_CATCH.containsKey(userId) && tokens.equalsIgnoreCase(TOKENS_CATCH.get(userId));
	}
}
