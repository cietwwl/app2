package com.chuangyou.xianni.state.condition.soul;

import com.chuangyou.xianni.entity.state.StateConditionConfig;
import com.chuangyou.xianni.entity.state.StateConditionInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.state.condition.BaseStateCondition;

/**
 * 魂幡
 * @author laofan
 *
 */
public class SoulStateCondition extends BaseStateCondition {

	private BaseStateCondition proxy;
	
	public SoulStateCondition(StateConditionInfo info, StateConditionConfig config, GamePlayer player) {
		super(info, config, player);
		// TODO Auto-generated constructor stub
		if(config.getTargetId() == 1 || config.getTargetId() == 2){
			proxy = new SoulLvStateCondition(info, config, player);
		}else if(config.getTargetId() == 3 || config.getTargetId() == 4){
			proxy = new SoulStarStateCondition(info, config, player);
		}else if(config.getTargetId() == 5 || config.getTargetId() == 6){
			proxy = new SoulProStateCondition(info, config, player);
		}else if(config.getTargetId() == 7){
			proxy = new SoulFightStateCondition(info, config, player);
		}
	}

	@Override
	public void addTrigger(GamePlayer player) {
		// TODO Auto-generated method stub
		if(proxy!=null)proxy.addTrigger(player);
	}

	@Override
	public void removeTrigger(GamePlayer player) {
		// TODO Auto-generated method stub
		if(proxy!=null)proxy.removeTrigger(player);
	}

	@Override
	public void initProcess() {
		// TODO Auto-generated method stub
		if(proxy!=null)proxy.initProcess();
	}

	@Override
	public boolean commitProcess() {
		// TODO Auto-generated method stub
		if(proxy!=null){
			
			return proxy.commitProcess();
		}
		return false;
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		
	}

}
