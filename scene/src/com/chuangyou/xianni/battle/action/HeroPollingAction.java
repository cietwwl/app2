package com.chuangyou.xianni.battle.action;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.role.objects.Player;
import com.chuangyou.xianni.warfield.helper.FieldConstants.FieldAttackRule;
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
		calPkVal(player);
		// 变身时间到,解除变身
		if (player.isCorrespondStatu() && System.currentTimeMillis() > player.getUnTransfigurationTime()) {
			player.unTransfiguration();
		}
		// 调试代码
		if (living.getLivingState() != Living.DISTORY && !WorldMgr.isExist(player.getArmyId())) {
			Log.error(player.getId() + "***********************出现下线未销毁的player*******************************" + getClass().getName() + "@" + Integer.toHexString(living.hashCode()));
			// player.clearData();
		}
	}

	/* pk值计算 **/
	private void calPkVal(Player player) {
		if (player.getField() != null) {
			if (living.getPkVal() > 0 && System.currentTimeMillis() - player.getPkValCalTime() >= 10 * 1000 && player.getField().getAttackRule(null, null) == FieldAttackRule.USEPLAYERMODE) {
				player.calPkVal();
			}
		}
	}
}
