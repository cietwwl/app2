package com.chuangyou.xianni.truck.helper;

import java.util.ArrayList;
import java.util.List;

import com.chuangyou.xianni.entity.truck.TruckSkillConfig;

public class TruckBillHelper {
	
	/** 被劫镖获得经验补偿 */
	public static final int TRUCK_BROKEN_OFFSET = 30901;
	
	/** 未被成功劫镖，结算时获得外经验 */
	public static final int TRUCK_UNBROKEN_EXP = 31001;
	
	/** 未被成功劫镖，结算时获得外奖励 */
	public static final int TRUCK_UNBROKEN_REWARD = 31101;
	
	/** 为他人护镖获得额外经验 */
	public static final int TRUCK_PROTECTOR_EXT_EXP = 31201;
	
	/** 为他人护镖获得额外奖励 */
	public static final int TRUCK_PROTECTOR_EXT_REWARD = 31301;

	/**
	 * 获取值集合
	 * @param skills
	 * @return
	 */
	public static List<Integer> getValueCollection(List<TruckSkillConfig> skills)
	{
		List<Integer> ret = new ArrayList<Integer>();
		if(skills == null)
			return ret;
		for(int i = 0; i<skills.size(); i++)
		{
			ret.add(skills.get(i).getValue());
		}
		return ret;
	}
	
	/**
	 * 固定数值添加
	 * @param truck
	 * @param valueType
	 * @return
	 */
	public static int fixedAdd(List<TruckSkillConfig> skills)
	{
		int ret = 0;
		if(skills == null)
			return ret;
		for(int i = 0; i<skills.size(); i++)
		{
			ret += skills.get(i).getValue();
		}
		return ret;
	}
	
	/**
	 * 按比例添加
	 * @param truck
	 * @param valueType
	 * @param base
	 * @return
	 */
	private static int rateAdd(List<TruckSkillConfig> skills, int valueType, int base)
	{
		int ret = 0;
		if(skills == null)
			return ret;
		for(int i = 0; i<skills.size(); i++)
		{
			ret += skills.get(i).getValuePercent() * 0.0001 * base;
		}
		return ret;
	}

	/**
	 * 按比例打折
	 * @param truck
	 * @param valueType
	 * @param base
	 * @return
	 */
	private static int rateCut(List<TruckSkillConfig> skills, int valueType, int base)
	{
		int ret = base;
		if(skills == null)
			return ret;
		int discount = 0;
		for(int i = 0; i<skills.size(); i++)
		{
			discount += skills.get(i).getValuePercent();
		}
		ret =  (int) (ret - discount * 0.0001 * ret);
		return ret;
	}
}
