package com.chuangyou.xianni.sql.dao;

import java.util.Map;

import com.chuangyou.xianni.entity.magicwp.MagicwpBanInfo;

public interface MagicwpBanInfoDao {

	public Map<Integer, MagicwpBanInfo> getAll(long playerId);
	
	public boolean add(MagicwpBanInfo info);
	
	public boolean update(MagicwpBanInfo info);
}
