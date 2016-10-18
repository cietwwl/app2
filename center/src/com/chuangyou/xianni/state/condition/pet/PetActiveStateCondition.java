package com.chuangyou.xianni.state.condition.pet;

import com.chuangyou.xianni.entity.state.StateConditionConfig;
import com.chuangyou.xianni.entity.state.StateConditionInfo;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.event.ObjectListener;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.state.condition.BaseStateCondition;

public class PetActiveStateCondition extends BaseStateCondition {

	public PetActiveStateCondition(StateConditionInfo info, StateConditionConfig config, GamePlayer player) {
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
				int petId= (int) event.getObject();
				int old = info.getProcess();
				if(config.getTargetId() == 1){
					if(config.getTargetNum() == petId){
						info.setProcess(1);
					}
				}else{
					info.setProcess(player.getPetInventory().getPetInfoMap().size());
				}
				
				if(old!=info.getProcess()){
					doNotifyUpdate();
				}
			}
		};
		player.addListener(listener, EventNameType.PET_ACTIVE);
	}

	@Override
	public void removeTrigger(GamePlayer player) {
		// TODO Auto-generated method stub
		player.removeListener(listener, EventNameType.PET_ACTIVE);
	}

	@Override
	public void initProcess() {
		// TODO Auto-generated method stub
		if(config.getTargetId() == 1){	
			if(player.getPetInventory().getPetInfo(config.getTargetNum())!=null){
				info.setProcess(1);
			}
		}else if(config.getTargetId() == 2){
			info.setProcess(player.getPetInventory().getPetInfoMap().size());
		}
	}

	@Override
	public boolean isComplete() {
		// TODO Auto-generated method stub
		if(config.getTargetId() == 1){
			if(info.getProcess()>=1){
				return true;
			}
		}else if(config.getTargetId() == 2){
			if(info.getProcess()>=config.getTargetNum()){
				return true;
			}
		}
		return false;
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
