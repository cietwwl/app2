package com.chuangyou.xianni.sql.dao;

import java.util.List;

import com.chuangyou.xianni.entity.guild.GuildInfo;

public interface GuildInfoDao {

	public List<GuildInfo> getAll();
	
	public int getMaxGuildId();
	
	public int getGuildNumByName(String name);
	
	public boolean remove(GuildInfo info);
	
	public boolean add(GuildInfo info);
	
	public boolean update(GuildInfo info);
}
