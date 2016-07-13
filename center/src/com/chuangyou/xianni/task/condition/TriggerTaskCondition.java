package com.chuangyou.xianni.task.condition;

import com.chuangyou.xianni.entity.task.TaskCfg;
import com.chuangyou.xianni.entity.task.TaskInfo;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.player.GamePlayer;

/**
 * 触发性任务条件
 * @author laofan
 *
 */
public class TriggerTaskCondition extends KillMonsterTaskCondition {

	public TriggerTaskCondition(TaskInfo info, TaskCfg cfg, GamePlayer player) {
		super(info, cfg, player);
		// TODO Auto-generated constructor stub
		this.eventType = EventNameType.TASK_TRIGGER;
	}

}
