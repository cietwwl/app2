package com.chuangyou.xianni.http.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.bag.BagInventory;
import com.chuangyou.xianni.common.template.LevelUpTempleteMgr;
import com.chuangyou.xianni.entity.property.BaseProperty;
import com.chuangyou.xianni.entity.soul.SoulCardConfig;
import com.chuangyou.xianni.entity.soul.SoulCardInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.skill.SkillUtil;
import com.chuangyou.xianni.skill.template.SimpleProperty;
import com.chuangyou.xianni.soul.logic.calc.ConcreteComponent;
import com.chuangyou.xianni.soul.logic.calc.GetComboAttLogic;
import com.chuangyou.xianni.soul.logic.calc.ICalcAttLogic;
import com.chuangyou.xianni.soul.logic.calc.LvAddComboAttLogic;
import com.chuangyou.xianni.soul.logic.calc.SoulCalcLogic;
import com.chuangyou.xianni.soul.logic.calc.StarAddComboAttLogic;
import com.chuangyou.xianni.soul.logic.calc.StarAndLvAddAttLogic;
import com.chuangyou.xianni.soul.logic.calc.UpComboAttLogic;
import com.chuangyou.xianni.soul.template.SoulTemplateMgr;

public class CalSoul extends SoulCalcLogic {
	/**
	 * 计算属性
	 * 
	 * @param soulData
	 * @param soulPer
	 */
	public static Map<String, Object> computeSoulAtt(GamePlayer player) {
		Map<String, Object> data = new CalSoul().calcBaseSoulAtts(player);
		return data;
	}

	public static Map<String, Object> computeSoulAttOnLine(GamePlayer player) {
		BaseProperty soulData = new BaseProperty();
		BaseProperty soulPer = new BaseProperty();
		player.getSoulInventory().computeSoulAtt(soulData, soulPer);
		Map<String, Object> data = new HashMap<>();
		CalFighting calFighting = new CalFighting();
		calFighting.addBag(soulData, soulPer);
		long fighting = calFighting.getFighting();
		data.put("totalPro", soulData);
		data.put("fighting", fighting);
		return data;
	}

	public Map<String, Object> calcBaseSoulAtts(GamePlayer player) {
		Map<String, Object> data = new HashMap<>();
		List<Object> list = new ArrayList<>();
		BaseProperty soulData = new BaseProperty();
		BaseProperty soulPer = new BaseProperty();

		Map<Integer, SimpleProperty> map = new HashMap<>();
		List<Integer> putOnCards = player.getSoulInventory().getSoulInfo().getCardsList();
		for (int cardId : putOnCards) {
			Map<Integer, SimpleProperty> map2 = new HashMap<>();
			Map<String, Object> data2 = new HashMap<>();

			SoulCardInfo soulCard = player.getSoulInventory().getCards().get(cardId);
			ConcreteComponent component = new ConcreteComponent();
			// 基础属性 +卡牌星级加成+卡牌等级加成
			ICalcAttLogic starAndLv = new StarAndLvAddAttLogic(component);
			// 上阵组合加成
			ICalcAttLogic upComboAtt = new UpComboAttLogic(starAndLv);
			// 获得卡牌组合加成
			ICalcAttLogic getComboAtt = new GetComboAttLogic(upComboAtt);
			// 组合中星级加成
			ICalcAttLogic starAddCombo = new StarAddComboAttLogic(getComboAtt);
			// 组合中等级加成
			ICalcAttLogic lvAddCombo = new LvAddComboAttLogic(starAddCombo);
			lvAddCombo.doProcess(player, map, soulCard, player.getSoulInventory().getCards());

			lvAddCombo.doProcess(player, map2, soulCard, player.getSoulInventory().getCards());
			BaseProperty soulData2 = new BaseProperty();
			BaseProperty soulPer2 = new BaseProperty();
			calcExpAdd(player, map2);
			for (SimpleProperty property : map2.values()) {
				if (property.isPre()) {
					SkillUtil.joinPro(soulPer2, property.getType(), property.getValue());
				} else {
					SkillUtil.joinPro(soulData2, property.getType(), property.getValue());
				}
			}
			CalFighting calFighting = new CalFighting();
			calFighting.addBag(soulData2, soulPer2);
			long fighting = calFighting.getFighting();
			data2.put("totalPro", soulData2);
			data2.put("fighting", fighting);
			SoulCardConfig soulCardConfig = SoulTemplateMgr.getCardConfig(soulCard.getCardId());
			data2.put("name", soulCardConfig.getName());
			data2.put("quality", soulCardConfig.getQuality());
			data2.put("star", soulCard.getStar());
			data2.put("lv", soulCard.getLv());
			data2.put("itemCount", player.getBagInventory().getItemCount(cardId));
			list.add(data2);
		}
		// 计算魂幡等级给魂幡功能加成的属性
		calcExpAdd(player, map);
		for (SimpleProperty property : map.values()) {
			if (property.isPre()) {
				SkillUtil.joinPro(soulPer, property.getType(), property.getValue());
			} else {
				SkillUtil.joinPro(soulData, property.getType(), property.getValue());
			}
		}
		CalFighting calFighting = new CalFighting();
		calFighting.addBag(soulData, soulPer);
		long fighting = calFighting.getFighting();
		data.put("totalPro", soulData);
		data.put("fighting", fighting);
		data.put("soulList", list);
		data.put("soulSize", player.getSoulInventory().getSoulInfo().getExp());// 魂魄数
		data.put("ficiency", player.getSoulInventory().getSoulInfo().getSoulProList());// 熟练度
		int lv = LevelUpTempleteMgr.getSoulLevel(player.getSoulInventory().getSoulInfo().getExp());
		float temp = 0.01f * lv;
		data.put("proportion", temp);// 比例
		
		
		return data;
	}

}
