package com.chuangyou.xianni.state.condition.soul;

import java.util.Map;

import com.chuangyou.xianni.entity.soul.SoulCardInfo;
import com.chuangyou.xianni.entity.state.StateConditionConfig;
import com.chuangyou.xianni.entity.state.StateConditionInfo;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.event.ObjectListener;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.state.condition.BaseStateCondition;

/**
 * 主魂等级
 * @author laofan
 *
 *       对应主魂ID	
1	任意1个
2	任意2个
3	任意3个
4	任意4个
5	任意5个
……	……

 *
 */
public class SoulLvStateCondition extends BaseStateCondition {

	public SoulLvStateCondition(StateConditionInfo info, StateConditionConfig config, GamePlayer player) {
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
				if(targetId == 1){
					if(config.getTargetNum() == tempInfo.getCardId()){
						info.setProcess(tempInfo.getLv());
					}
				}else if(targetId == 2){
					info.setProcess(getCount(player.getSoulInventory().getCards(), config.getTargetId1()));
				}
				if(old != info.getProcess()){					
					doNotifyUpdate();
				}
			}
		};
		player.addListener(listener, EventNameType.SOUL_LV);
	}

	@Override
	public void removeTrigger(GamePlayer player) {
		// TODO Auto-generated method stub
		player.removeListener(listener, EventNameType.SOUL_LV);
	}

	@Override
	public void initProcess() {
		// TODO Auto-generated method stub
		int targetId = this.config.getTargetId();
		if(targetId == 2){
			SoulCardInfo cardInfo = player.getSoulInventory().getCards().get(this.config.getTargetNum());
			if(cardInfo!=null){
				this.info.setProcess(cardInfo.getLv());	
			}
		}else if(targetId == 1){ 
			this.info.setProcess(getCount(player.getSoulInventory().getCards(), config.getTargetId1()));
		}
	}

	
	protected int getCount(Map<Integer, SoulCardInfo> map,int num){
		int count=0;
		for (SoulCardInfo soulInfo : map.values()) {
			if(soulInfo.getLv()>=num){
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
