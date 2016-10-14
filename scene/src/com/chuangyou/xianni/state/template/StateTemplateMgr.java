package com.chuangyou.xianni.state.template;

import java.util.Map;

import com.chuangyou.xianni.entity.state.ConsumSystemConfig;
import com.chuangyou.xianni.entity.state.StateConfig;
import com.chuangyou.xianni.entity.state.StateEventConfig;
import com.chuangyou.xianni.sql.dao.DBManager;

public class StateTemplateMgr {
	
	/**
	 * 消耗配置表
	 */
	private static Map<Integer, ConsumSystemConfig> consums;
	/**
	 * 境界事件配置表
	 */
	private static Map<Integer, StateEventConfig> events;
	
	/**
	 * 境界配置表
	 */
	private static Map<Integer, StateConfig> states;
	
	public static boolean init(){
		return reloadTemplateData();
	}
	
	
	public static boolean reloadTemplateData(){
		consums    = DBManager.getStateDao().getConsumConfig();
		if(consums == null ){
			return false;
		}
		if(consums.size() == 0){
			return false;
		}
		events     = DBManager.getStateDao().getStateEventConfig();
		if(events == null){
			return false;
		}
		if(events.size() == 0){
			return false;
		}
		
		states	   = DBManager.getStateDao().getStateConfig();
		if(states==null){
			return false;
		}
		if(states.size() == 0){
			return false;
		}
		
		return true;
	}
	
	public static Map<Integer, ConsumSystemConfig> getConsums() {
		return consums;
	}


	public static Map<Integer, StateEventConfig> getEvents() {
		return events;
	}


	public static Map<Integer, StateConfig> getStates() {
		return states;
	}
}
