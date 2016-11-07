package com.chuangyou.xianni.welfare.conditionHandle;

import java.util.Calendar;

import com.chuangyou.xianni.player.GamePlayer;

public class SignCondition extends BaseConditionHandle {

	public SignCondition(GamePlayer player) {
		super(player);
	}
	@Override
	public boolean judge(long parameter) {
		return parameter  == Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}
}
