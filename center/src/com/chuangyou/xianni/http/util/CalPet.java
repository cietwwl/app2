package com.chuangyou.xianni.http.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.chuangyou.common.util.AttributeUtil;
import com.chuangyou.xianni.entity.pet.PetAtt;
import com.chuangyou.xianni.entity.pet.PetGradeCfg;
import com.chuangyou.xianni.entity.pet.PetInfo;
import com.chuangyou.xianni.entity.pet.PetInfoCfg;
import com.chuangyou.xianni.entity.pet.PetLevelCfg;
import com.chuangyou.xianni.entity.pet.PetPhysiqueCfg;
import com.chuangyou.xianni.entity.pet.PetQualityCfg;
import com.chuangyou.xianni.entity.pet.PetSoulCfg;
import com.chuangyou.xianni.entity.property.BaseProperty;
import com.chuangyou.xianni.pet.template.PetTemplateMgr;
import com.chuangyou.xianni.skill.SkillUtil;
import com.chuangyou.xianni.sql.dao.DBManager;

public class CalPet {

	/**
	 * 计算玩家宠物属性
	 * 
	 * @param roleId
	 * @throws MXY2Exception
	 */
	public static Map<String, Object> computePetAtt(long playerId) {
		Map<String, Object> data = new HashMap<>();

		Map<Integer, Integer> attMap = new HashMap<Integer, Integer>();
		// Map<Integer, PetInfo> rolePetInfo =
		// player.getPetInventory().getPetInfoMap();
		Map<Integer, PetInfo> rolePetInfo = DBManager.getPetInfoDao().getAll(playerId);

		for (PetInfo pet : rolePetInfo.values()) {
			Map<Integer, Integer> petAtts = new HashMap<>();
			// 等级加成
			PetLevelCfg petLevelCfg = PetTemplateMgr.getLevelTemps().get(pet.getPetId() * 1000 + pet.getLevel());
			if (petLevelCfg != null) {
				AttributeUtil.putAttListToMap(petAtts, petLevelCfg.getAttList());
			}

			PetInfoCfg petInfoCfg = PetTemplateMgr.getPetTemps().get(pet.getPetId());
			if (petInfoCfg == null) {
				continue;
			}
			if (petInfoCfg.getIsSpecial() == 0) {
				// 炼体加成
				if (pet.getPhysique() > 0) {
					PetPhysiqueCfg petPhyCfg = PetTemplateMgr.getPhysiqueTemps().get(pet.getPetId() * 1000 + pet.getPhysique());
					if (petPhyCfg != null) {
						AttributeUtil.putAttListToMap(petAtts, petPhyCfg.getAttList());
					}
				}

				// 进阶加成
				if (pet.getGrade() > 0) {
					PetGradeCfg petGradeCfg = PetTemplateMgr.getGradeTemps().get(pet.getPetId() * 1000 + pet.getGrade());
					if (petGradeCfg != null) {
						AttributeUtil.putAttListToMap(petAtts, petGradeCfg.getAttList());
					}
				}

				// 品质加成
				if (pet.getQuality() > 0) {
					PetQualityCfg petQualityCfg = PetTemplateMgr.getQualityTemps().get(pet.getPetId() * 1000 + pet.getQuality());
					// AttributeUtil.putAttListToMap(petAtts,
					// petQualityCfg.getAttList());
					if (petQualityCfg != null) {
						for (int attType : petAtts.keySet()) {
							int attValue = petAtts.get(attType) * (1 + petQualityCfg.getAttPer() / 1000);
							petAtts.put(attType, attValue);
						}
					}
				}
			}
			AttributeUtil.putAttToMap(attMap, petAtts);
		}

		// 炼魂加成
		// PetAtt petAtt = player.getPetInventory().getPetAtt();
		PetAtt petAtt = DBManager.getPetAttDao().get(playerId);
		PetSoulCfg petSoulCfg = PetTemplateMgr.getSoulTemps().get(petAtt.getSoulLv());
		if (petSoulCfg != null) {
			AttributeUtil.putAttListToMap(attMap, petSoulCfg.getAttList());
		}

		BaseProperty property = new BaseProperty();
		Map<String, Integer> attMap2 = new HashMap<String, Integer>();
		for (Entry<Integer, Integer> integer : attMap.entrySet()) {
			attMap2.put(integer.getKey() + "", integer.getValue());
			SkillUtil.joinPro(property, integer.getKey(), integer.getValue());
		}
		CalFighting calFighting = new CalFighting();
		calFighting.addBag(property, property);
		long fighting = calFighting.getFighting();
		data.put("totalData", attMap2);
		data.put("fighting", fighting);
		return data;
	}

