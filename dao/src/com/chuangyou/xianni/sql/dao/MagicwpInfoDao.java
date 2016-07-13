package com.chuangyou.xianni.sql.dao;

import java.util.Map;

import com.chuangyou.xianni.entity.magicwp.MagicwpInfo;

public interface MagicwpInfoDao {

	public Map<Integer, MagicwpInfo> getAll(long playerId);
	
	public boolean add(MagicwpInfo info);
	
	public boolean update(MagicwpInfo info);
}
