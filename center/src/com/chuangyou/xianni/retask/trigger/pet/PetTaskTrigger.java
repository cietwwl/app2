package com.chuangyou.xianni.retask.trigger.pet;

import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.retask.iinterface.ITask;
import com.chuangyou.xianni.retask.iinterface.ITaskInitBehavior;
import com.chuangyou.xianni.retask.trigger.BaseTaskTrigger;

/**
 * 宠物
 * 
 * @author laofan
 *
 */
public class PetTaskTrigger extends BaseTaskTrigger implements ITaskInitBehavior {

	private BaseTaskTrigger proxy;

	public PetTaskTrigger(GamePlayer player, ITask task) {
		super(player, task);
		// TODO Auto-generated constructor stub
		switch (task.getTaskCfg().getTargetId()) {
			case 1:
			case 2:
				proxy = new PetActiveTaskTrigger(player, task);
				break;
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
			case 11:
			case 12:
			case 13:
			case 14:
				proxy = new PetOtherTaskTrigger(player, task);
				break;
			case 10:
				proxy = new PetFightTaskTrigger(player, task);
				break;
		}
	}

	@Override
	public void addTrigger() {
		// TODO Auto-generated method stub
		if (proxy != null)
			proxy.addTrigger();
	}

	@Override
	public void removeTrigger() {
		// TODO Auto-generated method stub
		if (proxy != null)
			proxy.removeTrigger();
	}

	@Override
	public void initTask() {
		// TODO Auto-generated method stub
		if (proxy != null)
			((ITaskInitBehavior) proxy).initTask();
	}

}
