package com.chuangyou.xianni.retask.trigger;

import java.util.Map;

import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.event.ObjectListener;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.retask.iinterface.ITask;
import com.chuangyou.xianni.retask.iinterface.ITaskInitBehavior;
import com.chuangyou.xianni.retask.vo.StateTask;

/**
 * 渡劫任务完成情况
 * @author laofan
 *
 */
public class StateTaskTrigger extends BaseTaskTrigger implements ITaskInitBehavior {

	public StateTaskTrigger(GamePlayer player, ITask task) {
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
				getTask().updateProcess(getCount(player.getStateInventory().getStateTasks(), getTask().getTaskCfg().getTargetId1()));
			}
		};
		this.player.addListener(listener, EventNameType.STATE_TAKS_FINISH);
		
	}

	@Override
	public void removeTrigger() {
		// TODO Auto-generated method stub
		this.player.removeListener(listener, EventNameType.STATE_TAKS_FINISH);
	}

	@Override
	public void initTask() {
		// TODO Auto-generated method stub
		getTask().getTaskInfo().setProcess(getCount(player.getStateInventory().getStateTasks(), getTask().getTaskCfg().getTargetId1()));
	}

	private int getCount(Map<Integer,StateTask> map,int stage){
		int count = 0;
		if(player.getBasePlayer().getPlayerInfo().getStateLv() == stage){
			for (StateTask task : map.values()) {
				if(task!=null){
					if(task.isFinish()){
						count++;
					}
				}
			}
		}
		return count;
	}
}
