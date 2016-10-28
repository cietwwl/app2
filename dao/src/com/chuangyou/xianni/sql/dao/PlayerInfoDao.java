package com.chuangyou.xianni.sql.dao;

import java.util.Date;
import java.util.List;

import com.chuangyou.xianni.entity.arena.FightData;
import com.chuangyou.xianni.entity.player.PlayerInfo;
import com.chuangyou.xianni.entity.player.PlayerJoinInfo;
import com.chuangyou.xianni.entity.player.PlayerTimeInfo;

public interface PlayerInfoDao {

	public List<FightData> getArenaOpponent(int myFight, int maxFight, int minFight);

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
	
	public List<PlayerInfo> getPlayerList(String account,String user,int userType,Date regBeginTime,Date regEndTime,int startLv,int endLv,int page,int pageSize);

	public int getCount(String account,String user,int userType,Date regBeginTime,Date regEndTime,int startLv,int endLv);
}
