package com.chuangyou.xianni.retask.trigger.soul;

import com.chuangyou.xianni.entity.soul.SoulInfo;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.event.ObjectListener;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.retask.iinterface.ITask;
import com.chuangyou.xianni.retask.iinterface.ITaskInitBehavior;
import com.chuangyou.xianni.retask.trigger.BaseTaskTrigger;

/**
 * 熟练度
 * 
 * @author laofan
 *
 */
public class SoulProTaskTrigger extends BaseTaskTrigger implements ITaskInitBehavior {

	public SoulProTaskTrigger(GamePlayer player, ITask task) {
		super(player, task);
		// TODO Auto-generated constructor stub
	}

	private int getCount(SoulInfo soulInfo, int num) {
		int count = 0;
		for (int value : soulInfo.getSoulProList()) {
			if (value >= num) {
				count++;
			}
		}
		return count;
	}


	@Override
	public void addTrigger() {
		// TODO Auto-generated method stub
		removeTrigger();
		this.listener = new ObjectListener() {

			@Override
			public void onEvent(ObjectEvent event) {
				// TODO Auto-generated method stub
				int index = (int) event.getObject();
				int targetId = getTask().getTaskCfg().getTargetId();
				if (targetId == 5) {
					if (index == getTask().getTaskCfg().getTargetNum()) {
						getTask().updateProcess(player.getSoulInventory().getSoulInfo().getProficiency(getTask().getTaskCfg().getTargetNum()));
					}
				} else if (targetId == 6) {
					getTask().updateProcess(getCount(player.getSoulInventory().getSoulInfo(), getTask().getTaskCfg().getTargetId1()));
				}
				

			}
		};
		player.addListener(listener, EventNameType.SOUL_PRO);
	}

	
	@Override
	public void removeTrigger() {
		// TODO Auto-generated method stub
		player.removeListener(listener, EventNameType.SOUL_PRO);
	}

	@Override
	public void initTask() {
		// TODO Auto-generated method stub
		int targetId = getTask().getTaskCfg().getTargetId();
		if (targetId == 5) {
			getTask().getTaskInfo().setProcess(player.getSoulInventory().getSoulInfo().getProficiency(getTask().getTaskCfg().getTargetNum()));
		} else if (targetId == 6) {
			getTask().getTaskInfo().setProcess(getCount(player.getSoulInventory().getSoulInfo(), getTask().getTaskCfg().getTargetId1()));
		}
	}

}
