package com.chuangyou.xianni.sql.dao;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.chuangyou.xianni.entity.guild.GuildMemberInfo;

public interface GuildMemberDao {

	public ConcurrentHashMap<Long, GuildMemberInfo> getGuildAll(int guildId);
	
	public List<GuildMemberInfo> getAll();
	
	public boolean add(GuildMemberInfo info);
	
	public boolean update(GuildMemberInfo info);
	
	public boolean remove(long playerId);
	
	public boolean removeAll(int guildId);
}
