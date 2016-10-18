package com.chuangyou.xianni.truck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.truck.TruckFun;
import com.chuangyou.xianni.entity.truck.TruckInfo;
import com.chuangyou.xianni.entity.truck.TruckSkillConfig;
import com.chuangyou.xianni.entity.truck.TruckSkillInfo;
import com.chuangyou.xianni.event.AbstractEvent;
import com.chuangyou.xianni.guild.manager.GuildManager;
import com.chuangyou.xianni.guild.struct.Guild;
import com.chuangyou.xianni.interfaces.IInventory;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.sql.dao.DBManager;

public class TruckInventory extends AbstractEvent implements IInventory {
	
	/** 为任何状态 */
	public static final int STATE_NONE = 0;
	/** 运镖中-个人 */
	public static final int STATE_RUNING_P = 1;
	/** 护镖中-个人 */
	public static final int STATE_PROTING_P = 2;
	/** 运镖中-帮派 */
	public static final int STATE_RUNING_G = 3;
	/** 护镖中-帮派 */
	public static final int STATE_PROTING_G = 4;
	/** 劫镖中 */
	public static final int STATE_ROBING = 5;
	
	/** 个人镖车 */
	public static final int TYPE_P = 1;
	/** 帮派镖车 */
	public static final int TYPE_G = 2;
	
	private GamePlayer player;
	/** 个人镖车 */
	private TruckInfo myTruckInfo;
	/** 镖师 */
	private TruckInfo truckerInfo;
	/** 帮派镖车 */
	private TruckInfo guildTruckInfo;
	/** 技能(镖车技能，帮派镖车技能，镖师技能，天赋) */
	private Map<Integer, TruckSkillInfo> skills;
	/** 状态 */
	private int state;
	
	public TruckInventory(GamePlayer player)
	{
		this.player = player;
	}
	
	@Override
	public boolean loadFromDataBase() {
		state = STATE_NONE;
		myTruckInfo = DBManager.getTruckDao().getTruckInfos(player.getPlayerId(), TruckInfo.PERSONAL_TRUCK);
		if(myTruckInfo == null)
		{
			createTrucker(this.player.getPlayerId(), TruckInfo.PERSONAL_TRUCK);
		}
		truckerInfo = DBManager.getTruckDao().getTruckInfos(player.getPlayerId(), TruckInfo.TRUCKER);
		if(truckerInfo == null)
		{
			createTrucker(this.player.getPlayerId(),TruckInfo.TRUCKER);
		}
		skills = new HashMap<Integer, TruckSkillInfo>();
		//获取镖车技能
		List<TruckSkillInfo> mySkillInfos = DBManager.getTruckDao().getTruckSkills(player.getPlayerId(), 1);
		if(mySkillInfos != null)
		{
			for(TruckSkillInfo skillInfo : mySkillInfos)
			{
				skills.put(skillInfo.getSkillType(), skillInfo);
			}
		}
		if(skills.size() == 0)	//创建默认天赋
		{
			createGift();
		}
		//帮派镖车技能
		Guild guild = GuildManager.getIns().getPlayerGuild(this.player.getPlayerId());
		if(guild != null)
		{
			guildTruckInfo = DBManager.getTruckDao().getTruckInfos(guild.getGuildInfo().getGuildId(), TruckInfo.GUILD_TRUCK);
			List<TruckSkillInfo> guildSkillInfo = DBManager.getTruckDao().getTruckSkills(guild.getGuildInfo().getGuildId(), 2);
			if(guildSkillInfo != null)
			{
				for(TruckSkillInfo skillInfo : guildSkillInfo)
				{
					skills.put(skillInfo.getSkillType(), skillInfo);
				}
			}
		}
		return true;
	}

	@Override
	public boolean unloadData() {
		state = STATE_NONE;
		myTruckInfo = null;
		guildTruckInfo = null;
		truckerInfo = null;
		if(skills != null)
			skills.clear();
		skills = null;
		return true;
	}

	@Override
	public boolean saveToDatabase() {
		if(myTruckInfo != null && myTruckInfo.getOp() == Option.Update)
			DBManager.getTruckDao().updateTruckInfo(myTruckInfo);
		if(truckerInfo != null && truckerInfo.getOp() == Option.Update)
			DBManager.getTruckDao().updateTruckInfo(truckerInfo);
		if(guildTruckInfo != null && guildTruckInfo.getOp() == Option.Update)
			DBManager.getTruckDao().updateTruckInfo(guildTruckInfo);
		if(skills != null)
		{
			for(TruckSkillInfo skillInfo : skills.values())
			{
				if(skillInfo.getOp() == Option.Update)
					DBManager.getTruckDao().updateTruckSkill(skillInfo);
			}
		}
		return true;
	}
	
