package com.chuangyou.xianni.soul.template;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.entity.soul.CardLvConfig;
import com.chuangyou.xianni.entity.soul.CardSkillConfig;
import com.chuangyou.xianni.entity.soul.CardStarConfig;
import com.chuangyou.xianni.entity.soul.SoulCardConfig;
import com.chuangyou.xianni.entity.soul.SoulMakeConfig;
import com.chuangyou.xianni.sql.dao.DBManager;

public class SoulTemplateMgr {
	
	private static Map<Integer, SoulCardConfig> map;
	
	private static Map<Integer, CardLvConfig> lvMap; 
	
	private static Map<Integer, CardStarConfig> starMap;
	
	private static Map<Integer, CardSkillConfig> skillMap;
	
	/**  材料制作概率MAP  */
	private static Map<Integer, SoulMakeConfig> makeMap;
	
	public static List<Integer> skillPool;
	
	public static int MAX_CARD_LV;
	
	/**
	 * QTE——CD时间
	 */
	public static final int QTE_CD = 5*60*1000;
 	
	public static boolean init(){
		return reloadTemplateData();
	}
	
	public static boolean reloadTemplateData(){
		skillPool = new ArrayList<>();
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
		for (int i : skillMap.keySet()) {
			if(i>2000)skillPool.add(i);
		}
		if(skillPool.size()==0)return false;
		
		makeMap = DBManager.getSoulDao().getSoulMakeConfig();
		if(makeMap == null)return false;
		
		
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
	
}
