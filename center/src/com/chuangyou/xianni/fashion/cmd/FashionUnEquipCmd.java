package com.chuangyou.xianni.fashion.cmd;

import java.util.Map;

import com.chuangyou.common.protobuf.pb.fashion.FashionUnEquipReqProto.FashionUnEquipReqMsg;
import com.chuangyou.common.protobuf.pb.fashion.FashionUnEquipRespProto.FashionUnEquipRespMsg;
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

@Cmd(code = Protocol.C_FASHION_UNEQUIP, desc = "时装脱下")
public class FashionUnEquipCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		FashionUnEquipReqMsg req = FashionUnEquipReqMsg.parseFrom(packet.getBytes());
		Map<Integer, FashionInfo> fashionMap = player.getFashionInventory().getFashionMap();
    	FashionInfo unEquipFashion = fashionMap.get(req.getFashionId());
    	if (unEquipFashion==null) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Fashion_Get_Fail, packet.getCode());
			return;
    	}
    	int unEquipFashionType = FashionManager.getFashionType(unEquipFashion);
		for (FashionInfo fashion : fashionMap.values()) {
			if (unEquipFashionType==FashionManager.getFashionType(fashion)) {//同类型时装
				if (fashion.isEquiped()) {
					fashion.setEquiped(false);
					//更新前后台
					FashionManager.updateFashion(fashion, player);
					
					//更新外形
					FashionManager.updateRoleEquipSkin(fashion, player);
				}
			}
		}
		
		FashionUnEquipRespMsg.Builder msg = FashionUnEquipRespMsg.newBuilder();
		PBMessage p = MessageUtil.buildMessage(Protocol.U_FASHION_UNEQUIP, msg);
		player.sendPbMessage(p);
	}

}
