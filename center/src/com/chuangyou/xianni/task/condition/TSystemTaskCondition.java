package com.chuangyou.xianni.task.condition;

import com.chuangyou.xianni.entity.task.TaskCfg;
import com.chuangyou.xianni.entity.task.TaskInfo;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.event.ObjectListener;
import com.chuangyou.xianni.player.GamePlayer;

/**
 * 天珠系统
 * @author laofan
 *
 */
public class TSystemTaskCondition extends KillMonsterTaskCondition {

	public TSystemTaskCondition(TaskInfo info, TaskCfg cfg, GamePlayer player) {
		super(info, cfg, player);
		// TODO Auto-generated constructor stub
		this.eventType = EventNameType.TASK_T_SYSTEM;
	}
	
	@Override
	public void addTrigger(GamePlayer player) {
		// TODO Auto-generated method stub
		if(this.info.getState()!=TaskInfo.ACCEPT)return;
		
		removeTrigger(player);
		dropAddTrigger(player);
		
		this.listener = new ObjectListener() {
			
			@Override
			public void onEvent(ObjectEvent event) {
				// TODO Auto-generated method stub
					doUpdateTask();
			}
		};
		this.player.addListener(listener, eventType);
	}

}
