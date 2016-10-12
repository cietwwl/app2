package com.chuangyou.xianni.truck.helper;

import com.chuangyou.xianni.common.templete.SystemConfigTemplateMgr;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.role.objects.Truck;
import com.chuangyou.xianni.world.ArmyProxy;

/**
 * 劫镖
 * @author wkghost
 *
 */
public class RobHelper {

	public static void rob(ArmyProxy army, Truck truck)
	{
		int cutRob = 0;
		if(truck.getTrucktype() == Truck.TRUCK_P)	//个人镖车
		{
			cutRob = SystemConfigTemplateMgr.getIntValue("EscortSupplies.Individual.Robbery");
		}
		else
		{
			cutRob = SystemConfigTemplateMgr.getIntValue("EscortSupplies.Faction.Robbery");
		}
		handler(truck, cutRob);
		TruckActionRespHelper.truckAction(army, TruckActionRespHelper.ACTION_ROB_SUC, truck);	//劫镖成功
	}
	
	/**
	 * 击杀后
	 */
	public static void robByKill(Truck truck, int cutRob)
	{
		handler(truck, cutRob);
	}
	
	/**
	 * 处理劫镖
	 * @param army
	 * @param truck
	 * @param cutRob
	 */
	private static void handler(Truck truck, int cutRob)
	{
		cutRob += truck.getProperty(EnumAttr.PK_VAL.getValue());
		truck.updateProperty(EnumAttr.PK_VAL, cutRob);
		TruckAttChgHelper.updateAtt(truck, EnumAttr.PK_VAL, cutRob);
		if(cutRob >= truck.getProperty(EnumAttr.WOOD.getValue()))
		{
			//设置镖车已经被劫镖
			truck.setRobbed(true);
			int mat = truck.getProperty(EnumAttr.METAL.getValue());
			//计算掉落物资的数量
			int cutmat = (int) (mat * 0.2f);
			//更新物资数量
			truck.updateProperty(EnumAttr.METAL, mat - cutmat);
			TruckAttChgHelper.updateAtt(truck, EnumAttr.METAL, mat - cutmat);
			//生成镖车物资掉落
			MaterialSpwanHelper.MaterialSpwan(truck, cutmat);
			//劫镖进度清0
			truck.updateProperty(EnumAttr.PK_VAL, 0);
			TruckAttChgHelper.updateAtt(truck, EnumAttr.PK_VAL, 0);
		}
	}
	
}
