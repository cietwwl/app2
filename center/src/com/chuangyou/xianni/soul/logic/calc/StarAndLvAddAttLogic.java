package com.chuangyou.xianni.soul.logic.calc;

import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.soul.CardStarConfig;
import com.chuangyou.xianni.entity.soul.SoulCardConfig;
import com.chuangyou.xianni.entity.soul.SoulCardInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.skill.SkillUtil;
import com.chuangyou.xianni.skill.template.SimpleProperty;
import com.chuangyou.xianni.soul.template.SoulTemplateMgr;


/**
 * 计算卡牌基础属性+等级加成+星阶加成
 * @author laofan
 *
 */
public class StarAndLvAddAttLogic extends ConcreteComponent {

	protected ICalcAttLogic logic;
	
	

	public StarAndLvAddAttLogic(ICalcAttLogic logic) {
		super();
		this.logic = logic;
	}

	@Override
	public void doProcess(GamePlayer player, Map<Integer, SimpleProperty> map, SoulCardInfo cardInfo, Map<Integer, SoulCardInfo> cards) {
		// TODO Auto-generated method stub
		logic.doProcess(player, map, cardInfo, cards);
		calcOneCardAtt(player, cardInfo.getCardId(), map);
//		System.out.println("StarAndLvAddAttLogic:"+map);
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
			int temp = (pro.getValue() + (int)(cardInfo.getLv()-1) * attAdd/10000);
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
