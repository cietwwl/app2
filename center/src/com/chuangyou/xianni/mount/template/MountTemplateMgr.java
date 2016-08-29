package com.chuangyou.xianni.mount.template;

import java.util.HashMap;
import java.util.Map;

import com.chuangyou.xianni.common.template.SystemConfigTemplateMgr;
import com.chuangyou.xianni.entity.mount.MountEquipCfg;
import com.chuangyou.xianni.entity.mount.MountGradeCfg;
import com.chuangyou.xianni.entity.mount.MountLevelCfg;
import com.chuangyou.xianni.entity.mount.MountWeaponCfg;
import com.chuangyou.xianni.sql.dao.DBManager;

public class MountTemplateMgr {

	private static Map<Integer, MountLevelCfg>					levelTemps	= new HashMap<>();

	private static Map<Integer, MountGradeCfg>					gradeTemps	= null;

	private static Map<Integer, MountGradeCfg>					mountTemps	= new HashMap<>();

	private static Map<Integer, Map<Integer, MountEquipCfg>>	equipTemps	= new HashMap<>();

	private static Map<Integer, MountWeaponCfg>					weaponTemps	= new HashMap<>();

	public static boolean init() {
		return reloadTemplate();
	}

	public static boolean reloadTemplate() {
		levelTemps = DBManager.getMountConfigDao().getLevel();
		mountTemps = DBManager.getMountConfigDao().getGrade();
		if (gradeTemps != null) {
			gradeTemps.clear();
			gradeTemps = null;
		}
		equipTemps = DBManager.getMountConfigDao().getEquip();
		weaponTemps = DBManager.getMountConfigDao().getWeapon();
		return true;
	}

	public static boolean reloadMontLevelCfg() {
		levelTemps = DBManager.getMountConfigDao().getLevel();
		return true;
	}

	public static boolean reloadMountGradeCfg() {
		mountTemps = DBManager.getMountConfigDao().getGrade();
		if (gradeTemps != null) {
			gradeTemps.clear();
			gradeTemps = null;
		}
		return true;

	}

	public static boolean reloadMountEquipCfg() {
		equipTemps = DBManager.getMountConfigDao().getEquip();
		return true;
	}

	public static boolean reloadMountWeaponCfg() {
		weaponTemps = DBManager.getMountConfigDao().getWeapon();
		return true;
	}

	public static Map<Integer, MountLevelCfg> getLevelTemps() {
		return levelTemps;
	}

	public static Map<Integer, MountGradeCfg> getGradeTemps() {
		if (gradeTemps == null) {
			int specialGrade = SystemConfigTemplateMgr.getIntValue("mount.grade.specialMount");
			gradeTemps = new HashMap<>();
			for (MountGradeCfg gradeCfg : mountTemps.values()) {
				if (gradeCfg.getGrade() != specialGrade) {
					gradeTemps.put(gradeCfg.getGrade(), gradeCfg);
				}
			}
		}
		return gradeTemps;
	}

	public static Map<Integer, MountGradeCfg> getMountTemps() {
		return mountTemps;
	}

	public static void setMountTemps(Map<Integer, MountGradeCfg> mountTemps) {
		MountTemplateMgr.mountTemps = mountTemps;
	}

	public static Map<Integer, Map<Integer, MountEquipCfg>> getEquipTemps() {
		return equipTemps;
	}

	public static Map<Integer, MountWeaponCfg> getWeaponTemps() {
		return weaponTemps;
	}
}
