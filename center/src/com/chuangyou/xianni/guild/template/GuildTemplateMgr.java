package com.chuangyou.xianni.guild.template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.entity.guild.GuildDonateCfg;
import com.chuangyou.xianni.entity.guild.GuildJobCfg;
import com.chuangyou.xianni.entity.guild.GuildJobPowerCfg;
import com.chuangyou.xianni.entity.guild.GuildSkillCfg;
import com.chuangyou.xianni.entity.guild.GuildSystemCfg;
import com.chuangyou.xianni.entity.guild.GuildWarehouseCfg;
import com.chuangyou.xianni.sql.dao.DBManager;

public class GuildTemplateMgr {

	public static Map<Integer, GuildJobCfg> jobMap = new HashMap<>();
	
	public static Map<Integer, GuildJobPowerCfg> powerMap = new HashMap<>();
	
	public static Map<Integer, GuildDonateCfg> donateMap = new HashMap<>();
	
	public static Map<Integer, GuildWarehouseCfg> warehouseMap = new HashMap<>();
	
	public static Map<Integer, Map<Integer, GuildSkillCfg>> skillMap = new HashMap<>();
	
	public static List<GuildSystemCfg> guildList = new ArrayList<>();
	
	public static boolean init(){
		return reloadTemplate();
	}
	
	public static boolean reloadTemplate(){
		jobMap = DBManager.getGuildConfigDao().getJob();
		powerMap = DBManager.getGuildConfigDao().getJobPower();
		donateMap = DBManager.getGuildConfigDao().getDonate();
		warehouseMap = DBManager.getGuildConfigDao().getWarehouse();
		skillMap = DBManager.getGuildConfigDao().getSkill();
		guildList = DBManager.getGuildConfigDao().getGuildCfg();
		return true;
	}

	public static Map<Integer, GuildJobCfg> getJobMap() {
		return jobMap;
	}

	public static Map<Integer, GuildJobPowerCfg> getPowerMap() {
		return powerMap;
	}

	public static Map<Integer, GuildDonateCfg> getDonateMap() {
		return donateMap;
	}

	public static Map<Integer, GuildWarehouseCfg> getWarehouseMap() {
		return warehouseMap;
	}

	public static Map<Integer, Map<Integer, GuildSkillCfg>> getSkillMap() {
		return skillMap;
	}

	public static void setSkillMap(Map<Integer, Map<Integer, GuildSkillCfg>> skillMap) {
		GuildTemplateMgr.skillMap = skillMap;
	}
	
	public static GuildSkillCfg getGuildSkill(int guildSkillId, int level){
		if(skillMap == null){
			return null;
		}
		Map<Integer, GuildSkillCfg> skillLevels = skillMap.get(guildSkillId);
		if(skillLevels == null){
			return null;
		}
		GuildSkillCfg cfg = skillLevels.get(level);
		return cfg;
	}

	public static List<GuildSystemCfg> getGuildList() {
		return guildList;
	}
}
