package com.chuangyou.xianni.pet.cmd;

import com.chuangyou.common.protobuf.pb.pet.PetQualityUpReqProto.PetQualityUpReqMsg;
import com.chuangyou.common.protobuf.pb.pet.PetQualityUpRespProto.PetQualityUpRespMsg;
import com.chuangyou.common.util.ThreadSafeRandom;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.item.BindType;
import com.chuangyou.xianni.entity.pet.PetInfo;
import com.chuangyou.xianni.entity.pet.PetInfoCfg;
import com.chuangyou.xianni.entity.pet.PetQualityCfg;
import com.chuangyou.xianni.pet.manager.PetManager;
import com.chuangyou.xianni.pet.template.PetTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.player.PlayerInfoSendCmd;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_PET_QUALITY_UP, desc = "宠物品质提升")
public class PetQualityUpCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		PetQualityUpReqMsg req = PetQualityUpReqMsg.parseFrom(packet.getBytes());
		PetInfo pet = player.getPetInventory().getPetInfo(req.getPetId());
		
		if(pet == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Pet_UnGet, packet.getCode());
			return;
		}
		
		PetInfoCfg petInfoCfg = PetTemplateMgr.getPetTemps().get(req.getPetId());
		if(petInfoCfg.getIsSpecial() > 0){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Pet_Special_CannotTrain, packet.getCode());
			return;
		}
		
		PetQualityCfg targetCfg = PetTemplateMgr.getQualityTemps().get(pet.getPetId() * 1000 + pet.getQuality() + 1);
		if(targetCfg == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Pet_Quality_Max, packet.getCode());
			return;
		}
		
		PetQualityCfg qualityCfg = PetTemplateMgr.getQualityTemps().get(pet.getPetId() * 1000 + pet.getQuality());
		
		//扣物品
		if(player.getBagInventory().getPlayerBagItemCount(qualityCfg.getNeedItem()) < qualityCfg.getNeedNum()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Prop_Is_Not_Enougth, packet.getCode());
			return;
		}
		if(!player.getBagInventory().removeItemFromPlayerBag(qualityCfg.getNeedItem(), qualityCfg.getNeedNum(), BindType.ALL)) return;
		
		//是否升级品质
		boolean hasUpLevel = false;
		if(pet.getQualityBless() >= qualityCfg.getProMax()){
			pet.setQuality(pet.getQuality() + 1);
			pet.setQualityBless(0);
			hasUpLevel = true;
		}else{
			
			boolean isSuccess = (new ThreadSafeRandom()).isSuccessful(qualityCfg.getRate(), 10000);
			int resultBless = pet.getQualityBless();
			if(isSuccess){
				resultBless ++;
			}else{
				resultBless = resultBless - 1 <= 0 ? 0 : resultBless - 1;
			}
			pet.setQualityBless(resultBless);
		}
		player.getPetInventory().updatePetInfo(pet);
		
		PetQualityUpRespMsg.Builder msg = PetQualityUpRespMsg.newBuilder();
		msg.setPetId(pet.getPetId());
		msg.setQuality(pet.getQuality());
		msg.setQualityBless(pet.getQualityBless());
		PBMessage p = MessageUtil.buildMessage(Protocol.U_PET_QUALITY_UP, msg);
		player.sendPbMessage(p);
		
		if(hasUpLevel){
			//宠物总属性改变
//			PetManager.changePetAtt(roleId);
			//影响人物属性改变
			player.getPetInventory().updataProperty();
			
			//宠物外形广播
			if(pet.getPetId() == player.getPetInventory().getPetAtt().getFightPetId()){
				PBMessage petSnapMsg = MessageUtil.buildMessage(Protocol.S_PET_INFO_UPDATE, PlayerInfoSendCmd.getPetInfoPacket(player));
				player.sendPbMessage(petSnapMsg);
			}
		}
	}

}
