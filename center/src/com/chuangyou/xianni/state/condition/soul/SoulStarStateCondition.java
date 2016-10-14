package com.chuangyou.xianni.state.condition.soul;

import java.util.Map;

import com.chuangyou.xianni.entity.soul.SoulCardInfo;
import com.chuangyou.xianni.entity.state.StateConditionConfig;
import com.chuangyou.xianni.entity.state.StateConditionInfo;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.event.ObjectListener;
import com.chuangyou.xianni.player.GamePlayer;

/**
 * 主魂星级
 * @author laofan
 *
 */
public class SoulStarStateCondition extends SoulLvStateCondition {

	public SoulStarStateCondition(StateConditionInfo info, StateConditionConfig config, GamePlayer player) {
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
				SoulCardInfo tempInfo = (SoulCardInfo) event.getObject();
				
				int old = info.getProcess();
				int targetId = config.getTargetId();
				if(targetId == 3){
					if(config.getTargetNum() == tempInfo.getCardId()){
						info.setProcess(tempInfo.getStar());
					}
				}else if(targetId == 4){
					info.setProcess(getCount(player.getSoulInventory().getCards(), config.getTargetId1()));
				}
				if(old != info.getProcess()){					
					doNotifyUpdate();
				}
			}
		};
		player.addListener(listener, EventNameType.SOUL_STAR);
	}

	@Override
	public void removeTrigger(GamePlayer player) {
		// TODO Auto-generated method stub
		player.removeListener(listener, EventNameType.SOUL_STAR);
	}

	@Override
	public void initProcess() {
		// TODO Auto-generated method stub
		int targetId = this.config.getTargetId();
		if(targetId == 4){
			SoulCardInfo cardInfo = player.getSoulInventory().getCards().get(this.config.getTargetNum());
			if(cardInfo!=null){
				this.info.setProcess(cardInfo.getStar());	
			}
		}else if(targetId==3){ 
			this.info.setProcess(getCount(player.getSoulInventory().getCards(), config.getTargetId1()));
		}
		
	}

	@Override
	protected int getCount(Map<Integer, SoulCardInfo> map,int num){
		int count=0;
		for (SoulCardInfo soulInfo : map.values()) {
			if(soulInfo.getStar()>=num){
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
