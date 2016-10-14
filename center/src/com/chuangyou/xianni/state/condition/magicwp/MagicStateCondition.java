package com.chuangyou.xianni.state.condition.magicwp;

import com.chuangyou.xianni.entity.state.StateConditionConfig;
import com.chuangyou.xianni.entity.state.StateConditionInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.state.condition.BaseStateCondition;

/**
 * 法宝
 * @author laofan
 *
 */
public class MagicStateCondition extends BaseStateCondition {

	
	private BaseStateCondition proxy; 
	
	public MagicStateCondition(StateConditionInfo info, StateConditionConfig config, GamePlayer player) {
		super(info, config, player);
		// TODO Auto-generated constructor stub
		switch (config.getTargetId()) {
			case 1:
			case 2:
				proxy = new MagicActiveStateCondition(info, config, player);
				break;
			case 3:
			case 4:
			case 5:
			case 6:
				proxy = new MagicWpStateCondition(info, config, player);
				break;
			case 7:
				proxy = new MagicFightStateCondition(info, config, player);
			default:
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
