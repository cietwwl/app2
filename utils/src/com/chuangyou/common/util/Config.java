package com.chuangyou.common.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

	private static Properties	properties	= null;
	private static String		mainPath	= null;

	public static boolean initConfig(String basePath, String path) {
		if (basePath == null) {
			return false;
		}
		if (!basePath.endsWith("/")) {
			basePath = basePath + "/";
		}
		mainPath = basePath;
		if (path == null || path.equals("")) {
			return false;
		}
		if (properties == null) {
			return loadProperties(mainPath + path);
		}
		return true;
	}

	private static boolean loadProperties(String path) {
		try {
			properties = new Properties();
			InputStream inputStream = new BufferedInputStream(new FileInputStream(path));
			properties.load(inputStream);
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 读取值
	 * 
	 * @param key
	 * @return
	 */
	public static String getPath(String key) {
		if (properties == null) {
			return null;
		}
		String path = properties.getProperty(key);
		if (path == null || path.trim().length() == 0) {
			return "";
		}
		if (mainPath != null && mainPath.trim().length() > 0) {
			if (path.startsWith("/") || mainPath.endsWith("/")) {
				path = mainPath + path;
			} else {
				path = mainPath + "/" + path;
			}
		}
		return path;
	}

	/**
	 * 读取值
	 * 
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		if (properties == null) {
			return null;
		}
		return properties.getProperty(key);
	}

	/**
	 * 读取值
	 * 
	 * @param key
	 * @return
	 */
	public static int getInt(String key) {
		if (properties == null) {
			return 0;
		}
		String value = properties.getProperty(key);
		if (SplitUtil.isNumeric(value)) {
			return Integer.parseInt(value);
		}
		return 0;
	}
}
