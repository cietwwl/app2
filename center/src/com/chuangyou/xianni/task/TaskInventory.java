package com.chuangyou.xianni.task;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.task.TaskCfg;
import com.chuangyou.xianni.entity.task.TaskInfo;
import com.chuangyou.xianni.event.AbstractEvent;
import com.chuangyou.xianni.interfaces.IInventory;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.retask.constant.ConditionType;
import com.chuangyou.xianni.retask.vo.RealTask;
import com.chuangyou.xianni.sql.dao.DBManager;
import com.chuangyou.xianni.task.template.TaskTemplateMgr;

public class TaskInventory extends AbstractEvent implements IInventory {

	private GamePlayer				player;

	private Map<Integer, RealTask>	taskInfos;

	/** 是否准备好 */
	private boolean					isReady	= false;

	public TaskInventory(GamePlayer player) {
		this.player = player;
	}

	/**
	 * 添加任务
	 * 
	 * @param info
	 * @return
	 */
	public void add(RealTask task) {
		taskInfos.put(task.getInfo().getTaskId(),task);
		DBManager.getTaskdao().add(task.getInfo());
	}

	 /**
	 * 删除任务
	 *
	 * @param info
	 * @return
	 */
	 public boolean del(int taskId) {
		 RealTask trigger = taskInfos.get(taskId);
		 trigger.removeTrigger();
		 taskInfos.remove(taskId);
		 return DBManager.getTaskdao().del(trigger.getInfo());
	 }

	/////////////////////////////////////////////////////////////
	@Override
	public boolean loadFromDataBase() {
		// TODO Auto-generated method stub
//		Map<Integer, TaskInfo> infos = DBManager.getTaskdao().get(player.getPlayerId());
//		if (infos == null) {
//			return false;
//		}
		return true;
	}
	
	private void doLoadDataBase(){
		Map<Integer, TaskInfo> infos = DBManager.getTaskdao().get(player.getPlayerId());
		if (infos == null) {
			return;
		}
		taskInfos = new HashMap<>();
		for (TaskInfo info : infos.values()) {
			TaskCfg cfg = TaskTemplateMgr.getTaskCfg(info.getTaskId());
			if (cfg == null) {
				continue;
			}
			RealTask realTask = new RealTask(cfg, info, player);
			taskInfos.put(info.getTaskId(), realTask);
			realTask.addTrigger();
			if(cfg.getTaskTarget() == ConditionType.KILL_PRIVATE_MONSTER){
				realTask.initTask();
			}
		}
	}

	@Override
	public boolean unloadData() {
		// TODO Auto-generated method stub
		if(this.taskInfos != null){			
			for (RealTask info : taskInfos.values()) {
				info.removeTrigger();
			}
			taskInfos.clear();
		}
		taskInfos = null;
		player = null;
		return true;
	}

	@Override
	public boolean saveToDatabase() {
		// TODO Auto-generated method stub
		if (taskInfos == null){			
			return true;
		}
		Iterator<RealTask> it = taskInfos.values().iterator();
		while (it.hasNext()) {
			RealTask info = it.next();
			if (info.getInfo().getOp() == Option.Update) {
				DBManager.getTaskdao().update(info.getInfo());
			}else if(info.getInfo().getOp() == Option.Insert){
				DBManager.getTaskdao().add(info.getInfo());
			}
		}
		return true;
	}

	public Map<Integer, RealTask> getTaskInfos() {
		if(taskInfos == null){
			doLoadDataBase();
		}
		return taskInfos;
	}

	public boolean isReady() {
		return isReady;
	}

	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}

}
