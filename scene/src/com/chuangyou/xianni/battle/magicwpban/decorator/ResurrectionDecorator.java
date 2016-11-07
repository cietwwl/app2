package com.chuangyou.xianni.battle.magicwpban.decorator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import com.chuangyou.common.protobuf.pb.battle.DamageMsgProto.DamageMsg;
import com.chuangyou.common.protobuf.pb.battle.MagicwpBanExeMsgProto.MagicwpBanExeMsg;
import com.chuangyou.xianni.battle.damage.Damage;
import com.chuangyou.xianni.battle.magicwpban.MagicwpBanBaseDecorator;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.constant.MagicwpBanConstant;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.objects.Player;
import com.chuangyou.xianni.warfield.helper.selectors.PlayerSelectorHelper;
import com.chuangyou.xianni.world.ArmyProxy;
import com.chuangyou.xianni.world.WorldMgr;

public class ResurrectionDecorator extends MagicwpBanBaseDecorator {
	private int probability;

	public ResurrectionDecorator(Player master, int level) {
		super(master, level, MagicwpBanConstant.RESURRECTION);
		probability = Math.min(200 + (level - 1) * 10, 350);
	}

	public boolean isEffect() {
		return random.next(10000) <= probability;
	}

	public void exe() {
		List<Damage> damages = new ArrayList<>();
		Damage curSoul = new Damage(master, master);
		curSoul.setDamageType(EnumAttr.CUR_SOUL.getValue());
		curSoul.setDamageValue(0 - master.getInitSoul());
		damages.add(curSoul);
		master.takeDamage(curSoul);

		Damage curBlood = new Damage(master, master);
		curBlood.setDamageType(EnumAttr.CUR_BLOOD.getValue());
		curBlood.setDamageValue(0 - master.getInitBlood());
		damages.add(curBlood);
		master.takeDamage(curBlood);

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
			Set<Long> players = master.getNears(new PlayerSelectorHelper(master));
			// 添加自己
			players.add(master.getArmyId());
			for (Long armyId : players) {
				ArmyProxy army = WorldMgr.getArmy(armyId);
				PBMessage message = MessageUtil.buildMessage(Protocol.U_G_DAMAGE, builder.build());
				if (army != null) {
					army.sendPbMessage(message);
				}
			}
		}
	}

}
