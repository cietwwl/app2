package com.chuangyou.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 敏感字过滤工具
 */
public class SensitivewordFilterUtil {
	@SuppressWarnings("rawtypes")
	private Map								sensitiveWordMap	= null;
	private static SensitivewordFilterUtil	filter;
	static {
		filter = new SensitivewordFilterUtil();
	}

	public void reloadSensitiveWord() {
		sensitiveWordMap = new SensitiveWordInit().initKeyWord();
	}

	/**
	 * 构造函数，初始化敏感词库
	 */
	private SensitivewordFilterUtil() {
		sensitiveWordMap = new SensitiveWordInit().initKeyWord();
	}

	public static SensitivewordFilterUtil getIntence() {
		return filter;
	}

	/**
	 * 获取文字中的敏感词
	 */
	private Set<String> getSensitiveWord(String txt) {
		Set<String> sensitiveWordList = new HashSet<String>();

		for (int i = 0; i < txt.length(); i++) {
			int length = CheckSensitiveWord(txt, i); // 判断是否包含敏感字符
			if (length > 0) { // 存在,加入list中
				sensitiveWordList.add(txt.substring(i, i + length));
				i = i + length - 1; // 减1的原因，是因为for会自增
			}
		}

		return sensitiveWordList;
	}

	/**
	 * 替换敏感字字符
	 */
	public String replaceSensitiveWord(String txt) {
		String resultTxt = txt;
		try {
			Set<String> set = getSensitiveWord(txt); // 获取所有的敏感词
			Iterator<String> iterator = set.iterator();
			String word = null;
			String replaceString = null;
			while (iterator.hasNext()) {
				word = iterator.next();
				replaceString = getReplaceChars("*", word.length());
				resultTxt = resultTxt.replaceAll(word, replaceString);
			}
		} catch (Exception e) {
			Log.error("txt:" + txt, e);
		}
		return resultTxt;
	}

	/**
	 * 获取替换字符串
	 * 
	 * @param replaceChar
	 * @param length
	 */
	private String getReplaceChars(String replaceChar, int length) {
		String resultReplace = replaceChar;
		for (int i = 1; i < length; i++) {
			resultReplace += replaceChar;
		}

		return resultReplace;
	}

	/**
	 * 检查文字中是否包含敏感字符，检查规则如下：
	 */
	@SuppressWarnings({ "rawtypes" })
	private int CheckSensitiveWord(String txt, int beginIndex) {
		boolean flag = false; // 敏感词结束标识位：用于敏感词只有1位的情况
		int matchFlag = 0; // 匹配标识数默认为0
		char word = 0;
		Map nowMap = sensitiveWordMap;
		for (int i = beginIndex; i < txt.length(); i++) {
			word = txt.charAt(i);
			nowMap = (Map) nowMap.get(word); // 获取指定key
			if (nowMap != null) { // 存在，则判断是否为最后一个
				matchFlag++; // 找到相应key，匹配标识+1
				if ("1".equals(nowMap.get("isEnd"))) { // 如果为最后一个匹配规则,结束循环，返回匹配标识数
					flag = true; // 结束标志位为true
					break;
				}
			} else { // 不存在，直接返回
				break;
			}
		}
		if (matchFlag < 2 || !flag) { // 长度必须大于等于1，为词
			matchFlag = 0;
		}
		return matchFlag;
	}

