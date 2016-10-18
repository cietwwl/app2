package com.chuangyou.xianni.state.condition;

import java.util.Map;

import com.chuangyou.xianni.entity.equip.EquipBarInfo;
import com.chuangyou.xianni.entity.rank.RankTempInfo;
import com.chuangyou.xianni.entity.state.StateConditionConfig;
import com.chuangyou.xianni.entity.state.StateConditionInfo;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.event.ObjectListener;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.rank.RankServerManager;

/**
 * 装备相关任务
 * @author laofan
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
		this.initListener();
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
			EquipBarInfo equipInfo = player.getEquipInventory().getEquipBarByPos((short) config.getTargetId1());
			if(equipInfo!=null){
				this.info.setProcess(equipInfo.getLevel());
			}
		}else if(targetId == 2){ 
			this.info.setProcess(getCountLv(player.getEquipInventory().getEquipBarInfoMap(), this.config.getTargetId1()));
		}else if(targetId == 3){ 
			EquipBarInfo equipInfo = player.getEquipInventory().getEquipBarByPos((short) config.getTargetId1());
			if(equipInfo!=null){
				this.info.setProcess(equipInfo.getGrade());
			}
		}else if(targetId == 4){
			this.info.setProcess(getCountGrade(player.getEquipInventory().getEquipBarInfoMap(), this.config.getTargetId1()));
		}else if(targetId == 5){
			RankTempInfo rankInfo = RankServerManager.getInstance().getRankTempInfo(player.getPlayerId());
			if(rankInfo!=null){
				info.setProcess((int)rankInfo.getEquip());
			}
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

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		if(this.listener!=null)return;
		this.listener = new ObjectListener() {
			
			@Override
			public void onEvent(ObjectEvent event) {
				// TODO Auto-generated method stub
				int targetId =  config.getTargetId();
				EquipBarInfo equipInfo = (EquipBarInfo) event.getObject();
				int old = info.getProcess();
				if(equipInfo!=null){
					if(targetId == 1){ 
						if(config.getTargetId1() == equipInfo.getPosition()){
							info.setProcess(equipInfo.getLevel());
						}
					}else if(targetId == 2){
						info.setProcess(getCountLv(player.getEquipInventory().getEquipBarInfoMap(), config.getTargetId1()));
					}else if(targetId == 3){  
						if(config.getTargetId1() == equipInfo.getPosition()){
							info.setProcess(equipInfo.getGrade());
						}
					}else if(targetId == 4){
						info.setProcess(getCountGrade(player.getEquipInventory().getEquipBarInfoMap(), config.getTargetId1()));
					}
				}else if(targetId == 5){
					RankTempInfo rankInfo = RankServerManager.getInstance().getRankTempInfo(player.getPlayerId());
					if(rankInfo!=null){
						info.setProcess((int)rankInfo.getEquip());
					}
				}
				
				if(old!=info.getProcess()){
					doNotifyUpdate();
				}
			}
		};
	}

}
