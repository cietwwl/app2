package com.chuangyou.xianni.vip.cmd;

import com.chuangyou.common.protobuf.pb.vip.ReqReceiveMsgProto.ReqReceiveMsg;
import com.chuangyou.common.protobuf.pb.vip.ResReceiveMsgProto.ResReceiveMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.vip.manager.VipManager;

@Cmd(code = Protocol.C_VIP_RECEIVE, desc = "领取vip")
public class ReceiveVipCmd extends AbstractCommand {
	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		ReqReceiveMsg msg = ReqReceiveMsg.parseFrom(packet.getBytes());
		int type = msg.getType();
		int id = msg.getId();
 		boolean res = VipManager.buyPackage(player,type, id, packet.getCode());
		if (res == true) {
			ResReceiveMsg.Builder resMsg = ResReceiveMsg.newBuilder();
			PBMessage p = MessageUtil.buildMessage(Protocol.U_VIP_RECEIVE, resMsg);
			player.sendPbMessage(p);
		}
	}
}
