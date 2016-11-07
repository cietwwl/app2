package com.chuangyou.xianni.retask.trigger;

import java.util.Map;

import com.chuangyou.xianni.entity.mount.MountEquipInfo;
import com.chuangyou.xianni.entity.rank.RankTempInfo;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.event.ObjectListener;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.rank.RankServerManager;
import com.chuangyou.xianni.retask.event.MountStateEvent;
import com.chuangyou.xianni.retask.iinterface.ITask;
import com.chuangyou.xianni.retask.iinterface.ITaskInitBehavior;

/**
 * 坐骑
 * 
 * @author laofan
 *
 */
public class MountTaskTrigger extends BaseTaskTrigger implements ITaskInitBehavior {

	public MountTaskTrigger(GamePlayer player, ITask task) {
		super(player, task);
		// TODO Auto-generated constructor stub
	}


	private int getCount(Map<Integer, MountEquipInfo> map, int num) {
		int count = 0;
		for (MountEquipInfo mountEquip : map.values()) {
			if (mountEquip.getEquipLevel() >= num) {
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
				MountStateEvent e = (MountStateEvent) event;
				if (e != null) {
					if (e.getTargetId() >= 1 && e.getTargetId() <= 3) {
						if (e.getTargetId() == getTask().getTaskCfg().getTargetId()) {
							getTask().updateProcess(e.getTargetNum());
						}
					} else if (e.getTargetId() == 4) {
						if (e.getTargetId1() == getTask().getTaskCfg().getTargetId1()) {
							getTask().updateProcess(e.getTargetNum());
						}
						getTask().updateProcess(getCount(player.getMountInventory().getMountEquip(), getTask().getTaskCfg().getTargetId1()));
					} else if (e.getTargetId() == 6) { // 战力
						RankTempInfo rankInfo = RankServerManager.getInstance().getRankTempInfo(player.getPlayerId());
						if (rankInfo != null) {
							getTask().updateProcess((int) rankInfo.getMount());
						}
					}
				}

			}
		};
		player.addListener(listener, EventNameType.MOUNT);
	}

	@Override
	public void removeTrigger() {
		// TODO Auto-generated method stub
		player.removeListener(listener, EventNameType.MOUNT);
	}

	@Override
	public void initTask() {
		// TODO Auto-generated method stub
		int targetId = getTask().getTaskCfg().getTargetId();
		if (targetId == 1) {
			getTask().getTaskInfo().setProcess(player.getMountInventory().getMount().getLevel());
		} else if (targetId == 2) {
			getTask().getTaskInfo().setProcess(player.getMountInventory().getMount().getGrade());
		} else if (targetId == 3) {
			getTask().getTaskInfo().setProcess(player.getMountInventory().getMount().getWeaponGrade());
		} else if (targetId == 4) {
			getTask().getTaskInfo().setProcess(player.getMountInventory().getMountEquip().get(getTask().getTaskCfg().getTargetNum()).getEquipLevel());
		} else if (targetId == 5) {
			getTask().getTaskInfo().setProcess(getCount(player.getMountInventory().getMountEquip(), getTask().getTaskCfg().getTargetId1()));
		} else if (targetId == 6) {
			RankTempInfo rankInfo = RankServerManager.getInstance().getRankTempInfo(player.getPlayerId());
			if (rankInfo != null) {
				getTask().getTaskInfo().setProcess((int) rankInfo.getMount());
			}
		}
	}

}
