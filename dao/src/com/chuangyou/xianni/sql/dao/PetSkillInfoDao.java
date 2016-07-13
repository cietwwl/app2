package com.chuangyou.xianni.sql.dao;

import java.util.Map;

import com.chuangyou.xianni.entity.pet.PetSkill;

public interface PetSkillInfoDao {

	public Map<Integer, PetSkill> getAll(long playerId);
	
	public boolean add(PetSkill info);
	
	public boolean update(PetSkill info);
}
