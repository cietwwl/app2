package com.chuangyou.xianni.pet.cmd;

import com.chuangyou.common.protobuf.pb.pet.PetSkillOpenReqProto.PetSkillOpenReqMsg;
import com.chuangyou.common.protobuf.pb.pet.PetSkillOpenRespProto.PetSkillOpenRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.pet.PetSkill;
import com.chuangyou.xianni.entity.pet.PetSkillInfoCfg;
import com.chuangyou.xianni.pet.manager.PetAttManager;
import com.chuangyou.xianni.pet.template.PetTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_PET_SKILL_OPEN, desc = "宠物技能解封")
public class PetSkillOpenCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		PetSkillOpenReqMsg req = PetSkillOpenReqMsg.parseFrom(packet.getBytes());
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
		if (skill.getState()!=2) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Pet_Skill_Opened, packet.getCode());
			return;
		}
		//检测消耗
		int needPropNum = 1;
		//扣除消耗
		if(player.getBagInventory().getPlayerBagItemCount(info.getItem()) < needPropNum){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Prop_Is_Not_Enougth, packet.getCode());
			return;
		}
		//扣物品
		if(!player.getBagInventory().removeItemFromPlayerBag(info.getItem(), needPropNum, ItemRemoveType.USE)) return;
		//解封
		skill.setState(3);
		player.getPetInventory().updatePetSkill(skill);
		//更新前后台
		PetAttManager.updateSkill(player, skill);
		
		PetSkillOpenRespMsg.Builder msg = PetSkillOpenRespMsg.newBuilder();
		PBMessage p = MessageUtil.buildMessage(Protocol.U_PET_SKILL_OPEN, msg);
		player.sendPbMessage(p);
	}

}
