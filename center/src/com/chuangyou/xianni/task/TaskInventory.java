package com.chuangyou.xianni.task;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.task.TaskInfo;
import com.chuangyou.xianni.event.AbstractEvent;
import com.chuangyou.xianni.interfaces.IInventory;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.sql.dao.DBManager;

public class TaskInventory extends AbstractEvent implements IInventory {

	private GamePlayer						player;

	private Map<Integer, TaskTriggerInfo>	taskInfos;

	/** 是否准备好 */
	private boolean							isReady	= false;

	public TaskInventory(GamePlayer player) {
		this.player = player;
	}

	/**
	 * 添加任务
	 * 
	 * @param info
	 * @return
	 */
	public boolean add(TaskTriggerInfo info) {
		info.addTrigger();
		taskInfos.put(info.getInfo().getTaskId(), info);
		return DBManager.getTaskdao().add(info.getInfo());
	}

	/**
	 * 删除任务
	 * 
	 * @param info
	 * @return
	 */
	public boolean del(TaskInfo info) {
		TaskTriggerInfo trigger = taskInfos.get(info.getTaskId());
		trigger.removeTrigger();
		taskInfos.remove(info.getTaskId());
		return DBManager.getTaskdao().del(info);
	}

	/////////////////////////////////////////////////////////////
	@Override
	public boolean loadFromDataBase() {
		// TODO Auto-generated method stub
		Map<Integer, TaskInfo> infos = DBManager.getTaskdao().get(player.getPlayerId());
		if (infos == null)
			return false;
		taskInfos = new HashMap<>();
		for (TaskInfo info : infos.values()) {
			TaskTriggerInfo triggerInfo = new TaskTriggerInfo(player, info);
			taskInfos.put(info.getTaskId(), triggerInfo);
			triggerInfo.addTrigger();
			if (triggerInfo.condition != null) {
				triggerInfo.condition.initProcess();
			}
		}
		return true;
	}

	@Override
	public boolean unloadData() {
		// TODO Auto-generated method stub
		for (TaskTriggerInfo info : taskInfos.values()) {
			info.removeTrigger();
		}
		taskInfos.clear();
		taskInfos = null;
		player = null;
		return true;
	}

	@Override
	public boolean saveToDatabase() {
		// TODO Auto-generated method stub
		if (taskInfos == null)
			return true;
		Iterator<Entry<Integer, TaskTriggerInfo>> it = taskInfos.entrySet().iterator();
		while (it.hasNext()) {
			TaskTriggerInfo info = it.next().getValue();
			if (info.getInfo().getOp() == Option.Update) {
				DBManager.getTaskdao().update(info.getInfo());
			}
		}
		return true;
	}

	public Map<Integer, TaskTriggerInfo> getTaskInfos() {
		return taskInfos;
	}

	public boolean isReady() {
		return isReady;
	}

	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}

}
