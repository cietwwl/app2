package com.chuangyou.xianni.sql.dao;

import java.util.List;

import com.chuangyou.xianni.entity.guild.GuildLogInfo;

public interface GuildLogInfoDao {

	public List<GuildLogInfo> getAll(int guildId);
	
	public boolean add(GuildLogInfo info);
	
	public boolean remove(GuildLogInfo info);
}
