package com.chuangyou.xianni.skill.cmd;

import com.chuangyou.common.protobuf.pb.skill.SkillTotalProResMsgProto.SkillTotalProResMsg;
import com.chuangyou.common.protobuf.pb.skill.UpHeroStageReqMsgProto.UpHeroStageReqMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.entity.property.BaseProperty;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.skill.manager.SkillManager;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_HERO_UPHEROSTAGECMD, desc = "技能阶段升级")
public class UpHeroStageCmd extends AbstractCommand {
	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		UpHeroStageReqMsg msg = UpHeroStageReqMsg.parseFrom(packet.getBytes());
		int stage = msg.getStage();
		boolean res = SkillManager.upSkillStage(player, stage);
		if (res && player.getSkillInventory() != null) {
			// UpHeroStageResMsg.Builder okMsg = UpHeroStageResMsg.newBuilder();
			// okMsg.setStage(stage);
			// PBMessage p =
			// MessageUtil.buildMessage(Protocol.U_HERO_UPHEROSTAGECMD, okMsg);
			// player.sendPbMessage(p);
			BaseProperty skillData = new BaseProperty();
			BaseProperty skillPer = new BaseProperty();
			// 加入技能属性
			player.getSkillInventory().getTotalPro(skillData, skillPer);
			SkillTotalProResMsg.Builder proMsg = SkillManager.getSkillTotalProResMsg(player, skillData);
			PBMessage p = MessageUtil.buildMessage(Protocol.U_HERO_GETSKILLTOLPRO, proMsg);
			player.sendPbMessage(p);
		}
	}
}
