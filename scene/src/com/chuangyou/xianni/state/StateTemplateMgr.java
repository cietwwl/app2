package com.chuangyou.xianni.state;

import java.util.Map;

import com.chuangyou.xianni.entity.state.ConsumSystemConfig;
import com.chuangyou.xianni.sql.dao.DBManager;

public class StateTemplateMgr {
	/**
	 * 消耗配置表
	 */
	private static Map<Integer, ConsumSystemConfig> consums;
	
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
		return true;
	}
	
	public static Map<Integer, ConsumSystemConfig> getConsums() {
		return consums;
	}
}
