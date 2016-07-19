package com.chuangyou.xianni.magicwp.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.common.util.ThreadSafeRandom;
import com.chuangyou.xianni.entity.magicwp.MagicwpGradeCfg;
import com.chuangyou.xianni.entity.magicwp.MagicwpRefineCfg;

public class MagicwpRefineManager {

	public static String getRefineAttStr(Map<Integer, Integer> attMap) {
		String attStr = "";
		int size = attMap.keySet().size();
		int index = 0;
		for (int attType : attMap.keySet()) {
			attStr += "" + (attType * 1000000 + attMap.get(attType));
			index++;
			if (index < size) {
				attStr += "_";
			}
		}
		return attStr;
	}

	public static List<Integer> getRefineAtts(String attStr) {
		List<Integer> attMap = new ArrayList<>();
		String[] attStrs = attStr.split("_");
		int attNum = 0;
		for (String att : attStrs) {
			if (att.equals("")) {
				continue;
			}
			attNum = Integer.parseInt(att);
			attMap.add(attNum);
		}
		return attMap;
	}

	public static Map<Integer, Integer> getRefineAttMap(String attStr) {
		Map<Integer, Integer> attMap = new HashMap<Integer, Integer>();
		String[] attStrs = attStr.split("_");
		int attNum = 0;
		for (String att : attStrs) {
			if (att.equals(""))
				continue;
			attNum = Integer.parseInt(att);

			MagicwpRefineManager.putAttNum(attMap, attNum);
		}
		return attMap;
	}

	public static void putAttNum(Map<Integer, Integer> attMap, int attNum) {
		int attType = (int) (attNum / 1000000);
		int attValue = attNum % 1000000;
		attMap.put(attType, attValue);
	}

	/**
	 * 计算洗炼法宝属性结果
	 * 
	 * @param gradeCfg
	 *            当前当宝等阶配置，取当前阶属性上限
	 * @param curAttMap
	 *            传入法宝当前洗炼属性
	 * @param refineCfg
	 *            当前法宝洗炼属性区间配置
	 * @param lockList
	 *            已经锁定属性类型列表
	 * @return
	 */
	public static Map<Integer, Integer> getRefineResultMap(MagicwpGradeCfg gradeCfg, Map<Integer, Integer> curAttMap, MagicwpRefineCfg refineCfg, List<Integer> lockList) {
		Map<Integer, Integer> resultAttMap = new HashMap<Integer, Integer>();

		int curValue;
		int changeValue;
		int resultValue;
		boolean isLock;
		int limitValue;
		boolean isAdd;

		ThreadSafeRandom random = new ThreadSafeRandom();
		for (int attType : gradeCfg.getMaxAttMap().keySet()) {
			curValue = curAttMap.get(attType);

			// 是否锁定这条属性
			isLock = false;
			for (int lockType : lockList) {
				if (attType == lockType) {
					isLock = true;
					break;
				}
			}
			if (isLock == true) {
				resultValue = curValue;
				resultAttMap.put(attType, resultValue);
				continue;
			}

			// 属性为0时必涨，属性达到上限时必减
			if (curValue == 0) {
				isAdd = true;
			} else if (curValue == gradeCfg.getMaxAttMap().get(attType)) {
				isAdd = false;
			} else {
				isAdd = random.isSuccessful(refineCfg.getPro(), 10000);
			}
			// 算出改变值
			changeValue = random.next(refineCfg.getMinAttMap().get(attType), refineCfg.getMaxAttMap().get(attType));
			if (isAdd == true) {
				limitValue = gradeCfg.getMaxAttMap().get(attType);
				resultValue = curValue + changeValue;
				if (resultValue > limitValue) {
					resultValue = limitValue;
				}
			} else {
				limitValue = 0;
				resultValue = curValue - changeValue;
				if (resultValue < limitValue) {
					resultValue = limitValue;
				}
			}
			resultAttMap.put(attType, resultValue);
		}

		return resultAttMap;
	}
}
