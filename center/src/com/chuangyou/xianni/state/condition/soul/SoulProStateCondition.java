package com.chuangyou.xianni.state.condition.soul;

import com.chuangyou.xianni.entity.soul.SoulInfo;
import com.chuangyou.xianni.entity.state.StateConditionConfig;
import com.chuangyou.xianni.entity.state.StateConditionInfo;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.event.ObjectListener;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.state.condition.BaseStateCondition;

/**
 * 熟练度
 * @author laofan
 *
 */
public class SoulProStateCondition extends BaseStateCondition {

	public SoulProStateCondition(StateConditionInfo info, StateConditionConfig config, GamePlayer player) {
		super(info, config, player);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addTrigger(GamePlayer player) {
		// TODO Auto-generated method stub
		this.listener = new ObjectListener() {
			
			@Override
			public void onEvent(ObjectEvent event) {
				// TODO Auto-generated method stub
				int index = (int) event.getObject();
				int targetId = config.getTargetId();
				int old = info.getProcess();
				if(targetId == 5){
					if(index == config.getTargetNum()){
						info.setProcess(player.getSoulInventory().getSoulInfo().getProficiency(config.getTargetNum()));
					}
				}else if(targetId == 6){
					info.setProcess(getCount(player.getSoulInventory().getSoulInfo(),config.getTargetId1()));
				}
				if(old != info.getProcess()){					
					doNotifyUpdate();
				}
				
			}
		};
		player.addListener(listener, EventNameType.SOUL_PRO);
	}

	@Override
	public void removeTrigger(GamePlayer player) {
		// TODO Auto-generated method stub
		player.removeListener(listener, EventNameType.SOUL_PRO);
	}

	@Override
	public void initProcess() {
		// TODO Auto-generated method stub
		int targetId = this.config.getTargetId();
		if(targetId == 5){
			info.setProcess(player.getSoulInventory().getSoulInfo().getProficiency(config.getTargetNum()));
		}else if(targetId== 6){
			info.setProcess(getCount(player.getSoulInventory().getSoulInfo(),config.getTargetId1()));
		}		
	}
	
	
	protected int getCount(SoulInfo soulInfo,int num){
		int count=0;
		for (int value : soulInfo.getSoulProList()) {
			if(value >=num){
				count ++;
			}
		}
		return count;
	}

	

	@Override
	public boolean commitProcess() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		
	}

}
