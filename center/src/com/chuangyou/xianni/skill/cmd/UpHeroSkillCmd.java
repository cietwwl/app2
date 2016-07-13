package com.chuangyou.xianni.skill.cmd;

import java.util.Map;
import java.util.Map.Entry;

import com.chuangyou.common.protobuf.pb.skill.SkillListMsgProto.SkillListMsg;
import com.chuangyou.common.protobuf.pb.skill.SkillTotalProResMsgProto.SkillTotalProResMsg;
import com.chuangyou.common.protobuf.pb.skill.UpSkillMsgProto.UpSkillMsg;
import com.chuangyou.common.protobuf.pb.skill.UpSkillOKMsgProto.UpSkillOKMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.entity.hero.HeroSkill;
import com.chuangyou.xianni.entity.property.SkillBaseProperty;
import com.chuangyou.xianni.entity.skill.SkillTempateInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.skill.SkillInventory;
import com.chuangyou.xianni.skill.manager.SkillManager;
import com.chuangyou.xianni.skill.template.SkillTempMgr;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_HERO_UPSKILL, desc = "技能升级")
public class UpHeroSkillCmd extends AbstractCommand {
	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		UpSkillMsg msg = UpSkillMsg.parseFrom(packet.getBytes());
		int skillId = msg.getSkillId();
		boolean res = SkillManager.upSkill(player, skillId, packet);
		if (res) {
			SkillTempateInfo skillInfo = SkillTempMgr.getSkillTemp(skillId);// 技能配置
			if (skillInfo.getMasterType() == SkillInventory.initiativeSkillType || skillInfo.getMasterType() == 3) {
				SkillListMsg.Builder okMsg = SkillListMsg.newBuilder();
				Map<String, HeroSkill> skills = player.getSkillInventory().getHeroSkill();
				for (Entry<String, HeroSkill> entry : skills.entrySet()) {
					int type = entry.getValue().getType();
					if (type == SkillInventory.initiativeSkillType || type == 3) {
						okMsg.addSkillId(entry.getValue().getSkillId());
//						System.out.println("entry.getValue().getSkillId() "+entry.getValue().getSkillId());
					}
				}
				PBMessage p = MessageUtil.buildMessage(Protocol.U_HERO_SKILLLISTOK, okMsg);
				player.sendPbMessage(p);
//				System.out.println(okMsg);
			} else if (skillInfo.getMasterType() == SkillInventory.passiveSkillType) {
				UpSkillOKMsg.Builder okMsg = UpSkillOKMsg.newBuilder();
				okMsg.setSkillId(skillId);
//				System.out.println("up ok skillId:" + skillId);
				PBMessage p = MessageUtil.buildMessage(Protocol.U_HERO_UpSkillOK, okMsg);
				player.sendPbMessage(p);
//				System.out.println(okMsg);
				
				
				SkillBaseProperty res1 = SkillManager.getTotalPro(player);
				SkillTotalProResMsg.Builder resMsg = SkillManager.getSkillTotalProResMsg(player, res1);
				PBMessage pbm = MessageUtil.buildMessage(Protocol.U_HERO_GETSKILLTOLPRO, resMsg);
				player.sendPbMessage(pbm);
			}
		}
	}
}
