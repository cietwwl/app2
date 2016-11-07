package com.chuangyou.xianni.retask.trigger.soul;

import java.util.Map;

import com.chuangyou.xianni.entity.soul.SoulCardInfo;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.event.ObjectListener;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.retask.iinterface.ITask;
import com.chuangyou.xianni.retask.iinterface.ITaskInitBehavior;
import com.chuangyou.xianni.retask.trigger.BaseTaskTrigger;

/**
 * 主魂星级
 * 
 * @author laofan
 *
 */
public class SoulStarTaskTrigger extends BaseTaskTrigger implements ITaskInitBehavior {

	public SoulStarTaskTrigger(GamePlayer player, ITask task) {
		super(player, task);
		// TODO Auto-generated constructor stub
	}



	private int getCount(Map<Integer, SoulCardInfo> map, int num) {
		int count = 0;
		for (SoulCardInfo soulInfo : map.values()) {
			if (soulInfo.getStar() >= num) {
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
				SoulCardInfo tempInfo = (SoulCardInfo) event.getObject();

				int targetId = getTask().getTaskCfg().getTargetId();
				if (targetId == 3) {
					if (getTask().getTaskCfg().getTargetNum() == tempInfo.getCardId()) {
						getTask().updateProcess(tempInfo.getStar());
					}
				} else if (targetId == 4) {
					getTask().updateProcess(getCount(player.getSoulInventory().getCards(), getTask().getTaskCfg().getTargetId1()));
				}
				
			}
		};
		player.addListener(listener, EventNameType.SOUL_STAR);
	}

	@Override
	public void removeTrigger() {
		// TODO Auto-generated method stub
		player.removeListener(listener, EventNameType.SOUL_STAR);
	}

	@Override
	public void initTask() {
		// TODO Auto-generated method stub
		int targetId = this.getTask().getTaskCfg().getTargetId();
		if (targetId == 4) {
			SoulCardInfo cardInfo = player.getSoulInventory().getCards().get(this.getTask().getTaskCfg().getTargetNum());
			if (cardInfo != null) {
				getTask().getTaskInfo().setProcess(cardInfo.getStar());
			}
		} else if (targetId == 3) {
			getTask().getTaskInfo().setProcess(getCount(player.getSoulInventory().getCards(), getTask().getTaskCfg().getTargetId1()));
		}
	}

}
