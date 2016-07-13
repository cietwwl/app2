package com.chuangyou.xianni.skill.cmd;

import com.chuangyou.common.protobuf.pb.skill.SkillTotalProResMsgProto.SkillTotalProResMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.entity.property.SkillBaseProperty;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.skill.manager.SkillManager;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_HERO_GETSKILLTOLPRO, desc = "获取英雄总属性")
public class GetHeroSkillTolProCmd extends AbstractCommand {
	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		SkillBaseProperty res = SkillManager.getTotalPro(player);
		SkillTotalProResMsg.Builder msg = SkillManager.getSkillTotalProResMsg(player,res);
		PBMessage p = MessageUtil.buildMessage(Protocol.U_HERO_GETSKILLTOLPRO, msg);
		player.sendPbMessage(p);
	}
}
