package com.chuangyou.xianni.http.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.chuangyou.common.util.AttributeUtil;
import com.chuangyou.xianni.artifact.manager.ArtifactManager;
import com.chuangyou.xianni.artifact.template.ArtifactTemplateMgr;
import com.chuangyou.xianni.bag.ItemManager;
import com.chuangyou.xianni.entity.artifact.ArtifactGradeupCfg;
import com.chuangyou.xianni.entity.artifact.ArtifactInfo;
import com.chuangyou.xianni.entity.artifact.ArtifactInfoCfg;
import com.chuangyou.xianni.entity.artifact.ArtifactJewelLevelCfg;
import com.chuangyou.xianni.entity.artifact.ArtifactJewelSuitCfg;
import com.chuangyou.xianni.entity.artifact.ArtifactLevelupCfg;
import com.chuangyou.xianni.entity.artifact.ArtifactSuitCfg;
import com.chuangyou.xianni.entity.item.ItemTemplateInfo;
import com.chuangyou.xianni.entity.property.BaseProperty;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.skill.SkillUtil;

public class CalArtifact extends ArtifactManager {

	public static Map<String, Object> computeArtifactPor(GamePlayer player) {
		Map<String, Object> data = new HashMap<>();
		List<Map<String, Object>> list = new ArrayList<>();

		Map<Integer, Integer> attMap = new HashMap<>();
		Map<Integer, ArtifactInfo> artifactInfoMap = player.getArtifactInventory().getArtifactMap();
		int activateNum = 0;
		int stoneTotalLevel = 0;
		// 神器总属性
		Map<Integer, Integer> artifactTotalAttMap = new HashMap<>();
		// 宝石总属性
		Map<Integer, Integer> stoneTotalAttMap = new HashMap<>();
		for (ArtifactInfo info : artifactInfoMap.values()) {
			Map<String, Object> data2 = new HashMap<>();
			data2.put("lv", info.getLevel());
			data2.put("star", info.getStar());

			Map<Integer, Integer> artifactAtts = new HashMap<>();
			// 神器等级属性
			if (info.getLevel() > 0) {
				ArtifactLevelupCfg levelCfg = ArtifactTemplateMgr.getLevelUpCfg(info.getArtifactId(), info.getLevel());
				AttributeUtil.putAttListToMap(artifactAtts, levelCfg.getAttList());
				activateNum += 1;
			}
			// 神器星级加成
			if (info.getStarLevel() > 0 || info.getStar() > 0) {
				ArtifactGradeupCfg gradeCfg = ArtifactTemplateMgr.getGradeUpCfg(info.getArtifactId(), info.getStarLevel(), info.getStar());

				for (int attType : artifactAtts.keySet()) {
					int attValue = artifactAtts.get(attType) * (1 + gradeCfg.getAttr() / 10000);
					artifactAtts.put(attType, attValue);
				}
			}
			// 单件神器属性放入神器总属性中
			AttributeUtil.putAttToMap(artifactTotalAttMap, artifactAtts);

			// 单件神器中宝石属性统计
			Map<Integer, Integer> stoneAtts = new HashMap<>();
			ArtifactInfoCfg cfg = ArtifactTemplateMgr.getArtifactCfg(info.getArtifactId());
			List<Map<String, Object>> stoneList = new ArrayList<>();
			for (int i = 1; i <= 4; i++) {
				int stoneId = cfg.getJewel(i);
				int stoneLevel = info.getStoneLevel(i);
				ArtifactJewelLevelCfg stoneLevelCfg = ArtifactTemplateMgr.getJewelLevelUpCfg(stoneId, stoneLevel);
				AttributeUtil.putAttNumToMap(stoneAtts, stoneLevelCfg.getAttr());
				stoneTotalLevel += stoneLevel;
				Map<String, Object> data3 = new HashMap<>();
				data3.put("stoneId", stoneId);
				data3.put("lv", stoneLevel);
				ItemTemplateInfo itemInfo = ItemManager.findItemTempInfo(stoneId);
				String name = "";
				if (itemInfo != null) {
					name = itemInfo.getName();
				}
				data3.put("name", name);
				stoneList.add(data3);
			}
			// 单件神器中的宝石属性放入宝石总属性
			AttributeUtil.putAttToMap(stoneTotalAttMap, stoneAtts);

			data2.put("stoneList", stoneList);
			list.add(data2);
		}

		// 计算神器套装加成
		List<ArtifactSuitCfg> suitList = ArtifactTemplateMgr.getSuitList();
		int addPer = 0;
		for (ArtifactSuitCfg suitCfg : suitList) {
			if (activateNum >= suitCfg.getSuitid()) {
				addPer += suitCfg.getAtt();
			}
		}
		for (int attType : artifactTotalAttMap.keySet()) {
			int attValue = artifactTotalAttMap.get(attType) * (1 + addPer / 10000);
			artifactTotalAttMap.put(attType, attValue);
		}

		// 计算宝石套装加成
		List<ArtifactJewelSuitCfg> stoneSuitList = ArtifactTemplateMgr.getJewelSuitList();
		for (ArtifactJewelSuitCfg stoneSuitCfg : stoneSuitList) {
			if (stoneTotalLevel >= stoneSuitCfg.getLevel()) {
				AttributeUtil.putAttListToMap(stoneTotalAttMap, stoneSuitCfg.getAttList());
			}
		}

		// 神器总属性和宝石总属性都放入属性最终属性列表
		AttributeUtil.putAttToMap(attMap, artifactTotalAttMap);
		AttributeUtil.putAttToMap(attMap, stoneTotalAttMap);
		data.put("list", list);
		BaseProperty property = new BaseProperty();
		Map<String, Integer> attMap2 = new HashMap<String, Integer>();
		for (Entry<Integer, Integer> integer : attMap.entrySet()) {
			attMap2.put(integer.getKey() + "", integer.getValue());
			SkillUtil.joinPro(property, integer.getKey(), integer.getValue());
		}
		CalFighting calFighting = new CalFighting();
		calFighting.addBag(property, property);
		long fighting = calFighting.getFighting();
		data.put("totalPro", attMap2);
		data.put("fighting", fighting);
		
		return data;
	}
}
