package com.chuangyou.xianni.state.template;

import java.util.Map;

import com.chuangyou.xianni.entity.state.ConsumSystemConfig;
import com.chuangyou.xianni.entity.state.StateConditionConfig;
import com.chuangyou.xianni.entity.state.StateConfig;
import com.chuangyou.xianni.sql.dao.DBManager;

public class StateTemplateMgr {
	
	/**
	 * 任务条件配置
	 */
	private static Map<Integer, StateConditionConfig> conditions;
	
	/**
	 * 境界配置表
	 */
	private static Map<Integer, StateConfig> states;
	
	/**
	 * 消耗配置表
	 */
	private static Map<Integer, ConsumSystemConfig> consums;
	
	public static boolean init(){
		return reloadTemplateData();
	}
	
	
	public static boolean reloadTemplateData(){
		conditions = DBManager.getStateDao().getStateConditionConfig();
		states	   = DBManager.getStateDao().getStateConfig();
		consums    = DBManager.getStateDao().getConsumConfig();
		if(conditions == null || states == null || consums == null ){
			return false;
		}
		if(conditions.size() == 0 || states.size() == 0 || consums.size() == 0){
			return false;
		}
		return true;
	}
	public static boolean reloadConditions(){
		conditions = DBManager.getStateDao().getStateConditionConfig();
		return true;
	}
	public static boolean reloadStates(){
		states	   = DBManager.getStateDao().getStateConfig();
		return true;
	}
	public static boolean reloadConsums(){
		consums    = DBManager.getStateDao().getConsumConfig();
		return true;
	}


	public static Map<Integer, StateConditionConfig> getConditions() {
		return conditions;
	}


	public static Map<Integer, StateConfig> getStates() {
		return states;
	}


	public static Map<Integer, ConsumSystemConfig> getConsums() {
		return consums;
	}
	
	
}
