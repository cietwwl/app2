package com.chuangyou.xianni.state.condition.pet;

import com.chuangyou.xianni.entity.state.StateConditionConfig;
import com.chuangyou.xianni.entity.state.StateConditionInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.state.condition.BaseStateCondition;

/**
 * 宠物
 * @author laofan
 *
 */
public class PetStateCondition extends BaseStateCondition {

	private BaseStateCondition proxy;
	
	public PetStateCondition(StateConditionInfo info, StateConditionConfig config, GamePlayer player) {
		super(info, config, player);
		// TODO Auto-generated constructor stub
		switch(config.getTargetId()){
			case 1:
			case 2:
				proxy = new PetActiveStateCondition(info, config, player);
				break;
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
				proxy = new PetOtherStateCondition(info, config, player);
				break;
			case 10:
				proxy = new PetFightStateCondition(info, config, player);
				break;
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

}
