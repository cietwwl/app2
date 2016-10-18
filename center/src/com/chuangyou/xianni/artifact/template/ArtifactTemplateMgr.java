package com.chuangyou.xianni.artifact.template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.entity.artifact.ArtifactGradeupCfg;
import com.chuangyou.xianni.entity.artifact.ArtifactInfoCfg;
import com.chuangyou.xianni.entity.artifact.ArtifactJewelLevelCfg;
import com.chuangyou.xianni.entity.artifact.ArtifactJewelSuitCfg;
import com.chuangyou.xianni.entity.artifact.ArtifactLevelupCfg;
import com.chuangyou.xianni.entity.artifact.ArtifactSuitCfg;
import com.chuangyou.xianni.sql.dao.DBManager;

public class ArtifactTemplateMgr {

	private static Map<Integer, ArtifactInfoCfg> artifactMap = new HashMap<>();
	private static Map<Long, ArtifactLevelupCfg> levelUpMap = new HashMap<>();
	private static Map<Long, ArtifactGradeupCfg> gradeUpMap = new HashMap<>();
	private static List<ArtifactSuitCfg> suitList = new ArrayList<>();
	private static Map<Integer, List<ArtifactJewelLevelCfg>> jewelLevelupListMap = new HashMap<>();
	private static List<ArtifactJewelSuitCfg> jewelSuitList = new ArrayList<>();
	
	public static boolean init(){
		return reloadTemplate();
	}
	
	public static boolean reloadTemplate(){
		artifactMap = DBManager.getArtifactConfigDao().getArtifactInfo();
		levelUpMap = DBManager.getArtifactConfigDao().getArtifactLevel();
		gradeUpMap = DBManager.getArtifactConfigDao().getArtifactGrade();
		suitList = DBManager.getArtifactConfigDao().getArtifactSuit();
		jewelLevelupListMap = DBManager.getArtifactConfigDao().getArtifactJewelLevel();
		jewelSuitList = DBManager.getArtifactConfigDao().getArtifactJewelSuit();
		return true;
	}
	public static boolean reloadArtifactMap(){
		artifactMap = DBManager.getArtifactConfigDao().getArtifactInfo();
		return true;
	}
	public static boolean reloadLevelUpMap(){
		levelUpMap = DBManager.getArtifactConfigDao().getArtifactLevel();
		return true;
	}
	public static boolean reloadGradeUpMap(){
		gradeUpMap = DBManager.getArtifactConfigDao().getArtifactGrade();
		return true;
	}
	public static boolean reloadSuitList(){
		suitList = DBManager.getArtifactConfigDao().getArtifactSuit();
		return true;
	}
	public static boolean reloadJewelLevelupListMap(){
		jewelLevelupListMap = DBManager.getArtifactConfigDao().getArtifactJewelLevel();
		return true;
	}
	public static boolean reloadJewelSuitList(){
		jewelSuitList = DBManager.getArtifactConfigDao().getArtifactJewelSuit();
		return true;
	}
	
	public static ArtifactInfoCfg getArtifactCfg(int artifactId){
		return getArtifactMap().get(artifactId);
	}
	
	public static ArtifactLevelupCfg getLevelUpCfg(int artifactId, int level){
		long tempId = (long)artifactId * 1000 + level;
		return getLevelUpMap().get(tempId);
	}
	
	public static ArtifactGradeupCfg getGradeUpCfg(int artifactId, int starLevel, int star){
		long tempId = (long)artifactId * 1000 + starLevel * 100 + star;
		return getGradeUpMap().get(tempId);
	}
	
	public static ArtifactJewelLevelCfg getJewelLevelUpCfg(int jewelId, int level){
		List<ArtifactJewelLevelCfg> levels = getJewelLevelupListMap().get(jewelId);
		if(levels == null) return null;
		
		return levels.get(level);
	}
	
	public static int getJewelLevel(int jewelId, long totalExp){
		List<ArtifactJewelLevelCfg> levels = jewelLevelupListMap.get(jewelId);
		if(levels == null){
			return 0;
		}
		long needTotalExp = 0;
		ArtifactJewelLevelCfg curCfg = null;
		for(ArtifactJewelLevelCfg levelCfg: levels){
			needTotalExp += levelCfg.getMaxExp();
			if(needTotalExp > totalExp){
				return levelCfg.getLevel();
			}
			curCfg = levelCfg;
		}
		return curCfg != null?curCfg.getLevel():0;
	}
	public static long getJewelLevelNeedExp(int jewelId, int level){
		List<ArtifactJewelLevelCfg> levels = getJewelLevelupListMap().get(jewelId);
		if(levels == null){
			return 0;
		}
		long needTotalExp = 0;
		for(ArtifactJewelLevelCfg levelCfg:levels){
			if(levelCfg.getLevel() == level){
				return needTotalExp;
			}
			needTotalExp += levelCfg.getMaxExp();
		}
		
		return needTotalExp;
	}

	public static Map<Integer, ArtifactInfoCfg> getArtifactMap() {
		return artifactMap;
	}

	public static Map<Long, ArtifactLevelupCfg> getLevelUpMap() {
		return levelUpMap;
	}

	public static Map<Long, ArtifactGradeupCfg> getGradeUpMap() {
		return gradeUpMap;
	}

	public static List<ArtifactSuitCfg> getSuitList() {
		return suitList;
	}

	public static Map<Integer, List<ArtifactJewelLevelCfg>> getJewelLevelupListMap() {
		return jewelLevelupListMap;
	}

	public static List<ArtifactJewelSuitCfg> getJewelSuitList() {
		return jewelSuitList;
	}
}
