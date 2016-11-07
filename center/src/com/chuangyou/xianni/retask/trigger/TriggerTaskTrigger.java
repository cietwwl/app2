package com.chuangyou.xianni.retask.trigger;

import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.retask.iinterface.ITask;

/**
 * 触 发 器
 * @author laofan
 *
 */
public class TriggerTaskTrigger extends KillMonsterTaskTrigger{

	public TriggerTaskTrigger(GamePlayer player, ITask task) {
		super(player, task);
		// TODO Auto-generated constructor stub
		eventType = EventNameType.TASK_TRIGGER;
	}

}