	public static void main(String[] args) {
		SensitivewordFilterUtil filter = SensitivewordFilterUtil.getIntence();
		System.out.println("敏感词的数量：" + filter.sensitiveWordMap.size());
		String string = "电话电话电话太多的伤感情怀也许只局限于饲养基地 荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释自杀指南怀那些自己经历的伤感。" + "然后法轮功 我们的扮演的角色就是跟电话随着主人公的喜红电话客联盟 怒哀乐而过于牵强的把自己的情感也附加于银幕情节中，然后感动就流泪，"
				+ "难过就躺在某一个人的怀里尽情的阐述心扉或者手机卡复制器一个人一杯红酒一部电影在夜三139级片 深人静的晚上，关上电静静的发呆着。";

		String string2 = "太多的伤感情怀也许只局限于饲养基地 荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释自杀指南怀那些自己经历的伤感。" + "然后法轮功 我们的扮演的角色就是跟电话随着主人公的喜红电话客联盟 怒哀乐而过于牵强的把自己的情感也附加于银幕情节中，然后感动就流泪，"
				+ "难过就躺在某一个人的怀里尽情的阐述心扉或者手机卡复制器一个人一杯红酒一部电影在夜三139级片 深人静的晚上，关上电静静的发呆着。";
		System.out.println("待检测语句字数：" + string.length());
		long beginTime = System.currentTimeMillis();
		// Set<String> set = filter.getSensitiveWord(string, 2);
		for (int i = 0; i <= 100000; i++) {
			string = filter.replaceSensitiveWord(string);
		}
		long endTime = System.currentTimeMillis();
		// System.out.println("语句中包含敏感词的个数为：" + set.size() + "。包含：" + set);
		System.out.println("总共消耗时间为：" + (endTime - beginTime));

//		Set<String> setq = SensitiveWordInit.readSensitiveWordFile();
//		long beginTime1 = System.currentTimeMillis();
//		for (int i = 0; i <= 100000; i++) {
//			for (String k : setq) {
//				if (string2.indexOf(k) != -1) {
//					string2 = string2.replaceAll(k, "**");
//				}
//			}
//		}
//		System.out.println("总共消耗时间为：" + (System.currentTimeMillis() - beginTime1));
		System.err.println(string2);
		System.err.println(string);
	}

	public static class SensitiveWordInit {

		@SuppressWarnings("rawtypes")
		public HashMap sensitiveWordMap;

		public SensitiveWordInit() {
			super();
		}

		@SuppressWarnings("rawtypes")
		public Map initKeyWord() {
			try {
				// 读取敏感词库
				Set<String> keyWordSet = readSensitiveWordFile();
				// 将敏感词库加入到HashMap中
				addSensitiveWordToHashMap(keyWordSet);
				// spring获取application，然后application.setAttribute("sensitiveWordMap",sensitiveWordMap);
			} catch (Exception e) {
				Log.error("初始化敏感字库失败", e);
			}
			return sensitiveWordMap;
		}

		/**
		 * 读取敏感词库，将敏感词放入HashSet中，构建一个DFA算法模型：<br>
		 * 中 = { isEnd = 0 国 = {<br>
		 * isEnd = 1 人 = {isEnd = 0 民 = {isEnd = 1} } 男 = { isEnd = 0 人 = {
		 * isEnd = 1 } } } } 五 = { isEnd = 0 星 = { isEnd = 0 红 = { isEnd = 0 旗 =
		 * { isEnd = 1 } } } }
		 * 
		 * @param keyWordSet
		 *            敏感词库
		 */
		@SuppressWarnings({ "rawtypes", "unchecked" })
		private void addSensitiveWordToHashMap(Set<String> keyWordSet) {
			sensitiveWordMap = new HashMap(keyWordSet.size()); // 初始化敏感词容器，减少扩容操作
			String key = null;
			Map nowMap = null;
			Map<String, String> newWorMap = null;
			// 迭代keyWordSet
			Iterator<String> iterator = keyWordSet.iterator();
			while (iterator.hasNext()) {
				key = iterator.next(); // 关键字
				nowMap = sensitiveWordMap;
				for (int i = 0; i < key.length(); i++) {
					char keyChar = key.charAt(i); // 转换成char型
					Object wordMap = nowMap.get(keyChar); // 获取

					if (wordMap != null) { // 如果存在该key，直接赋值
						nowMap = (Map) wordMap;
					} else { // 不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
						newWorMap = new HashMap<String, String>();
						newWorMap.put("isEnd", "0"); // 不是最后一个
						nowMap.put(keyChar, newWorMap);
						nowMap = newWorMap;
					}

					if (i == key.length() - 1) {
						nowMap.put("isEnd", "1"); // 最后一个
					}
				}
			}
		}

		/**
		 * 读取敏感词库中的内容，将内容添加到set集合中
		 */
		public static Set<String> readSensitiveWordFile() {
			Set<String> set = new HashSet<String>();
			try {
				set = FilterWordSet.getFilterwordcache();
			} catch (Exception e) {
				Log.error("read sensitiveword error", e);
			}
			return set;
		}
	}
}
