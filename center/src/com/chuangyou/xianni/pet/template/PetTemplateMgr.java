package com.chuangyou.xianni.pet.template;

import java.util.HashMap;
import java.util.Map;

import com.chuangyou.xianni.entity.pet.PetGradeCfg;
import com.chuangyou.xianni.entity.pet.PetInfoCfg;
import com.chuangyou.xianni.entity.pet.PetLevelCfg;
import com.chuangyou.xianni.entity.pet.PetPhysiqueCfg;
import com.chuangyou.xianni.entity.pet.PetQualityCfg;
import com.chuangyou.xianni.entity.pet.PetSkillInfoCfg;
import com.chuangyou.xianni.entity.pet.PetSkillLevelCfg;
import com.chuangyou.xianni.entity.pet.PetSkillSlotCfg;
import com.chuangyou.xianni.entity.pet.PetSoulCfg;
import com.chuangyou.xianni.sql.dao.DBManager;

public class PetTemplateMgr {

	private static Map<Integer, PetInfoCfg>			petTemps		= new HashMap<>();

	private static Map<Integer, PetGradeCfg>		gradeTemps		= new HashMap<>();

	private static Map<Integer, PetLevelCfg>		levelTemps		= new HashMap<>();

	private static Map<Integer, PetPhysiqueCfg>		physiqueTemps	= new HashMap<>();

	private static Map<Integer, PetQualityCfg>		qualityTemps	= new HashMap<>();

	private static Map<Integer, PetSoulCfg>			soulTemps		= new HashMap<>();

	private static Map<Integer, PetSkillInfoCfg>	skillInfoTemps	= new HashMap<>();

	private static Map<Integer, PetSkillLevelCfg>	skillLevelTemps	= new HashMap<>();

	private static Map<Integer, PetSkillSlotCfg>	skillSlotTemps	= new HashMap<>();

	public static boolean init() {
		return reloadTemplate();
	}

	public static boolean reloadTemplate() {
		petTemps = DBManager.getPetConfigDao().getPetInfo();
		gradeTemps = DBManager.getPetConfigDao().getGrade();
		levelTemps = DBManager.getPetConfigDao().getLevel();
		physiqueTemps = DBManager.getPetConfigDao().getPhysique();
		qualityTemps = DBManager.getPetConfigDao().getQuality();
		soulTemps = DBManager.getPetConfigDao().getSoul();
		skillInfoTemps = DBManager.getPetConfigDao().getSkillInfo();
		skillLevelTemps = DBManager.getPetConfigDao().getSkillLevel();
		skillSlotTemps = DBManager.getPetConfigDao().getSkillSlot();
		return true;
	}

	public static boolean reloadPetInfoCfg() {
		petTemps = DBManager.getPetConfigDao().getPetInfo();
		return true;
	}

	public static boolean reloadPetGradeCfg() {
		gradeTemps = DBManager.getPetConfigDao().getGrade();
		return true;
	}

	public static boolean reloadPetLevelCfg() {
		levelTemps = DBManager.getPetConfigDao().getLevel();
		return true;
	}

	public static boolean reloadPetPhysiqueCfg() {
		physiqueTemps = DBManager.getPetConfigDao().getPhysique();
		return true;
	}

	public static boolean reloadPetQualityCfg() {
		qualityTemps = DBManager.getPetConfigDao().getQuality();
		return true;
	}

	public static boolean reloadPetSoulCfg() {
		soulTemps = DBManager.getPetConfigDao().getSoul();
		return true;
	}

	public static boolean reloadPetSkillInfoCfg() {
		skillInfoTemps = DBManager.getPetConfigDao().getSkillInfo();
		return true;
	}

	public static boolean reloadSkillLevelCfg() {
		skillLevelTemps = DBManager.getPetConfigDao().getSkillLevel();
		return true;
	}

	public static boolean reloadPetSkillSlotCfg() {
		skillSlotTemps = DBManager.getPetConfigDao().getSkillSlot();
		return true;
	}

	public static Map<Integer, PetInfoCfg> getPetTemps() {
		return petTemps;
	}

	public static Map<Integer, PetGradeCfg> getGradeTemps() {
		return gradeTemps;
	}

	public static Map<Integer, PetLevelCfg> getLevelTemps() {
		return levelTemps;
	}

	public static Map<Integer, PetPhysiqueCfg> getPhysiqueTemps() {
		return physiqueTemps;
	}

	public static Map<Integer, PetQualityCfg> getQualityTemps() {
		return qualityTemps;
	}

	public static Map<Integer, PetSoulCfg> getSoulTemps() {
		return soulTemps;
	}

	public static Map<Integer, PetSkillInfoCfg> getSkillInfoTemps() {
		return skillInfoTemps;
	}

	public static Map<Integer, PetSkillLevelCfg> getSkillLevelTemps() {
		return skillLevelTemps;
	}

	public static Map<Integer, PetSkillSlotCfg> getSkillSlotTemps() {
		return skillSlotTemps;
	}
}
