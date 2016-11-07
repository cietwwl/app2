package com.chuangyou.xianni.activeSystem.template;

import java.util.Map;

import com.chuangyou.xianni.entity.active.ActiveConfig;
import com.chuangyou.xianni.sql.dao.DBManager;

/**
 * 日常活动模板数据
 * 
 * @author laofan
 *
 */
public class ActiveTemplateMgr {

	private static Map<Integer, ActiveConfig> activeConfigs;

	public static boolean init() {
		return reloadActiveTemp();
	}

	public static boolean reloadActiveTemp() {
		activeConfigs = DBManager.getActiveDao().getActiveConfig();
		if (activeConfigs == null || activeConfigs.size()==0) {
			return false;
		}
		return true;
	}

	public static Map<Integer, ActiveConfig> getActiveConfigs() {
		return activeConfigs;
	}

	

	
	
	

}
