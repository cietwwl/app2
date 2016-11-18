package com.chuangyou.xianni.battle.magicwpban.decorator;

import java.util.Set;

import com.chuangyou.common.protobuf.pb.battle.DamageMsgProto.DamageMsg;
import com.chuangyou.common.protobuf.pb.battle.MagicwpBanExeMsgProto.MagicwpBanExeMsg;
import com.chuangyou.xianni.battle.damage.Damage;
import com.chuangyou.xianni.battle.magicwpban.MagicwpBanBaseDecorator;
import com.chuangyou.xianni.constant.MagicwpBanConstant;
import com.chuangyou.xianni.proto.BroadcastUtil;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.objects.Player;
import com.chuangyou.xianni.warfield.helper.selectors.PlayerSelectorHelper;

public class BiteDecorator extends MagicwpBanBaseDecorator {

	public BiteDecorator(Player master, int level) {
		super(master, level, MagicwpBanConstant.BITE);
	}

	public void exe(Damage damage) {
		if (damage.getDamageValue() <= 0) {
			return;
		}
		Damage newDamage = new Damage(damage.getSource(), damage.getTarget());
		newDamage.setDamageType(damage.getDamageType());
		newDamage.setDamageValue(damage.getDamageValue());
		newDamage.setCalcType(damage.getCalcType());
		newDamage.setFromType(Damage.MAGICWP_BAN);
		newDamage.setFromId(temp.getCode());
		newDamage.setTipType(damage.getTipType());
		damage.getSource().takeDamage(newDamage);

		MagicwpBanExeMsg.Builder builder = MagicwpBanExeMsg.newBuilder();
		DamageMsg.Builder dmsg = DamageMsg.newBuilder();
		newDamage.writeProto(dmsg);
		builder.addDamages(dmsg);
		builder.setLivingId(getMaster().getId());
		builder.setBanlv(getLevel());
		builder.setBanSkillId(getTemp().getCode());

		Set<Long> nears = getMaster().getNears(new PlayerSelectorHelper(getMaster()));
		nears.add(getMaster().getArmyId());
		BroadcastUtil.sendBroadcastPacket(nears, Protocol.U_MAGICWP_BAN_EXE, builder.build());
	}

}
