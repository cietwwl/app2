package com.chuangyou.xianni.fashion.cmd;

import com.chuangyou.common.protobuf.pb.fashion.FashionActivateReqProto.FashionActivateReqMsg;
import com.chuangyou.common.protobuf.pb.fashion.FashionActivateRespProto.FashionActivateRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.fashion.manager.FashionManager;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_FASHION_ACTIVATE, desc = "时装激活")
public class FashionActivateCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		FashionActivateReqMsg req = FashionActivateReqMsg.parseFrom(packet.getBytes());
		int fashionId = req.getFashionId();
    	//进阶
    	if(!FashionManager.activateFashion(fashionId, player, packet.getCode())){
    		return;
    	}
    	//返回
    	FashionActivateRespMsg.Builder msg = FashionActivateRespMsg.newBuilder();
		PBMessage p = MessageUtil.buildMessage(Protocol.U_FASHION_ACTIVATE, msg);
		player.sendPbMessage(p);
	}

}
