package com.chuangyou.xianni.http;

import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.common.HttpCommandSet;

/**
 * 响应工厂
 * 
 */
public class CoreHttpResponseFactory extends HttpResponseFactory {
//	/**
//	 * 测试
//	 */
//	private static final String	GET_ONLINE_USER_COUNT	= "test";
//	/**
//	 * 注册账号
//	 */
//	private static final String	REGISTER				= "register";

	public static String getResult(String rootPath, Map<String, String> params, boolean isAdminIp) {
		try {
			if (!isAdminIp && !allowedPublicNetwork(rootPath)) {
				return "visitor Ip error, The address does not allow external access";
			}
			BaseRespone resp = createReshone(rootPath);
			if (resp != null) {
				return resp.getResult(params);
			} else {
				return "error rootPath";
			}
		} catch (Exception e) {
			Log.error("http getResult error ,rootPath:" + rootPath, e);
		}
		return "error";
	}

	public static BaseRespone createReshone(String rootPath) {
		
		return HttpCommandSet.getHttpRespone(rootPath);
////		switch (rootPath) {
////			case GET_ONLINE_USER_COUNT:
////				return new TestResponse();
////			case REGISTER:
////				return new RegisterResponse();
////			 	
////		}
//
//		return null;
	}

	private static boolean allowedPublicNetwork(String rootPath) {
		return false;
	}
}
