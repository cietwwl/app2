package com.chuangyou.xianni.retask.trigger.soul;

import com.chuangyou.xianni.entity.rank.RankTempInfo;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.event.ObjectListener;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.rank.RankServerManager;
import com.chuangyou.xianni.retask.iinterface.ITask;
import com.chuangyou.xianni.retask.iinterface.ITaskInitBehavior;
import com.chuangyou.xianni.retask.trigger.BaseTaskTrigger;

/**
 *  魂幡战斗力
 * @author laofan
 *
 */
public class SoulFightTaskTrigger extends BaseTaskTrigger implements ITaskInitBehavior {

	public SoulFightTaskTrigger(GamePlayer player, ITask task) {
		super(player, task);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addTrigger() {
		// TODO Auto-generated method stub
		this.listener = new ObjectListener() {
			
			@Override
			public void onEvent(ObjectEvent event) {
				// TODO Auto-generated method stub
				
				RankTempInfo rankInfo = RankServerManager.getInstance().getRankTempInfo(player.getPlayerId());
				if(rankInfo!=null){
					getTask().updateProcess((int)rankInfo.getSoul());
				}
		
				
			}
		};
		player.addListener(this.listener, EventNameType.SOUL_FIGHT);
	}

	@Override
	public void removeTrigger() {
		// TODO Auto-generated method stub
		player.removeListener(this.listener, EventNameType.SOUL_FIGHT);
	}

	@Override
	public void initTask() {
		// TODO Auto-generated method stub
		RankTempInfo rankInfo = RankServerManager.getInstance().getRankTempInfo(player.getPlayerId());
		if(rankInfo!=null){
			getTask().getTaskInfo().setProcess((int)rankInfo.getSoul());
		}
	}

}
