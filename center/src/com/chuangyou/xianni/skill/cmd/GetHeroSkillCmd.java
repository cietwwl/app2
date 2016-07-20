package com.chuangyou.xianni.skill.cmd;

import java.util.Map;

import com.chuangyou.common.protobuf.pb.skill.ResSkillInfoMsgProto.ResSkillInfoMsg;
import com.chuangyou.common.protobuf.pb.skill.SkillListProto.SkillList;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.entity.hero.HeroSkill;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_HERO_GETSKILLINFO, desc = "获取英雄学习后的技能")
public class GetHeroSkillCmd extends AbstractCommand {
	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		Map<String, HeroSkill> heroSkill = player.getSkillInventory().getHeroSkill();
		ResSkillInfoMsg.Builder msg = ResSkillInfoMsg.newBuilder();
		for (HeroSkill skill : heroSkill.values()) {
			SkillList.Builder bean = SkillList.newBuilder();
			bean.setSkillId(skill.getSkillId());
			msg.addInfo(bean);
		}
		PBMessage p = MessageUtil.buildMessage(Protocol.U_HERO_GETSKILLINFO, msg);
		player.sendPbMessage(p);
	}
}
