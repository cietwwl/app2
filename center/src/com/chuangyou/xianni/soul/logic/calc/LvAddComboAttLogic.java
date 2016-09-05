package com.chuangyou.xianni.soul.logic.calc;

import java.util.Map;

import com.chuangyou.xianni.entity.soul.CardComboConfig;
import com.chuangyou.xianni.entity.soul.SoulCardInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.skill.template.SimpleProperty;

/**
 * 等级对组合的附加影响
 * @author laofan
 *
 */
public class LvAddComboAttLogic extends GetComboAttLogic {

	public LvAddComboAttLogic(ICalcAttLogic logic) {
		super(logic);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doProcess(GamePlayer player, Map<Integer, SimpleProperty> map, CardComboConfig config, Map<Integer, SoulCardInfo> cards) {
		// TODO Auto-generated method stub
		logic.doProcess(player, map, config, cards);
		if(!this.isValid(player, config))return; 
		//todo添加等级对组合的属性加成
		
		
		
		
	}
	
	

}
