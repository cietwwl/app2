package com.chuangyou.xianni.battle.cmd;

import com.chuangyou.common.protobuf.pb.soul.SoulInfoProto.SoulInfoMsg;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;

@Cmd(code=Protocol.S_REQ_SOUL_EXP,desc="同步魂幡数")
public class SoulExpUpdateCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		SoulInfoMsg req = SoulInfoMsg.parseFrom(packet.getBytes());
		long exp = req.getExp();
		army.getPlayer().updateProperty(EnumAttr.SOUL_EXP, exp);
	}

}
