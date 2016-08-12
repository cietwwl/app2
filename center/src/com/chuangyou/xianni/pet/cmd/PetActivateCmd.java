package com.chuangyou.xianni.pet.cmd;

import com.chuangyou.common.protobuf.pb.pet.PetActivateReqProto.PetActivateReqMsg;
import com.chuangyou.common.protobuf.pb.pet.PetActivateRespProto.PetActivateRespMsg;
import com.chuangyou.common.protobuf.pb.pet.PetInfoBeanProto.PetInfoBeanMsg;
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

@Cmd(code = Protocol.C_PET_ACTIVATE, desc = "宠物激活")
public class PetActivateCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		PetActivateReqMsg req = PetActivateReqMsg.parseFrom(packet.getBytes());
		PetInfo pet = player.getPetInventory().getPetInfo(req.getPetId());
		
		if(pet != null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Pet_Open_Already, packet.getCode());
			return;
		}
		
		PetInfoCfg petCfg = PetTemplateMgr.getPetTemps().get(req.getPetId());
		
		if(petCfg == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Pet_UnExist, packet.getCode());
			return;
		}
		
		int needItem = petCfg.getNeedItem();
		
		//扣物品
		if(player.getBagInventory().getPlayerBagItemCount(needItem) < 1){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Prop_Is_Not_Enougth, packet.getCode());
			return;
		}
		if(!player.getBagInventory().removeItemFromPlayerBag(needItem, 1, ItemRemoveType.USE)) return;
		
		pet = new PetInfo(player.getPlayerId(), req.getPetId());
		player.getPetInventory().addPetInfo(pet);
		
		PetActivateRespMsg.Builder msg = PetActivateRespMsg.newBuilder();
		
		PetInfoBeanMsg.Builder bean = PetInfoBeanMsg.newBuilder();
		bean.setPetId(pet.getPetId());
		bean.setTalent(pet.getTalent());
		bean.setLevel(pet.getLevel());
		bean.setLevelExp(pet.getLevelExp());
		bean.setPhysique(pet.getPhysique());
		bean.setQuality(pet.getQuality());
		bean.setQualityBless(pet.getQualityBless());
		bean.setGrade(pet.getGrade());
		bean.setGradeBless(pet.getGradeBless());
		msg.setPetInfo(bean);
		PBMessage p = MessageUtil.buildMessage(Protocol.U_PET_ACTIVATE, msg);
		player.sendPbMessage(p);
		
		//宠物总属性改变
//		PetManager.changePetAtt(roleId);
		//影响人物属性改变
		player.getPetInventory().updataProperty();
	}

}
