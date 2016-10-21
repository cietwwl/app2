package com.chuangyou.xianni.role.objects;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.chuangyou.xianni.campaign.Campaign;
import com.chuangyou.xianni.campaign.CampaignMgr;
import com.chuangyou.xianni.role.helper.RoleConstants.RoleType;
import com.chuangyou.xianni.warfield.spawn.GatherSpawnNode;
import com.chuangyou.xianni.world.ArmyProxy;

/**
 * 采集物
 * 
 * @author laofan
 * 
 */
public class Gather extends Living {

	private String			name;

	/**
	 * 采集物采集CD缓存
	 */
	private Map<Long, Long>	playerCdTimers	= new HashMap<>();

	public Gather(long id, GatherSpawnNode node, String name) {
		super(id);
		setType(RoleType.gather);
		this.node = node;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Gather [name=" + name + ", getId()=" + getId() + "]";
	}

	/**
	 * 添加CD
	 * 
	 * @param playerId
	 * @param time
	 */
	public void addCdTime(long playerId, long time) {
		synchronized (playerCdTimers) {
			if (playerCdTimers.size() > 10) {
				clearCd();
			}
			playerCdTimers.put(playerId, time);
		}
	}

	/**
	 * 删除CD时间
	 * 
	 * @param playerId
	 */
	public void removeCdTime(long playerId) {
		synchronized (playerCdTimers) {
			playerCdTimers.remove(playerId);
		}
	}

	/**
	 * 获取记录CD的时间
	 * 
	 * @param playerId
	 * @return
	 */
	public long getTime(long playerId) {
		if (playerCdTimers.containsKey(playerId)) {
			return playerCdTimers.get(playerId);
		}
		return 0;
	}

	@Override
	public boolean onDie(Living source) {
		if (super.onDie(source)) {
			this.playerCdTimers.clear();
		}
		return true;
	}

	/**
	 * 清理一下有些时间已经很久的CD
	 */
	public void clearCd() {
		synchronized (playerCdTimers) {
			Iterator<Entry<Long, Long>> it = playerCdTimers.entrySet().iterator();
			long currentT = System.currentTimeMillis();
			while (it.hasNext()) {
				if (currentT - it.next().getValue() > 60 * 1000) {
					it.remove();
				}
			}
		}
	}
	
	/**
	 * 触发采集
	 * @param army
	 */
	public void trigger(ArmyProxy army){
		if (field != null && field.getCampaignId() > 0) {
			Campaign campaign = CampaignMgr.getCampagin(field.getCampaignId());
			if (campaign != null) {
				campaign.onTriggerPoint(army, this.node);
			}
		}
	}

}
