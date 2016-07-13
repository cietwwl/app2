package com.chuangyou.xianni.sql.dao;

import java.util.List;

import com.chuangyou.xianni.entity.campaign.CampaignRecordInfo;

public interface CampaignRecordInfoDao {

	public List<CampaignRecordInfo> getRecods(long playerId);

	public boolean saveOrUpdata(CampaignRecordInfo info);

	public int getMaxId();
}
