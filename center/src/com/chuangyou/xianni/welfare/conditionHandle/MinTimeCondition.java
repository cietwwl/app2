package com.chuangyou.xianni.welfare.conditionHandle;

import com.chuangyou.xianni.player.GamePlayer;

public class MinTimeCondition extends BaseConditionHandle {

	public MinTimeCondition(GamePlayer player) {
		super(player);
	}

	@Override
	public boolean judge(long parameter) {
		return parameter <= System.currentTimeMillis();
	}

}
