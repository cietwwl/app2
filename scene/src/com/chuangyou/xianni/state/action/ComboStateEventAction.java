package com.chuangyou.xianni.state.action;

import com.chuangyou.xianni.campaign.StateCampaign;
import com.chuangyou.xianni.exec.ActionQueue;
import com.chuangyou.xianni.state.stateEvent.BaseStateEvent;
import com.chuangyou.xianni.world.ArmyProxy;

public class ComboStateEventAction extends EmptyStateDelayAction {

	private BaseStateEvent stateEvent;
	public ComboStateEventAction(ActionQueue queue, int delay, StateCampaign campaign, ArmyProxy army,BaseStateEvent stateEvent) {
		super(queue, delay, campaign, army);
		this.stateEvent = stateEvent;
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(stateEvent!=null){
			stateEvent.exec(campaign, army);
		}
	}
	
	

}
