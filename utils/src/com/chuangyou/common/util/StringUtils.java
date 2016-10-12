package com.chuangyou.common.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 字符串辅助类
 * </pre>
 */
public class StringUtils {

	/**
	 * 判断字符串是否超过最大长度
	 * 
	 * @param rawStr
	 * @param maxLen
	 * @return
	 */
	public static String verifyMaxLen(String rawStr, int maxLen) {
		if (rawStr == null || rawStr.trim().length() == 0) {
			return rawStr;
		}
		if (rawStr.length() > maxLen) {
			return rawStr.substring(0, maxLen);
		}
		return rawStr;
	}
	
	public static boolean verifyMaxByteLen(String str, int maxLen){
		try {
			if(str.getBytes("UTF-8").length > maxLen){
				return false;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * <pre>
	 * 判断字符串是否为 null 或者 空串
	 * </pre>
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}

	/**
	 * <pre>
	 * 是否为数字类型(负数返回false)
	 * </pre>
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		if (str.matches("\\d*")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * <pre>
	 * 判断指定字符串是否包含空白字符，包括\\s*|\t|\r|\n
	 * </pre>
	 * 
	 * @param str
	 * @return
	 */
	public static boolean containWhitespace(String str) {
		if (str == null || str.isEmpty()) {
			return false;
		}

		char[] data = str.toCharArray();
		for (char i : data) {
			if (Character.isWhitespace(i)) {
				return true;
			}
		}

		return false;
	}
	
	/**
	 * 自定义字符串格式化
	 * 
	 * @param str bd:1,be:0:15,aa:1:4:4,ab:1:4:4:5,ac:1:4:4:1:4:4:5
	 */
	public static Map<String, List<Object>> strToMap(String str) {
		Map<String, List<Object>> dataMap = new HashMap<String, List<Object>>();
		if (str == null || str.equals(""))
			return dataMap;
		String[] arrStr = str.split(",");
		for (String str1 : arrStr) {
			String[] arrStr2 = str1.split(":");
			ArrayList<Object> arr = new ArrayList<Object>();
			for (int i = 0; i < arrStr2.length; i++) {
				if (i == 0 && !dataMap.containsKey(arrStr2[i]))
					dataMap.put(arrStr2[i], arr);
				else
					arr.add(arrStr2[i]);
			}
		}
		return dataMap;
	}
 

	/**
	 * map 转自定义字符串
	 * 
	 * @param map {"ab":[1,2,3,4,5,6,7,8],"ac":[1,2,3,4,5,6,7,8]}
	 */
	public static String mapToStr(Map<String, List<Object>> map) {
		StringBuffer strBuff = new StringBuffer("");
		boolean isRun = false;
		for (String str : map.keySet()) {
			strBuff.append(str);
			strBuff.append(":");
			List<Object> arr = map.get(str);
			for (int i = 0; i < arr.size(); i++) {
				strBuff.append(arr.get(i));
				if (i == arr.size() - 1) {
					strBuff.append(",");
				} else {
					strBuff.append(":");
				}
				isRun = true;
			}
		}
		if (isRun) {
			return strBuff.substring(0, strBuff.length() - 1);
		}
		return strBuff.toString();
	}


}
