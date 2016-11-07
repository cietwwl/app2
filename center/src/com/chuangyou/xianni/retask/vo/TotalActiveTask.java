package com.chuangyou.xianni.retask.vo;

import com.chuangyou.xianni.entity.task.ITaskCfg;
import com.chuangyou.xianni.entity.task.ITaskInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.retask.behavior.process.TotalActiveProcessBehavior;
import com.chuangyou.xianni.retask.iinterface.ITaskProcessBehavior;

/**
 * 计算活跃总数的任务
 * @author laofan
 *
 */
public class TotalActiveTask extends ActiveTask {

	public TotalActiveTask(ITaskCfg cfg, ITaskInfo info, GamePlayer player) {
		super(cfg, info, player);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public ITaskProcessBehavior getTaskProcessBehavior() {
		// TODO Auto-generated method stub
		return new TotalActiveProcessBehavior(this, player);
	}
	
	
	@Override
	public boolean isFinish() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 没有任务条件限制
	 */
	@Override
	protected boolean isCanStart() {
		// TODO Auto-generated method stub
		return true;
	}
	

	
}
