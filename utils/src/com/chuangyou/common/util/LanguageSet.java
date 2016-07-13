package com.chuangyou.common.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 语言包管理
 * 
 * @author melody.liu
 * 
 */
public final class LanguageSet {

	/**
	 * 缓存命令对象
	 **/
	private static final Map<String, String> pLanguageCache = new HashMap<String, String>();

	private LanguageSet() {
	}

	/**
	 * 加载语言包
	 * 
	 * @param path
	 * @return
	 */
	public static boolean loadLanguage(String path) {
		if (path == null || path.equals("")) {
			return false;
		}
		try {
			FileInputStream fis = new FileInputStream(path);
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(isr);
			String lineContent;
			int sum = 0;
			while ((lineContent = bufferedReader.readLine()) != null) {
				int index = lineContent.indexOf("=");
				if (index > -1) {
					String key = lineContent.substring(0, index);
					String value = lineContent.substring(index + 1);
					sum = sum + value.length();
					pLanguageCache.put(key, value);
				}
			}
			System.out.println(sum);
			bufferedReader.close();
			isr.close();
			fis.close();
			return true;
		} catch (FileNotFoundException e) {
			Log.error("语言包资源文件不存在", e);
			return false;
		} catch (IOException e) {
			Log.error("语言包资源文件加载失败", e);
			return false;
		}
	}

	/**
	 * 重新加载语言包
	 * 
	 * @return
	 */
	public static boolean ReloadLanguageResource() {
		pLanguageCache.clear();
		boolean result = loadLanguage(Config.getPath("language.path"));
		return result;
	}

	/**
	 * 从语言包中获取对应的值(有参数)
	 * 
	 * @param key
	 * @param paras
	 * @return
	 */
	public static String getResource(String key, Object... paras) {
		// ReloadLanguageResource();
		if (!pLanguageCache.containsKey(key)) {
			Log.error("key：" + key + "在语言包中不存在");
			return "";
		}
		try {
			String initValue = pLanguageCache.get(key);
			String msg = MessageFormat.format(initValue, paras);
			return msg;
		} catch (Exception e) {
			// TODO: handle exception
			Log.error("获取资源出错:" + e);
			return "";
		}
	}

	/**
	 * 从语言包中获取对应的值(无参数)
	 * 
	 * @param key
	 * @return
	 */
	public static String getResource(String key) {
		// ReloadLanguageResource();
		if (!pLanguageCache.containsKey(key)) {
			Log.error("key：" + key + "在语言包中不存在");
			return "";
		}
		try {
			// return pLanguageCache.get(key);
			String msg = pLanguageCache.get(key);
			return msg;
		} catch (Exception e) {
			Log.error("获取资源出错:" + e);
			return "";
		}
	}
}
