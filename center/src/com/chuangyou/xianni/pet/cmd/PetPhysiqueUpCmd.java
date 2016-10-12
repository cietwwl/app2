package com.chuangyou.xianni.pet.cmd;

import com.chuangyou.common.protobuf.pb.pet.PetPhysiqueUpReqProto.PetPhysiqueUpReqMsg;
import com.chuangyou.common.protobuf.pb.pet.PetPhysiqueUpRespProto.PetPhysiqueUpRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.pet.PetInfo;
import com.chuangyou.xianni.entity.pet.PetInfoCfg;
import com.chuangyou.xianni.entity.pet.PetPhysiqueCfg;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.pet.template.PetTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.player.PlayerInfoSendCmd;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.state.event.PetStateEvent;

@Cmd(code = Protocol.C_PET_PHYSIQUE_UP, desc = "宠物炼体")
public class PetPhysiqueUpCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		
		PetPhysiqueUpReqMsg req = PetPhysiqueUpReqMsg.parseFrom(packet.getBytes());
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
		
		PetPhysiqueCfg targetPhy = PetTemplateMgr.getPhysiqueTemps().get(pet.getPetId()*1000 + pet.getPhysique() + 1);
		
		if(targetPhy == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Pet_Physique_Max, packet.getCode());
			return;
		}
		
		PetPhysiqueCfg phyCfg = PetTemplateMgr.getPhysiqueTemps().get(pet.getPetId()*1000 + pet.getPhysique());
		
		//扣物品
		if(player.getBagInventory().getPlayerBagItemCount(phyCfg.getNeedItem()) < phyCfg.getNeedNum()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Prop_Is_Not_Enougth, packet.getCode());
			return;
		}
		if(!player.getBagInventory().removeItemFromPlayerBag(phyCfg.getNeedItem(), phyCfg.getNeedNum(), ItemRemoveType.PET_PHYSIQUEUP)) return;
		
		pet.setPhysique(pet.getPhysique() + 1);
		player.getPetInventory().updatePetInfo(pet);
		
		PetPhysiqueUpRespMsg.Builder msg = PetPhysiqueUpRespMsg.newBuilder();
		msg.setPetId(pet.getPetId());
		msg.setPhysique(pet.getPhysique());
		PBMessage p = MessageUtil.buildMessage(Protocol.U_PET_PHYSIQUE_UP, msg);
		player.sendPbMessage(p);
		
		//宠物总属性改变
//		PetManager.changePetAtt(roleId);
		//影响人物属性改变
		player.getPetInventory().updataProperty();
		player.notifyListeners(new PetStateEvent(this, 1, pet.getPetId(),pet.getPhysique(), EventNameType.PET));
		//宠物外形广播
		if(pet.getPetId() == player.getPetInventory().getPetAtt().getFightPetId()){
			PBMessage petSnapMsg = MessageUtil.buildMessage(Protocol.S_PET_INFO_UPDATE, PlayerInfoSendCmd.getPetInfoPacket(player));
			player.sendPbMessage(petSnapMsg);
		}
	}

}
