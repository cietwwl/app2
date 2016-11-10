package com.chuangyou.common.util.random;

import java.util.List;
import java.util.Random;

/**
 * 带权重的随机
 * @author laofan
 *
 */
public class WeightRandomUtil {

	/**
	 * 获取带权重的随机出来的元素
	 * @param list
	 * @return
	 */
	public static <T extends IWeight> T getRandomWeight(List<T> list){		
		int total = 0;
		for (IWeight iWeight : list) {
			total += iWeight.getWeight();
		}
		if(total>0){
			int r = new Random().nextInt(total);
			int flag = 0;
			for (T iWeight : list) {
				if (r >= flag && r < flag+iWeight.getWeight()) {
					return iWeight;
				}
				flag += iWeight.getWeight();
			}
		}
		return null;
	}
	
	/**
	 * 获取带权重的随机出来的元素,总权重值固定
	 * @param total
	 * @param list
	 * @return
	 */
	public static <T extends IWeight> T getRandomWeight(int total, List<T> list){
		if(total>0){
			int r = new Random().nextInt(total);
			int flag = 0;
			for (T iWeight : list) {
				if (r >= flag && r < flag+iWeight.getWeight()) {
					return iWeight;
				}
				flag += iWeight.getWeight();
			}
		}
		return null;
	}
}
