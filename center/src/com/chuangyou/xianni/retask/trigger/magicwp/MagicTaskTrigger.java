package com.chuangyou.xianni.retask.trigger.magicwp;

import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.retask.iinterface.ITask;
import com.chuangyou.xianni.retask.iinterface.ITaskInitBehavior;
import com.chuangyou.xianni.retask.trigger.BaseTaskTrigger;

/**
 * 法宝
 * 
 * @author laofan
 *
 */
public class MagicTaskTrigger extends BaseTaskTrigger implements ITaskInitBehavior {

	private BaseTaskTrigger proxy;

	public MagicTaskTrigger(GamePlayer player, ITask task) {
		super(player, task);
		// TODO Auto-generated constructor stub
		switch (getTask().getTaskCfg().getTargetId()) {
			case 1:
			case 2:
				proxy = new MagicActiveTaskTrigger(player, task);
				break;
			case 3:
			case 4:
			case 5:
			case 6:
				proxy = new MagicWpTaskTrigger(player, task);
				break;
			case 7:
				proxy = new MagicFightTaskTrigger(player, task);
			default:
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
