package com.chuangyou.xianni.fashion.template;

import java.util.HashMap;
import java.util.Map;

import com.chuangyou.xianni.entity.fashion.FashionCfg;
import com.chuangyou.xianni.entity.fashion.FashionLevelCfg;
import com.chuangyou.xianni.entity.fashion.FashionQualityCfg;
import com.chuangyou.xianni.sql.dao.DBManager;

public class FashionTemplateMgr {

	private static Map<Integer, FashionCfg> fashionTemps = new HashMap<>();
	
	private static Map<Integer, FashionLevelCfg> levelTemps = new HashMap<>();
	
	private static Map<Integer, FashionQualityCfg> qualityTemps = new HashMap<>();
	
	public static boolean init(){
		return reloadTemplate();
	}
	
	public static boolean reloadTemplate(){
		fashionTemps = DBManager.getFashionConfigDao().getFashion();
		levelTemps = DBManager.getFashionConfigDao().getLevel();
		qualityTemps = DBManager.getFashionConfigDao().getQuality();
		return true;
	}

	public static Map<Integer, FashionCfg> getFashionTemps() {
		return fashionTemps;
	}

	public static Map<Integer, FashionLevelCfg> getLevelTemps() {
		return levelTemps;
	}

	public static Map<Integer, FashionQualityCfg> getQualityTemps() {
		return qualityTemps;
	}
}
