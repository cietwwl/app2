package com.chuangyou.xianni.state.condition;

import com.chuangyou.xianni.entity.state.StateConditionConfig;
import com.chuangyou.xianni.entity.state.StateConditionInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.retask.constant.ConditionType;

/**
 * 触发器境界任务
 * @author laofan
 *
 */
public class TriggerStateCondition extends KillMonsterStateCondition {

	public TriggerStateCondition(StateConditionInfo info, StateConditionConfig config, GamePlayer player) {
		super(info, config, player);
		// TODO Auto-generated constructor stub
		eventType = ConditionType.TRIGGER;
	}
	
	

}
