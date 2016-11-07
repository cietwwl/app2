package com.chuangyou.xianni.retask.trigger.soul;

import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.retask.iinterface.ITask;
import com.chuangyou.xianni.retask.iinterface.ITaskInitBehavior;
import com.chuangyou.xianni.retask.trigger.BaseTaskTrigger;

/**
 * 魂幡
 * @author laofan
 *
 */
public class SoulTaskTrigger extends BaseTaskTrigger implements ITaskInitBehavior{

	private BaseTaskTrigger proxy;
	
	public SoulTaskTrigger(GamePlayer player, ITask task) {
		super(player, task);
		// TODO Auto-generated constructor stub
		if(getTask().getTaskCfg().getTargetId() == 1 || getTask().getTaskCfg().getTargetId() == 2){
			proxy = new SoulLvTaskTrigger(player, task);
		}else if(getTask().getTaskCfg().getTargetId() == 3 || getTask().getTaskCfg().getTargetId() == 4){
			proxy = new SoulStarTaskTrigger(player, task);
		}else if(getTask().getTaskCfg().getTargetId() == 5 || getTask().getTaskCfg().getTargetId() == 6){
			proxy = new SoulProTaskTrigger(player, task);
		}else if(getTask().getTaskCfg().getTargetId() == 7){
			proxy = new SoulFightTaskTrigger(player, task);
		}
	}
	

	@Override
	public void addTrigger() {
		// TODO Auto-generated method stub
		if(proxy!=null)proxy.addTrigger();
	}

	@Override
	public void removeTrigger() {
		// TODO Auto-generated method stub
		if(proxy!=null)proxy.removeTrigger();
	}


	@Override
	public void initTask() {
		// TODO Auto-generated method stub
		if(proxy!=null)((ITaskInitBehavior)proxy).initTask();
	}

}
