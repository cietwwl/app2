package com.chuangyou.xianni.task.script;

import com.chuangyou.xianni.script.IScript;

public interface ITaskScript extends IScript {

	/** *
	 * 接任务执行脚本
	 * @param playerId
	 * @param taskId
	 */
	public void acceptTask(long playerId,int taskId);
	
	/**
	 * 完成任务执行脚本
	 * @param playerId
	 * @param taskId
	 */
	public void finishTask(long playerId,int taskId);
	
	/**
	 * 提交任务执行脚本
	 * @param playerId
	 * @param taskId
	 */
	public void commitTask(long playerId,int taskId);
	
}
