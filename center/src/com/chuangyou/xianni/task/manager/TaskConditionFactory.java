package com.chuangyou.xianni.task.manager;

import com.chuangyou.xianni.entity.task.TaskCfg;
import com.chuangyou.xianni.entity.task.TaskInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.task.condition.BaseTaskCondiction;
import com.chuangyou.xianni.task.condition.ItemTaskCondition;
import com.chuangyou.xianni.task.condition.KillMonsterTaskCondition;
import com.chuangyou.xianni.task.condition.KillPrivateMonsterCondition;
import com.chuangyou.xianni.task.condition.NpcDialogTaskCondition;
import com.chuangyou.xianni.task.condition.PassFbTaskCondition;
import com.chuangyou.xianni.task.condition.QTETaskCondition;
import com.chuangyou.xianni.task.condition.TSystemTaskCondition;
import com.chuangyou.xianni.task.condition.TriggerTaskCondition;
import com.chuangyou.xianni.task.template.TaskTemplateMgr;

public class TaskConditionFactory {
	
	/**
	 * 创建任务条件
	 * @param info
	 * @param player
	 * @return
	 */
	public static BaseTaskCondiction createCondition(TaskInfo info,GamePlayer player){
		TaskCfg cfg = TaskTemplateMgr.getTaskCfg(info.getTaskId());
		if(cfg ==null)return null;
		BaseTaskCondiction condition = null;
		switch(cfg.getTaskTarget()){
			case TaskCfg.KILL_MONST:
				condition = new KillMonsterTaskCondition(info, cfg, player);
				break;
			case TaskCfg.COMMIT_ITEM:
			case TaskCfg.GET_ITEM:
			case TaskCfg.PATCH:	
				condition = new ItemTaskCondition(info, cfg, player);
				break;
			case TaskCfg.NPC_DIALOG:
				condition = new NpcDialogTaskCondition(info, cfg, player);
				break;
			case TaskCfg.PASS_FB:
				condition = new PassFbTaskCondition(info, cfg, player);
				break;
			case TaskCfg.T_SYSTEM:
				condition = new TSystemTaskCondition(info, cfg, player);
				break;
			case TaskCfg.QTE:
				condition = new QTETaskCondition(info, cfg, player);
				break;
			case TaskCfg.TRIGGER:
				condition = new TriggerTaskCondition(info, cfg, player);
				break;
			case TaskCfg.KILL_PRIVATE_MONSTER:
				condition = new KillPrivateMonsterCondition(info, cfg, player);
				break;
				default:
					break;
		}
		
		if(condition!=null){
			condition.addTrigger(player);
		}
		return condition;
		
		
	}
	
}
