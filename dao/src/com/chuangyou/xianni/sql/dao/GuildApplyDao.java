package com.chuangyou.xianni.sql.dao;

import java.util.concurrent.ConcurrentHashMap;

import com.chuangyou.xianni.entity.guild.GuildApplyInfo;

public interface GuildApplyDao {

	public ConcurrentHashMap<Long, GuildApplyInfo> getGuildAll(int guildId);
	
	public boolean add(GuildApplyInfo info);
	
	public boolean remove(int guildId, long playerId);
	
	public boolean removeAll(int guildId);
}
