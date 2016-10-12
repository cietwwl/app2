package com.chuangyou.xianni.truck;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.entity.truck.TruckFun;
import com.chuangyou.xianni.entity.truck.TruckLevelConfig;
import com.chuangyou.xianni.entity.truck.TruckSkillConfig;
import com.chuangyou.xianni.sql.dao.DBManager;

public class TruckTempMgr {
	/** 个人镖车 等级配置 **/
	private static Map<Integer, TruckLevelConfig> personalTruckLevelConfig;
	/** 帮派镖车 等级配置 **/
	private static Map<Integer, TruckLevelConfig> guildTruckLevelConfig;
	/** 镖师  等级配置 **/
	private static Map<Integer, TruckLevelConfig> truckerLevelConfig;
	/** 功能 */
	private static Map<Integer, TruckFun> truckFuncs;
	
//	/** 个人镖车 技能配置 **/
//	private static Map<Integer, TruckSkillConfig> personalTruckSkillConfig;
//	/** 帮派镖车技能配置 **/
//	private static Map<Integer, TruckSkillConfig> guildlTruckSkillConfig;
//	/** 镖师 技能配置 **/
//	private static Map<Integer, TruckSkillConfig> truckerSkillConfig;
	/** 技能配置 */
	private static Map<Integer, TruckSkillConfig> allSkillConfig;
	
	public static boolean init(){
		return reloadTemplate();
	}
	
	public static boolean reloadTemplate(){
		personalTruckLevelConfig = new HashMap<Integer, TruckLevelConfig>();
		guildTruckLevelConfig = new HashMap<Integer, TruckLevelConfig>();
		truckerLevelConfig = new HashMap<Integer, TruckLevelConfig>();
		
//		personalTruckSkillConfig = new HashMap<Integer, TruckSkillConfig>();
//		guildlTruckSkillConfig = new HashMap<Integer, TruckSkillConfig>();
//		truckerSkillConfig = new HashMap<Integer, TruckSkillConfig>();
		allSkillConfig = new HashMap<Integer, TruckSkillConfig>();
		
		List<TruckLevelConfig> levelConfig = DBManager.getTruckDao().getLevelConfig();
		for(TruckLevelConfig config : levelConfig)
		{
			if(config.getType() == TruckLevelConfig.PERSONAL)
			{
				personalTruckLevelConfig.put(config.getLevel(), config);
			}
			else if(config.getType() == TruckLevelConfig.TRUCKER)
			{
				truckerLevelConfig.put(config.getLevel(), config);
			}
			else if(config.getType() == TruckLevelConfig.GUILD)
			{
				guildTruckLevelConfig.put(config.getLevel(), config);
			}
		}
		
		List<TruckSkillConfig> skillConfig = DBManager.getTruckDao().getSkillConfig();
		for(TruckSkillConfig config : skillConfig)
		{
//			if(config.getEscortType() == TruckSkillConfig.PERSONAL)
//			{
//				personalTruckSkillConfig.put(config.getId(), config);
//			}
//			else if(config.getEscortType() == TruckSkillConfig.TRUCKER)
//			{
//				truckerSkillConfig.put(config.getId(), config);
//			}
//			else if(config.getEscortType() == TruckSkillConfig.GUILD)
//			{
//				guildlTruckSkillConfig.put(config.getId(), config);
//			}
			allSkillConfig.put(config.getId(), config);
		}
		
		truckFuncs = new HashMap<Integer, TruckFun>();
		List<TruckFun> funcs = DBManager.getTruckDao().getSkillFunc();
		for(TruckFun fun : funcs)
		{
			truckFuncs.put(fun.getId(), fun);
		}
		return true;
	}

	public static Map<Integer, TruckLevelConfig> getPersonalTruckLevelConfig() {
		return personalTruckLevelConfig;
	}

	public static Map<Integer, TruckLevelConfig> getGuildTruckLevelConfig() {
		return guildTruckLevelConfig;
	}

	public static Map<Integer, TruckLevelConfig> getTruckerLevelConfig() {
		return truckerLevelConfig;
	}

//	public static Map<Integer, TruckSkillConfig> getPersonalTruckSkillConfig() {
//		return personalTruckSkillConfig;
//	}
//
//	public static Map<Integer, TruckSkillConfig> getGuildlTruckSkillConfig() {
//		return guildlTruckSkillConfig;
//	}
//
//	public static Map<Integer, TruckSkillConfig> getTruckerSkillConfig() {
//		return truckerSkillConfig;
//	}

	public static Map<Integer, TruckSkillConfig> getAllSkillConfig() {
		return allSkillConfig;
	}

	public static Map<Integer, TruckFun> getTruckFuncs() {
		return truckFuncs;
	}
}
