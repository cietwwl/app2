package com.chuangyou.xianni.pet.cmd;

import com.chuangyou.common.protobuf.pb.pet.PetGradeUpReqProto.PetGradeUpReqMsg;
import com.chuangyou.common.protobuf.pb.pet.PetGradeUpRespProto.PetGradeUpRespMsg;
import com.chuangyou.common.util.ThreadSafeRandom;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.pet.PetGradeCfg;
import com.chuangyou.xianni.entity.pet.PetInfo;
import com.chuangyou.xianni.entity.pet.PetInfoCfg;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.pet.template.PetTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.state.event.PetStateEvent;

@Cmd(code = Protocol.C_PET_GRADE_UP, desc = "宠物进阶")
public class PetGradeUpCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		PetGradeUpReqMsg req = PetGradeUpReqMsg.parseFrom(packet.getBytes());
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
		
		//判断目标阶数
		PetGradeCfg targetCfg = PetTemplateMgr.getGradeTemps().get(pet.getPetId() * 1000 + pet.getGrade() + 1);
		if(targetCfg == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Pet_Grade_Max, packet.getCode());
			return;
		}
		
		PetGradeCfg gradeCfg = PetTemplateMgr.getGradeTemps().get(pet.getPetId() * 1000 + pet.getGrade());
		
		//扣物品
		if(player.getBagInventory().getPlayerBagItemCount(gradeCfg.getUpgradeItem()) < gradeCfg.getUpgradeItemNum()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Prop_Is_Not_Enougth, packet.getCode());
			return;
		}
		if(!player.getBagInventory().removeItemFromPlayerBag(gradeCfg.getUpgradeItem(), gradeCfg.getUpgradeItemNum(), ItemRemoveType.PET_GRADEUP)) return;
		
		//升阶
		ThreadSafeRandom random = new ThreadSafeRandom();
		boolean isSuccess = false;
		if(pet.getGradeBless() >= gradeCfg.getBlessValve()){
			if(pet.getGradeBless() >= gradeCfg.getBlessMax()){
				isSuccess = true;
			}else{
				int rate = gradeCfg.getRate();
				isSuccess = random.isSuccessful(rate, 10000);
			}
		}
		if(isSuccess){
			pet.setGrade(pet.getGrade() + 1);
			pet.setGradeBless(0);
		}else{
			int addBless = random.next(gradeCfg.getFailBlessMin(), gradeCfg.getFailBlessMax());
			pet.setGradeBless(pet.getGradeBless() + addBless);
			if(pet.getGradeBless() >= gradeCfg.getBlessMax()){
				pet.setGrade(pet.getGrade() + 1);
				pet.setGradeBless(0);
				isSuccess = true;
			}
		}
		player.getPetInventory().updatePetInfo(pet);
		
		PetGradeUpRespMsg.Builder msg = PetGradeUpRespMsg.newBuilder();
		msg.setPetId(pet.getPetId());
		msg.setGrade(pet.getGrade());
		msg.setGradeBless(pet.getGradeBless());
		PBMessage p = MessageUtil.buildMessage(Protocol.U_PET_GRADE_UP, msg);
		player.sendPbMessage(p);
		
		if(isSuccess){
			//宠物总属性改变
//			PetManager.changePetAtt(roleId);
			//影响人物属性改变
			player.getPetInventory().updataProperty();
			player.notifyListeners(new PetStateEvent(this, 8, pet.getPetId(), pet.getGrade(), EventNameType.PET));
		}
	}

}
