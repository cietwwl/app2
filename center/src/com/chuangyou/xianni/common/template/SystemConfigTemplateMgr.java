package com.chuangyou.xianni.common.template;

import java.util.HashMap;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.common.SystemConfig;
import com.chuangyou.xianni.sql.dao.DBManager;

public class SystemConfigTemplateMgr {
	private static Map<String, SystemConfig>	systemTemps			= new HashMap<>();

	/************** 调用频率较高的值加载时存起来 *****************/
	public static int							dropPackageOverTime	= 0;

	public static boolean init() {
		return reloadTemplateSystem();
	}

	public static boolean reloadTemplateSystem() {
		systemTemps = DBManager.getSystemConfigDao().getAll();
		dropPackageOverTime = systemTemps.get("drop.package.overtime").getValue();
		return true;
	}

	public static Map<String, SystemConfig> getSystemTemps() {
		return systemTemps;
	}

	public static int getIntValue(String key) {
		SystemConfig cfg = systemTemps.get(key);
		if (cfg == null) {
			Log.error("getIntValue is null ,key : " + key);
			return 0;
		}
		return cfg.getValue();
	}

	public static String getStringValue(String key) {
		SystemConfig cfg = systemTemps.get(key);
		if (cfg == null) {
			Log.error("getIntValue is null ,key : " + key);
			return "";
		}
		return cfg.get_desc();
	}
}
