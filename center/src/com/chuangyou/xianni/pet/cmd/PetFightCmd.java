package com.chuangyou.xianni.pet.cmd;

import com.chuangyou.common.protobuf.pb.pet.PetFightReqProto.PetFightReqMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_PET_FIGHT, desc = "宠物出战")
public class PetFightCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		PetFightReqMsg req = PetFightReqMsg.parseFrom(packet.getBytes());
		int petId = req.getPetId();
		
		if(player.getPetInventory() != null){
			player.getPetInventory().petFight(petId, true);
		}
	}

}
