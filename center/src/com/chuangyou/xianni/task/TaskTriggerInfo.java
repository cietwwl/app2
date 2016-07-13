package com.chuangyou.xianni.task;

import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.StringUtils;
import com.chuangyou.xianni.entity.task.TaskInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.script.manager.ScriptManager;
import com.chuangyou.xianni.task.condition.BaseTaskCondiction;
import com.chuangyou.xianni.task.manager.TaskConditionFactory;
import com.chuangyou.xianni.task.script.ITaskScript;


/**
 * 触发任务
 * @author laofan
 *
 */
public class TaskTriggerInfo implements ITaskTrigger {

	protected BaseTaskCondiction condition;
	protected GamePlayer player;
	protected TaskInfo info;
	
	
	public TaskTriggerInfo(GamePlayer player,TaskInfo info) {
		this.player = player;
		this.info = info;
		this.condition = TaskConditionFactory.createCondition(info, player);
		if(condition == null){
			Log.error("任务条件创建失败："+info.getTaskId());
		}else{
			this.condition.setParent(this);
		}
		
	}



	@Override
	public void addTrigger() {
		// TODO Auto-generated method stub
		if(this.condition==null)return;
		if(this.condition.getListener()==null){
			this.condition.addTrigger(player);
		}
	}


	@Override
	public void removeTrigger() {
		// TODO Auto-generated method stub
		if(this.condition==null)return;
		this.condition.removeTrigger(player);
	}
	
	
	/**
	 * 执行脚本
	 * @param scriptID
	 */
	public void doScript(String scriptID,byte state){
		//执行脚本
		if(!StringUtils.isNullOrEmpty(scriptID)){
			ITaskScript script= (ITaskScript) ScriptManager.getScriptById(scriptID);
			if(script!=null){
				if(state == 1){
					script.acceptTask(player.getPlayerId(), info.getTaskId());
				}else if(state == 2){
					script.finishTask(player.getPlayerId(), info.getTaskId());
				}else if(state == 3){
					script.commitTask(player.getPlayerId(), info.getTaskId());
				}
			}else{
				Log.error("找不到脚本："+scriptID);
			}
		}
	}



	public TaskInfo getInfo() {
		return info;
	}



	public BaseTaskCondiction getCondition() {
		return condition;
	}
	
	
	
}
