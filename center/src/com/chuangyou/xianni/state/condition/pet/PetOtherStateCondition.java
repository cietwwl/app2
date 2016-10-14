package com.chuangyou.xianni.state.condition.pet;

import java.util.Map;

import com.chuangyou.xianni.entity.pet.PetInfo;
import com.chuangyou.xianni.entity.state.StateConditionConfig;
import com.chuangyou.xianni.entity.state.StateConditionInfo;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.event.ObjectListener;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.state.condition.BaseStateCondition;
import com.chuangyou.xianni.state.event.PetStateEvent;

public class PetOtherStateCondition extends BaseStateCondition {

	public PetOtherStateCondition(StateConditionInfo info, StateConditionConfig config, GamePlayer player) {
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
				if(e.getTargetId() == 3 || e.getTargetId() == 6 || e.getTargetId() == 8){
					if(e.getTargetId1() == config.getTargetId1()){
						info.setProcess(e.getTargetNum());
					}
					if(e.getTargetId() == 3){  //炼体
						info.setProcess(getCountPhy(player.getPetInventory().getPetInfoMap(),config.getTargetId1()));
					}
					if(e.getTargetId() == 6){ //品质
						info.setProcess(getCountQuality(player.getPetInventory().getPetInfoMap(),config.getTargetId1()));
					}
					if(e.getTargetId() == 8){ //进阶
						info.setProcess(getCountQuality(player.getPetInventory().getPetInfoMap(),config.getTargetId1()));
					}
				}else if(e.getTargetId() == 5){
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
		if(targetId == 3){
			if(player.getPetInventory().getPetInfo(config.getTargetNum())!=null){
				info.setProcess(player.getPetInventory().getPetInfo(config.getTargetNum()).getPhysique());
			}
		}else if(targetId == 4){
			info.setProcess(getCountPhy(player.getPetInventory().getPetInfoMap(),config.getTargetId1()));
		}else if(targetId == 5){
			info.setProcess(player.getPetInventory().getPetAtt().getSoulLv());
		}else if(targetId == 6){
			if(player.getPetInventory().getPetInfo(config.getTargetNum())!=null){
				info.setProcess(player.getPetInventory().getPetInfo(config.getTargetNum()).getQuality());
			}
		}else if(targetId == 7){
			info.setProcess(getCountQuality(player.getPetInventory().getPetInfoMap(),config.getTargetId1()));
		}else if(targetId == 8){
			if(player.getPetInventory().getPetInfo(config.getTargetNum())!=null){
				info.setProcess(player.getPetInventory().getPetInfo(config.getTargetNum()).getGrade());
			}
		}else if(targetId == 9){
			info.setProcess(getCountGrade(player.getPetInventory().getPetInfoMap(),config.getTargetId1()));
		}
	}

	/**
	 * 炼体
	 * @param map
	 * @param num
	 * @return
	 */
	protected int getCountPhy(Map<Integer, PetInfo> map,int num){
		int count=0;
		for (PetInfo mountEquip : map.values()) {
			if(mountEquip.getPhysique()>=num){
				count ++;
			}
		}
		return count;
	}
	
	/**
	 * 品质
	 * @param map
	 * @param num
	 * @return
	 */
	protected int getCountQuality(Map<Integer, PetInfo> map,int num){
		int count=0;
		for (PetInfo mountEquip : map.values()) {
			if(mountEquip.getQuality()>=num){
				count ++;
			}
		}
		return count;
	}
	
	/**
	 * 进阶
	 * @param map
	 * @param num
	 * @return
	 */
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
	public boolean commitProcess() {
		// TODO Auto-generated method stub
		return false;
	}

}
