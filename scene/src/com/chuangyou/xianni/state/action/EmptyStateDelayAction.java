package com.chuangyou.xianni.state.action;

import com.chuangyou.xianni.campaign.StateCampaign;
import com.chuangyou.xianni.exec.ActionQueue;
import com.chuangyou.xianni.exec.DelayAction;
import com.chuangyou.xianni.world.ArmyProxy;

/**
 * 延迟执行action
 * @author laofan
 *
 */
public class EmptyStateDelayAction extends DelayAction {

	/**
	 * 副本
	 */
	protected StateCampaign campaign;
	/**
	 * 参与事件的部队信息
	 */
	protected ArmyProxy army;
	
	
	public EmptyStateDelayAction(ActionQueue queue, int delay, StateCampaign campaign, ArmyProxy army) {
		super(queue, delay);
		this.campaign = campaign;
		this.army = army;
	}

	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		this.campaign.execRandomEvent();	
		
	}

}
