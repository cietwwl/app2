package com.chuangyou.xianni.task.condition;

import com.chuangyou.xianni.entity.task.TaskCfg;
import com.chuangyou.xianni.entity.task.TaskInfo;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.player.GamePlayer;

/**
 * QTE任务条件 
 * @author laofan
 *
 */
public class QTETaskCondition extends NpcDialogTaskCondition {

	public QTETaskCondition(TaskInfo info, TaskCfg cfg, GamePlayer player) {
		super(info, cfg, player);
		// TODO Auto-generated constructor stub
		this.eventType = EventNameType.TASK_QTE;
	}

}
