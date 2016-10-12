package com.chuangyou.xianni.sql.dao;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.chuangyou.xianni.entity.guild.GuildWarehouseItemInfo;

public interface GuildWarehouseItemDao {

	public List<GuildWarehouseItemInfo> getAll();
	
	public ConcurrentHashMap<Integer, GuildWarehouseItemInfo> getAll(int guildId);
	
	public boolean add(GuildWarehouseItemInfo info);
	
	public boolean update(GuildWarehouseItemInfo info);
	
	public boolean remove(int guildId, int itemTempId);
	
	public boolean removeAll(int guildId);
}
