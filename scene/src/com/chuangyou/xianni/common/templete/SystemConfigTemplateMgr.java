package com.chuangyou.xianni.common.templete;

import java.util.HashMap;
import java.util.Map;

import com.chuangyou.xianni.entity.common.SystemConfig;
import com.chuangyou.xianni.sql.dao.DBManager;

public class SystemConfigTemplateMgr {
	private static Map<String, SystemConfig> systemTemps = new HashMap<>();
	
	/************** 调用频率较高的值加载时存起来 *****************/
	/** 掉落物超时删除时间 单位：秒 */
	public static int dropPackageOverTime = 0;
	
	public static boolean init(){
		return reloadTemplateSystem();
	}
	
	public static boolean reloadTemplateSystem(){
		systemTemps = DBManager.getSystemConfigDao().getAll();
		
		dropPackageOverTime = systemTemps.get("drop.package.overtime").getValue();
		return true;
	}

	public static Map<String, SystemConfig> getSystemTemps() {
		return systemTemps;
	}
	
	public static int getIntValue(String key){
		SystemConfig cfg = systemTemps.get(key);
		return cfg.getValue();
	}
	
	public static String getStringValue(String key){
		SystemConfig cfg = systemTemps.get(key);
		return cfg.get_desc();
	}
}
