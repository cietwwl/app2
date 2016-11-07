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

public class ResistDeathDecorator extends MagicwpBanBaseDecorator {
	private int probability;

	public ResistDeathDecorator(Player master, int level) {
		super(master, level, MagicwpBanConstant.RESIST_DEATH);
		probability = Math.min(100 + 10 * (level - 1), 250);
	}

	public boolean isEffect() {
		return random.next(10000) <= probability;
	}

	public void exe() {
		int resortBlood = master.getProperty(EnumAttr.MAX_BLOOD) * 10 / 100;

		List<Damage> damages = new ArrayList<>();
		Damage blood = new Damage(master, master);
		blood.setDamageType(EnumAttr.CUR_BLOOD.getValue());
		blood.setDamageValue(-resortBlood);
		blood.setSource(master);
		blood.setCalcType(DamageEffecterType.BLOOD);
		blood.setFromType(Damage.MAGICWP_BAN);
		blood.setFromId(temp.getCode());
		damages.add(blood);
		master.takeDamage(blood);

		MagicwpBanExeMsg.Builder builder = MagicwpBanExeMsg.newBuilder();
		for (Damage d : damages) {
			DamageMsg.Builder dmsg = DamageMsg.newBuilder();
			d.writeProto(dmsg);
			builder.addDamages(dmsg);
		}
		builder.setLivingId(getMaster().getId());
		builder.setBanlv(getLevel());
		builder.setBanSkillId(getTemp().getCode());

		Set<Long> nears = getMaster().getNears(new PlayerSelectorHelper(getMaster()));
		nears.add(getMaster().getArmyId());
		BroadcastUtil.sendBroadcastPacket(nears, Protocol.U_MAGICWP_BAN_EXE, builder.build());
	}

}
