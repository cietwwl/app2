package com.chuangyou.xianni.common.template;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.constant.LevelUpType;
import com.chuangyou.xianni.entity.level.LevelUp;
import com.chuangyou.xianni.sql.dao.DBManager;

public class LevelUpTempleteMgr {

	private static Map<Integer, Map<Integer, LevelUp>> levelUpMap = new HashMap<>();
	
	private static Map<Integer, List<LevelUp>> levelUpListMap = new HashMap<>();
	
	private static Map<Integer, Integer> maxLevelMap = new HashMap<>();
	
	public static boolean init(){
		return reloadTemplete();
	}
	
	public static boolean reloadTemplete(){
		levelUpListMap = DBManager.getLevelUpDao().getAll();
		
		levelUpMap = new HashMap<>();
		for(int type:levelUpListMap.keySet()){
			
			int curMaxLevel = 0;
			
			List<LevelUp> typeLevelList = levelUpListMap.get(type);
			
			Map<Integer, LevelUp> typeLevelMap = levelUpMap.get(type);
			if(typeLevelMap == null){
				typeLevelMap = new HashMap<>();
				levelUpMap.put(type, typeLevelMap);
			}
			for(LevelUp levelUp:typeLevelList){
				if(levelUp.getLevel() > curMaxLevel){
					curMaxLevel = levelUp.getLevel();
				}
				typeLevelMap.put(levelUp.getLevel(), levelUp);
			}
			
			maxLevelMap.put(type, curMaxLevel);
			
			Collections.sort(typeLevelList);
		}
		return true;
	}
	
	/**
	 * 根据人物总经验获取人物等级
	 * @param totalExp
	 * @return
	 */
	public static int getPlayerLevel(long totalExp){
		List<LevelUp> list = levelUpListMap.get(LevelUpType.PLAYER);
		if(list == null){
			return 1;
		}
		long templeteTotalExp = 0;
		for(LevelUp levelUp:list){
			templeteTotalExp += levelUp.getExp();
			if(templeteTotalExp > totalExp){
				return levelUp.getLevel();
			}
		}
		return maxLevelMap.get(LevelUpType.PLAYER);
	}
	
	/**
	 * 根据人物等级获得当前等级需要的总经验
	 * @param level
	 * @return
	 */
	public static long getPlayerLevelNeedExp(int level){
		List<LevelUp> list = levelUpListMap.get(LevelUpType.PLAYER);
		if(list == null){
			return 0;
		}
		long templeteTotalExp = 0;
		for(LevelUp levelUp:list){
			if(levelUp.getLevel() == level){
				return templeteTotalExp;
			}
			templeteTotalExp += levelUp.getExp();
		}
		LevelUp maxLevel = levelUpMap.get(LevelUpType.PLAYER).get(maxLevelMap.get(LevelUpType.PLAYER));
		return maxLevel.getExp();
	}
	
	/**
	 * 获取人物最大等级
	 * @return
	 */
	public static int getPlayerMaxLevel(){
		return maxLevelMap.get(LevelUpType.PLAYER);
	}
	
	/**
	 * 获取人物指定等级信息
	 * @param level
	 * @return
	 */
	public static LevelUp getPlayerLevelUp(int level){
		Map<Integer, LevelUp> typeLevelMap = levelUpMap.get(LevelUpType.PLAYER);
		if(typeLevelMap == null){
			return new LevelUp();
		}
		return typeLevelMap.get(level);
	}

	public static Map<Integer, Map<Integer, LevelUp>> getLevelUpMap() {
		return levelUpMap;
	}

	public static void setLevelUpMap(Map<Integer, Map<Integer, LevelUp>> levelUpMap) {
		LevelUpTempleteMgr.levelUpMap = levelUpMap;
	}

	public static Map<Integer, List<LevelUp>> getLevelUpListMap() {
		return levelUpListMap;
	}

	public static void setLevelUpListMap(Map<Integer, List<LevelUp>> levelUpListMap) {
		LevelUpTempleteMgr.levelUpListMap = levelUpListMap;
	}

	public static Map<Integer, Integer> getMaxLevelMap() {
		return maxLevelMap;
	}

	public static void setMaxLevelMap(Map<Integer, Integer> maxLevelMap) {
		LevelUpTempleteMgr.maxLevelMap = maxLevelMap;
	}

}
