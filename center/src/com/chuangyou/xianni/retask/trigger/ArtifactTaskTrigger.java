package com.chuangyou.xianni.retask.trigger;

import java.util.Map;

import com.chuangyou.xianni.entity.artifact.ArtifactInfo;
import com.chuangyou.xianni.entity.rank.RankTempInfo;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.event.ObjectListener;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.rank.RankServerManager;
import com.chuangyou.xianni.retask.event.ArtifactStateEvent;
import com.chuangyou.xianni.retask.iinterface.ITask;
import com.chuangyou.xianni.retask.iinterface.ITaskInitBehavior;

/**
 * 神器
 * 
 * @author laofan
 *
 */
public class ArtifactTaskTrigger extends BaseTaskTrigger implements ITaskInitBehavior {

	public ArtifactTaskTrigger(GamePlayer player, ITask task) {
		super(player, task);
		// TODO Auto-generated constructor stub
	}




	private int getCountStar(Map<Integer, ArtifactInfo> map, int num) {
		int count = 0;
		for (ArtifactInfo mountEquip : map.values()) {
			if (mountEquip.getStar() >= num) {
				count++;
			}
		}
		return count;
	}

	private int getCountLv(Map<Integer, ArtifactInfo> map, int num) {
		int count = 0;
		for (ArtifactInfo mountEquip : map.values()) {
			if (mountEquip.getLevel() >= num) {
				count++;
			}
		}
		return count;
	}

	private int getCountStoneLv(Map<Integer, ArtifactInfo> map, int num) {
		int count = 0;
		for (ArtifactInfo mountEquip : map.values()) {
			if (mountEquip.getStoneMaxLevel() >= num) {
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
				ArtifactStateEvent e = (ArtifactStateEvent) event;
				if (e.getTargetId() == 1 || e.getTargetId() == 3 || e.getTargetId() == 5) {
					if (getTask().getTaskCfg().getTargetNum() == e.getTargetId1()) {
						getTask().updateProcess(e.getTargetNum());
					}
					if (e.getTargetId() == 1) {
						getTask().updateProcess(getCountLv(player.getArtifactInventory().getArtifactMap(), getTask().getTaskCfg().getTargetId1()));
					} else if (e.getTargetId() == 3) {
						getTask().updateProcess(getCountStar(player.getArtifactInventory().getArtifactMap(), getTask().getTaskCfg().getTargetId1()));
					} else if (e.getTargetId() == 5) {
						getTask().updateProcess(getCountStoneLv(player.getArtifactInventory().getArtifactMap(), getTask().getTaskCfg().getTargetId1()));
					}
				} else if (e.getTargetId() == 7) {
					RankTempInfo rankInfo = RankServerManager.getInstance().getRankTempInfo(player.getPlayerId());
					if (rankInfo != null) {
						getTask().updateProcess((int) rankInfo.getArtifact());
					}
				}

			}
		};
		player.addListener(listener, EventNameType.ARTIFACT);
	}

	@Override
	public void removeTrigger() {
		// TODO Auto-generated method stub
		player.removeListener(listener, EventNameType.ARTIFACT);
	}

	@Override
	public void initTask() {
		// TODO Auto-generated method stub
		int targetId = getTask().getTaskCfg().getTargetId();
		if (targetId == 1) {
			getTask().getTaskInfo().setProcess(player.getArtifactInventory().getArtifactMap().get(getTask().getTaskCfg().getTargetNum()).getLevel());
		} else if (targetId == 2) {
			getTask().getTaskInfo().setProcess(getCountLv(player.getArtifactInventory().getArtifactMap(), getTask().getTaskCfg().getTargetId1()));
		} else if (targetId == 3) {
			getTask().getTaskInfo().setProcess(player.getArtifactInventory().getArtifactMap().get(getTask().getTaskCfg().getTargetNum()).getStar());
		} else if (targetId == 4) {
			getTask().getTaskInfo().setProcess(getCountStar(player.getArtifactInventory().getArtifactMap(), getTask().getTaskCfg().getTargetId1()));
		} else if (targetId == 5) {
			getTask().getTaskInfo().setProcess(player.getArtifactInventory().getArtifactMap().get(getTask().getTaskCfg().getTargetNum()).getStoneMaxLevel());
		} else if (targetId == 6) {
			getTask().getTaskInfo().setProcess(getCountStoneLv(player.getArtifactInventory().getArtifactMap(), getTask().getTaskCfg().getTargetId1()));
		} else if (targetId == 7) {
			RankTempInfo rankInfo = RankServerManager.getInstance().getRankTempInfo(player.getPlayerId());
			if (rankInfo != null) {
				getTask().getTaskInfo().setProcess((int) rankInfo.getArtifact());
			}
		}
	}

}
