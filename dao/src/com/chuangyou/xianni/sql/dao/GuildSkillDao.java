package com.chuangyou.xianni.sql.dao;

import java.util.Map;

import com.chuangyou.xianni.entity.guild.GuildSkillInfo;

public interface GuildSkillDao {

	public Map<Integer, GuildSkillInfo> getAll(long playerId);
	
	public boolean add(GuildSkillInfo info);
	
	public boolean update(GuildSkillInfo info);
}
