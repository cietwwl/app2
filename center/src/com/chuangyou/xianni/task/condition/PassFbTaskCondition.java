package com.chuangyou.xianni.task.condition;

import com.chuangyou.xianni.entity.task.TaskCfg;
import com.chuangyou.xianni.entity.task.TaskInfo;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.player.GamePlayer;

/**
 * 通关副本
 * @author laofan
 *
 */
public class PassFbTaskCondition extends KillMonsterTaskCondition {

	
	public PassFbTaskCondition(TaskInfo info, TaskCfg cfg, GamePlayer player) {
		super(info, cfg, player);
		this.eventType = EventNameType.TASK_PASS_FB;
		// TODO Auto-generated constructor stub
	}

	

}
