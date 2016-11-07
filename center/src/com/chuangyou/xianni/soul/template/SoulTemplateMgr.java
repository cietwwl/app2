package com.chuangyou.xianni.soul.template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.entity.soul.CardComboConfig;
import com.chuangyou.xianni.entity.soul.CardLvConfig;
import com.chuangyou.xianni.entity.soul.CardRateConfig;
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
	 * 抽卡概率配置表
	 */
	private static Map<Integer, CardRateConfig> cardRateCofig;
	
	/**
	 * 卡牌颜色池
	 */
	private static Map<Integer, List<SoulCardConfig>> colorPoolsMap;
	
	 
	
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
	
	/**
	 * 每天免费可抽卡次数
	 */
	public static final int FREE_DRAW_CARD_TIME_DAY = 10;
 	
	public static boolean init(){
		return reloadTemplateData();
	}
	
	public static boolean reloadTemplateData(){
		skillPool = new ArrayList<>();
		fuseSkillPoolMap = new HashMap<>();
		map = DBManager.getSoulDao().getCardConfigs();
		if(map == null || map.size()==0){
			return false;
		}
		
		colorPoolsMap = new HashMap<Integer, List<SoulCardConfig>>();
		Iterator<SoulCardConfig> it = map.values().iterator();
		while(it.hasNext()){
			SoulCardConfig config = it.next();
			if(!colorPoolsMap.containsKey(config.getQuality())){
				colorPoolsMap.put(config.getQuality(), new ArrayList<>());
			}
			colorPoolsMap.get(config.getQuality()).add(config);
		}
		
		
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
		
		cardRateCofig = DBManager.getSoulDao().getCardRateConfig();
		if(cardRateCofig == null || cardRateCofig.values().size()==0){
			return false;
		}
				
		return true;
	}
	
	public static boolean reloadMap(){//tb_z_soul_template
		map = DBManager.getSoulDao().getCardConfigs();
		return true;
	}
	public static boolean reloadLvMap(){//tb_z_soul_cardLevel
		lvMap = DBManager.getSoulDao().getCardLvConfigs();
		return true;
	}
	public static boolean reloadStarMap(){//tb_z_soul_star
		starMap = DBManager.getSoulDao().getCardStarConfig();
		return true;
	}
	public static boolean reloadSkillMap(){//tb_z_soul_skill
		skillMap = DBManager.getSoulDao().getCardSkillCofig();
		return true;
	}
	public static boolean reloadMakeMap(){//tb_z_soul_proficiency
		makeMap = DBManager.getSoulDao().getSoulMakeConfig();
		return true;
	}
	public static boolean reloadFuseSkillMap(){//tb_z_soul_fuseSkill
		fuseSkillMap = DBManager.getSoulDao().getFuseSkillConfig();
		return true;
	}
	public static boolean reloadFuseItemConfigMap(){//tb_z_soul_fuseItem
		fuseItemConfigMap = DBManager.getSoulDao().getFuseItemConfig();
		return true;
	}
	public static boolean reloadCardComboMap(){//tb_z_soul_combo
		cardComboMap = DBManager.getSoulDao().getCardComboConfig();
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

	public static Map<Integer, CardRateConfig> getCardRateCofig() {
		return cardRateCofig;
	}

	public static Map<Integer, List<SoulCardConfig>> getColorPoolsMap() {
		return colorPoolsMap;
	}
	
}
