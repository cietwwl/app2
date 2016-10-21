package com.chuangyou.xianni.shop.cmd;

import com.chuangyou.common.protobuf.pb.shop.GetMallInfoReqProto.GetMallInfoReqMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.constant.PlayerState;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.shop.logic.GetMaillInfoLogic;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code=Protocol.C_REQ_MALL_INFO,desc = "请求商城中的数据")
public class GetMallInfoReqCmd extends AbstractCommand {
	

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		if(player.getPlayerState() == PlayerState.OFFLINE)return;	
		if(player.getShopInventory() == null)return;
		
		GetMallInfoReqMsg req = GetMallInfoReqMsg.parseFrom(packet.getBytes());
		int type = req.getRequestType();
		new GetMaillInfoLogic().process(player, type);
	}
	
	
	
}
