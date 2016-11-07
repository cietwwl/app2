package com.chuangyou.xianni.retask.trigger;

import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.event.ObjectListener;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.retask.event.ActiveEvent;
import com.chuangyou.xianni.retask.iinterface.ITask;

/**
 * 活跃度任务
 * @author laofan
 *
 */
public class ActiveTaskTrigger extends BaseTaskTrigger {

	public ActiveTaskTrigger(GamePlayer player, ITask task) {
		super(player, task);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addTrigger() {
		// TODO Auto-generated method stub
		this.listener = new ObjectListener() {
			
			@Override
			public void onEvent(ObjectEvent event) {
				// TODO Auto-generated method stub
				ActiveEvent e = (ActiveEvent) event;
				if(e.getTargetId() == getTask().getTaskCfg().getTargetId()){
					getTask().updateProcess(getTask().getTaskInfo().getProcess(), e.getTargetNum());
				}
			}
		};
		this.player.addListener(listener, EventNameType.ADD_ACTIVE_VALUE);
	}

	@Override
	public void removeTrigger() {
		// TODO Auto-generated method stub
		this.player.removeListener(listener, EventNameType.ADD_ACTIVE_VALUE);
	}

}
