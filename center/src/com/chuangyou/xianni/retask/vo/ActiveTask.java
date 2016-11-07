package com.chuangyou.xianni.retask.vo;

import java.util.Date;

import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.active.ActiveConfig;
import com.chuangyou.xianni.entity.active.ActiveInfo;
import com.chuangyou.xianni.entity.task.ITaskCfg;
import com.chuangyou.xianni.entity.task.ITaskInfo;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.retask.behavior.process.ActiveProcessBehavior;
import com.chuangyou.xianni.retask.event.ActiveEvent;
import com.chuangyou.xianni.retask.factory.AbstractTaskTriggerFactory;
import com.chuangyou.xianni.retask.factory.ActiveTaskFactory;
import com.chuangyou.xianni.retask.iinterface.ITaskProcessBehavior;

/**
 * 活跃系统任务
 * @author laofan
 *
 */
public class ActiveTask extends SimpleTask {

	public ActiveTask(ITaskCfg cfg, ITaskInfo info, GamePlayer player) {
		super(cfg, info, player);
		// TODO Auto-generated constructor stub
	}

	@Override
	public AbstractTaskTriggerFactory getFactory() {
		// TODO Auto-generated method stub
		return ActiveTaskFactory.getInstance();
	}

	@Override
	public ITaskProcessBehavior getTaskProcessBehavior() {
		// TODO Auto-generated method stub
		return new ActiveProcessBehavior(this, player);
	}
	
	public ActiveConfig getConfig(){
		return (ActiveConfig) this.cfg;
	}
	
	public ActiveInfo getInfo(){
		return (ActiveInfo) this.info;
	}
	
	/**
	 * 是否可以开始
	 * @return
	 */
	protected boolean isCanStart(){
		if(getConfig().getNeedLv()>player.getBasePlayer().getPlayerInfo().getLevel()){
			return false;
		}
		return true;
	}
	
	
	
	/**
	 * 任务完成处理
	 * 1：给活跃度奖励
	 * 2：设置状态改变
	 */
	public void doTaskFinish(){
		if(this.isFinish() && getInfo().getStatus()==0){	
			this.player.notifyListeners(new ActiveEvent(this,getConfig().getType(),0,getConfig().getReward(),EventNameType.ADD_ACTIVE_VALUE));
		//	this.player.notifyListeners(new ObjectEvent(this, getConfig().getReward(), EventNameType.ADD_ACTIVE_VALUE));
			getInfo().setStatus(1);
			getInfo().setRewardTime(new Date());
			getInfo().setOp(Option.Update);
		}
	}

	
	@Override
	public void initTask() {
		// TODO Auto-generated method stub
		if(this.isCanStart()){			
			super.initTask();
		}
	}

	@Override
	public void updateProcess(int newValue) {
		// TODO Auto-generated method stub
		if(this.isCanStart()){				
			super.updateProcess(newValue);
		}
	}



}
