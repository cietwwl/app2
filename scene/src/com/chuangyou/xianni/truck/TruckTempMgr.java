package com.chuangyou.xianni.truck;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.entity.truck.TruckCheckPointConfig;
import com.chuangyou.xianni.entity.truck.TruckFun;
import com.chuangyou.xianni.entity.truck.TruckSkillConfig;
import com.chuangyou.xianni.sql.dao.DBManager;

public class TruckTempMgr {
	//开始的路点
	public static final int FRIST = 1;
	//中间的路点
	public static final int MID = 2;
	///最后的路点
	public static final int LAST = 3;
	///转场点
	public static final int TRANSPORT = 4;
	
//	/** 招募	 */
//	public static int ZhaoMu = 0;
//	/** 激励 */
//	public static int JiLi = 0;
//	/** 坚守 */
//	public static int JianShou = 0;
//	/** 捷运 */
//	public static int JieYun = 0;
//	/** 暗标 */
//	public static int AnBiao = 0;
	
	/** 路点信息 */
	private static Map<Integer, TruckCheckPointConfig> checkpoints;
	/** 功能 */
	private static Map<Integer, TruckFun> truckFuncs;
	/** 技能配置 */
	private static Map<Integer, TruckSkillConfig> allSkillConfig;
	
	private static TruckCheckPointConfig fristCheckPoint;
	public static TruckCheckPointConfig getFristCheckPoint() {
		return fristCheckPoint;
	}

	public static TruckCheckPointConfig getLastCheckPoint() {
		return lastCheckPoint;
	}

	private static TruckCheckPointConfig lastCheckPoint;
	
	public static boolean init(){
		return reloadTemplate();
	}
	
	public static boolean reloadTemplate(){
		checkpoints = new HashMap<Integer, TruckCheckPointConfig>();
		List<TruckCheckPointConfig> checkpointLst = DBManager.getTruckDao().getCheckPoints();
		for (int i = 0; i < checkpointLst.size(); i++) {
			TruckCheckPointConfig checkpointInfo = checkpointLst.get(i);
			if(checkpointInfo.getPointType() == 1) fristCheckPoint = checkpointInfo;
			if(checkpointInfo.getPointType() == 3) lastCheckPoint = checkpointInfo;
			checkpoints.put(checkpointInfo.getId(), checkpointInfo);
		}
		truckFuncs = new HashMap<Integer, TruckFun>();
		List<TruckFun> funcs = DBManager.getTruckDao().getSkillFunc();
//		ZhaoMu = funcs.get(0).getId();
//		JiLi = funcs.get(1).getId();
//		JianShou = funcs.get(2).getId();
//		JieYun = funcs.get(3).getId();
//		AnBiao = funcs.get(4).getId();
		for(TruckFun fun : funcs)
		{
			truckFuncs.put(fun.getId(), fun);
		}
		allSkillConfig = new HashMap<Integer, TruckSkillConfig>();
		List<TruckSkillConfig> skillConfig = DBManager.getTruckDao().getSkillConfig();
		for(TruckSkillConfig config : skillConfig)
		{
			allSkillConfig.put(config.getId(), config);
		}
		return true;
	}
	
	public static Map<Integer, TruckCheckPointConfig> getCheckPoints()
	{
		return checkpoints;
	}
	
	public static Map<Integer, TruckFun> getTruckFuncs() {
		return truckFuncs;
	}
	
	public static Map<Integer, TruckSkillConfig> getAllSkillConfig() {
		return allSkillConfig;
	}
}
