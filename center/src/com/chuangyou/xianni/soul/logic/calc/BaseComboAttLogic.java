package com.chuangyou.xianni.soul.logic.calc;

import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.entity.soul.CardComboConfig;
import com.chuangyou.xianni.entity.soul.SoulCardInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.skill.template.SimpleProperty;

/**
 * 基础组合属性计算
 * @author laofan
 *
 */
public class BaseComboAttLogic extends ConcreteComponent {

	protected ICalcAttLogic logic;
	
	public BaseComboAttLogic(ICalcAttLogic logic) {
		this.logic = logic;
		// TODO Auto-generated constructor stub
	}

	/**
	 * 是否有效
	 * 
	 * @param player
	 * @return
	 */
	protected boolean isValid(GamePlayer player,CardComboConfig config){
		return true;
	}


	@Override
	public void doProcess(GamePlayer player, Map<Integer, SimpleProperty> map, CardComboConfig config, Map<Integer, SoulCardInfo> cards) {
		// TODO Auto-generated method stub
		logic.doProcess(player, map, config, cards);
		if(!this.isValid(player,config))return;
		//计算组合基本属性
		List<Integer> list = config.getEffectList();
		if(list==null)return;
		for (int att : list) {
			SoulCalcLogic.addAttValue(map, att);
		}
	}
	

}
