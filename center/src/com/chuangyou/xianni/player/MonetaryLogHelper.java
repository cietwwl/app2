package com.chuangyou.xianni.player;

import java.util.Date;

import com.chuangyou.xianni.entity.log.MonetaryLogInfo;
import com.chuangyou.xianni.log.LogManager;

public class MonetaryLogHelper {

	public static void addLog(long playerId, int toalCount, int moneyType, int changeWay, int changeCount) {
		MonetaryLogInfo log = new MonetaryLogInfo();
		log.setPlayerId(playerId);
		log.setMoneyType(moneyType);
		log.setChangeWay(changeWay);
		log.setToalCount(toalCount);
		log.setChangeCount(changeCount);
		log.setChangeTime(new Date());
		LogManager.addMonetaryLog(log);
	}

}
