package com.chuangyou.xianni.retask.vo;

import com.chuangyou.xianni.entity.task.ITaskCfg;
import com.chuangyou.xianni.entity.task.ITaskInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.retask.factory.AbstractTaskTriggerFactory;
import com.chuangyou.xianni.retask.iinterface.ITask;
import com.chuangyou.xianni.retask.iinterface.ITaskInitBehavior;
import com.chuangyou.xianni.retask.iinterface.ITaskProcessBehavior;
import com.chuangyou.xianni.retask.iinterface.ITriggerObserver;

/**
 * 简单任务类
 * @author laofan
 *
 */
public abstract class SimpleTask implements ITask {

	/**
	 * 静态配置表
	 */
	protected final ITaskCfg cfg;
	/**
	 * 动态数据
	 */
	protected final ITaskInfo info;
	
	/**
	 * 事件观察者
	 */
	private final ITriggerObserver triggerObserver;
	/**
	 * 初始化形为器
	 */
	private final ITaskInitBehavior taskInitBehavior;
	/**
	 * 进度处理形为器
	 */
	private final ITaskProcessBehavior taskProcessBehavior;
	
	/**
	 * 
	 */
	protected final GamePlayer player;
	
	
	public SimpleTask(ITaskCfg cfg, ITaskInfo info,GamePlayer player) {
		super();
		this.cfg = cfg;
		this.info = info;
		this.player = player;
		triggerObserver  = getFactory().createObserver(cfg,player,this);
		taskInitBehavior = getFactory().createInitBehavior(cfg,player,this);
		taskProcessBehavior = getTaskProcessBehavior();
	}

	/**
	 * 抽象工厂方法
	 * @return
	 */
	public abstract AbstractTaskTriggerFactory getFactory();
	
	/**
	 * 抽象任务处理器
	 * @return
	 */
	public abstract ITaskProcessBehavior getTaskProcessBehavior();
	
	@Override
	public ITaskCfg getTaskCfg() {
		// TODO Auto-generated method stub
		return cfg;
	}

	@Override
	public ITaskInfo getTaskInfo() {
		// TODO Auto-generated method stub
		return info;
	}

	@Override
	public void addTrigger() {
		// TODO Auto-generated method stub		
		removeTrigger();
		if(triggerObserver!=null){
			triggerObserver.addTrigger();
		}
	}

	@Override
	public void removeTrigger() {
		// TODO Auto-generated method stub
		if(triggerObserver!=null){
			triggerObserver.removeTrigger();
		}
	}

	@Override
	public void updateProcess(int source, int changeValue) {
		// TODO Auto-generated method stub
		updateProcess(source+changeValue);
	}

	
	@Override
	public void initTask() {
		// TODO Auto-generated method stub
		if(taskInitBehavior!=null){
			taskInitBehavior.initTask();
		}
	}

	@Override
	public boolean isFinish() {
		// TODO Auto-generated method stub
		return info.getProcess()>=cfg.getTargetNum();
	}

	@Override
	public void updateProcess(int newValue) {
		// TODO Auto-generated method stub
		if(getTaskInfo().getProcess()!=newValue){
			if(taskProcessBehavior!=null){
				taskProcessBehavior.process(newValue);
			}
		}
	}

	
	
}
