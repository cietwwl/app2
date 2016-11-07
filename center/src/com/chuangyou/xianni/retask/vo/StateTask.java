package com.chuangyou.xianni.retask.vo;

import com.chuangyou.xianni.entity.task.ITaskCfg;
import com.chuangyou.xianni.entity.task.ITaskInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.retask.behavior.process.StateTaskProcessBehavior;
import com.chuangyou.xianni.retask.factory.AbstractTaskTriggerFactory;
import com.chuangyou.xianni.retask.factory.StateTaskFactory;
import com.chuangyou.xianni.retask.iinterface.ITaskProcessBehavior;

/**
 * 
 * 境界任务
 * @author laofan
 *
 */
public class StateTask extends SimpleTask {

	public StateTask(ITaskCfg cfg, ITaskInfo info, GamePlayer player) {
		super(cfg, info, player);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public AbstractTaskTriggerFactory getFactory() {
		// TODO Auto-generated method stub
		return StateTaskFactory.getInstance();
	}

	
	@Override
	public ITaskProcessBehavior getTaskProcessBehavior() {
		// TODO Auto-generated method stub
		return new StateTaskProcessBehavior(this,player);
	}

}
