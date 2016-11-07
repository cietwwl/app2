package com.chuangyou.xianni.welfare.conditionHandle;

import com.chuangyou.xianni.player.GamePlayer;

public class MinLevelCondition extends BaseConditionHandle {

	public MinLevelCondition(GamePlayer player) {
		super(player);
	}
	@Override
	public boolean judge(long parameter) {
		return parameter  <= player.getLevel();
	}

}
