package com.chuangyou.xianni.state;

import com.chuangyou.xianni.entity.state.StateEventConfig;
import com.chuangyou.xianni.state.stateEvent.BaseStateEvent;
import com.chuangyou.xianni.state.stateEvent.BuffStateEvent;
import com.chuangyou.xianni.state.stateEvent.MonsterStateEvent;

/**
 * 事件生成工厂
 * @author laofan
 *
 */
public class StateEventFactory {

	public static BaseStateEvent createEventById(StateEventConfig config){
		switch(config.getType()){
			case StateEventConfig.TYPE_BUFF:
				return new BuffStateEvent(config);
			case StateEventConfig.TYPE_MONSTER:
				return new MonsterStateEvent(config);
		}
		return null;
	}
}
