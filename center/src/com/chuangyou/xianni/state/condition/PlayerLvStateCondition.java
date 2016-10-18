package com.chuangyou.xianni.state.condition;

import java.util.Map;

import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.state.StateConditionConfig;
import com.chuangyou.xianni.entity.state.StateConditionInfo;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.event.ObjectListener;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.player.event.PlayerPropertyUpdateEvent;

/**
 * 人物等级
 * @author laofan
 *
 */
public class PlayerLvStateCondition extends BaseStateCondition {

	
	public PlayerLvStateCondition(StateConditionInfo info, StateConditionConfig config, GamePlayer player) {
		super(info, config, player);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void addTrigger(GamePlayer player) {
		// TODO Auto-generated method stub
		this.initProcess();
		player.getBasePlayer().addListener(listener, EventNameType.UPDATE_PLAYER_PROPERTY);
	}

	@Override
	public void removeTrigger(GamePlayer player) {
		// TODO Auto-generated method stub
		player.getBasePlayer().removeListener(listener, EventNameType.UPDATE_PLAYER_PROPERTY);
	}

	@Override
	public void initProcess() {
		// TODO Auto-generated method stub
		this.info.setProcess(player.getBasePlayer().getPlayerInfo().getLevel());
		this.info.setOp(Option.Update);
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
				PlayerPropertyUpdateEvent e = (PlayerPropertyUpdateEvent) event;
				Map<Integer, Long> map = e.getChangeMap();
				if(map!=null){
					for(Map.Entry<Integer, Long> entry:map.entrySet()){
						if(entry.getKey() == EnumAttr.Level.getValue()){
							long lv = entry.getValue();
							info.setProcess((int)lv);
							info.setOp(Option.Update);
							doNotifyUpdate();
						}
					}
				}
			}
		};
	}

}
