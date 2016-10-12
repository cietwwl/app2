package com.chuangyou.xianni.sql.dao;

import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.entity.guild.GuildDonateCfg;
import com.chuangyou.xianni.entity.guild.GuildJobCfg;
import com.chuangyou.xianni.entity.guild.GuildJobPowerCfg;
import com.chuangyou.xianni.entity.guild.GuildSkillCfg;
import com.chuangyou.xianni.entity.guild.GuildSystemCfg;
import com.chuangyou.xianni.entity.guild.GuildWarehouseCfg;

public interface GuildConfigDao {
	public Map<Integer, GuildJobCfg> getJob(); 
	
	public Map<Integer, GuildJobPowerCfg> getJobPower();
	
	public Map<Integer, GuildDonateCfg> getDonate();
	
	public Map<Integer, GuildWarehouseCfg> getWarehouse();
	
	public Map<Integer, Map<Integer, GuildSkillCfg>> getSkill();
	
	public List<GuildSystemCfg> getGuildCfg();
}
