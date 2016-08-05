package com.chuangyou.xianni.inverseBead.cmd;

import com.chuangyou.common.protobuf.pb.inverseBead.ReqReceiveAuraMsgProto.ReqReceiveAuraMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;

public class ReceiveAuraCmd extends AbstractCommand {
	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		ReqReceiveAuraMsg msg = ReqReceiveAuraMsg.parseFrom(packet.getBytes());
		
		
	}
}
