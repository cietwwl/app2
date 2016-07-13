package com.chuangyou.xianni.sql.dao;

import java.util.List;
import com.chuangyou.xianni.entity.player.PlayerInfo;
import com.chuangyou.xianni.entity.player.PlayerJoinInfo;
import com.chuangyou.xianni.entity.player.PlayerTimeInfo;

public interface PlayerInfoDao {
	public boolean add(PlayerInfo playerInfo);

	public boolean update(PlayerInfo playerInfo);

	public PlayerInfo getPlayerInfo(long playerId);

	public List<PlayerInfo> getByUserId(long userId);

	public List<PlayerInfo> getByNickName(String nickName);

	public long getMaxPlayerId();

	public boolean addJoinInfo(PlayerJoinInfo playerJoinInfo);

	public boolean updateJoinInfo(PlayerJoinInfo playerJoinInfo);

	public PlayerJoinInfo getJoinInfo(long playerId);

	public boolean addTimeInfo(PlayerTimeInfo playerTimeInfo);

	public boolean updateTimeInfo(PlayerTimeInfo playerTimeInfo);

	public PlayerTimeInfo getTimeInfo(long playerId);

	public PlayerInfo getPlayerInfo(String nickName);
}
