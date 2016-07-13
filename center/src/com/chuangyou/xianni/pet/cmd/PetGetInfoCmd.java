package com.chuangyou.xianni.pet.cmd;

import java.util.Map;

import com.chuangyou.common.protobuf.pb.pet.PetGetInfoRespProto.PetGetInfoRespMsg;
import com.chuangyou.common.protobuf.pb.pet.PetInfoBeanProto.PetInfoBeanMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.entity.pet.PetAtt;
import com.chuangyou.xianni.entity.pet.PetInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_PET_GETINFO, desc = "获取宠物信息")
public class PetGetInfoCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		PetAtt petAtt = player.getPetInventory().getPetAtt();
		Map<Integer, PetInfo> rolePetMap = player.getPetInventory().getPetInfoMap();
		
		PetGetInfoRespMsg.Builder msg = PetGetInfoRespMsg.newBuilder();
		msg.setFightPetId(petAtt.getFightPetId());
		msg.setSoulLevel(petAtt.getSoulLv());
		msg.setSoulExp(petAtt.getSoulExp());
		msg.setGridNum((byte)petAtt.getSkillSlotNum());
		for(PetInfo pet:rolePetMap.values()){
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
			msg.addPetInfo(bean);
		}
		PBMessage p = MessageUtil.buildMessage(Protocol.U_PET_GETINFO, msg);
		player.sendPbMessage(p);
	}

}
