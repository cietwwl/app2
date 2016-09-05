package com.chuangyou.xianni.soul.template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.entity.soul.CardComboConfig;
import com.chuangyou.xianni.entity.soul.CardLvConfig;
import com.chuangyou.xianni.entity.soul.CardSkillConfig;
import com.chuangyou.xianni.entity.soul.CardStarConfig;
import com.chuangyou.xianni.entity.soul.FuseItemConfig;
import com.chuangyou.xianni.entity.soul.SoulCardConfig;
import com.chuangyou.xianni.entity.soul.SoulFuseSkillConfig;
import com.chuangyou.xianni.entity.soul.SoulMakeConfig;
import com.chuangyou.xianni.sql.dao.DBManager;

public class SoulTemplateMgr {
	
	private static Map<Integer, SoulCardConfig> map;
	
	private static Map<Integer, CardLvConfig> lvMap; 
	
	private static Map<Integer, CardStarConfig> starMap;
	/**
	 * 卡牌技能配置
	 */
	private static Map<Integer, CardSkillConfig> skillMap;
	
	/**
	 * 卡牌技能随机池
	 */
	public static List<CardSkillConfig> skillPool;
	
	
	/**  材料制作概率MAP  */
	private static Map<Integer, SoulMakeConfig> makeMap;
	

	/**
	 * 融合技能字典
	 */
	private static Map<Integer, SoulFuseSkillConfig> fuseSkillMap;
	
	/**
	 * 融合技能池字典
	 */
	private static Map<Integer, List<SoulFuseSkillConfig>> fuseSkillPoolMap;
	
	/**
	 * 融合制作材料时.生成相关物品配置表
	 */
	private static Map<Integer, FuseItemConfig> fuseItemConfigMap;
	
	/**
	 * 卡牌组合配置表
	 */
	private static Map<Integer, CardComboConfig> cardComboMap;
	
	/**
	 * 最大卡牌等级
	 */
	public static int MAX_CARD_LV;
	
	/**
	 * 技能有限时间
	 */
	public static final int MAX_SOUL_SKILL_CD = 24*60*60*1000;
	
	/**
	 * QTE——CD时间
	 */
	public static final int QTE_CD = 5*60*1000;
 	
	public static boolean init(){
		return reloadTemplateData();
	}
	
	public static boolean reloadTemplateData(){
		skillPool = new ArrayList<>();
		fuseSkillPoolMap = new HashMap<>();
		map = DBManager.getSoulDao().getCardConfigs();
		if(map == null)return false;
		lvMap = DBManager.getSoulDao().getCardLvConfigs();
		if(lvMap == null)return false;
		for (int lv : lvMap.keySet()) {
			MAX_CARD_LV = Math.max(lv, MAX_CARD_LV);
		}
		starMap = DBManager.getSoulDao().getCardStarConfig();
		if(starMap == null)return false;
		skillMap = DBManager.getSoulDao().getCardSkillCofig();
		if(skillMap == null)return false;
		for (CardSkillConfig info : skillMap.values()) {
			if(info.getId()>2000)skillPool.add(info);
		}
		if(skillPool.size()==0)return false;
		
		makeMap = DBManager.getSoulDao().getSoulMakeConfig();
		if(makeMap == null)return false;
		
		fuseSkillMap = DBManager.getSoulDao().getFuseSkillConfig();
		if(fuseSkillMap == null)return false;
		for (SoulFuseSkillConfig info : fuseSkillMap.values()) {
			if(!fuseSkillPoolMap.containsKey(info.getPoolId())){
				fuseSkillPoolMap.put(info.getPoolId(), new ArrayList<>());
			}
			fuseSkillPoolMap.get(info.getPoolId()).add(info);
		}
		fuseItemConfigMap = DBManager.getSoulDao().getFuseItemConfig();
		if(fuseItemConfigMap == null)return false;
		
		cardComboMap = DBManager.getSoulDao().getCardComboConfig();
		if(cardComboMap == null || cardComboMap.values().size()==0){
			return false;
		}
		return true;
	}
	
	
	public static SoulCardConfig getCardConfig(int cardId){
		return map.get(cardId);
	}
	
	public static CardLvConfig getCardLvConfig(int lev){
		return lvMap.get(lev);
	}
	
	public static CardStarConfig getCardStarConfig(int star){
		return starMap.get(star);
	}
	
	public static CardSkillConfig getCardSkillCofig(int id){
		return skillMap.get(id);
	}

	public static Map<Integer, List<SoulFuseSkillConfig>> getFuseSkillPoolMap() {
		return fuseSkillPoolMap;
	}

	public static Map<Integer, FuseItemConfig> getFuseItemConfigMap() {
		return fuseItemConfigMap;
	}

	public static Map<Integer, CardComboConfig> getCardComboMap() {
		return cardComboMap;
	}
	
}
