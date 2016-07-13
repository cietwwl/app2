package com.chuangyou.xianni.fashion.cmd;

import java.util.Map;

import com.chuangyou.common.protobuf.pb.fashion.FashionEquipReqProto.FashionEquipReqMsg;
import com.chuangyou.common.protobuf.pb.fashion.FashionEquipRespProto.FashionEquipRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.fashion.FashionInfo;
import com.chuangyou.xianni.fashion.manager.FashionManager;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_FASHION_EQUIP, desc = "时装穿上")
public class FashionEquipCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		FashionEquipReqMsg req = FashionEquipReqMsg.parseFrom(packet.getBytes());
    	Map<Integer, FashionInfo> fashionMap = player.getFashionInventory().getFashionMap();
    	FashionInfo equipFashion = fashionMap.get(req.getFashionId());
    	if (equipFashion==null) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Fashion_Get_Fail, packet.getCode());
			return;
    	}
    	int equipFashionType = FashionManager.getFashionType(equipFashion);
		for (FashionInfo fashion : fashionMap.values()) {
			if (equipFashionType==FashionManager.getFashionType(fashion)) {//同类型时装
				if (fashion.isEquiped()) {
					fashion.setEquiped(false);
					FashionManager.updateFashion(fashion, player);
				}
			}
		}
		
		equipFashion.setEquiped(true);
		//更新新后台
		FashionManager.updateFashion(equipFashion, player);
		
		//更新外形
		FashionManager.updateRoleEquipSkin(equipFashion, player);
		
		FashionEquipRespMsg.Builder msg = FashionEquipRespMsg.newBuilder();
		PBMessage p = MessageUtil.buildMessage(Protocol.U_FASHION_EQUIP, msg);
		player.sendPbMessage(p);
	}

}
