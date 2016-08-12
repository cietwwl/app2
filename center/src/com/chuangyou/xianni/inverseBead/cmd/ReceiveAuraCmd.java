package com.chuangyou.xianni.inverseBead.cmd;

import com.chuangyou.common.protobuf.pb.inverseBead.ReqReceiveAuraMsgProto.ReqReceiveAuraMsg;
import com.chuangyou.common.protobuf.pb.inverseBead.ResReceiveAuraMsgProto.ResReceiveAuraMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.inverseBead.manager.InverseBeadManager;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_INVERSE_RECEIVE_AURA, desc = "请求领取灵气液")
public class ReceiveAuraCmd extends AbstractCommand {
	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		ReqReceiveAuraMsg msg = ReqReceiveAuraMsg.parseFrom(packet.getBytes());
		int res = InverseBeadManager.receiveAura(player, packet.getCode());
		
		ResReceiveAuraMsg.Builder resMsg = ResReceiveAuraMsg.newBuilder();
		resMsg.setNum(res);
		PBMessage p = MessageUtil.buildMessage(Protocol.U_INVERSE_RECEIVE_AURA, resMsg);
		player.sendPbMessage(p);
		
	}
}
