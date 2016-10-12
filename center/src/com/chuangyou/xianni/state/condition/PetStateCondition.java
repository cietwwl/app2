package com.chuangyou.xianni.state.condition;

import java.util.Map;

import com.chuangyou.xianni.entity.pet.PetInfo;
import com.chuangyou.xianni.entity.state.StateConditionConfig;
import com.chuangyou.xianni.entity.state.StateConditionInfo;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.event.ObjectListener;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.state.event.PetStateEvent;

public class PetStateCondition extends BaseStateCondition {

	public PetStateCondition(StateConditionInfo info, StateConditionConfig config, GamePlayer player) {
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
				PetStateEvent e = (PetStateEvent) event;
				int old = info.getProcess();
				if(e.getTargetId() == 1 || e.getTargetId() == 4 || e.getTargetId() == 6){
					if(e.getTargetNum() == config.getTargetNum()){
						info.setProcess(e.getTargetNum1());
					}
					if(e.getTargetId() == 1){
						info.setProcess(getCountPhy(player.getPetInventory().getPetInfoMap(),config.getTargetId1()));
					}
					if(e.getTargetId() == 4){
						info.setProcess(getCountQuality(player.getPetInventory().getPetInfoMap(),config.getTargetId1()));
					}
					if(e.getTargetId() == 6){
						info.setProcess(getCountQuality(player.getPetInventory().getPetInfoMap(),config.getTargetId1()));
					}
				}else if(e.getTargetId() == 3){
					info.setProcess(e.getTargetNum());
				}
				
				if(old!=info.getProcess()){
					doNotifyUpdate();
				}
				
			}
		};
		player.addListener(listener, EventNameType.PET);
		
	}

	@Override
	public void removeTrigger(GamePlayer player) {
		// TODO Auto-generated method stub
		player.removeListener(listener, EventNameType.PET);
	}

	@Override
	public void initProcess() {
		// TODO Auto-generated method stub
		int targetId = config.getTargetId();
		if(targetId == 1){
			if(player.getPetInventory().getPetInfo(config.getTargetNum())!=null){
				info.setProcess(player.getPetInventory().getPetInfo(config.getTargetNum()).getPhysique());
			}
		}else if(targetId == 2){
			info.setProcess(getCountPhy(player.getPetInventory().getPetInfoMap(),config.getTargetId1()));
		}else if(targetId == 3){
			info.setProcess(player.getPetInventory().getPetAtt().getSoulLv());
		}else if(targetId == 4){
			if(player.getPetInventory().getPetInfo(config.getTargetNum())!=null){
				info.setProcess(player.getPetInventory().getPetInfo(config.getTargetNum()).getQuality());
			}
		}else if(targetId == 5){
			info.setProcess(getCountQuality(player.getPetInventory().getPetInfoMap(),config.getTargetId1()));
		}else if(targetId == 6){
			if(player.getPetInventory().getPetInfo(config.getTargetNum())!=null){
				info.setProcess(player.getPetInventory().getPetInfo(config.getTargetNum()).getGrade());
			}
		}else if(targetId == 7){
			info.setProcess(getCountQuality(player.getPetInventory().getPetInfoMap(),config.getTargetId1()));
		}
	}

	protected int getCountPhy(Map<Integer, PetInfo> map,int num){
		int count=0;
		for (PetInfo mountEquip : map.values()) {
			if(mountEquip.getPhysique()>=num){
				count ++;
			}
		}
		return count;
	}
	
	protected int getCountQuality(Map<Integer, PetInfo> map,int num){
		int count=0;
		for (PetInfo mountEquip : map.values()) {
			if(mountEquip.getQuality()>=num){
				count ++;
			}
		}
		return count;
	}
	
	protected int getCountGrade(Map<Integer, PetInfo> map,int num){
		int count=0;
		for (PetInfo mountEquip : map.values()) {
			if(mountEquip.getGrade()>=num){
				count ++;
			}
		}
		return count;
	}
	
	@Override
	public boolean isComplete() {
		// TODO Auto-generated method stub
		
		
		return false;
	}

	@Override
	public boolean commitProcess() {
		// TODO Auto-generated method stub
		return false;
	}

}
