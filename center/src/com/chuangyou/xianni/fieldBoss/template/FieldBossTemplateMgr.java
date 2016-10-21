package com.chuangyou.xianni.fieldBoss.template;

import java.util.HashMap;
import java.util.Map;

import com.chuangyou.xianni.entity.fieldBoss.FieldBossCfg;
import com.chuangyou.xianni.sql.dao.DBManager;

public class FieldBossTemplateMgr {
	
	private static Map<Integer, FieldBossCfg> fieldBossMap = new HashMap<>();
	
	private static FieldBossCfg worldBossCfg = null;
	
	public static boolean init(){
		return reloadTemplate();
	}
	
	public static boolean reloadTemplate(){
		fieldBossMap = DBManager.getFieldBossConfigDao().getAll();
		
		for(FieldBossCfg cfg: fieldBossMap.values()){
			if(cfg.getType() == FieldBossCfg.WORLD_BOSS){
				worldBossCfg = cfg;
			}
		}
		return true;
	}

	public static Map<Integer, FieldBossCfg> getFieldBossMap() {
		return fieldBossMap;
	}

	public static FieldBossCfg getWorldBossCfg() {
		return worldBossCfg;
	}
}
