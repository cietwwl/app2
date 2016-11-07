package com.chuangyou.xianni.welfare.conditionHandle;

import com.chuangyou.xianni.player.GamePlayer;

public class LoginDaysCondition extends BaseConditionHandle {

	public LoginDaysCondition(GamePlayer player) {
		super(player);
	}

	@Override
	public boolean judge(long parameter) {
		return parameter <= player.getWelfareConditionRecordInventory().getInfo().getDays();
	}

}
