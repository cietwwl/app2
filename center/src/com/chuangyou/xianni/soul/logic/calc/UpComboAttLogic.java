package com.chuangyou.xianni.soul.logic.calc;

import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.entity.soul.CardComboConfig;
import com.chuangyou.xianni.entity.soul.SoulCardInfo;
import com.chuangyou.xianni.entity.soul.SoulInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.skill.template.SimpleProperty;

/**
 * 上阵才加成属性的组合类型
 * 
 * @author laofan
 *
 */
public class UpComboAttLogic extends BaseComboAttLogic {

	public UpComboAttLogic(ICalcAttLogic logic) {
		super(logic);
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * 1：组合齐全。
	 * 2：组合中所有卡都上阵
	 */
	@Override
	protected boolean isValid(GamePlayer player,CardComboConfig config){
		if(config.getType() != CardComboConfig.TYPE_UP)return false;
		SoulInfo info = player.getSoulInventory().getSoulInfo();
		List<Integer> list = info.getCardsList();
		boolean i = list.containsAll(config.getMateList());
		return i;
	}


	@Override
	public void doProcess(GamePlayer player, Map<Integer, SimpleProperty> map, SoulCardInfo soulCard, Map<Integer, SoulCardInfo> cards) {
		// TODO Auto-generated method stub
		super.doProcess(player, map, soulCard, cards);
//		System.out.println("UpComboAttLogic:"+map.get(3).getValue());
	}
	
	
	

	

	

}