	/**
	 * 获取宠物属性
	 * 
	 * @param playerId
	 * @return
	 */
	public static List<Object> getPetListInfo(long playerId) {

		// Map<Integer, PetInfo> rolePetInfo =
		// player.getPetInventory().getPetInfoMap();

		Map<Integer, PetInfo> rolePetInfo = DBManager.getPetInfoDao().getAll(playerId);
		List<Object> list = new ArrayList<>();

		for (PetInfo pet : rolePetInfo.values()) {
			Map<Integer, Integer> attMap = new HashMap<Integer, Integer>();
			Map<String, Object> info = new HashMap<String, Object>();
			Map<Integer, Integer> petAtts = new HashMap<>();
			// 等级加成
			PetLevelCfg petLevelCfg = PetTemplateMgr.getLevelTemps().get(pet.getPetId() * 1000 + pet.getLevel());
			if (petLevelCfg != null) {
				AttributeUtil.putAttListToMap(petAtts, petLevelCfg.getAttList());
			}

			PetInfoCfg petInfoCfg = PetTemplateMgr.getPetTemps().get(pet.getPetId());
			if (petInfoCfg == null) {
				continue;
			}
			if (petInfoCfg.getIsSpecial() == 0) {

				// 炼体加成
				if (pet.getPhysique() > 0) {
					PetPhysiqueCfg petPhyCfg = PetTemplateMgr.getPhysiqueTemps().get(pet.getPetId() * 1000 + pet.getPhysique());
					if (petPhyCfg != null) {
						AttributeUtil.putAttListToMap(petAtts, petPhyCfg.getAttList());
					}
				}

				// 进阶加成
				if (pet.getGrade() > 0) {
					PetGradeCfg petGradeCfg = PetTemplateMgr.getGradeTemps().get(pet.getPetId() * 1000 + pet.getGrade());
					if (petGradeCfg != null) {
						AttributeUtil.putAttListToMap(petAtts, petGradeCfg.getAttList());
					}
				}

				// 品质加成
				if (pet.getQuality() > 0) {
					PetQualityCfg petQualityCfg = PetTemplateMgr.getQualityTemps().get(pet.getPetId() * 1000 + pet.getQuality());
					// AttributeUtil.putAttListToMap(petAtts,
					// petQualityCfg.getAttList());
					if (petQualityCfg != null) {
						for (int attType : petAtts.keySet()) {
							int attValue = petAtts.get(attType) * (1 + petQualityCfg.getAttPer() / 1000);
							petAtts.put(attType, attValue);
						}
					}
				}
			}

			PetInfoCfg cofnig = PetTemplateMgr.getPetTemps().get(pet.getPetId());
			info.put("petName", cofnig.getName());
			AttributeUtil.putAttToMap(attMap, petAtts);

			////
			BaseProperty property = new BaseProperty();
			Map<String, Integer> attMap2 = new HashMap<String, Integer>();
			for (Entry<Integer, Integer> integer : attMap.entrySet()) {
				attMap2.put(integer.getKey() + "", integer.getValue());
				SkillUtil.joinPro(property, integer.getKey(), integer.getValue());
			}

			CalFighting calFighting = new CalFighting();
			calFighting.addBag(property, property);
			long fighting = calFighting.getFighting();
			info.put("totalData", attMap2);
			info.put("fighting", fighting);
			info.put("lv", pet.getLevel());
			list.add(info);
		}
		return list;
	}

	/**
	 * 获取宠物属性
	 * 
	 * @param playerId
	 * @return
	 */
	public static Map<String, Object> getPetSoulInfo(long playerId) {
		Map<String, Object> data = new HashMap<>();
		Map<Integer, Integer> attMap = new HashMap<Integer, Integer>();
		// 炼魂加成
		// PetAtt petAtt = player.getPetInventory().getPetAtt();
		PetAtt petAtt = DBManager.getPetAttDao().get(playerId);
		PetSoulCfg petSoulCfg = PetTemplateMgr.getSoulTemps().get(petAtt.getSoulLv());
		if (petSoulCfg != null) {
			AttributeUtil.putAttListToMap(attMap, petSoulCfg.getAttList());
		}

		BaseProperty property = new BaseProperty();
		Map<String, Integer> attMap2 = new HashMap<String, Integer>();
		for (Entry<Integer, Integer> integer : attMap.entrySet()) {
			attMap2.put(integer.getKey() + "", integer.getValue());
			SkillUtil.joinPro(property, integer.getKey(), integer.getValue());
		}
		CalFighting calFighting = new CalFighting();
		calFighting.addBag(property, property);
		long fighting = calFighting.getFighting();
		data.put("totalData", attMap2);
		data.put("fighting", fighting);
		data.put("lv", petAtt.getSoulLv());
		return data;

	}

}
