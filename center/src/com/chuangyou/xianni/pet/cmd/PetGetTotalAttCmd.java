package com.chuangyou.xianni.pet.cmd;

import java.util.Map;

import com.chuangyou.common.protobuf.pb.AttributeBeanProto.AttributeBeanMsg;
import com.chuangyou.common.protobuf.pb.pet.PetGetTotalAttRespProto.PetGetTotalAttRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.pet.manager.PetManager;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_PET_GETTOTALATT, desc = "获取宠物总属性")
public class PetGetTotalAttCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		Map<Integer, Integer> attMap = PetManager.computePetAtt(player);
		
		PetGetTotalAttRespMsg.Builder msg = PetGetTotalAttRespMsg.newBuilder();
		
		for(int attType:attMap.keySet()){
			AttributeBeanMsg.Builder bean = AttributeBeanMsg.newBuilder();
			bean.setAttType(attType);
			bean.setAttValue(attMap.get(attType));
			msg.addAtt(bean);
		}
		PBMessage p = MessageUtil.buildMessage(Protocol.U_PET_GETTOTALATT, msg);
		player.sendPbMessage(p);
	}

}
