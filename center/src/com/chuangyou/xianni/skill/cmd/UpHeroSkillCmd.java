package com.chuangyou.xianni.skill.cmd;

import java.util.Map;
import java.util.Map.Entry;
import com.chuangyou.common.protobuf.pb.skill.SkillListMsgProto.SkillListMsg;
import com.chuangyou.common.protobuf.pb.skill.UpSkillMsgProto.UpSkillMsg;
import com.chuangyou.common.protobuf.pb.skill.UpSkillOKMsgProto.UpSkillOKMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.constant.SkillConstant.SkillMainType;
import com.chuangyou.xianni.entity.hero.HeroSkill;
import com.chuangyou.xianni.entity.skill.SkillTempateInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.skill.manager.SkillManager;
import com.chuangyou.xianni.skill.template.SkillTempMgr;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_HERO_UPSKILL, desc = "技能升级")
public class UpHeroSkillCmd extends AbstractCommand {
	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		UpSkillMsg msg = UpSkillMsg.parseFrom(packet.getBytes());
		int skillId = msg.getSkillId();
		boolean res = SkillManager.upSkill1(player, skillId);
		SkillTempateInfo skillInfo = SkillTempMgr.getSkillTemp(skillId);// 技能配置
		if (res) {
			if (skillInfo.getMasterType() == SkillMainType.ACTIVE || skillInfo.getMasterType() == SkillMainType.COMMON_ATTACK) {
				SkillListMsg.Builder okMsg = SkillListMsg.newBuilder();
				Map<Integer, HeroSkill> skills = player.getSkillInventory().getHeroSkill();
				for (Entry<Integer, HeroSkill> entry : skills.entrySet()) {
					int type = entry.getValue().getType();
					if (type == SkillMainType.ACTIVE || type == SkillMainType.COMMON_ATTACK) {
						okMsg.addSkillId(entry.getValue().getSkillId());
					}
				}
				PBMessage p = MessageUtil.buildMessage(Protocol.U_HERO_SKILLLISTOK, okMsg);
				player.sendPbMessage(p);
			} else if (skillInfo.getMasterType() == SkillMainType.PASSIVE) {
				UpSkillOKMsg.Builder okMsg = UpSkillOKMsg.newBuilder();
				okMsg.setSkillId(skillInfo.getNextTempId());
				PBMessage p = MessageUtil.buildMessage(Protocol.U_HERO_UpSkillOK, okMsg);
				player.sendPbMessage(p);
			}
		}
	}
}
