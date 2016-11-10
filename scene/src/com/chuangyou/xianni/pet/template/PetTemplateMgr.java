package com.chuangyou.xianni.pet.template;

import java.util.HashMap;
import java.util.Map;

import com.chuangyou.xianni.entity.pet.PetInfoCfg;
import com.chuangyou.xianni.sql.dao.DBManager;

public class PetTemplateMgr {

	private static Map<Integer, PetInfoCfg>			petTemps		= new HashMap<>();

	public static boolean init() {
		return reloadTemplate();
	}

	public static boolean reloadTemplate() {
		petTemps = DBManager.getPetConfigDao().getPetInfo();
		return true;
	}
	public static Map<Integer, PetInfoCfg> getPetTemps() {
		return petTemps;
	}
}
