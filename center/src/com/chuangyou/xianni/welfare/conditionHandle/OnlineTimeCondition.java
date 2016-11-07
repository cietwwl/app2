package com.chuangyou.xianni.welfare.conditionHandle;

import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.welfare.WelfareConditionRecordInventory;

public class OnlineTimeCondition extends BaseConditionHandle {

	public OnlineTimeCondition(GamePlayer player) {
		super(player);
	}
	@Override
	public boolean judge(long parameter) {
		WelfareConditionRecordInventory daysGiftPackageInventory = player.getWelfareConditionRecordInventory();
		return parameter  <= (System.currentTimeMillis() - daysGiftPackageInventory.getOnlineStartTime()) / 1000 + daysGiftPackageInventory.getInfo().getOnLineTime();
	}

}
