package com.chuangyou.xianni.equip.template;

import java.util.HashMap;
import java.util.Map;

import com.chuangyou.xianni.entity.equip.EquipAwakenCfg;
import com.chuangyou.xianni.entity.equip.EquipBarGradeCfg;
import com.chuangyou.xianni.entity.equip.EquipSuitCfg;
import com.chuangyou.xianni.sql.dao.DBManager;

public class EquipTemplateMgr {

	private static Map<Short, Map<Integer, EquipBarGradeCfg>> barGradeMap = new HashMap<>();
	
	private static Map<Long, EquipAwakenCfg> awakenMap = new HashMap<>();
	
//	private static Map<Integer, EquipResolveCfg> resolveMap = new HashMap<>();
	
	private static Map<Integer, EquipSuitCfg> suitMap = new HashMap<>();
	
	public static boolean init(){
		return reloadTemplate();
	}
	
	public static boolean reloadTemplate(){
		barGradeMap = DBManager.getEquipConfigDao().getGrade();
		awakenMap = DBManager.getEquipConfigDao().getAwaken();
//		resolveMap = DBManager.getEquipConfigDao().getResolve();
		suitMap = DBManager.getEquipConfigDao().getSuit();
		return true;
	}
	
	public static EquipBarGradeCfg getBarGradeCfg(short position, int grade){
		Map<Integer, EquipBarGradeCfg> gradeMap = barGradeMap.get(position);
		if(gradeMap == null) return null;
		return gradeMap.get(grade);
	}

	public static Map<Short, Map<Integer, EquipBarGradeCfg>> getBarGradeMap() {
		return barGradeMap;
	}

	public static Map<Long, EquipAwakenCfg> getAwakenMap() {
		return awakenMap;
	}

//	public static Map<Integer, EquipResolveCfg> getResolveMap() {
//		return resolveMap;
//	}

	public static Map<Integer, EquipSuitCfg> getSuitMap() {
		return suitMap;
	}
	
	public static boolean reloadGrade(){
		barGradeMap = DBManager.getEquipConfigDao().getGrade();
		return true;
	}
	public static boolean reloadAwaken(){
		awakenMap = DBManager.getEquipConfigDao().getAwaken();
		return true;
	}
	public static boolean reloadSuit(){
		suitMap = DBManager.getEquipConfigDao().getSuit();
		return true;
	}
}
