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
	 * 根据总经验获取当前等级
	 * @param type
	 * @param totalExp
	 * @return
	 */
	public static int getLevelByTotalExp(int type, long totalExp){
		List<LevelUp> list = levelUpListMap.get(type);
		if(list == null){
			return 1;
		}
		long templateTotalExp = 0;
		for(LevelUp levelUp: list){
			templateTotalExp += levelUp.getExp();
			if(templateTotalExp > totalExp){
				return levelUp.getLevel();
			}
		}
		return maxLevelMap.get(type);
	}
	
	/**
	 * 获取指定等级需要的总经验
	 * @param type
	 * @param level
	 * @return
	 */
	public static long getLevelNeedExp(int type, int level){
		List<LevelUp> list = levelUpListMap.get(type);
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
		LevelUp maxLevel = levelUpMap.get(type).get(maxLevelMap.get(type));
		return maxLevel.getExp();
	}
	/**
	 * 获取最大等级
	 * @param type
	 * @return
	 */
	public static int getMaxLevel(int type){
		if(!maxLevelMap.containsKey(type)){
			return 0;
		}
		return maxLevelMap.get(type);
	}
	
	/**
	 * 获取当前等级配置
	 * @param type
	 * @param level
	 * @return
	 */
	public static LevelUp getLevelUp(int type, int level){
		Map<Integer, LevelUp> typeLevelMap = levelUpMap.get(type);
		if(typeLevelMap == null){
			return new LevelUp();
		}
		return typeLevelMap.get(level);
	}
	
	/**
	 * 根据人物总经验获取人物等级
	 * @param totalExp
	 * @return
	 */
	public static int getPlayerLevel(long totalExp){
		return getLevelByTotalExp(LevelUpType.PLAYER, totalExp);
	}
	
	/**
	 * 根据人物等级获得当前等级需要的总经验
	 * @param level
	 * @return
	 */
	public static long getPlayerLevelNeedExp(int level){
		return getLevelNeedExp(LevelUpType.PLAYER, level);
	}
	
	/**
	 * 获取人物最大等级
	 * @return
	 */
	public static int getPlayerMaxLevel(){
		return getMaxLevel(LevelUpType.PLAYER);
	}
	
	/**
	 * 获取人物指定等级信息
	 * @param level
	 * @return
	 */
	public static LevelUp getPlayerLevelUp(int level){
		return getLevelUp(LevelUpType.PLAYER, level);
	}
	
	/**
	 * 获取装备栏最高等级
	 * @param position
	 * @return
	 */
	public static int getEquipBarMaxLevel(short position){
		return getMaxLevel(LevelUpType.EQUIPBARSTART + position);
	}
	/**
	 * 获取装备栏升级信息
	 * @param position
	 * @param level
	 * @return
	 */
	public static LevelUp getEquipBarLevel(short position, int level){
		return getLevelUp(LevelUpType.EQUIPBARSTART + position, level);
	}
	
	
	/**
	 * 根据总经验获取等级
	 * @param totalExp
	 * @return
	 */
	public static int getSoulLevel(long totalExp){
		List<LevelUp> list = levelUpListMap.get(LevelUpType.SOUL);
		if(list == null){
			return 1;
		}
		for(LevelUp levelUp:list){
			if(levelUp.getExp() >= totalExp){
				return levelUp.getLevel();
			}
		}
		return maxLevelMap.get(LevelUpType.SOUL);
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
