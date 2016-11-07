package com.chuangyou.xianni.battle.magicwpban.decorator;

import java.util.Set;

import com.chuangyou.common.protobuf.pb.battle.MagicwpBanExeMsgProto.MagicwpBanExeMsg;
import com.chuangyou.xianni.battle.magicwpban.MagicwpBanBaseDecorator;
import com.chuangyou.xianni.constant.MagicwpBanConstant;
import com.chuangyou.xianni.proto.BroadcastUtil;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.objects.Player;
import com.chuangyou.xianni.warfield.helper.selectors.PlayerSelectorHelper;

public class ImmuneDamageDecorator extends MagicwpBanBaseDecorator {
	private int probability;

	public ImmuneDamageDecorator(Player master, int level) {
		super(master, level, MagicwpBanConstant.IMMUNE_DAMAGE);
		probability = Math.min(300 + 10 * (level - 1), 450);
	}

	public boolean isEffect() {
		return random.next(10000) <= probability;
	}

	public void exe() {
		MagicwpBanExeMsg.Builder builder = MagicwpBanExeMsg.newBuilder();
		builder.setLivingId(getMaster().getId());
		builder.setBanlv(getLevel());
		builder.setBanSkillId(getTemp().getCode());

		Set<Long> nears = getMaster().getNears(new PlayerSelectorHelper(getMaster()));
		nears.add(getMaster().getArmyId());
		BroadcastUtil.sendBroadcastPacket(nears, Protocol.U_MAGICWP_BAN_EXE, builder.build());
	}

}
