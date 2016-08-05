package com.chuangyou.xianni.space.logic.op;

import com.chuangyou.xianni.entity.space.SpaceInfo;
import com.chuangyou.xianni.player.GamePlayer;

public interface ISpaceOpLogic {
	public void doProcess(GamePlayer player,GamePlayer reqPlayer,SpaceInfo info,int op);
}
