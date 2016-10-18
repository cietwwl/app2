package com.chuangyou.xianni.battle.action;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.role.objects.Player;
import com.chuangyou.xianni.world.WorldMgr;

/**
 * 英雄自检动作/buffer运算
 */
public class HeroPollingAction extends PollingAction {

	public HeroPollingAction(Player player) {
		super(player, PollingAction.DELAY);
	}

	@Override
	public void exec() {
		Player player = (Player) this.living;
		// 变身时间到,解除变身
		if (player.isCorrespondStatu() && System.currentTimeMillis() > player.getUnTransfigurationTime()) {
			player.unTransfiguration();
		}
		// 调试代码
		if (living.getLivingState() != Living.DISTORY && !WorldMgr.isExist(player.getArmyId())) {
			Log.error(player.getId() + "***********************出现下线未销毁的player*******************************" + getClass().getName() + "@" + Integer.toHexString(hashCode()));
			//player.clearData();
		}
	}
}
