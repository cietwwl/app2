package com.chuangyou.xianni.state.logic;

import java.util.HashMap;
import java.util.Map;

import com.chuangyou.xianni.entity.property.BaseProperty;
import com.chuangyou.xianni.entity.state.StateConfig;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.skill.SkillUtil;
import com.chuangyou.xianni.skill.template.SimpleProperty;
import com.chuangyou.xianni.soul.logic.calc.SoulCalcLogic;
import com.chuangyou.xianni.state.template.StateTemplateMgr;

/**
 * 计算境界加成属性
 * @author laofan
 *
 */
public class CalcStateAddAttLogic extends BaseStateLogic {
	
	public void calc(GamePlayer player,BaseProperty stateData,BaseProperty statePer){
		Map<Integer, SimpleProperty> map = new HashMap<>();
		
		int state = player.getBasePlayer().getPlayerInfo().getStateLv();
		if(state>0){
			for(int i=1;i<=state;i++){
				StateConfig config = StateTemplateMgr.getStates().get(i);
				if(config!=null){
					int[] atts = config.getPropertyList();
					for (int j : atts) {
						SoulCalcLogic.addAttValue(map, j);
					}
				}
			}
		}
		
		for (SimpleProperty property : map.values()) {
			if (property.isPre()) {
				SkillUtil.joinPro(statePer, property.getType(), property.getValue());
			} else {
				SkillUtil.joinPro(stateData, property.getType(), property.getValue());
			}
		}
	}
}
