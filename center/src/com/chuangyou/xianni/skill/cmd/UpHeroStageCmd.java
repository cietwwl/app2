package com.chuangyou.xianni.skill.cmd;

import com.chuangyou.common.protobuf.pb.skill.SkillTotalProResMsgProto.SkillTotalProResMsg;
import com.chuangyou.common.protobuf.pb.skill.UpHeroStageReqMsgProto.UpHeroStageReqMsg;
import com.chuangyou.common.protobuf.pb.skill.UpHeroStageResMsgProto.UpHeroStageResMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.entity.property.SkillBaseProperty;
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
		boolean res = SkillManager.UpSkillStage(player, stage, packet);
		if (res) {
			// UpHeroStageResMsg.Builder okMsg = UpHeroStageResMsg.newBuilder();
			// okMsg.setStage(stage);
			// PBMessage p = MessageUtil.buildMessage(Protocol.U_HERO_UPHEROSTAGECMD, okMsg);
			// player.sendPbMessage(p);
			SkillBaseProperty res1 = SkillManager.getTotalPro(player);
			SkillTotalProResMsg.Builder okMsg = SkillManager.getSkillTotalProResMsg(player, res1);
			PBMessage p = MessageUtil.buildMessage(Protocol.U_HERO_GETSKILLTOLPRO, okMsg);
			player.sendPbMessage(p);
		}
	}
}
