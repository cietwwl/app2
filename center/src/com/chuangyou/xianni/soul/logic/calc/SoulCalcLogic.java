package com.chuangyou.xianni.soul.logic.calc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.chuangyou.common.protobuf.pb.army.PropertyMsgProto.PropertyMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.common.template.LevelUpTempleteMgr;
import com.chuangyou.xianni.entity.property.BaseProperty;
import com.chuangyou.xianni.entity.soul.CardStarConfig;
import com.chuangyou.xianni.entity.soul.SoulCardConfig;
import com.chuangyou.xianni.entity.soul.SoulCardInfo;
import com.chuangyou.xianni.entity.soul.SoulInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.skill.SkillUtil;
import com.chuangyou.xianni.skill.template.SimpleProperty;
import com.chuangyou.xianni.soul.template.SoulTemplateMgr;

public class SoulCalcLogic {

	/**
	 * 计算基础属性值
	 * 
	 * @param player
	 */
	public void calcBaseSoulAtts(GamePlayer player, BaseProperty pre, BaseProperty data) {
		Map<Integer, SimpleProperty> map = new HashMap<>();
		
		List<Integer> putOnCards = player.getSoulInventory().getSoulInfo().getCardsList();
		for (int cardId : putOnCards) {
			SoulCardInfo soulCard = player.getSoulInventory().getCards().get(cardId);
			ConcreteComponent component = new ConcreteComponent();
			//基础属性 +卡牌星级加成+卡牌等级加成
			ICalcAttLogic starAndLv = new StarAndLvAddAttLogic(component);
			//上阵组合加成
			ICalcAttLogic upComboAtt = new UpComboAttLogic(starAndLv);
			//获得卡牌组合加成
			ICalcAttLogic getComboAtt = new GetComboAttLogic(upComboAtt);
			//组合中星级加成
			ICalcAttLogic starAddCombo = new StarAddComboAttLogic(getComboAtt);
			//组合中等级加成
			ICalcAttLogic lvAddCombo = new LvAddComboAttLogic(starAddCombo);
			
			lvAddCombo.doProcess(player, map, soulCard, player.getSoulInventory().getCards());
		}
		
		
		// 计算魂幡等级给魂幡功能加成的属性
		List<PropertyMsg> list = calcExpAdd(player, map);
		player.getSoulInventory().setList(list);
		// 加入人身上
		for (SimpleProperty property : map.values()) {
			if (property.isPre()) {
				SkillUtil.joinPro(pre, property.getType(), property.getValue());
			} else {
				SkillUtil.joinPro(data, property.getType(), property.getValue());
			}
		}
	}


	
	/**
	 * 计算魂魄数转成的等级给属性的加成值
	 * 
	 * @param player
	 * @param pre
	 * @param data
	 */
	/**
	 * 计算魂魄数转成的等级给属性的加成值 并返回通信协议
	 * 
	 * @param player
	 * @param map
	 * @return
	 */
	public List<PropertyMsg> calcExpAdd(GamePlayer player, Map<Integer, SimpleProperty> map) {
		List<PropertyMsg> list = new ArrayList<>();
		int lv = LevelUpTempleteMgr.getSoulLevel(player.getSoulInventory().getSoulInfo().getExp());
		if (lv >= 0) {
			float temp = 0.01f * lv;
			Iterator<SimpleProperty> it = map.values().iterator();
			while (it.hasNext()) {
				SimpleProperty spro = it.next();
				if (spro.getValue() > 0) {
					PropertyMsg.Builder msg = PropertyMsg.newBuilder();
					msg.setType(spro.getType());
					msg.setBasePoint(spro.getValue());
					spro.setValue((int) (spro.getValue() + temp * spro.getValue()));
					msg.setTotalPoint(spro.getValue());
					list.add(msg.build());
				}
			}
		}
		return list;
	}

