package com.chuangyou.xianni.task.condition;

import java.util.Date;

import com.chuangyou.xianni.drop.manager.DropManager;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.task.TaskCfg;
import com.chuangyou.xianni.entity.task.TaskInfo;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.event.ObjectListener;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.task.TaskTriggerInfo;
import com.chuangyou.xianni.task.manager.TaskManager;

/**
 * 任务条件抽象类
 * @author laofan
 *
 */
public abstract class BaseTaskCondiction {
	
	protected TaskInfo info;
	protected TaskCfg cfg;
	protected GamePlayer player;
	protected ObjectListener listener;
	protected ObjectListener dropListener;
	protected TaskTriggerInfo parent;
	
	public abstract void addTrigger(GamePlayer player);

	public abstract void removeTrigger(GamePlayer player);
	
	
	/**
	 * 添加掉落触发器
	 * @param player
	 */
	protected void dropAddTrigger(GamePlayer player){
		if(this.info.getState()!=TaskInfo.ACCEPT)return;
		dropRemoveTrigger(player);
		if(cfg.getDropId()>0){
			this.dropListener = new ObjectListener() {	
				@Override
				public void onEvent(ObjectEvent event) {
					// TODO Auto-generated method stub
					DropManager.dropTaskItems(player, cfg.getDropId());
				}
			};
			this.player.addListener(dropListener, EventNameType.TASK_KILL_MONSTER);
		}
	}
	
	/**
	 * 删除掉落触发器
	 * @param player
	 */
	protected void dropRemoveTrigger(GamePlayer player){
		this.player.removeListener(dropListener, EventNameType.TASK_KILL_MONSTER);
	}
	
	/** 初始化进度 */
	public abstract void initProcess();
	
	/**  接收任务处理  */
	public abstract void acceptProcess();
	
	public BaseTaskCondiction(TaskInfo info, TaskCfg cfg, GamePlayer player) {
		super();
		this.info = info;
		this.cfg = cfg;
		this.player = player;
	}
	
	/**
	 * 任务完成后。 执行
	 * 且不取消事件监听
	 * @return
	 */
	protected void doFinishNoCancelTrigger(){
		if(this.info.getProcess()>=cfg.getTargetNum()){
			doUpdate();
			TaskManager.sendNotify(player, info);
			this.parent.doScript(cfg.getCompleteScriptId(),this.info.getState());
		}else{	
			TaskManager.sendNotify(player, info);
		}
	}
	
	/**
	 * 做任务完成执行操作。
	 * 且取消事件监听
	 */
	protected void doFinishAndCancelTrigger(){
		if(this.info.getProcess()>=cfg.getTargetNum()){
			this.removeTrigger(player);
			doUpdate();
			TaskManager.sendNotify(player, info);
			this.parent.doScript(cfg.getCompleteScriptId(),this.info.getState());
		}else{
			TaskManager.sendNotify(player, info);
		}
	}
	
	/**
	 * 执行更新
	 */
	protected void doUpdate(){
		this.info.setState(TaskInfo.FINISH);
		this.info.setOp(Option.Update);
		this.info.setUpdateTime(new Date());
	}
	

	/**
	 * 增加进度
	 * @param num
	 */
	protected void addProcess(int num){
		if(this.info.getState()!=TaskInfo.ACCEPT)return;
		this.info.setProcess(this.info.getProcess()+num);
		this.info.setUpdateTime(new Date());
		this.info.setOp(Option.Update);
	}
	
	/**
	 * 减进度
	 * @param num
	 */
	protected void reduceProcess(int num){
		this.info.setProcess(this.info.getProcess()-num);
		this.info.setUpdateTime(new Date());
		if(this.info.getProcess()<cfg.getTargetNum()){
			this.info.setState(TaskInfo.ACCEPT);
			doFinishNoCancelTrigger();
		}
		this.info.setOp(Option.Update);
	}

	public TaskInfo getInfo() {
		return info;
	}

	public void setParent(TaskTriggerInfo parent) {
		this.parent = parent;
	}

	public ObjectListener getListener() {
		return listener;
	}

	public TaskCfg getCfg() {
		return cfg;
	}

}
