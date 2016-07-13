package com.chuangyou.xianni.task.condition;

import com.chuangyou.xianni.entity.task.TaskCfg;
import com.chuangyou.xianni.entity.task.TaskInfo;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.event.ObjectListener;
import com.chuangyou.xianni.player.GamePlayer;

/**
 * npc对话任务满足条件 
 * @author laofan
 *
 */
public class NpcDialogTaskCondition extends KillMonsterTaskCondition {

	public NpcDialogTaskCondition(TaskInfo info, TaskCfg cfg, GamePlayer player) {
		super(info, cfg, player);
		// TODO Auto-generated constructor stub
		this.eventType = EventNameType.TASK_NPC_DIALOG;
	}
	
	@Override
	public void addTrigger(GamePlayer player) {
		// TODO Auto-generated method stub
		if(this.info.getState()!=TaskInfo.ACCEPT)return;
		this.listener = new ObjectListener() {
			
			@Override
			public void onEvent(ObjectEvent event) {
				// TODO Auto-generated method stub
				if((int)event.getObject() == info.getTaskId()){
					doUpdateTask();
				}
			}
		};
		this.player.addListener(listener, eventType);
	}
	
	

}