	/**
	 * 计算人物身上的属性
	 * 
	 * @param player
	 * @param info
	 * @param pre
	 * @param data
	 */
	public void calcCardsAtts(GamePlayer player, Map<Integer, SimpleProperty> map) {
		SoulInfo info = player.getSoulInventory().getSoulInfo();
		if (info == null)
			return;
		calcOneCardAtt(player, info.getCard1(), map);
		calcOneCardAtt(player, info.getCard2(), map);
		calcOneCardAtt(player, info.getCard3(), map);
		calcOneCardAtt(player, info.getCard4(), map);
		calcOneCardAtt(player, info.getCard5(), map);
		calcOneCardAtt(player, info.getCard6(), map);
		calcOneCardAtt(player, info.getCard7(), map);
		calcOneCardAtt(player, info.getCard8(), map);
	}

	/**
	 * 计算单个卡牌的属性加成
	 * 
	 * @param player
	 * @param cardId
	 * @param pre
	 * @param data
	 */
	public void calcOneCardAtt(GamePlayer player, int cardId, Map<Integer, SimpleProperty> map) {
		if (cardId <= 0)
			return;
		SoulCardInfo cardInfo = player.getSoulInventory().getCards().get(cardId);
		SoulCardConfig cardConfig = SoulTemplateMgr.getCardConfig(cardId);
		if (cardConfig != null && cardInfo != null) {
			calcSimpleProperty(cardConfig.getAttrInit1(), cardConfig.getAttrAdd1(), map, cardInfo);
			calcSimpleProperty(cardConfig.getAttrInit2(), cardConfig.getAttrAdd2(), map, cardInfo);
			calcSimpleProperty(cardConfig.getAttrInit3(), cardConfig.getAttrAdd3(), map, cardInfo);
			calcSimpleProperty(cardConfig.getAttrInit4(), cardConfig.getAttrAdd4(), map, cardInfo);
		}
	}

	/**
	 * 计算卡牌基础属性+等级加成+星阶加成
	 * 
	 * @param attInit
	 * @param attAdd
	 * @param map
	 * @param lv
	 */
	private void calcSimpleProperty(int attInit, int attAdd, Map<Integer, SimpleProperty> map, SoulCardInfo cardInfo) {
		if (attInit > 0) {
			SimpleProperty pro = SkillUtil.readPro(attInit);
			int temp = (pro.getValue() + (cardInfo.getLv()-1) * attAdd);
			temp = calcStarAdd(temp, cardInfo);
			pro.setValue(temp);
			if (map.containsKey(pro.getType())) {
				SimpleProperty t = map.get(pro.getType());
				t.setValue(t.getValue() + temp);
			}else{
				map.put(pro.getType(), pro);
			}
		}
	}
	
	/**
	 *  叠加属性接口
	 * @param map
	 * @param att
	 * @param count:卡牌数量。因为是每张都加
	 */
	public static void addAttValue(Map<Integer, SimpleProperty> map,int att){
		if (att > 0) {
			SimpleProperty pro = SkillUtil.readPro(att);
			pro.setValue(pro.getValue());
			if (map.containsKey(pro.getType())) {
				SimpleProperty t = map.get(pro.getType());
				t.setValue(t.getValue() + pro.getValue());
			}else{
				map.put(pro.getType(), pro);
			}
		}
	}

	/**
	 * 计算星级加成
	 * 
	 * @param temp
	 * @param cardInfo
	 * @return
	 */
	private int calcStarAdd(int temp, SoulCardInfo cardInfo) {
		if (cardInfo.getStar() != 0) {
			CardStarConfig starConfig = SoulTemplateMgr.getCardStarConfig(cardInfo.getStar());
			if (starConfig != null) {
				temp = (int) ((1 + starConfig.getAttrAdd() * 0.0001) * temp);
			} else {
				Log.error("CardStarConfig配置数据未有：" + cardInfo.getStar());
			}
		}
		return temp;
	}
}
