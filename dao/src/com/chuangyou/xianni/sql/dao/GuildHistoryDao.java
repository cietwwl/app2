package com.chuangyou.xianni.sql.dao;

import com.chuangyou.xianni.entity.guild.GuildMemberInfo;

public interface GuildHistoryDao {

	public GuildMemberInfo get(long playerId);
	
	public boolean add(GuildMemberInfo info);
	
	public boolean update(GuildMemberInfo info);
}
