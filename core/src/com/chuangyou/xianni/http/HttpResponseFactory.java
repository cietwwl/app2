package com.chuangyou.xianni.http;

import java.util.Map;

import com.chuangyou.common.util.Log;

/**
 * 响应工厂
 * 
 */
public abstract class HttpResponseFactory {

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

	static BaseRespone createReshone(String rootPath) {
		return null;
	}

	private static boolean allowedPublicNetwork(String rootPath) {
		return false;
	}
}
