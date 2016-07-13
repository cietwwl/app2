/**
 * All rights reserved. This material is confidential and proprietary to 7ROAD SQ team.
 */
package com.chuangyou.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 * 检查是否包含特定字符
 * </pre>
 */
public class MessageFilter {
	private static final String FILTER_REGEX = "\\pP|\\pS|\\pZ";

	private static List<String> regexAdWords;

	public static boolean init() {
		regexAdWords = new ArrayList<String>();
		String path = Config.getPath("language.path");
		path = path.substring(0, path.lastIndexOf("/")) + "/ad.txt";
		try {
			File file = new File(path);
			if (!file.exists()) {
				Log.error("不存在文件 ：" + path);
				return true;
			}
			FileInputStream fis = new FileInputStream(path);
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(isr);
			String lineContent;
			while ((lineContent = bufferedReader.readLine()) != null) {
				if (!StringUtils.isNullOrEmpty(lineContent)) {
					regexAdWords.addAll(Arrays.asList(lineContent.split("\\|")));
				}
			}
			return true;
		} catch (Exception e) {
			Log.error("加载广告过滤正则表达式异常", e);
			regexAdWords.clear();
		}
		return false;
	}

	/**
	 * <pre>
	 * 过滤广告字符
	 * </pre>
	 * 
	 * @param isFilter 是否过滤
	 * @param grade 大于此等级不过滤
	 * @param msg 过滤的目标字符
	 * @return
	 */
	public static boolean isInFilterList(boolean isFilter, int grade, String msg) {
		if (isFilter == false || regexAdWords == null || grade >= 11 || regexAdWords.size() == 0 || StringUtils.isNullOrEmpty(msg)) {
			return false;
		}
		String resultStr = msg.replaceAll(FILTER_REGEX, "");
		resultStr = resultStr.toLowerCase();
		for (String regex : regexAdWords) {
			if (resultStr.contains(regex)) {
				return true;
			}
		}
		return false;
	}
}
