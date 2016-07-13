package com.chuangyou.common.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public final class SmsConifg {
	/**
	 * 缓存对象
	 **/
	private static final Map<String, String> pSmsConfig = new HashMap<String, String>();

	private SmsConifg() {
	}

	/**
	 * 加载
	 * 
	 * @param path
	 * @return
	 */
	public static boolean loadSms(String path) {
		if (path == null || path.equals("")) {
			return false;
		}
		try {
			FileInputStream fis = new FileInputStream(path);
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(isr);
			String lineContent;
			while ((lineContent = bufferedReader.readLine()) != null) {
				int index = lineContent.indexOf("=");
				if (index > -1) {
					String key = lineContent.substring(0, index);
					String value = lineContent.substring(index + 1);
					if (!pSmsConfig.containsKey(key)) {
						pSmsConfig.put(key, value);
					}
				}
			}
			bufferedReader.close();
			isr.close();
			fis.close();
			return true;
		} catch (FileNotFoundException e) {
			Log.error("代理商游戏名称配置文件加载错误", e);
			return false;
		} catch (IOException e) {
			Log.error("代理商游戏名称配置文件加载错误", e);
			return false;
		}
	}


	/**
	 * 从语言包中获取对应的值(无参数)
	 * 
	 * @param key
	 * @return
	 */
	public static String getResource(String key) {
		if (!pSmsConfig.containsKey(key)) {
			Log.error("key：" + key + "在配置中不存在");
			return "";
		}
		try {
			return pSmsConfig.get(key);
		} catch (Exception e) {
			Log.error("获取配置出错:" + e);
			return "";
		}
	}
}
