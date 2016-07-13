package com.chuangyou.xianni.sql.dao;

import java.util.Map;

import com.chuangyou.xianni.entity.pet.PetInfo;

public interface PetInfoDao {

	public Map<Integer, PetInfo> getAll(long playerId);
	
	public boolean add(PetInfo info);
	
	public boolean update(PetInfo info);
}
