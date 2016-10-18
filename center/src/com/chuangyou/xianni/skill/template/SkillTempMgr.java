package com.chuangyou.xianni.skill.template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.chuangyou.xianni.entity.skill.SkillStage;
import com.chuangyou.xianni.entity.skill.SkillTempateInfo;
import com.chuangyou.xianni.sql.dao.DBManager;

/**
 * 技能配置
 * 
 * @author Administrator
 */
public class SkillTempMgr {
	/**
	 * 技能配置数据
	 */
	private static Map<Integer, SkillTempateInfo>	skillTemp	= new HashMap<Integer, SkillTempateInfo>();
	

	// /**
	// * 技能属性配置
	// */
	// private static Map<Integer, SkillPropertyTemplateInfo> skillProTemp = new
	// HashMap<Integer, SkillPropertyTemplateInfo>();
	/**
	 * 技能阶段
	 */
	private static Map<Integer, SkillStage>			skillStage	= new HashMap<Integer, SkillStage>();

	public static boolean init() {
		reloadPb();
		reloadSkillStage();
		return true;
	}

	public static boolean reloadPb() {
		// 加载基础技能
		List<SkillTempateInfo> skillTempInfos = DBManager.getSkillTempateInfoDao().load();
		if (skillTempInfos != null && skillTempInfos.size() > 0) {
			for (SkillTempateInfo stemp : skillTempInfos) {
				skillTemp.put(stemp.getTemplateId(), stemp);				
			}
		}
		// 加载技能属性模板
		// List<SkillPropertyTemplateInfo> skillProTempList =
		// DBManager.getSkillPropertyTemplateInfoDao().load();
		// if (skillProTempList != null && !skillProTempList.isEmpty()) {
		// for (SkillPropertyTemplateInfo skillPropertyTemplateInfo :
		// skillProTempList) {
		// skillProTemp.put(skillPropertyTemplateInfo.getTemplateId(),
		// skillPropertyTemplateInfo);
		// }
		// }
		// 技能阶段
		// List<SkillStage> skillStageList =
		// DBManager.getSkillStageDao().load();
		// if (skillStageList != null && !skillStageList.isEmpty()) {
		// for (SkillStage stage : skillStageList) {
		// skillStage.put(stage.getGradeLevel(), stage);
		// }
		// }
		return true;
	}
	
	public static boolean reloadSkillStage(){
		List<SkillStage> skillStageList = DBManager.getSkillStageDao().load();
		if (skillStageList != null && !skillStageList.isEmpty()) {
			for (SkillStage stage : skillStageList) {
				skillStage.put(stage.getGradeLevel(), stage);
			}
		}
		return true;
	}

	/**
	 * 获取技能模板
	 * 
	 * @param templateId
	 * @return
	 */
	public static SkillTempateInfo getSkillTemp(int templateId) {
		if (skillTemp.containsKey(templateId)) {
			return skillTemp.get(templateId);
		}
		return null;
	}

	/**
	 * 根据类型获取技能模板列表
	 * 
	 * @param skillType
	 * @return
	 */
	public static Map<Integer, SkillTempateInfo> getSkillTempByType(int skillType, int skillType2) {
		Map<Integer, SkillTempateInfo> list = new HashMap<Integer, SkillTempateInfo>();
		// Map<Integer, SkillTempateInfo> skillTempMap = new HashMap<Integer,
		// SkillTempateInfo>();
		for (Entry<Integer, SkillTempateInfo> entry : skillTemp.entrySet()) {
			SkillTempateInfo temp = entry.getValue();
			if (temp.getMasterType() == skillType || temp.getMasterType() == skillType2)
				list.put(entry.getKey(), temp);
		}
		return list;
	}

