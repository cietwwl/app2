package com.chuangyou.xianni.soul.logic;

import java.util.Date;

import com.chuangyou.common.protobuf.pb.soul.FuseSkillProto.FuseSkillMsg;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.soul.SoulInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.soul.logic.calc.SoulCalcAddLogic;
import com.chuangyou.xianni.soul.template.SoulTemplateMgr;

/**
 * 检测魂幡技能是否过期
 * 
 * @author laofan
 *
 */
public class CheckFuseSkillLogic {

	private void clearSkill(int index, GamePlayer player) {
		// 同步到scene服务器
		FuseSkillMsg.Builder msg = FuseSkillMsg.newBuilder();
		msg.setIndex(index);
		msg.setColor(0);
		msg.setFuseSkillId(0);

		PBMessage pkg = MessageUtil.buildMessage(Protocol.S_REQ_SOUL_FUSESKILL_UPDATE, msg);
		player.sendPbMessage(pkg);
	}

	public void checkFuseSkillAndSync(GamePlayer player) {
		SoulInfo soulInfo = player.getSoulInventory().getSoulInfo();
		SoulCalcAddLogic logic = new SoulCalcAddLogic();
		Date now = new Date();
		if (soulInfo.getFuseSkillId1() > 0 && logic.isExpire(soulInfo.getFuseSkillCreateTime1(), SoulTemplateMgr.MAX_SOUL_SKILL_CD)) {
			soulInfo.setFuseSkillId1(0);
			soulInfo.setFuseSkillColor1(0);
			soulInfo.setFuseSkillCreateTime1(now);
			soulInfo.setOp(Option.Update);
			// 同步到scene服务器
			clearSkill(1,player);
		}
		if (soulInfo.getFuseSkillId2() > 0 && logic.isExpire(soulInfo.getFuseSkillCreateTime2(), SoulTemplateMgr.MAX_SOUL_SKILL_CD)) {
			soulInfo.setFuseSkillId2(0);
			soulInfo.setFuseSkillColor2(0);
			soulInfo.setFuseSkillCreateTime2(now);
			soulInfo.setOp(Option.Update);
			// 同步到scene服务器
			clearSkill(2,player);
		}
		if (soulInfo.getFuseSkillId3() > 0 && logic.isExpire(soulInfo.getFuseSkillCreateTime3(), SoulTemplateMgr.MAX_SOUL_SKILL_CD)) {
			soulInfo.setFuseSkillId3(0);
			soulInfo.setFuseSkillColor3(0);
			soulInfo.setFuseSkillCreateTime3(now);
			soulInfo.setOp(Option.Update);
			// 同步到scene服务器
			clearSkill(3,player);
		}
		if (soulInfo.getFuseSkillId4() > 0 && logic.isExpire(soulInfo.getFuseSkillCreateTime4(), SoulTemplateMgr.MAX_SOUL_SKILL_CD)) {
			soulInfo.setFuseSkillId4(0);
			soulInfo.setFuseSkillColor4(0);
			soulInfo.setFuseSkillCreateTime4(now);
			soulInfo.setOp(Option.Update);
			// 同步到scene服务器
			clearSkill(4,player);
		}

	}
}
