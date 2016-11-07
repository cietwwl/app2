package com.chuangyou.xianni.battle.magicwpban.decorator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.chuangyou.common.protobuf.pb.battle.DamageMsgProto.DamageMsg;
import com.chuangyou.common.protobuf.pb.battle.MagicwpBanExeMsgProto.MagicwpBanExeMsg;
import com.chuangyou.xianni.battle.damage.Damage;
import com.chuangyou.xianni.battle.magicwpban.MagicwpBanBaseDecorator;
import com.chuangyou.xianni.constant.DamageEffecterType;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.constant.MagicwpBanConstant;
import com.chuangyou.xianni.proto.BroadcastUtil;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.objects.Player;
import com.chuangyou.xianni.warfield.helper.selectors.PlayerSelectorHelper;

public class SuckBloodDecorator extends MagicwpBanBaseDecorator {

	public SuckBloodDecorator(Player master, int level) {
		super(master, level, MagicwpBanConstant.SUCK_BLOOD);
	}

	@Override
	public boolean isEffect() {
		return random.next(100) <= 5;
	}

	@Override
	public int getEffectValue() {
		return 0;
	}

	@Override
	public void exe(int value) {
		if (value <= 0) {
			return;
		}
		int effctValue = Math.min(5 + (level - 1) * 2, 35);
		int restore = value * effctValue / 100;

		Damage blood = new Damage(master, master);
		blood.setDamageType(EnumAttr.CUR_BLOOD.getValue());
		blood.setDamageValue(-restore);
		blood.setSource(master);
		blood.setCalcType(DamageEffecterType.BLOOD);
		blood.setFromType(Damage.MAGICWP_BAN);
		blood.setFromId(temp.getCode());
		master.takeDamage(blood);

		MagicwpBanExeMsg.Builder builder = MagicwpBanExeMsg.newBuilder();
		DamageMsg.Builder dmsg = DamageMsg.newBuilder();
		blood.writeProto(dmsg);
		builder.addDamages(dmsg);
		builder.setLivingId(getMaster().getId());
		builder.setBanlv(getLevel());
		builder.setBanSkillId(getTemp().getCode());

		Set<Long> nears = getMaster().getNears(new PlayerSelectorHelper(getMaster()));
		nears.add(getMaster().getArmyId());
		BroadcastUtil.sendBroadcastPacket(nears, Protocol.U_MAGICWP_BAN_EXE, builder.build());
	}

}
