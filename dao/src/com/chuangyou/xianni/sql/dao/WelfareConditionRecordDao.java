package com.chuangyou.xianni.sql.dao;

import com.chuangyou.xianni.entity.sevenDaysGiftPacks.WelfareConditionRecordInfo;

public interface WelfareConditionRecordDao {
	WelfareConditionRecordInfo getInfoByPlayerId(long playerId);
	boolean update(WelfareConditionRecordInfo sevenDaysGiftPackageInfo);
	boolean add(WelfareConditionRecordInfo sevenDaysGiftPackageInfo);
}
