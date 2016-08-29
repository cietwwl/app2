package com.chuangyou.xianni.magicwp.template;

import java.util.HashMap;
import java.util.Map;

import com.chuangyou.xianni.entity.magicwp.MagicwpBanCfg;
import com.chuangyou.xianni.entity.magicwp.MagicwpBanLevelCfg;
import com.chuangyou.xianni.entity.magicwp.MagicwpCfg;
import com.chuangyou.xianni.entity.magicwp.MagicwpGradeCfg;
import com.chuangyou.xianni.entity.magicwp.MagicwpLevelCfg;
import com.chuangyou.xianni.entity.magicwp.MagicwpRefineCfg;
import com.chuangyou.xianni.sql.dao.DBManager;

public class MagicwpTemplateMgr {

	private static Map<Integer, MagicwpCfg> magicwpTemps = new HashMap<>();
	
	private static Map<Integer, MagicwpLevelCfg> levelTemps = new HashMap<>();
	
	private static Map<Integer, MagicwpGradeCfg> gradeTemps = new HashMap<>();
	
	private static Map<Integer, MagicwpRefineCfg> refineTemps = new HashMap<>();
	
	private static Map<Integer, MagicwpBanCfg> banTemps = new HashMap<>();
	
	private static Map<Integer, MagicwpBanLevelCfg> banLevelTemps = new HashMap<>();
	
	public static boolean init(){
		return reloadTemplate();
	}
	
	public static boolean reloadTemplate(){
		magicwpTemps = DBManager.getMagicwpConfigDao().getMagic();
		levelTemps = DBManager.getMagicwpConfigDao().getLevel();
		gradeTemps = DBManager.getMagicwpConfigDao().getGrade();
		refineTemps = DBManager.getMagicwpConfigDao().getRefine();
		banTemps = DBManager.getMagicwpConfigDao().getBan();
		banLevelTemps = DBManager.getMagicwpConfigDao().getBanLevel();
		return true;
	}
	
//	public static boolean reloadMagicwpCfg(){
//		return true;
//	}
//	public static boolean reloadMagicwpCfg(){
//		return true;
//	}
//	public static boolean reloadMagicwpCfg(){
//		return true;
//	}
//	public static boolean reloadMagicwpCfg(){
//		return true;
//	}
//	public static boolean reloadMagicwpCfg(){
//		return true;
//	}
//	public static boolean reloadMagicwpCfg(){
//		return true;
//	}
	

	public static Map<Integer, MagicwpCfg> getMagicwpTemps() {
		return magicwpTemps;
	}

	public static Map<Integer, MagicwpLevelCfg> getLevelTemps() {
		return levelTemps;
	}

	public static Map<Integer, MagicwpGradeCfg> getGradeTemps() {
		return gradeTemps;
	}

	public static Map<Integer, MagicwpRefineCfg> getRefineTemps() {
		return refineTemps;
	}

	public static Map<Integer, MagicwpBanCfg> getBanTemps() {
		return banTemps;
	}

	public static Map<Integer, MagicwpBanLevelCfg> getBanLevelTemps() {
		return banLevelTemps;
	}
}
