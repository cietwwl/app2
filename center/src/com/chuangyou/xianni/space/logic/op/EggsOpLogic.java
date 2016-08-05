package com.chuangyou.xianni.space.logic.op;

import com.chuangyou.xianni.entity.space.SpaceInfo;
import com.chuangyou.xianni.player.GamePlayer;

/**
 * 鸡蛋
 * @author laofan
 *
 */
public class EggsOpLogic extends LikeOpLogic {

	@Override
	public void doProcess(GamePlayer player, GamePlayer reqPlayer, SpaceInfo info, int op) {
		// TODO Auto-generated method stub
		reqPlayer.getSpaceInventory().addEggs();
		doAll(player, reqPlayer, info, 0, op);
	}
	
}
