package com.chuangyou.xianni.battle.damage;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.constant.EnumAttr;

public class DamageFactory {

	public static DamageCalculator createCalculator(int calcWay, int job) {
		DamageCalculator calculator = null;
		EnumAttr type = EnumAttr.getEnumAttrByValue(calcWay);
		if(type == null){
			Log.error("传入计算方式有误, 检查配置; calcWay1: " + calcWay);
			return new BloodDamageCalculator();
		}
		switch (type) {
			case CUR_SOUL:
				calculator = new SouDamageCalculator();
				break;
			case CUR_BLOOD:
				calculator = new BloodDamageCalculator();
				break;
			default:
				calculator = new BloodDamageCalculator();
				Log.error("传入计算方式有误, 检查配置; calcWay: " + calcWay);
				break;
		}
		return calculator;
	}
}
