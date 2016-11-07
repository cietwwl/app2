package com.chuangyou.xianni.skill.cmd;

import java.util.Map;
import java.util.Map.Entry;
import com.chuangyou.common.protobuf.pb.skill.SkillListMsgProto.SkillListMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.constant.SkillConstant.SkillMainType;
import com.chuangyou.xianni.entity.hero.HeroSkill;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.skill.manager.SkillManager;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_HERO_ONKEYUPSKILL, desc = "一键升级主动技能")
public class OneKeyUpHeroSkillCmd extends AbstractCommand {
	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {

		boolean res = SkillManager.oneKeyUpSkill(player);
		if (res) {
			SkillListMsg.Builder okMsg = SkillListMsg.newBuilder();
			Map<Integer, HeroSkill> skills = player.getSkillInventory().getHeroSkill();
			for (Entry<Integer, HeroSkill> entry : skills.entrySet()) {
				int type = entry.getValue().getType();
				if (type == SkillMainType.ACTIVE || type == SkillMainType.COMMON_ATTACK)
					okMsg.addSkillId(entry.getValue().getSkillId());
			}
			PBMessage p = MessageUtil.buildMessage(Protocol.U_HERO_SKILLLISTOK, okMsg);
			player.sendPbMessage(p);
		}
	}
}
