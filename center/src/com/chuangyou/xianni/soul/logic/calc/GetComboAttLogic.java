package com.chuangyou.xianni.soul.logic.calc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.entity.soul.CardComboConfig;
import com.chuangyou.xianni.entity.soul.SoulCardInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.skill.template.SimpleProperty;

/**
 * 获得+上阵其中一张就可以激活 属性加成计算
 * 
 * @author laofan
 *
 */
public class GetComboAttLogic extends BaseComboAttLogic {

	public GetComboAttLogic(ICalcAttLogic logic) {
		super(logic);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 1：组合齐全
	 * 2：组合中至少有一张卡穿在身上
	 */
	@Override
	protected boolean isValid(GamePlayer player,CardComboConfig config) {
		// TODO Auto-generated method stub
		if(config.getType() != CardComboConfig.TYPE_GET)return false;
		List<Integer> allCardIds = new ArrayList<>(player.getSoulInventory().getCards().keySet());
		List<Integer> putOnList  = player.getSoulInventory().getSoulInfo().getCardsList();
		List<Integer> comboList  = config.getMateList();
		putOnList.retainAll(comboList);
		if(putOnList.size()==0)return false;
		if(!allCardIds.containsAll(comboList))return false;	
		return true;
	}

	@Override
	public void doProcess(GamePlayer player, Map<Integer, SimpleProperty> map, SoulCardInfo soulCard, Map<Integer, SoulCardInfo> cards) {
		// TODO Auto-generated method stub
		super.doProcess(player, map, soulCard, cards);
//		System.out.println("GetComboAttLogic:"+map);
	}

	
	

}
