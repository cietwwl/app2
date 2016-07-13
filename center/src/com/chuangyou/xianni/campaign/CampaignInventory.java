package com.chuangyou.xianni.campaign;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.chuangyou.xianni.entity.campaign.CampaignRecordInfo;
import com.chuangyou.xianni.interfaces.IInventory;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.sql.dao.DBManager;

public class CampaignInventory implements IInventory {
	GamePlayer							player;

	/** 副本完成记录 */
	Map<Integer, CampaignRecordInfo>	records	= new ConcurrentHashMap<>();

	public CampaignInventory(GamePlayer player) {
		this.player = player;
	}

	public boolean isComplete(int campaignId) {
		return true;
	}

	@Override
	public boolean loadFromDataBase() {
		List<CampaignRecordInfo> allRecords = DBManager.getCampaignRecordInfoDao().getRecods(player.getPlayerId());

		if (allRecords != null && allRecords.size() > 0) {
			for (CampaignRecordInfo record : allRecords) {
				records.put(record.getCampaignId(), record);
			}
		}
		return true;
	}

	@Override
	public boolean unloadData() {
		return true;
	}

	@Override
	public boolean saveToDatabase() {
		return true;
	}

}
