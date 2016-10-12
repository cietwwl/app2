package com.chuangyou.xianni.guild;

import java.util.HashMap;
import java.util.Map;

import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.guild.GuildMemberInfo;
import com.chuangyou.xianni.entity.guild.GuildSkillCfg;
import com.chuangyou.xianni.entity.guild.GuildSkillInfo;
import com.chuangyou.xianni.entity.property.BaseProperty;
import com.chuangyou.xianni.event.AbstractEvent;
import com.chuangyou.xianni.guild.manager.GuildManager;
import com.chuangyou.xianni.guild.struct.Guild;
import com.chuangyou.xianni.guild.template.GuildTemplateMgr;
import com.chuangyou.xianni.interfaces.IInventory;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.skill.SkillUtil;
import com.chuangyou.xianni.sql.dao.DBManager;

public class GuildInventory extends AbstractEvent implements IInventory {
	
	private GamePlayer player;
	
	/**
	 * 上次所在帮派中自己的信息
	 */
	private GuildMemberInfo historyMemberInfo;
	
	private Map<Integer, GuildSkillInfo> skillMap;
	
	public GuildInventory(GamePlayer player) {
		// TODO Auto-generated constructor stub
		this.player = player;
	}
	
	/**
	 * 获取玩家最后一次所在帮派信息
	 * @return
	 */
	public GuildMemberInfo getHistoryMember(){
		if(this.historyMemberInfo == null){
			historyMemberInfo = new GuildMemberInfo();
			historyMemberInfo.setPlayerId(player.getPlayerId());
			historyMemberInfo.setGuildId(0);
			historyMemberInfo.setJob(0);;
			historyMemberInfo.setContributionTotal(0);
			historyMemberInfo.setContribution(0);
			historyMemberInfo.setFortune(0);
			historyMemberInfo.setOp(Option.Insert);
		}
		return this.historyMemberInfo;
	}

	public Map<Integer, GuildSkillInfo> getSkillMap() {
		if(skillMap == null) skillMap = new HashMap<>();
		return skillMap;
	}
	
	public void addGuildSkill(GuildSkillInfo skillInfo){
		this.getSkillMap().put(skillInfo.getGuildSkillId(), skillInfo);
	}

	@Override
	public boolean loadFromDataBase() {
		// TODO Auto-generated method stub
		historyMemberInfo = DBManager.getGuildHistoryDao().get(player.getPlayerId());
		skillMap = DBManager.getGuildSkillDao().getAll(player.getPlayerId());
		return true;
	}

	@Override
	public boolean unloadData() {
		// TODO Auto-generated method stub
		this.historyMemberInfo = null;
		
		if(this.skillMap != null){
			this.skillMap.clear();
			this.skillMap = null;
		}
		
		this.player = null;
		return true;
	}

	@Override
	public boolean saveToDatabase() {
		// TODO Auto-generated method stub
//		Guild guild = GuildManager.getIns().getPlayerGuild(player.getPlayerId());
//		if(guild != null){
//			guild.saveToDatabase();
//		}
		if(this.historyMemberInfo != null){
			if(this.historyMemberInfo.getOp() == Option.Insert){
				DBManager.getGuildHistoryDao().add(historyMemberInfo);
			}else if(this.historyMemberInfo.getOp() == Option.Update){
				DBManager.getGuildHistoryDao().update(historyMemberInfo);
			}
		}
		
		if(skillMap != null && skillMap.size() > 0){
			for(GuildSkillInfo info: skillMap.values()){
				short op = info.getOp();
				if(op == Option.Insert){
					DBManager.getGuildSkillDao().add(info);
				}else if(op == Option.Update){
					DBManager.getGuildSkillDao().update(info);
				}
			}
		}
		return true;
	}
	
	public void getTotalSkillProperty(BaseProperty guildSkillData, BaseProperty guildSkillPer){
		Guild guild = GuildManager.getIns().getPlayerGuild(player.getPlayerId());
		if(guild == null) return;
		for(GuildSkillInfo info: skillMap.values()){
			GuildSkillCfg cfg = GuildTemplateMgr.getGuildSkill(info.getGuildSkillId(), info.getLevel());
			if(cfg == null) continue;
			
			int skillId = cfg.getSkillId();
			SkillUtil.getSkillProperty(guildSkillData, guildSkillPer, skillId);
		}
	}
	
	public void updateSkillProperty(){
		if(player.getArmyInventory() != null){
			BaseProperty data = new BaseProperty();
			BaseProperty per = new BaseProperty();
			
			getTotalSkillProperty(data, per);
			player.getArmyInventory().getHero().addGuildSkill(data, per);
			player.getArmyInventory().updateProperty();
		}
	}

}
