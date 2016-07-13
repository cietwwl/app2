package com.chuangyou.xianni.pet.cmd;

import com.chuangyou.common.protobuf.pb.pet.PetSkillActivateReqProto.PetSkillActivateReqMsg;
import com.chuangyou.common.protobuf.pb.pet.PetSkillActivateRespProto.PetSkillActivateRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.item.BindType;
import com.chuangyou.xianni.entity.pet.PetInfo;
import com.chuangyou.xianni.entity.pet.PetSkill;
import com.chuangyou.xianni.entity.pet.PetSkillInfoCfg;
import com.chuangyou.xianni.pet.manager.PetAttManager;
import com.chuangyou.xianni.pet.template.PetTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_PET_SKILL_ACTIVATE, desc = "宠物技能激活")
public class PetSkillActivateCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		PetSkillActivateReqMsg req = PetSkillActivateReqMsg.parseFrom(packet.getBytes());
		int skillId = req.getSkillId();
		PetSkillInfoCfg skillInfoCfg = PetTemplateMgr.getSkillInfoTemps().get(skillId);
		if (skillInfoCfg==null) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Pet_Skill_NotFound, packet.getCode());
			return;
		}
		PetSkill skill = player.getPetInventory().getPetSkill(req.getSkillId());
		if (skill!=null) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Pet_Skill_Beactived, packet.getCode());
			return;
		}
		//检测消耗
		int needPropNum = 0;
		if (skillInfoCfg.getNeedjihuopet()>0) {
			PetInfo pet = player.getPetInventory().getPetInfo(skillInfoCfg.getNeedjihuopet());
			if (pet==null) {
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Pet_UnExist, packet.getCode());
				return;
			}
		}
		if (skillInfoCfg.getNeedjihuoitem()>0) {
			needPropNum = 1;
			//判断物品
			if(player.getBagInventory().getPlayerBagItemCount(skillInfoCfg.getNeedjihuoitem()) < needPropNum){
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Prop_Is_Not_Enougth, packet.getCode());
				return;
			}
			//扣物品
			if(!player.getBagInventory().removeItemFromPlayerBag(skillInfoCfg.getNeedjihuoitem(), needPropNum, BindType.ALL)) return;
		}
		//激活
		skill = new PetSkill(player.getPlayerId(), skillId);
		skill.setState(2);
		player.getPetInventory().addPetSkill(skill);
		//更新前后台
		PetAttManager.updateSkill(player, skill);
		
		PetSkillActivateRespMsg.Builder msg = PetSkillActivateRespMsg.newBuilder();
		PBMessage p = MessageUtil.buildMessage(Protocol.U_PET_SKILL_ACTIVATE, msg);
		player.sendPbMessage(p);
	}

}
