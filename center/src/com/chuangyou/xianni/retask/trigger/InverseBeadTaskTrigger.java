package com.chuangyou.xianni.retask.trigger;

import java.util.Map;

import com.chuangyou.xianni.entity.inverseBead.PlayerInverseBead;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.event.ObjectListener;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.retask.event.InverseBeadEvent;
import com.chuangyou.xianni.retask.iinterface.ITask;
import com.chuangyou.xianni.retask.iinterface.ITaskInitBehavior;

/**
 *  天逆珠任务
 * @author laofan
 *
 */
public class InverseBeadTaskTrigger extends BaseTaskTrigger implements ITaskInitBehavior {

	public InverseBeadTaskTrigger(GamePlayer player, ITask task) {
		super(player, task);
		// TODO Auto-generated constructor stub
		this.eventType = EventNameType.TASK_T_SYSTEM;
	}

	@Override
	public void addTrigger() {
		// TODO Auto-generated method stub
		this.listener = new ObjectListener() {
			
			@Override
			public void onEvent(ObjectEvent event) {
				// TODO Auto-generated method stub
				int targetId =  getTask().getTaskCfg().getTargetId();
				
				InverseBeadEvent e = (InverseBeadEvent) event;
				if(e.getTargetId() == getTask().getTaskCfg().getTargetId() &&
						e.getTargetId1() == getTask().getTaskCfg().getTargetId1()){
					getTask().updateProcess(e.getTargetNum());
				}
				if(targetId==6){
					int lv = getCountMaxLv(player.getInverseBeadInventory().getInverseBead(),getTask().getTaskCfg().getTargetId1());
					getTask().updateProcess(lv);
				}
			}
		};
		player.addListener(this.listener,eventType);
	}

	@Override
	public void removeTrigger() {
		// TODO Auto-generated method stub
		player.removeListener(this.listener,eventType);
	}

	/**
	 * 任意1个达到Y等级
	 * @param map
	 * @param stage
	 * @return
	 */
	private int getCountMaxLv(Map<Integer, PlayerInverseBead> map,int stage){
		int count=0;
		for (PlayerInverseBead ebi : map.values()) {
			if(ebi.getStage() == stage && ebi.getMarking()>=count){
				count = ebi.getMarking();
			}
		}
		return count;
	}
	
	
	@Override
	public void initTask() {
		// TODO Auto-generated method stub
		int targetId =  getTask().getTaskCfg().getTargetId();
		if(targetId==6){
			int lv = getCountMaxLv(player.getInverseBeadInventory().getInverseBead(),getTask().getTaskCfg().getTargetId1());
			getTask().getTaskInfo().setProcess(lv);
		}else{
			
			PlayerInverseBead pib = player.getInverseBeadInventory().get(targetId);
			if(pib!=null){
				if(pib.getStage() == getTask().getTaskCfg().getTargetId1()){
					getTask().getTaskInfo().setProcess(pib.getMarking());
				}
			}
		}
		
		
		
	}

}
