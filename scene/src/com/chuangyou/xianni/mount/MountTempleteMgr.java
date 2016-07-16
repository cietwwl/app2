package com.chuangyou.xianni.mount;

import java.util.HashMap;
import java.util.Map;

import com.chuangyou.xianni.entity.mount.MountGradeCfg;
import com.chuangyou.xianni.sql.dao.DBManager;

public class MountTempleteMgr {

	private static Map<Integer, MountGradeCfg> mountTemps = new HashMap<>();
	
	public static boolean init(){
		return reloadTemplate();
	}
	
	public static boolean reloadTemplate(){
		mountTemps = DBManager.getMountConfigDao().getGrade();
		return true;
	}

	public static Map<Integer, MountGradeCfg> getMountTemps() {
		return mountTemps;
	}
}
