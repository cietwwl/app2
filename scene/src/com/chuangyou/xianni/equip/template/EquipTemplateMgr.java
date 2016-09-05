package com.chuangyou.xianni.equip.template;

import java.util.HashMap;
import java.util.Map;

import com.chuangyou.xianni.entity.equip.EquipAwakenCfg;
import com.chuangyou.xianni.sql.dao.DBManager;

public class EquipTemplateMgr {

	private static Map<Long, EquipAwakenCfg> awakenMap = new HashMap<>();
	
	public static boolean init(){
		return reloadTemplate();
	}
	
	public static boolean reloadTemplate(){
		awakenMap = DBManager.getEquipConfigDao().getAwaken();
		return true;
	}

	public static Map<Long, EquipAwakenCfg> getAwakenMap() {
		return awakenMap;
	}
}
