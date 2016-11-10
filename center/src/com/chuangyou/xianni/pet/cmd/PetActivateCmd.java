package com.chuangyou.xianni.pet.cmd;

import com.chuangyou.common.protobuf.pb.pet.PetActivateReqProto.PetActivateReqMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.pet.PetInfo;
import com.chuangyou.xianni.entity.pet.PetInfoCfg;
import com.chuangyou.xianni.pet.template.PetTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
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
		
		if(petCfg.getActivateType() == PetInfoCfg.ACTIVATE_BY_ITEM){
			int needItem = petCfg.getNeedItem();
			
			//扣物品
			if(player.getBagInventory().getPlayerBagItemCount(needItem) < 1){
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Prop_Is_Not_Enougth, packet.getCode());
				return;
			}
			if(!player.getBagInventory().removeItemFromPlayerBag(needItem, 1, ItemRemoveType.PET_ACTIVATE)) return;
		}else if(petCfg.getActivateType() == PetInfoCfg.ACTIVATE_BY_STATE){
			int needStateLv = petCfg.getJingjieLv();
			
			//判断玩家境界等级
			if(player.getBasePlayer().getPlayerInfo().getStateLv() < needStateLv){
				return;
			}
		}else{
			Log.error("宠物激活错误：tb_z_pet_info配置错误，petId = " + petCfg.getId());
			return;
		}
		
		player.getPetInventory().activePet(req.getPetId());
		
	}

}
