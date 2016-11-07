package com.chuangyou.xianni.retask.trigger.magicwp;

import java.util.Map;

import com.chuangyou.xianni.entity.magicwp.MagicwpInfo;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.event.ObjectListener;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.retask.iinterface.ITask;
import com.chuangyou.xianni.retask.iinterface.ITaskInitBehavior;
import com.chuangyou.xianni.retask.trigger.BaseTaskTrigger;

/**
 * 法宝
 * 
 * @author laofan
 *
 */
public class MagicWpTaskTrigger extends BaseTaskTrigger implements ITaskInitBehavior {

	public MagicWpTaskTrigger(GamePlayer player, ITask task) {
		super(player, task);
		// TODO Auto-generated constructor stub
	}

	


	private int getCountLv(Map<Integer, MagicwpInfo> map, int num) {
		int count = 0;
		for (MagicwpInfo mountEquip : map.values()) {
			if (mountEquip.getLevel() >= num) {
				count++;
			}
		}
		return count;
	}

	private int getCountGrade(Map<Integer, MagicwpInfo> map, int num) {
		int count = 0;
		for (MagicwpInfo mountEquip : map.values()) {
			if (mountEquip.getGrade() >= num) {
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
				MagicwpInfo tempInfo = (MagicwpInfo) event.getObject();
				int targetId = getTask().getTaskCfg().getTargetId();
				if (targetId == 3) {
					if (tempInfo.getMagicwpId() == getTask().getTaskCfg().getTargetNum()) {
						getTask().updateProcess(player.getMagicwpInventory().getMagicwpInfo(getTask().getTaskCfg().getTargetNum()).getLevel());
					}
				} else if (targetId == 4) {
					getTask().updateProcess(getCountLv(player.getMagicwpInventory().getMagicwpInfoMap(), getTask().getTaskCfg().getTargetId1()));
				} else if (targetId == 5) {
					if (tempInfo.getMagicwpId() == getTask().getTaskCfg().getTargetNum()) {
						getTask().updateProcess(player.getMagicwpInventory().getMagicwpInfo(getTask().getTaskCfg().getTargetNum()).getGrade());
					}
				} else if (targetId == 6) {
					getTask().updateProcess(getCountGrade(player.getMagicwpInventory().getMagicwpInfoMap(), getTask().getTaskCfg().getTargetId1()));
				}
			}
		};
		player.addListener(listener, EventNameType.MAGICWP);
	}

	@Override
	public void removeTrigger() {
		// TODO Auto-generated method stub
		player.removeListener(listener, EventNameType.MAGICWP);
	}

	@Override
	public void initTask() {
		// TODO Auto-generated method stub
		int targetId = getTask().getTaskCfg().getTargetId();
		if (targetId == 3) {
			if (player.getMagicwpInventory().getMagicwpInfo(getTask().getTaskCfg().getTargetNum()) != null) {
				getTask().getTaskInfo().setProcess(player.getMagicwpInventory().getMagicwpInfo(getTask().getTaskCfg().getTargetNum()).getLevel());
			}
		} else if (targetId == 4) {
			getTask().getTaskInfo().setProcess(getCountLv(player.getMagicwpInventory().getMagicwpInfoMap(), getTask().getTaskCfg().getTargetId1()));
		} else if (targetId == 5) {
			if (player.getMagicwpInventory().getMagicwpInfo(getTask().getTaskCfg().getTargetNum()) != null) {
				getTask().getTaskInfo().setProcess(player.getMagicwpInventory().getMagicwpInfo(getTask().getTaskCfg().getTargetNum()).getGrade());
			}
		} else if (targetId == 6) {
			getTask().getTaskInfo().setProcess(getCountGrade(player.getMagicwpInventory().getMagicwpInfoMap(), getTask().getTaskCfg().getTargetId1()));
		}
	}

}
