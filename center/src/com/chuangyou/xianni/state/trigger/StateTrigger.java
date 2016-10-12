package com.chuangyou.xianni.state.trigger;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.state.StateConditionConfig;
import com.chuangyou.xianni.entity.state.StateConditionInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.state.condition.BaseStateCondition;
import com.chuangyou.xianni.state.condition.StateConditionFactory;
import com.chuangyou.xianni.state.template.StateTemplateMgr;
import com.chuangyou.xianni.task.ITaskTrigger;

public class StateTrigger implements ITaskTrigger{

	private StateConditionInfo info;
	private StateConditionConfig config;
	private BaseStateCondition condition;
	private GamePlayer player;
	
	
	public StateTrigger(StateConditionInfo info,GamePlayer player) {
		this.info = info;
		this.player = player;
		this.config = StateTemplateMgr.getConditions().get(info.getStateId());
		if(config!=null){			
			this.condition = StateConditionFactory.createCondition(config,info,player);
			if(this.condition == null){
				Log.error("没有相对应的处理境界任务处理器："+config.getTargetType()+"info:"+info.toString());
			}
		}else{
			Log.error("境界配置表有错误："+info.getStateId());
		}
	}

	@Override
	public void addTrigger() {
		// TODO Auto-generated method stub
		if(this.condition!=null){
			this.condition.addTrigger(player);
		}
	}

	@Override
	public void removeTrigger() {
		// TODO Auto-generated method stub
		if(this.condition!=null){
			this.condition.removeTrigger(player);
		}
	}

	public StateConditionInfo getInfo() {
		return info;
	}

	public BaseStateCondition getCondition() {
		return condition;
	}

}
