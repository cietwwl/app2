package com.chuangyou.xianni.soul.logic.calc;

import java.util.Map;

import com.chuangyou.xianni.entity.soul.CardComboConfig;
import com.chuangyou.xianni.entity.soul.SoulCardInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.skill.template.SimpleProperty;

/**
 * 星级给卡牌组合附加的属性加成计算
 * @author laofan
 *
 */
public class StarAddComboAttLogic extends GetComboAttLogic {

	public StarAddComboAttLogic(ICalcAttLogic logic) {
		super(logic);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doProcess(GamePlayer player, Map<Integer, SimpleProperty> map, CardComboConfig config, Map<Integer, SoulCardInfo> cards) {
		// TODO Auto-generated method stub
		super.doProcess(player, map, config, cards);
		if(this.isValid(player, config)){
			//todo 计算星级对组合的加成
		}
	}

	
	

	

}
