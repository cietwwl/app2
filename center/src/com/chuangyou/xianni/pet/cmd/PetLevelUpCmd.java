package com.chuangyou.xianni.pet.cmd;

import com.chuangyou.common.protobuf.pb.pet.PetLevelUpReqProto.PetLevelUpReqMsg;
import com.chuangyou.common.protobuf.pb.pet.PetLevelUpRespProto.PetLevelUpRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.pet.PetInfo;
import com.chuangyou.xianni.entity.pet.PetInfoCfg;
import com.chuangyou.xianni.entity.pet.PetLevelCfg;
import com.chuangyou.xianni.pet.manager.PetLevelManager;
import com.chuangyou.xianni.pet.manager.PetManager;
import com.chuangyou.xianni.pet.template.PetTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_PET_LEVEL_UP, desc = "宠物升级")
public class PetLevelUpCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		PetLevelUpReqMsg req = PetLevelUpReqMsg.parseFrom(packet.getBytes());
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
		
		if(pet.getLevel() >= player.getBasePlayer().getPlayerInfo().getLevel()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Pet_Level_More_RoleLevel, packet.getCode());
			return;
		}
		
		PetLevelCfg targetLevel = PetTemplateMgr.getLevelTemps().get(req.getPetId() * 1000 + pet.getLevel() + 1);
		
		if(targetLevel == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Pet_Level_Max, packet.getCode());
			return;
		}
		
		int oldLevel = pet.getLevel();
		if(req.getUseItem() == 0){
			if(!PetLevelManager.petUpLevelOneKey(player, pet, packet.getCode())) return;
		}else{
			if(!PetLevelManager.petUpLevelByItem(player, pet, req.getUseItem(), packet.getCode())) return;
		}
		
		PetLevelUpRespMsg.Builder msg = PetLevelUpRespMsg.newBuilder();
		msg.setPetId(pet.getPetId());
		msg.setLevel(pet.getLevel());
		msg.setExp(pet.getLevelExp());
		PBMessage p = MessageUtil.buildMessage(Protocol.U_PET_LEVEL_UP, msg);
		player.sendPbMessage(p);
		
		if(pet.getLevel() - oldLevel > 0){
			//宠物总属性改变
//			PetManager.changePetAtt(roleId);
			//影响人物属性改变
			player.getArmyInventory().getArmy().getHero().addPet(PetManager.computePetAtt(player));
			player.getArmyInventory().updateProperty();
		}
	}

}
