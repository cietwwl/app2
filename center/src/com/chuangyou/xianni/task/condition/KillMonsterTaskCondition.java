package com.chuangyou.xianni.task.condition;

import com.chuangyou.xianni.entity.task.TaskCfg;
import com.chuangyou.xianni.entity.task.TaskInfo;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.event.ObjectListener;
import com.chuangyou.xianni.player.GamePlayer;

/**
 * 杀怪任务条件
 * @author laofan
 *
 */
public class KillMonsterTaskCondition extends BaseTaskCondiction {

	protected int eventType = EventNameType.TASK_KILL_MONSTER;
	
	public KillMonsterTaskCondition(TaskInfo info, TaskCfg cfg, GamePlayer player) {
		super(info, cfg, player);
		// TODO Auto-generated constructor stub
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
				if((int)event.getObject() == cfg.getTargetId()){
					doUpdateTask();
				}
			}
		};
		this.player.addListener(listener, eventType);
	}

	@Override
	public void removeTrigger(GamePlayer player) {
		// TODO Auto-generated method stub
		this.dropRemoveTrigger(player);
		this.player.removeListener(listener, eventType);
	}
	
	
	/**
	 * 执行任务更新
	 */
	protected void doUpdateTask(){
		this.addProcess(1);
		this.doFinishAndCancelTrigger();
	}

	@Override
	public void initProcess() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void acceptProcess() {
		// TODO Auto-generated method stub
		
	}

		
	
}
