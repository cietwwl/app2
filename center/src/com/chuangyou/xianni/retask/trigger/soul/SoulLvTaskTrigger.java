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
 * 主魂等级
 * 
 * @author laofan
 *
 *         对应主魂ID 1 任意1个 2 任意2个 3 任意3个 4 任意4个 5 任意5个 …… ……
 *
 * 
 */
public class SoulLvTaskTrigger extends BaseTaskTrigger implements ITaskInitBehavior {

	public SoulLvTaskTrigger(GamePlayer player, ITask task) {
		super(player, task);
		// TODO Auto-generated constructor stub
	}


	private int getCount(Map<Integer, SoulCardInfo> map, int num) {
		int count = 0;
		for (SoulCardInfo soulInfo : map.values()) {
			if (soulInfo.getLv() >= num) {
				count++;
			}
		}
		return count;
	}

	@Override
	public void addTrigger() {
		// TODO Auto-generated method stub
		removeTrigger();
		this.listener = new ObjectListener() {

			@Override
			public void onEvent(ObjectEvent event) {
				// TODO Auto-generated method stub
				SoulCardInfo tempInfo = (SoulCardInfo) event.getObject();

				int targetId = getTask().getTaskCfg().getTargetId();
				if (targetId == 1) {
					if (getTask().getTaskCfg().getTargetNum() == tempInfo.getCardId()) {
						getTask().updateProcess(tempInfo.getLv());
					}
				} else if (targetId == 2) {
					getTask().updateProcess(getCount(player.getSoulInventory().getCards(), getTask().getTaskCfg().getTargetId1()));
				}
				
			}
		};
		player.addListener(listener, EventNameType.SOUL_LV);
	}

	@Override
	public void removeTrigger() {
		// TODO Auto-generated method stub
		player.removeListener(listener, EventNameType.SOUL_LV);
	}

	@Override
	public void initTask() {
		// TODO Auto-generated method stub
		int targetId = getTask().getTaskCfg().getTargetId();
		if (targetId == 2) {
			SoulCardInfo cardInfo = player.getSoulInventory().getCards().get(getTask().getTaskCfg().getTargetNum());
			if (cardInfo != null) {
				getTask().getTaskInfo().setProcess(cardInfo.getLv());
			}
		} else if (targetId == 1) {
			getTask().getTaskInfo().setProcess(getCount(player.getSoulInventory().getCards(), getTask().getTaskCfg().getTargetId1()));
		}
	}

}
