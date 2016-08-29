package com.chuangyou.xianni.pet.cmd;

import com.chuangyou.common.protobuf.pb.pet.PetSkillSlotUnlockReqProto.PetSkillSlotUnlockReqMsg;
import com.chuangyou.common.protobuf.pb.pet.PetSkillSlotUnlockRespProto.PetSkillSlotUnlockRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.pet.PetAtt;
import com.chuangyou.xianni.entity.pet.PetSkillSlotCfg;
import com.chuangyou.xianni.pet.template.PetTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_PET_SKILL_SLOT_UNLOCK, desc = "宠物技能槽解锁")
public class PetSkillSlotUnlockCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		PetSkillSlotUnlockReqMsg req = PetSkillSlotUnlockReqMsg.parseFrom(packet.getBytes());
		int targetNum = req.getTargetIndex();
		PetAtt petAtt = player.getPetInventory().getPetAtt();
		if (targetNum<=petAtt.getSkillSlotNum()) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Pet_Skill_EquipGrid_Unlocked, packet.getCode());
			return;
		}
		
		PetSkillSlotCfg targetSlotCfg = PetTemplateMgr.getSkillSlotTemps().get(targetNum);
		if (targetSlotCfg == null) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Pet_Skill_Invalid_EquipIndex, packet.getCode());
			return;
		}
		//检测消耗
		int needId = PetTemplateMgr.getSkillSlotTemps().get(1).getNeedItem();
		int needNum = 0;
		for (int i = petAtt.getSkillSlotNum(); i < targetNum; i++) {
			needNum += PetTemplateMgr.getSkillSlotTemps().get(i).getNeedItemNum();
		}
		
		//扣除消耗
		if(player.getBagInventory().getPlayerBagItemCount(needId) < needNum){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Prop_Is_Not_Enougth, packet.getCode());
			return;
		}
		//扣物品
		if(!player.getBagInventory().removeItemFromPlayerBag(needId, needNum, ItemRemoveType.PET_SKILLSLOT_UNLOCK)) return;
		//开锁
        petAtt.setSkillSlotNum(targetNum);
        player.getPetInventory().updatePetAtt(petAtt);
		
    	PetSkillSlotUnlockRespMsg.Builder msg = PetSkillSlotUnlockRespMsg.newBuilder();
    	msg.setSlotNum((byte)petAtt.getSkillSlotNum());
		PBMessage p = MessageUtil.buildMessage(Protocol.U_PET_SKILL_SLOT_UNLOCK, msg);
		player.sendPbMessage(p);
	}

}
