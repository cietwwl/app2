package com.chuangyou.xianni.sql.dao;

import com.chuangyou.xianni.entity.campaign.LimitlessCampaignRecordInfo;

public interface LimitlessCampaignRecordInfoDao {
	LimitlessCampaignRecordInfo getByPlayerId(long playerId);

	boolean savaOrUpdata(LimitlessCampaignRecordInfo info);
}
