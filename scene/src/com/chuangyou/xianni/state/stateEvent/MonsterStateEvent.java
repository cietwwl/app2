package com.chuangyou.xianni.state.stateEvent;

import com.chuangyou.xianni.campaign.StateCampaign;
import com.chuangyou.xianni.entity.state.StateEventConfig;
import com.chuangyou.xianni.warfield.spawn.NodeState;
import com.chuangyou.xianni.warfield.spawn.SpwanNode;
import com.chuangyou.xianni.warfield.spawn.WorkingState;
import com.chuangyou.xianni.warfield.template.SpawnTemplateMgr;
import com.chuangyou.xianni.world.ArmyProxy;

/**
 * 开启刷怪节点
 * @author laofan
 *
 */
public class MonsterStateEvent extends BaseStateEvent {

	public MonsterStateEvent(StateEventConfig event) {
		super(event);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void exec(StateCampaign campaign, ArmyProxy army) {
		// TODO Auto-generated method stub
		super.exec(campaign, army);
		int[] nodes = event.getParamStrToInt();
		for (int nodeId : nodes) {		
			SpwanNode node = campaign.getNode(SpawnTemplateMgr.getSpwanId(nodeId));
			if(node!=null){
				NodeState state = new WorkingState(node);
				node.stateTransition(state);
			}
		}
	}
	
	

}
