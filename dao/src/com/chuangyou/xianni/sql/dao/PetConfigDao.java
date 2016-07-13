package com.chuangyou.xianni.sql.dao;

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

public interface PetConfigDao {
	public Map<Integer, PetInfoCfg> getPetInfo();
	
	public Map<Integer, PetGradeCfg> getGrade();
	
	public Map<Integer, PetLevelCfg> getLevel();
	
	public Map<Integer, PetPhysiqueCfg> getPhysique();
	
	public Map<Integer, PetQualityCfg> getQuality();
	
	public Map<Integer, PetSoulCfg> getSoul();
	
	public Map<Integer, PetSkillInfoCfg> getSkillInfo();
	
	public Map<Integer, PetSkillLevelCfg> getSkillLevel();
	
	public Map<Integer, PetSkillSlotCfg> getSkillSlot();
}
