package com.chuangyou.xianni.pet.cmd;

import com.chuangyou.common.protobuf.pb.pet.PetTalentUpReqProto.PetTalentUpReqMsg;
import com.chuangyou.common.protobuf.pb.pet.PetTalentUpRespProto.PetTalentUpRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.pet.PetInfo;
import com.chuangyou.xianni.entity.pet.PetInfoCfg;
import com.chuangyou.xianni.pet.template.PetTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_PET_TALENT_UP, desc = "宠物资质提升")
public class PetTalentUpCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		PetTalentUpReqMsg req = PetTalentUpReqMsg.parseFrom(packet.getBytes());
		PetInfo pet = player.getPetInventory().getPetInfo(req.getPetId());
		if(pet == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Pet_UnGet, packet.getCode());
			return;
		}
		
		PetInfoCfg petCfg = PetTemplateMgr.getPetTemps().get(req.getPetId());
		if(petCfg == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Pet_UnExist, packet.getCode());
			return;
		}
		if(petCfg.getIsSpecial() > 0){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Pet_Special_CannotTrain, packet.getCode());
			return;
		}
		
		if(pet.getTalent() >= petCfg.getZizhiMax()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Pet_Talent_Max, packet.getCode());
			return;
		}
		
		if(req.getUseItem() != petCfg.getZizhiItem1() && req.getUseItem() != petCfg.getZizhiItem2()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Prop_Use_Error, packet.getCode());
			return;
		}
		
		//扣物品
		if(player.getBagInventory().getPlayerBagItemCount(req.getUseItem()) < 1){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Prop_Is_Not_Enougth, packet.getCode());
			return;
		}
		//扣道具
		if(!player.getBagInventory().removeItemFromPlayerBag(req.getUseItem(), 1, ItemRemoveType.USE)) return;
		
		pet.setTalent(pet.getTalent() + petCfg.getAddZizhi());
		player.getPetInventory().updatePetInfo(pet);
		
		PetTalentUpRespMsg.Builder msg = PetTalentUpRespMsg.newBuilder();
		msg.setPetId(pet.getPetId());
		msg.setTalent(pet.getTalent());
		PBMessage p = MessageUtil.buildMessage(Protocol.U_PET_TALENT_UP, msg);
		player.sendPbMessage(p);
		
		//宠物总属性改变
//		PetManager.changePetAtt(roleId);
		//影响人物属性改变
		player.getPetInventory().updataProperty();
	}

}
