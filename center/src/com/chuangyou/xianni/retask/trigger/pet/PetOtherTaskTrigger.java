package com.chuangyou.xianni.retask.trigger.pet;

import java.util.Map;

import com.chuangyou.xianni.entity.pet.PetInfo;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.event.ObjectListener;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.retask.event.PetStateEvent;
import com.chuangyou.xianni.retask.iinterface.ITask;
import com.chuangyou.xianni.retask.iinterface.ITaskInitBehavior;
import com.chuangyou.xianni.retask.trigger.BaseTaskTrigger;

public class PetOtherTaskTrigger extends BaseTaskTrigger implements ITaskInitBehavior {

	public PetOtherTaskTrigger(GamePlayer player, ITask task) {
		super(player, task);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 炼体
	 * 
	 * @param map
	 * @param num
	 * @return
	 */
	private int getCountPhy(Map<Integer, PetInfo> map, int num) {
		int count = 0;
		for (PetInfo mountEquip : map.values()) {
			if (mountEquip.getPhysique() >= num) {
				count++;
			}
		}
		return count;
	}

	/**
	 * 品质
	 * 
	 * @param map
	 * @param num
	 * @return
	 */
	private int getCountQuality(Map<Integer, PetInfo> map, int num) {
		int count = 0;
		for (PetInfo mountEquip : map.values()) {
			if (mountEquip.getQuality() >= num) {
				count++;
			}
		}
		return count;
	}

	/**
	 * 进阶
	 * 
	 * @param map
	 * @param num
	 * @return
	 */
	private int getCountGrade(Map<Integer, PetInfo> map, int num) {
		int count = 0;
		for (PetInfo mountEquip : map.values()) {
			if (mountEquip.getGrade() >= num) {
				count++;
			}
		}
		return count;
	}



	/**
	 * 等级
	 * 
	 * @param map
	 * @param num
	 * @return
	 */
	private int getCountLv(Map<Integer, PetInfo> map, int num) {
		int count = 0;
		for (PetInfo mountEquip : map.values()) {
			if (mountEquip.getLevel() >= num) {
				count++;
			}
		}
		return count;
	}
	

	/**
	 * 资质
	 * 
	 * @param map
	 * @param num
	 * @return
	 */
	private int getCountTalent(Map<Integer, PetInfo> map, int num) {
		int count = 0;
		for (PetInfo mountEquip : map.values()) {
			if (mountEquip.getTalent() >= num) {
				count++;
			}
		}
		return count;
	}
	
	@Override
	public void addTrigger() {
		// TODO Auto-generated method stub
		this.listener = new ObjectListener() {

			@Override
			public void onEvent(ObjectEvent event) {
				// TODO Auto-generated method stub
				PetStateEvent e = (PetStateEvent) event;
				if (e.getTargetId() == 3 || e.getTargetId() == 6 || e.getTargetId() == 8 || e.getTargetId() == 11 || e.getTargetId() == 13) {
					if (e.getTargetId1() == getTask().getTaskCfg().getTargetId1()) {
						getTask().updateProcess(e.getTargetNum());
					}
					if (e.getTargetId() == 3) { // 炼体
						getTask().updateProcess(getCountPhy(player.getPetInventory().getPetInfoMap(), getTask().getTaskCfg().getTargetId1()));
					}
					if (e.getTargetId() == 6) { // 品质
						getTask().updateProcess(getCountQuality(player.getPetInventory().getPetInfoMap(), getTask().getTaskCfg().getTargetId1()));
					}
					if (e.getTargetId() == 8) { // 进阶
						getTask().updateProcess(getCountQuality(player.getPetInventory().getPetInfoMap(), getTask().getTaskCfg().getTargetId1()));
					}
					if(e.getTargetId() == 11){ // 等级
						getTask().updateProcess(getCountLv(player.getPetInventory().getPetInfoMap(), getTask().getTaskCfg().getTargetId1()));
					}
					if(e.getTargetId() == 13){ //资质
						getTask().updateProcess(getCountTalent(player.getPetInventory().getPetInfoMap(), getTask().getTaskCfg().getTargetId1()));
					}
				} else if (e.getTargetId() == 5) {
					getTask().updateProcess(e.getTargetNum());
				}
			}
		};
		player.addListener(listener, EventNameType.PET);
	}

	@Override
	public void removeTrigger() {
		// TODO Auto-generated method stub
		player.removeListener(listener, EventNameType.PET);
	}

	@Override
	public void initTask() {
		// TODO Auto-generated method stub
		int targetId = getTask().getTaskCfg().getTargetId();
		if (targetId == 3) {
			if (player.getPetInventory().getPetInfo(getTask().getTaskCfg().getTargetId1()) != null) {
				getTask().getTaskInfo().setProcess(player.getPetInventory().getPetInfo(getTask().getTaskCfg().getTargetId1()).getPhysique());
			}
		} else if (targetId == 4) {
			getTask().getTaskInfo().setProcess(getCountPhy(player.getPetInventory().getPetInfoMap(), getTask().getTaskCfg().getTargetId1()));
		} else if (targetId == 5) {
			getTask().getTaskInfo().setProcess(player.getPetInventory().getPetAtt().getSoulLv());
		} else if (targetId == 6) {
			if (player.getPetInventory().getPetInfo(getTask().getTaskCfg().getTargetId1()) != null) {
				getTask().getTaskInfo().setProcess(player.getPetInventory().getPetInfo(getTask().getTaskCfg().getTargetId1()).getQuality());
			}
		} else if (targetId == 7) {
			getTask().getTaskInfo().setProcess(getCountQuality(player.getPetInventory().getPetInfoMap(), getTask().getTaskCfg().getTargetId1()));
		} else if (targetId == 8) {
			if (player.getPetInventory().getPetInfo(getTask().getTaskCfg().getTargetId1()) != null) {
				getTask().getTaskInfo().setProcess(player.getPetInventory().getPetInfo(getTask().getTaskCfg().getTargetId1()).getGrade());
			}
		} else if (targetId == 9) {
			getTask().getTaskInfo().setProcess(getCountGrade(player.getPetInventory().getPetInfoMap(), getTask().getTaskCfg().getTargetId1()));
		} else if(targetId == 11){
			if (player.getPetInventory().getPetInfo(getTask().getTaskCfg().getTargetId1()) != null) {
				getTask().getTaskInfo().setProcess(player.getPetInventory().getPetInfo(getTask().getTaskCfg().getTargetId1()).getLevel());
			}
		}else if(targetId == 12){
			getTask().getTaskInfo().setProcess(getCountLv(player.getPetInventory().getPetInfoMap(), getTask().getTaskCfg().getTargetId1()));
		}else if(targetId == 13){
			if (player.getPetInventory().getPetInfo(getTask().getTaskCfg().getTargetId1()) != null) {
				getTask().getTaskInfo().setProcess(player.getPetInventory().getPetInfo(getTask().getTaskCfg().getTargetId1()).getTalent());
			}
		}else if(targetId == 14){
			getTask().getTaskInfo().setProcess(getCountTalent(player.getPetInventory().getPetInfoMap(), getTask().getTaskCfg().getTargetId1()));
		}
	}

}
