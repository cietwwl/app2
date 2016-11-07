package com.chuangyou.xianni.battle.magicwpban.decorator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.chuangyou.common.protobuf.pb.battle.DamageMsgProto.DamageMsg;
import com.chuangyou.common.protobuf.pb.battle.MagicwpBanExeMsgProto.MagicwpBanExeMsg;
import com.chuangyou.common.util.MathUtils;
import com.chuangyou.xianni.battle.OrderFactory;
import com.chuangyou.xianni.battle.damage.BloodDamageCalculator;
import com.chuangyou.xianni.battle.damage.Damage;
import com.chuangyou.xianni.battle.damage.SoulDamageCalculator;
import com.chuangyou.xianni.battle.magicwpban.MagicwpBanBaseDecorator;
import com.chuangyou.xianni.constant.DamageEffecterType;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.constant.MagicwpBanConstant;
import com.chuangyou.xianni.proto.BroadcastUtil;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.role.objects.Player;
import com.chuangyou.xianni.warfield.helper.selectors.BeAttackerSelectorHelper;
import com.chuangyou.xianni.warfield.helper.selectors.PlayerSelectorHelper;

public class WeaponDecorator extends MagicwpBanBaseDecorator {
	private int damagePercent;

	public WeaponDecorator(Player master, int level) {
		super(master, level, MagicwpBanConstant.WEAPON);
		damagePercent = Math.min(10000 + (level - 1) * 500, 17500);
	}

	public boolean isEffect() {
		return random.next(100) <= 3;
	}

	public void exe() {
		Set<Long> nears = master.getNears(new BeAttackerSelectorHelper(master));
		Set<Living> beChoosers = new HashSet<>();
		for (Long id : nears) {
			Living target = master.getField().getLiving(id);
			if (target == null) {
				continue;
			}
			if (!OrderFactory.attackCheck(master.getField(), master, target)) {
				continue;
			}
			// 半径3M范围内
			float i = MathUtils.getDistByXZ(master.getPostion(), target.getPostion());
			if (i > 4) {
				continue;
			}
			beChoosers.add(target);
		}

		// 给予伤害
		for (Living target : beChoosers) {
			List<Damage> damages = new ArrayList<>();
			Damage damage1 = new Damage(target, master);
			Damage damage2 = new Damage(target, master);

			int damageValue1 = new BloodDamageCalculator().calcDamage(master, target, damagePercent, 0);
			damage1.setTarget(target);
			damage1.setSource(master);
			damage1.setFromType(Damage.MAGICWP_BAN);
			damage1.setFromId(temp.getCode());
			damage1.setDamageType(EnumAttr.CUR_BLOOD.getValue());
			damage1.setDamageValue(damageValue1);
			damage1.setCalcType(DamageEffecterType.BLOOD);
			if (damageValue1 != 0) {
				target.takeDamage(damage1);
				damages.add(damage1);
			}
			int damageValue2 = new SoulDamageCalculator().calcDamage(master, target, damagePercent, 0);
			damage2.setTarget(target);
			damage2.setSource(master);
			damage2.setFromType(Damage.MAGICWP_BAN);
			damage2.setFromId(temp.getCode());
			damage2.setDamageType(EnumAttr.CUR_SOUL.getValue());
			damage2.setDamageValue(damageValue2);
			damage2.setCalcType(DamageEffecterType.SOUL);
			if (damageValue2 != 0) {
				target.takeDamage(damage2);
				damages.add(damage2);
			}

			if (damages.size() > 0) {
				MagicwpBanExeMsg.Builder builder = MagicwpBanExeMsg.newBuilder();
				for (Damage d : damages) {
					DamageMsg.Builder dmsg = DamageMsg.newBuilder();
					d.writeProto(dmsg);
					builder.addDamages(dmsg);
				}
				builder.setLivingId(getMaster().getId());
				builder.setBanlv(getLevel());
				builder.setBanSkillId(getTemp().getCode());

				Set<Long> players = getMaster().getNears(new PlayerSelectorHelper(getMaster()));
				players.add(getMaster().getArmyId());
				BroadcastUtil.sendBroadcastPacket(players, Protocol.U_MAGICWP_BAN_EXE, builder.build());
			}
		}
	}
}
