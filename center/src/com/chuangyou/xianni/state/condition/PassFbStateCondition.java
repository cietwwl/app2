package com.chuangyou.xianni.state.condition;

import com.chuangyou.xianni.entity.state.StateConditionConfig;
import com.chuangyou.xianni.entity.state.StateConditionInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.retask.constant.ConditionType;

/**
 * 通关副本
 * @author laofan
 *
 */
public class PassFbStateCondition extends KillMonsterStateCondition {

	public PassFbStateCondition(StateConditionInfo info, StateConditionConfig config, GamePlayer player) {
		super(info, config, player);
		// TODO Auto-generated constructor stub
		this.eventType = ConditionType.PASS_FB;
	}

}
