package com.chuangyou.xianni.state.condition;

import java.util.Map;

import com.chuangyou.xianni.entity.equip.EquipBarInfo;
import com.chuangyou.xianni.entity.state.StateConditionConfig;
import com.chuangyou.xianni.entity.state.StateConditionInfo;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.event.ObjectListener;
import com.chuangyou.xianni.player.GamePlayer;

/**
 * 装备相关任务
 * @author laofan
 *	111	武器等级
112	头盔等级
113	项链等级
114	上衣等级
115	戒子等级
116	护手等级
117	玉佩等级
118	鞋子等级
1	任意一个等级
2	全部达到等级
211	武器加持
212	头盔加持
213	项链加持
214	上衣加持
215	戒子加持
216	护手加持
217	玉佩加持
218	鞋子加持
3	任意一个加持
4	全部达到加持

 *
 */
public class EquipStateCondition extends BaseStateCondition {

	public EquipStateCondition(StateConditionInfo info, StateConditionConfig config, GamePlayer player) {
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
				int targetId =  config.getTargetId();
				EquipBarInfo equipInfo = (EquipBarInfo) event.getObject();
				int old = info.getProcess();
				if(targetId == 1){ 
					if(config.getTargetNum() == equipInfo.getPosition()){
						info.setProcess(equipInfo.getLevel());
					}
				}else if(targetId == 2){
					info.setProcess(getCountLv(player.getEquipInventory().getEquipBarInfoMap(), config.getTargetId1()));
				}else if(targetId == 3){  
					if(config.getTargetNum() == equipInfo.getPosition()){
						info.setProcess(equipInfo.getGrade());
					}
				}else if(targetId == 4){
					info.setProcess(getCountGrade(player.getEquipInventory().getEquipBarInfoMap(), config.getTargetId1()));
				}
				
				if(old!=info.getProcess()){
					doNotifyUpdate();
				}
			}
		};
		player.addListener(this.listener, EventNameType.EQUIP);
	}

	@Override
	public void removeTrigger(GamePlayer player) {
		// TODO Auto-generated method stub
		player.removeListener(this.listener, EventNameType.EQUIP);
	}

	@Override
	public void initProcess() {
		// TODO Auto-generated method stub
		int targetId =  config.getTargetId();
		if(targetId == 1){ 
			EquipBarInfo equipInfo = player.getEquipInventory().getEquipBarByPos((short) config.getTargetNum());
			if(equipInfo!=null){
				this.info.setProcess(equipInfo.getLevel());
			}
		}else if(targetId == 2){ 
			this.info.setProcess(getCountLv(player.getEquipInventory().getEquipBarInfoMap(), this.config.getTargetId1()));
		}else if(targetId == 3){ 
			EquipBarInfo equipInfo = player.getEquipInventory().getEquipBarByPos((short) config.getTargetNum());
			if(equipInfo!=null){
				this.info.setProcess(equipInfo.getGrade());
			}
		}else if(targetId == 4){
			this.info.setProcess(getCountGrade(player.getEquipInventory().getEquipBarInfoMap(), this.config.getTargetId1()));
		}
	}
	
	
	protected int getCountLv(Map<Short, EquipBarInfo> map,int num){
		int count=0;
		for (EquipBarInfo ebi : map.values()) {
			if(ebi.getLevel()>=num){
				count ++;
			}
		}
		return count;
	}
	
	protected int getCountGrade(Map<Short, EquipBarInfo> map,int num){
		int count=0;
		for (EquipBarInfo ebi : map.values()) {
			if(ebi.getGrade()>=num){
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
