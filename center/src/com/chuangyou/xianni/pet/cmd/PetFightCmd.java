package com.chuangyou.xianni.pet.cmd;

import com.chuangyou.common.protobuf.pb.pet.PetFightReqProto.PetFightReqMsg;
import com.chuangyou.common.protobuf.pb.pet.PetFightRespProto.PetFightRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.pet.PetAtt;
import com.chuangyou.xianni.entity.pet.PetInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.player.PlayerInfoSendCmd;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_PET_FIGHT, desc = "宠物出战")
public class PetFightCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		PetFightReqMsg req = PetFightReqMsg.parseFrom(packet.getBytes());
		PetInfo pet = player.getPetInventory().getPetInfo(req.getPetId());
		
		if(pet == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Pet_UnGet, packet.getCode());
			return;
		}
		
		PetAtt petAtt = player.getPetInventory().getPetAtt();
		
		//请求出战的宠物已经是当前出战宠物则不处理
		if(petAtt.getFightPetId() == req.getPetId()) return;
		
		petAtt.setFightPetId(req.getPetId());
		player.getPetInventory().updatePetAtt(petAtt);
		
		PetFightRespMsg.Builder msg = PetFightRespMsg.newBuilder();
		msg.setFightPetId(petAtt.getFightPetId());
		PBMessage p = MessageUtil.buildMessage(Protocol.U_PET_FIGHT, msg);
		player.sendPbMessage(p);
		
		//同步玩家场景中的宠物更换
		PBMessage petSnapMsg = MessageUtil.buildMessage(Protocol.S_PET_INFO_UPDATE, PlayerInfoSendCmd.getPetInfoPacket(player));
		player.sendPbMessage(petSnapMsg);
	}

}
