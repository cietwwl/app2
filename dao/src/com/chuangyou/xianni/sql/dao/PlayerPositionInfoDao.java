package com.chuangyou.xianni.sql.dao;

import com.chuangyou.xianni.entity.player.PlayerPositionInfo;

public interface PlayerPositionInfoDao {
	boolean save(PlayerPositionInfo pinfo);
	
	PlayerPositionInfo get(long playerId);
}
