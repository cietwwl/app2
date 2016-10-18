package com.chuangyou.xianni.task.condition;

import com.chuangyou.xianni.entity.task.TaskCfg;
import com.chuangyou.xianni.entity.task.TaskInfo;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.event.ObjectListener;
import com.chuangyou.xianni.player.GamePlayer;

/**
 * 物品相关任务条件
 * @author laofan
 *
 */
public class ItemTaskCondition extends KillMonsterTaskCondition {

	private ObjectListener reduceListener;
	
	public ItemTaskCondition(TaskInfo info, TaskCfg cfg, GamePlayer player) {
		super(info, cfg, player);
		// TODO Auto-generated constructor stub
		this.eventType = EventNameType.TASK_ITEM_CHANGE_ADD;
	}

	
	
	@Override
	public void addTrigger(GamePlayer player) {
		// TODO Auto-generated method stub
		if(this.info.getState()!=TaskInfo.ACCEPT)return;
		removeTrigger(player);
		dropAddTrigger(player);	
		this.listener = new ObjectListener() {			
			@Override
			public void onEvent(ObjectEvent event) {
				// TODO Auto-generated method stub
				if((int)event.getObject() == cfg.getTargetId()){
					doAddUpdate();
				}
			}
		};
		this.player.getBagInventory().addListener(listener, eventType);
		reduceListener =  new ObjectListener() {
			
			@Override
			public void onEvent(ObjectEvent event) {
				// TODO Auto-generated method stub
				if((int)event.getObject() == cfg.getTargetId()){
					doReduceUpdate();
				}
			}
		};
		this.player.getBagInventory().addListener(reduceListener,EventNameType.TASK_ITEM_CHANGE_REDUCE);
	}

	@Override
	public void removeTrigger(GamePlayer player) {
		// TODO Auto-generated method stub
		this.dropRemoveTrigger(player);
		this.player.getBagInventory().removeListener(listener, eventType);
		this.player.getBagInventory().removeListener(reduceListener,EventNameType.TASK_ITEM_CHANGE_REDUCE);
	}
	
	
	/**
	 * 减少进度的相关更新
	 */
	protected void doReduceUpdate(){
		int num = this.info.getProcess();
		int nowNum = this.player.getBagInventory().getItemCount(cfg.getTargetId());
		int temp = num - nowNum;
		this.reduceProcess(temp);
	}

	/**
	 * 增加物品的进度操作
	 */
	protected void doAddUpdate() {
		int num = this.info.getProcess();
		int nowNum = this.player.getBagInventory().getItemCount(cfg.getTargetId());
		int temp = nowNum - num;
		this.addProcess(temp);
		this.doFinishNoCancelTrigger();
	}

	/**
	 * 初始处理
	 * 1：任务可接初始化
	 * 2：任务从数据库加载初始化
	 */
	@Override
	public void initProcess() {
		// TODO Auto-generated method stub
		if(info.getState() == TaskInfo.ACCEPT){
			acceptProcess();
		}
	}



	@Override
	public void acceptProcess() {
		// TODO Auto-generated method stub
		int nowNum = this.player.getBagInventory().getItemCount(cfg.getTargetId());
		this.addProcess(nowNum);
		this.doFinishNoCancelTrigger();	
	}
	
	

}
