package com.chuangyou.xianni.pet.cmd;

import com.chuangyou.common.protobuf.pb.pet.PetSoulUpReqProto.PetSoulUpReqMsg;
import com.chuangyou.common.protobuf.pb.pet.PetSoulUpRespProto.PetSoulUpRespMsg;
import com.chuangyou.common.util.ThreadSafeRandom;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.item.BindType;
import com.chuangyou.xianni.entity.pet.PetAtt;
import com.chuangyou.xianni.entity.pet.PetSoulCfg;
import com.chuangyou.xianni.pet.manager.PetManager;
import com.chuangyou.xianni.pet.template.PetTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.player.PlayerInfoSendCmd;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_PET_SOUL_UP, desc = "宠物炼魂")
public class PetSoulUpCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		PetSoulUpReqMsg req = PetSoulUpReqMsg.parseFrom(packet.getBytes());
		boolean isToLvup = req.getType()==1;//是否一键升级，是则在道具不足时消耗等价元宝
		
		PetAtt petAtt = player.getPetInventory().getPetAtt();
		
		PetSoulCfg nextSoulCfg = PetTemplateMgr.getSoulTemps().get(petAtt.getSoulLv() + 1);
		if (nextSoulCfg == null) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Pet_Soul_Max, packet.getCode());
			return;
		}
		PetSoulCfg petSoul = PetTemplateMgr.getSoulTemps().get(petAtt.getSoulLv());
		//检测消耗
		int needNum = 1;//所需道具数
		int moneyNum = 0;//所需元宝数
		if(player.getBagInventory().getPlayerBagItemCount(petSoul.getNeedItem()) < needNum){
			if (!isToLvup) {
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Prop_Is_Not_Enougth, packet.getCode());
				return;
			}
			//道具不足，一键升级则消耗元宝
        	needNum = 0;
        	moneyNum = 10;//TODO 需读取商城
        	
            long money = player.getBasePlayer().getPlayerInfo().getCash();
            if (money<moneyNum) {
            	ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Money_UnEnough, packet.getCode());
				return;
            }
		}
        //增加经验
        int addExp = 0;
        boolean isSuccess = (new ThreadSafeRandom()).isSuccessful(petSoul.getCriticalPer(), 10000);
        if (isSuccess == true) {
        	addExp = petSoul.getCriticalExp();
        } else {
        	addExp = petSoul.getNormalExp();
        }
        //是否升级
        boolean hasUpLevel = false;
        petAtt.setSoulExp(petAtt.getSoulExp()+addExp);
        if (petAtt.getSoulExp()>=petSoul.getExp()) {
        	petAtt.setSoulLv(petAtt.getSoulLv()+1);
        	petAtt.setSoulExp(0);
        	hasUpLevel = true;
        }
        player.getPetInventory().updatePetAtt(petAtt);
    	//扣除消耗
        if (needNum>0) {
        	if(player.getBagInventory().getPlayerBagItemCount(petSoul.getNeedItem()) < needNum){
        		ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Prop_Is_Not_Enougth, packet.getCode(), "道具不足");
        		return;
        	}
    		if(!player.getBagInventory().removeItemFromPlayerBag(petSoul.getNeedItem(), needNum, BindType.ALL)) return;
        }
        if (moneyNum>0) {
        	if(player.getBasePlayer().getPlayerInfo().getCash() < moneyNum){
        		ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Money_UnEnough, packet.getCode(), "仙玉不足");
        		return;
        	}
        	if(!player.getBasePlayer().consumeCash(moneyNum)) return;
        }
		//回复
		PetSoulUpRespMsg.Builder msg = PetSoulUpRespMsg.newBuilder();
		msg.setSoulLevel(petAtt.getSoulLv());
		msg.setSoulExp(petAtt.getSoulExp());
		PBMessage p = MessageUtil.buildMessage(Protocol.U_PET_SOUL_UP, msg);
		player.sendPbMessage(p);
		
		if(hasUpLevel){
//			//宠物总属性改变
//			PetManager.changePetAtt(roleId);
			//影响人物属性改变
			player.getPetInventory().updataProperty();
			
			//宠物外形广播
			PBMessage petSnapMsg = MessageUtil.buildMessage(Protocol.S_PET_INFO_UPDATE, PlayerInfoSendCmd.getPetInfoPacket(player));
			player.sendPbMessage(petSnapMsg);
		}
	}

}
