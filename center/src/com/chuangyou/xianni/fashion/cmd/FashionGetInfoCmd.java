package com.chuangyou.xianni.fashion.cmd;

import java.util.Map;

import com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.FashionBeanMsg;
import com.chuangyou.common.protobuf.pb.fashion.FashionGetRespProto.FashionGetRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.entity.fashion.FashionInfo;
import com.chuangyou.xianni.fashion.manager.FashionManager;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_FASHION_GET, desc = "获取时装信息")
public class FashionGetInfoCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		Map<Integer, FashionInfo> fashionMap = player.getFashionInventory().getFashionMap();
		
		FashionGetRespMsg.Builder msg = FashionGetRespMsg.newBuilder();
    	for (FashionInfo fashion : fashionMap.values()) {
    		FashionBeanMsg.Builder bean = FashionManager.toBean(fashion);
    		msg.addFashion(bean);
		}
    	PBMessage p = MessageUtil.buildMessage(Protocol.U_FASHION_GET, msg);
    	player.sendPbMessage(p);
	}

}
