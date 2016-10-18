package com.chuangyou.xianni.state.condition;

import com.chuangyou.xianni.entity.state.StateConditionConfig;
import com.chuangyou.xianni.entity.state.StateConditionInfo;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.event.ObjectListener;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.task.constant.ConditionType;

/**
 *  杀怪境界任务
 * @author laofan
 *
 */
public class KillMonsterStateCondition extends BaseStateCondition {

	protected int eventType = ConditionType.KILL_MONST;
	
	public KillMonsterStateCondition(StateConditionInfo info, StateConditionConfig config, GamePlayer player) {
		super(info, config, player);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addTrigger(GamePlayer player) {
		// TODO Auto-generated method stub
		this.initListener();		
		this.player.addListener(listener, eventType);
	}
	
	/**
	 * 增加进度1
	 */
	protected void incOneProcess(){
		info.setProcess(info.getProcess()+1);
		doNotifyUpdate();
	}

	@Override
	public void removeTrigger(GamePlayer player) {
		// TODO Auto-generated method stub
		this.player.removeListener(listener, eventType);
	}

	@Override
	public void initProcess() {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean commitProcess() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		if(this.listener != null)return;
		this.listener = new ObjectListener() {		
			@Override
			public void onEvent(ObjectEvent event) {
				// TODO Auto-generated method stub
				if((int)event.getObject() == config.getTargetId()){
					incOneProcess();
				}
			}
		};
	}

}