	/**
	 * 添加一个镖车技能
	 * @param skillInfo
	 */
	public boolean addSkill(TruckSkillInfo skillInfo)
	{
		if(skills.containsKey(skillInfo.getSkillType()))
		{
			skillInfo.setOp(Option.Update);
		}
		else
		{
			skillInfo.setOp(Option.Insert);
			DBManager.getTruckDao().addTruckSkill(skillInfo);
		}
		skills.put(skillInfo.getSkillType(), skillInfo);
		return true;
	}
	
	/**
	 * 获取镖车配置，通过效果类型
	 * @return
	 */
	public List<TruckSkillConfig> getSkillConfigByValueType(int valueType)
	{
		List<TruckSkillConfig> ret = new ArrayList<TruckSkillConfig>();
		for(TruckSkillInfo skillInfo : skills.values())
		{
			TruckSkillConfig skillConfig = TruckTempMgr.getAllSkillConfig().get(skillInfo.getSkillId());
			if(skillConfig.getValueType() == valueType)
			{
				ret.add(skillConfig);
			}
		}
		return ret;
	}
	
	/**
	 * 按ValueType整理
	 * @return
	 */
	public Map<Integer, List<TruckSkillConfig>> getSkillInfos()
	{
		Map<Integer, List<TruckSkillConfig>> ret = new HashMap<Integer, List<TruckSkillConfig>>();
		for(TruckSkillInfo skillInfo : skills.values())
		{
			TruckSkillConfig skillConfig = TruckTempMgr.getAllSkillConfig().get(skillInfo.getSkillId());
			if(skillConfig.getValueType() == 0) continue;
			if(!ret.containsKey(skillConfig.getValueType()))
				ret.put(skillConfig.getValueType(), new ArrayList<TruckSkillConfig>());
			ret.get(skillConfig.getValueType()).add(skillConfig);
		}
		return ret;
	}
	
	/**
	 * 创建一个镖师/镖师/帮派镖车
	 * @return
	 */
	public void createTrucker(long ownerId, int type)
	{
		TruckInfo truckInfo = new TruckInfo();
		truckInfo.setExp(0);
		truckInfo.setLevel(1);
		truckInfo.setOwnerId(ownerId);
		truckInfo.setSkillPoint(100);
		truckInfo.setType(type);
		truckInfo.setOp(Option.Insert);
		DBManager.getTruckDao().addTruckInfo(truckInfo);
		if(type == TruckInfo.PERSONAL_TRUCK)
			myTruckInfo = truckInfo;
		else 
			guildTruckInfo = truckInfo;
	}
	
	/**
	 * 创建一个帮派镖车
	 */
	public void createGuildTrucker(int guildId)
	{
		//帮派镖车技能
		Guild guild = GuildManager.getIns().getPlayerGuild(this.player.getPlayerId());
		if(guild != null)
		{
			guildTruckInfo = DBManager.getTruckDao().getTruckInfos(guild.getGuildInfo().getGuildId(), TruckInfo.GUILD_TRUCK);
			if(guildTruckInfo == null)
				createTrucker(guildId, TruckInfo.GUILD_TRUCK);
		}
	}
	
	/**
	 * 销毁帮派镖车相关
	 */
	public void destoryGuildTruckAbout()
	{
		guildTruckInfo = null;
		
	}
	
	/**
	 * 创建天赋
	 */
	private void createGift()
	{
		for(TruckFun fun : TruckTempMgr.getTruckFuncs().values())
		{
			int skillId = fun.getId();
			TruckSkillConfig skillConfig = TruckTempMgr.getAllSkillConfig().get(skillId);
			if(skillConfig == null) 
			{
				Log.error("天赋配置不正确 skillId = " + skillId);
				continue;
			}
			TruckSkillInfo skillInfo = new TruckSkillInfo();
			skillInfo.setOwnerId(player.getPlayerId());
			skillInfo.setLevel(1);
			skillInfo.setType(TruckSkillInfo.SKILL_GIFT);
			skillInfo.setSkillId(skillConfig.getId());
			skillInfo.setTrucktype(TYPE_P);
			skillInfo.setSkillType(skillConfig.getSkillType());
			skillInfo.setOp(Option.Insert);
			addSkill(skillInfo);
		}
	}
	
	/**
	 * 更新状态
	 * @param s
	 */
	public void setState(int s)
	{
		this.state = s;
	}

	public TruckInfo getMyTruckInfo() {
		return myTruckInfo;
	}
	
	public TruckInfo getTruckerInfo() {
		return truckerInfo;
	}

	public TruckInfo getGuildTruckInfo() {
		return guildTruckInfo;
	}

	public Map<Integer, TruckSkillInfo> getSkills() {
		return skills;
	}
	
	public int getState(){
		return state;
	}

}
