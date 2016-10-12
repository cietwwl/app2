package com.chuangyou.xianni.sql.dao;

import com.chuangyou.xianni.entity.arena.ArenaInfo;

public interface ArenaInfoDao {
	public boolean saveOrUpdata(ArenaInfo arenaInfo);

	public ArenaInfo get(long playerId);
}
