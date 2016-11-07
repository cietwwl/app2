package com.chuangyou.xianni.battle.magicwpban.decorator;

import java.util.ArrayList;
import java.util.List;
import com.chuangyou.common.protobuf.pb.battle.MagicwpBanExeMsgProto.MagicwpBanExeMsg;
import com.chuangyou.xianni.battle.magicwpban.MagicwpBanBaseDecorator;
import com.chuangyou.xianni.battle.skill.Skill;
import com.chuangyou.xianni.constant.MagicwpBanConstant;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.objects.Player;
import com.chuangyou.xianni.world.ArmyProxy;
import com.chuangyou.xianni.world.WorldMgr;

public class CoolDownDecorator extends MagicwpBanBaseDecorator {

	public CoolDownDecorator(Player master, int level) {
		super(master, level, MagicwpBanConstant.COOL_DOWN);
	}

	public boolean isEffect() {
		return random.next(100) <= 10;
	}

	public void exe() {
		List<Skill> allSkills = new ArrayList<>(master.getDrivingSkills().values());
		for (Skill skill : allSkills) {
			skill.setLastUsed(skill.getLastUsed() - 1000);
		}
		MagicwpBanExeMsg.Builder builder = MagicwpBanExeMsg.newBuilder();
		builder.setLivingId(getMaster().getId());
		builder.setBanlv(getLevel());
		builder.setBanSkillId(getTemp().getCode());

		ArmyProxy army = WorldMgr.getArmy(getMaster().getArmyId());
		PBMessage message = MessageUtil.buildMessage(Protocol.U_MAGICWP_BAN_EXE, builder);
		if (army != null) {
			army.sendPbMessage(message);
		}
	}

}
