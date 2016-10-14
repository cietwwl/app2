package com.chuangyou.xianni.state.condition.soul;

import com.chuangyou.xianni.entity.rank.RankTempInfo;
import com.chuangyou.xianni.entity.state.StateConditionConfig;
import com.chuangyou.xianni.entity.state.StateConditionInfo;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.event.ObjectListener;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.rank.RankServerManager;
import com.chuangyou.xianni.state.condition.BaseStateCondition;

/**
 *  魂幡战斗力
 * @author laofan
 *
 */
public class SoulFightStateCondition extends BaseStateCondition {

	public SoulFightStateCondition(StateConditionInfo info, StateConditionConfig config, GamePlayer player) {
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
				int old = info.getProcess();
				
				RankTempInfo rankInfo = RankServerManager.getInstance().getRankTempInfo(player.getPlayerId());
				if(rankInfo!=null){
					info.setProcess((int)rankInfo.getSoul());
				}
		
				if(old!=info.getProcess()){
					doNotifyUpdate();
				}
			}
		};
		player.addListener(this.listener, EventNameType.SOUL_FIGHT);
	}

	@Override
	public void removeTrigger(GamePlayer player) {
		// TODO Auto-generated method stub
		player.removeListener(this.listener, EventNameType.SOUL_FIGHT);
	}

	@Override
	public void initProcess() {
		// TODO Auto-generated method stub
		RankTempInfo rankInfo = RankServerManager.getInstance().getRankTempInfo(player.getPlayerId());
		if(rankInfo!=null){
			info.setProcess((int)rankInfo.getSoul());
		}
	}

	@Override
	public boolean commitProcess() {
		// TODO Auto-generated method stub
		return false;
	}

}