	// /**
	// * 获取技能属性模板
	// *
	// * @param templateId
	// * @return
	// */
	// public static SkillPropertyTemplateInfo getSkillProTemp(int templateId) {
	// if (skillProTemp.containsKey(templateId)) {
	// return skillProTemp.get(templateId);
	// } else {
	// SimpleProperty simp = readPro(templateId);
	// SkillPropertyTemplateInfo tempModel = new SkillPropertyTemplateInfo();
	// tempModel.setTemplateId(templateId);
	// assignPro(tempModel, simp.getType(), simp.getValue());
	// if (simp.getDataType() == SimpleProperty.PERCENTAGE || simp.getDataType()
	// == SimpleProperty.DECR_PERCENTAGE) {
	// tempModel.setType(1);// 百分比数值
	// }
	// skillProTemp.put(templateId, tempModel);
	// return tempModel;
	// }
	// }

	/**
	 * 获取技能阶段模板
	 * 
	 * @param templateId
	 * @return
	 */
	public static SkillStage getSkillStage(int lv) {
		if (skillStage.containsKey(lv)) {
			return skillStage.get(lv);
		}
		return null;
	}

	// /** 属性赋值 */
	// public static SkillPropertyTemplateInfo
	// assignPro(SkillPropertyTemplateInfo temp, int key, int val) {
	// switch (key - 1) {
	// case Living.SOUL:
	// temp.setSoul(val);
	// break;
	// case Living.BLOOD:
	// temp.setBlood(val);
	// break;
	// case Living.ATTACK:
	// temp.setAttack(val);
	// break;
	// case Living.DEFENCE:
	// temp.setDefence(val);
	// break;
	// case Living.SOUL_ATTACK:
	// temp.setSoulAttack(val);
	// break;
	// case Living.SOUL_DEFENCE:
	// temp.setSoulDefence(val);
	// break;
	// case Living.ACCURATE:
	// temp.setAccurate(val);
	// break;
	// case Living.DODGE:
	// temp.setDodge(val);
	// break;
	// case Living.CRIT:
	// temp.setCrit(val);
	// break;
	// case Living.CRIT_DEFENCE:
	// temp.setCritDefence(val);
	// break;
	// case Living.CRIT_ADDTION:
	// temp.setCritAddtion(val);
	// break;
	// case Living.CRIT_CUT:
	// temp.setCritCut(val);
	// break;
	// case Living.BLOOD_ATTACK_ADDTION:
	// temp.setBloodAttackAddtion(val);
	// break;
	// case Living.BLOOD_ATTACK_CUT:
	// temp.setBloodAttackCut(val);
	// break;
	// case Living.SOUL_ATTACK_ADDTION:
	// temp.setSoulAttackAddtion(val);
	// break;
	// case Living.SOUL_ATTACK_CUT:
	// temp.setSoulAttackCut(val);
	// break;
	// case Living.REGAIN_SOUL:
	// temp.setRegainSoul(val);
	// break;
	// case Living.REGAIN_BLOOD:
	// temp.setRegainBlood(val);
	// break;
	// case Living.METAL:
	// temp.setMetal(val);
	// break;
	// case Living.WOOD:
	// temp.setWood(val);
	// break;
	// case Living.WATER:
	// temp.setWater(val);
	// break;
	// case Living.FIRE:
	// temp.setFire(val);
	// break;
	// case Living.EARTH:
	// temp.setEarth(val);
	// break;
	// case Living.METAL_DEFENCE:
	// temp.setMetalDefence(val);
	// break;
	// case Living.WOOD_DEFENCE:
	// temp.setWoodDefence(val);
	// break;
	// case Living.WATER_DEFENCE:
	// temp.setWaterDefence(val);
	// break;
	// case Living.FIRE_DEFENCE:
	// temp.setFireDefence(val);
	// break;
	// case Living.EARTH_DEFENCE:
	// temp.setEarthDefence(val);
	// break;
	// case Living.SPEED:
	// temp.setSpeed(val);
	// break;
	// default:
	// break;
	// }
	// return temp;
	// }

}
