package com.chuangyou.xianni.sql.dao;

import java.util.Map;

import com.chuangyou.xianni.entity.pet.PetAtt;

public interface PetTotalAttDao {

	public PetAtt get(long playerId);
	
	public boolean add(PetAtt info);
	
	public boolean update(PetAtt info);
}
