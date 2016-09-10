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

	public static int getInitBorn() {
		SystemConfig cfg = systemTemps.get("init.born.map");
		if (cfg == null) {
			Log.error("getIntValue is null ,key : init.born.map");
			return 1007;
		}
		return cfg.getValue();
	}

	public static int getReBorn() {
		SystemConfig cfg = systemTemps.get("init.reborn.map");
		if (cfg == null) {
			Log.error("getIntValue is null ,key : init.reborn.map");
			return 1009;
		}
		return cfg.getValue();
	}

//	public static int getIdBuiderWay() {
//		SystemConfig cfg = systemTemps.get("id.builder.way");
//		if (cfg == null) {
//			Log.error("getIntValue is null ,key : id.builder.way");
//			return 0;
//		}
//		return cfg.getValue();
//	}

	public static int getSpaceGiftPrice() {
		SystemConfig cfg = systemTemps.get("space.gift.perPrice");
		if (cfg == null) {
			Log.error("getIntValue is null ,key : space.gift.perPrice");
			return 10000;
		}
		return cfg.getValue();
	}

	public static int getSpaceCollectionPrice() {
		SystemConfig cfg = systemTemps.get("space.addCollection.perPrice");
		if (cfg == null) {
			Log.error("getIntValue is null ,key : space.addCollection.perPrice");
			return 10000;
		}
		return cfg.getValue();
	}

	public static int getBirthdayItem() {
		SystemConfig cfg = systemTemps.get("space.birthday.gift");
		if (cfg == null) {
			Log.error("getIntValue is null ,key : space.birthday.gift");
			return 0;
		}
		return cfg.getValue();
	}

	public static int getSpaceEggs() {
		SystemConfig cfg = systemTemps.get("space.op.eggs");
		if (cfg == null) {
			Log.error("getIntValue is null ,key : space.op.eggs");
			return 0;
		}
		return cfg.getValue();
	}

	public static int getSpaceFlower() {
		SystemConfig cfg = systemTemps.get("space.op.flower");
		if (cfg == null) {
			Log.error("getIntValue is null ,key : space.op.flower");
			return 0;
		}
		return cfg.getValue();
	}

	public static Map<Integer, Integer> getSoulItemExp() {
		SystemConfig cfg1 = systemTemps.get("soul.hunpo.spenditem");
		SystemConfig cfg2 = systemTemps.get("soul.hunpo.addvalue");
		if (cfg1 == null) {
			Log.error("getIntValue is null ,key : soul.hunpo.spenditem");
			return null;
		}
		if (cfg2 == null) {
			Log.error("getIntValue is null ,key : soul.hunpo.addvalue");
			return null;
		}

		String[] items = cfg1.getStrValue().split(",");
		String[] exps = cfg2.getStrValue().split(",");
		if (items.length == exps.length) {
			Map<Integer, Integer> map = new HashMap<>();
			for (int i = 0; i < items.length; i++) {
				int key = Integer.parseInt(items[i]);
				int value = Integer.parseInt(exps[i]);
				map.put(key, value);
			}
			return map;
		}
		return null;
	}

	public static String[] getMakeConfig() {
		SystemConfig cfg1 = systemTemps.get("soul.make.low");
		SystemConfig cfg2 = systemTemps.get("soul.make.mid");
		SystemConfig cfg3 = systemTemps.get("soul.make.hight");
		if (cfg1 == null) {
			Log.error("getIntValue is null ,key : soul.make.low");
			return null;
		}
		if (cfg2 == null) {
			Log.error("getIntValue is null ,key : soul.make.mid");
			return null;
		}
		if (cfg3 == null) {
			Log.error("getIntValue is null ,key : soul.make.hight");
			return null;
		}
		String[] strs = { cfg1.getStrValue(), cfg2.getStrValue(), cfg3.getStrValue() };
		return strs;
	}

}
