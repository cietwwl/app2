package com.chuangyou.xianni.ai2.proxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.chuangyou.common.protobuf.pb.battle.DamageListMsgProtocol.DamageListMsg;
import com.chuangyou.common.protobuf.pb.battle.DamageMsgProto.DamageMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.battle.damage.Damage;
import com.chuangyou.xianni.constant.DamageEffecterType;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.constant.RoleConstants.RoleType;
import com.chuangyou.xianni.proto.BroadcastUtil;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.objects.ActiveLiving;
import com.chuangyou.xianni.warfield.helper.selectors.PlayerSelectorHelper;
import com.chuangyou.xianni.world.HeartbeatWorldMgr;

public abstract class AI {
	protected ActiveLiving living;

	public AI(ActiveLiving living) {
		this.living = living;
		HeartbeatWorldMgr.addAI(this);
	}

	public void exe() {
		try {
			exec();
			calBlood();
			dateBufferCal();
			leaveFight();
		} catch (Exception e) {
			Log.error("AI exe ", e);
		}
	}

	public abstract void exec();

	/** 自存在buff计算器 */
	private void dateBufferCal() {
		living.exeWorkBuffer();
	}

	/** 脱战 */
	private void leaveFight() {
		if (living.isFighting() && System.currentTimeMillis() - living.getLastFightTM() > 20 * 1000) {
			living.leaveFight();
		}
	}

	/* 气血计算 */
	private void calBlood() {
		if (living.getType() != RoleType.monster && living.getType() != RoleType.player && living.getType() != RoleType.avatar) {
			return;
		}
		if (living.isDie()) {
			return;
		}
		long now = System.currentTimeMillis();
		if (now - living.getRestoreTime() > 10 * 1000) {

			living.setRestoreTime(now);
			List<Damage> damages = new ArrayList<>();

			if (living.lessSoul() > 0) {
				Damage soulDamage = new Damage(living, living);
				soulDamage.setDamageType(EnumAttr.CUR_SOUL.getValue());
				soulDamage.setCalcType(DamageEffecterType.SOUL);
				int restore = Math.min(living.getRegainSoul(), living.lessSoul());
				if (restore > 0) {
					soulDamage.setDamageValue(0 - restore);
					damages.add(soulDamage);
					living.takeDamage(soulDamage);
				}

			}
			if (living.lessBlood() > 0) {
				Damage bloodDamage = new Damage(living, living);
				bloodDamage.setDamageType(EnumAttr.CUR_BLOOD.getValue());
				bloodDamage.setCalcType(DamageEffecterType.BLOOD);
				int restore = Math.min(living.getRegainBlood(), living.lessBlood());
				if (restore > 0) {
					bloodDamage.setDamageValue(0 - restore);
					damages.add(bloodDamage);
					living.takeDamage(bloodDamage);
				}
			}

			if (damages.size() > 0) {
				DamageListMsg.Builder damagesPb = DamageListMsg.newBuilder();
				for (Damage damage : damages) {
					damagesPb.setAttackId(-1);
					DamageMsg.Builder dmsg = DamageMsg.newBuilder();
					damage.writeProto(dmsg);
					damagesPb.addDamages(dmsg);
				}
				Set<Long> players = living.getNears(new PlayerSelectorHelper(living));
				// 添加自己
				players.add(living.getArmyId());
				BroadcastUtil.sendBroadcastPacket(players, Protocol.U_G_DAMAGE, damagesPb.build());
			}
		}
	}
}
