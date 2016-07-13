package com.chuangyou.xianni.pet.cmd;

import com.chuangyou.common.protobuf.pb.pet.PetSkillUpReqProto.PetSkillUpReqMsg;
import com.chuangyou.common.protobuf.pb.pet.PetSkillUpRespProto.PetSkillUpRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.pet.PetSkill;
import com.chuangyou.xianni.entity.pet.PetSkillInfoCfg;
import com.chuangyou.xianni.entity.pet.PetSkillLevelCfg;
import com.chuangyou.xianni.pet.manager.PetAttManager;
import com.chuangyou.xianni.pet.template.PetTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_PET_SKILL_UP, desc = "宠物技能升级")
public class PetSkillUpCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		PetSkillUpReqMsg req = PetSkillUpReqMsg.parseFrom(packet.getBytes());
		int skillId = req.getSkillId();
		PetSkillInfoCfg info = PetTemplateMgr.getSkillInfoTemps().get(skillId);
		if (info==null) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Pet_Skill_NotFound, packet.getCode());
			return;
		}
		PetSkill skill = player.getPetInventory().getPetSkill(skillId);
		if (skill==null) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Pet_Skill_NotFound, packet.getCode());
			return;
		}
		if (skill.getLevel()>=PetAttManager.PET_SKILL_LV_MAX) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Pet_Skill_LvMax, packet.getCode());
			return;
		}
		//检测消耗
		int key = PetAttManager.getPetSkillKey(skill);
		PetSkillLevelCfg lvInfo = PetTemplateMgr.getSkillLevelTemps().get(key);
		int moneyNum = lvInfo.getLvupGold();

		if(player.getBasePlayer().getPlayerInfo().getMoney() < moneyNum){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Money_UnEnough, packet.getCode(), "灵石不足");
		}
        //扣除消耗
		if(!player.getBasePlayer().consumeMoney(moneyNum)) return;
		//升级
		skill.setLevel(skill.getLevel()+1);
		//更新前后台
		PetAttManager.updateSkill(player, skill);
		
		PetSkillUpRespMsg.Builder msg = PetSkillUpRespMsg.newBuilder();
		PBMessage p = MessageUtil.buildMessage(Protocol.U_PET_SKILL_UP, msg);
		player.sendPbMessage(p);
	}

}
