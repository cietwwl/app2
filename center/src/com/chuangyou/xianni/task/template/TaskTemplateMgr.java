package com.chuangyou.xianni.task.template;

import java.util.HashMap;
import java.util.Map;

import com.chuangyou.xianni.entity.task.TaskCfg;
import com.chuangyou.xianni.sql.dao.DBManager;

/**
 * 模板管理器
 * @author laofan
 *
 */
public class TaskTemplateMgr {

	private static Map<Integer, TaskCfg> tasks = new HashMap<>();
	
	private static Map<Byte, TaskCfg> linkHeadTasks;
	
	
	public static boolean init(){
		return reloadTemplate();
	}
	
	public static boolean reloadTemplate(){
		tasks = DBManager.getTasktemplatedao().getAll();
		if(tasks==null)return false;
		linkHeadTasks = new HashMap<>();
		for (TaskCfg cfg : tasks.values()) {
			if(cfg.getTaskPre()==0){
				linkHeadTasks.put(cfg.getTaskLink(), cfg);
			}
		}
		return true;
	}
	
	/**
	 * 获取任务模板
	 * @param taskId
	 * @return
	 */
	public static TaskCfg getTaskCfg(int taskId){
		return tasks.get(taskId);
	}
	
	
	/**
	 * 获取链表的头任务
	 * @param link
	 * @return
	 */
	public static TaskCfg getLinkHead(byte link){
		return linkHeadTasks.get(link);
	}
	
	
	
}
