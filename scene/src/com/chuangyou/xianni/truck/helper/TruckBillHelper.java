package com.chuangyou.xianni.truck.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.common.templete.SystemConfigTemplateMgr;
import com.chuangyou.xianni.entity.truck.TruckSkillConfig;
import com.chuangyou.xianni.entity.truck.TruckSkillInfo;
import com.chuangyou.xianni.role.objects.Truck;
import com.chuangyou.xianni.script.IScript;
import com.chuangyou.xianni.script.manager.ScriptManager;
import com.chuangyou.xianni.truck.TruckTempMgr;

/**
 * 镖车结算
 * @author wkghost
 *
 */

public class TruckBillHelper {

//	public static ITruckSkill getScript()
//	{
//		IScript iScript = ScriptManager.getScriptById("truckSkill");
//		if (iScript != null) {
//			return (ITruckSkill) iScript;
//		}
//		return null;
//	}

	
	/**
	 * 初始物资
	 * @param truckType
	 * @return
	 */
	public static int getInitMat(Truck truck)
	{
		int mat = 0;
		if(truck.getTrucktype() == Truck.TRUCK_P)
		{
			mat = SystemConfigTemplateMgr.getIntValue("EscortSupplies.Individual.Start");
		}
		else
		{
			mat = SystemConfigTemplateMgr.getIntValue("EscortSupplies.Faction.Start");
		}
		return mat;
	}
	
	/**
	 * 载重
	 * @return
	 */
	public static int getMaxWeight(Truck truck)
	{
		int baseWeight = 0;
		if(truck.getTrucktype() == Truck.TRUCK_P)
		{
			baseWeight = SystemConfigTemplateMgr.getIntValue("EscortSupplies.Individual.EscortCarLoading");
			baseWeight = baseWeight + fixedAdd(truck, 10101) + rateAdd(truck, 10102, baseWeight);
		}
		else
		{
			baseWeight = SystemConfigTemplateMgr.getIntValue("EscortSupplies.Faction.EscortCarLoading");
			baseWeight = baseWeight + fixedAdd(truck, 20101) + rateAdd(truck, 20102, baseWeight);
		}
		return baseWeight;
	}
	
	/**
	 * 速度加成
	 * @return
	 */
	public static int getBaseSpeed(Truck truck)
	{
		int baseSpeed = 0;
		if(truck.getTrucktype()  == Truck.TRUCK_P)
		{
			baseSpeed = SystemConfigTemplateMgr.getIntValue("EscortSupplies.Individual.EscortCarSpeed");
			baseSpeed = baseSpeed + fixedAdd(truck, 10201) + rateAdd(truck, 10202, baseSpeed);
		}
		else
		{
			baseSpeed = SystemConfigTemplateMgr.getIntValue("EscortSupplies.Faction.EscortCarSpeed");
			baseSpeed = baseSpeed +  fixedAdd(truck, 20201) + rateAdd(truck, 20202, baseSpeed);
		}
		return baseSpeed;
	}
	
	/**
	 * 劫镖耐久
	 * @return
	 */
	public static int getDurable(Truck truck)
	{
		int burable = 0;
		if(truck.getTrucktype() == Truck.TRUCK_P)
		{
			burable = SystemConfigTemplateMgr.getIntValue("EscortSupplies.Individual.EscortCarBlood");
			burable = burable + fixedAdd(truck, 10301) + rateAdd(truck, 10302, burable);
		}
		else
		{
			burable = SystemConfigTemplateMgr.getIntValue("EscortSupplies.Faction.EscortCarBlood");
			burable = burable + fixedAdd(truck, 20301) + rateAdd(truck, 20302, burable);
		}
		return burable;
	}
	
	/**
	 * 功能免费次数
	 * @return
	 */
	public static int getFuncFreeCount(Truck truck, int funcId)
	{
		int ret = 0;
		switch(funcId)
		{
		case 40200:
			ret += fixedAdd(truck, 30202);
			break;
		case 40300:
			ret += fixedAdd(truck, 30302);
			break;
		case 40400:
			ret += fixedAdd(truck, 30402);
			break;
		case 40500:
			ret += fixedAdd(truck, 30503);
			break;
		}
		return ret;
	}
	
	/**
	 * 获取物资的价格
	 * @return
	 */
	public static int getMatPrice(Truck truck, int price)
	{
		return rateCut(truck, 30601, price);
	}
	
	/**
	 * 获取出售物质总量的个数加成
	 * @return
	 */
	public static int getAddMatPlus(Truck truck, int base)
	{
		return rateAdd(truck, 30701, base);
	}
	
	/**
	 * 获取完成后的总经验加成
	 * @return
	 */
	public static int getFinalExpPlus(Truck truck, int base)
	{
		return fixedAdd(truck, 30801);
	}
	
	/**
	 * 未被成功劫镖，结算时获得外经验
	 * @return
	 */
	public static int getExpRewardPlus(Truck truck)
	{
		return fixedAdd(truck, 31001);
	}
	
	/**
	 * 未被成功劫镖，结算时获得外奖励
	 * @return
	 */
	public static int getReward(Truck truck)
	{
		return fixedAdd(truck, 31101);
	}
	
	//#########################################################################################
	/**
	 * 固定数值添加
	 * @param truck
	 * @param valueType
	 * @return
	 */
	private static int fixedAdd(Truck truck, int valueType)
	{
		int ret = 0;
		List<TruckSkillConfig> skills = getTruckSkillsByValueType(truck, valueType);
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
	private static int rateAdd(Truck truck, int valueType, int base)
	{
		int ret = 0;
		List<TruckSkillConfig> skills = getTruckSkillsByValueType(truck, valueType);
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
	private static int rateCut(Truck truck, int valueType, int base)
	{
		int ret = base;
		List<TruckSkillConfig> skills = getTruckSkillsByValueType(truck, valueType);
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

	/***
	 * 根据计算类型获取技能配置列表
	 * @param skills
	 * @param valueType
	 * @returns
	 */
	public static List<TruckSkillConfig> getTruckSkillsByValueType(Truck truck, int valueType)
	{
		List<TruckSkillConfig> retList = new ArrayList<TruckSkillConfig>();
		List<TruckSkillInfo> skillInfos = truck.getSkills().get(valueType);
		if(skillInfos != null)
		{
			for(int i = 0; i<skillInfos.size(); i++)
			{
				TruckSkillConfig skillConfig = TruckTempMgr.getAllSkillConfig().get(skillInfos.get(i).getSkillId());
				if(skillConfig != null)
					retList.add(skillConfig);
			}
		}
		return retList;
	}

}
