package com.chuangyou.xianni.retask.trigger.magicwp;

import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.event.ObjectListener;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.retask.iinterface.ITask;
import com.chuangyou.xianni.retask.iinterface.ITaskInitBehavior;
import com.chuangyou.xianni.retask.trigger.BaseTaskTrigger;


public class MagicActiveTaskTrigger extends BaseTaskTrigger implements ITaskInitBehavior {

	public MagicActiveTaskTrigger(GamePlayer player, ITask task) {
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
				int magicwpId = (int) event.getObject();
				if (getTask().getTaskCfg().getTargetId() == 1) {
					if (getTask().getTaskCfg().getTargetNum() == magicwpId) {
						getTask().updateProcess(1);
					}
				} else {
					getTask().updateProcess(player.getMagicwpInventory().getMagicwpInfoMap().size());
				}

				

			}
		};
		player.addListener(listener, EventNameType.MAGICWP_ACTIVE);
	}

	@Override
	public void removeTrigger() {
		// TODO Auto-generated method stub
		player.removeListener(listener, EventNameType.MAGICWP_ACTIVE);
	}

	@Override
	public void initTask() {
		// TODO Auto-generated method stub
		if (getTask().getTaskCfg().getTargetId() == 1) {
			if (player.getMagicwpInventory().getMagicwpInfo(getTask().getTaskCfg().getTargetNum()) != null) {
				getTask().getTaskInfo().setProcess(1);
			}
		} else if (getTask().getTaskCfg().getTargetId() == 2) {
			getTask().getTaskInfo().setProcess(player.getMagicwpInventory().getMagicwpInfoMap().size());
		}
	}

}
