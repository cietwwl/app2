package com.chuangyou.xianni.battle.cmd;

import com.chuangyou.common.protobuf.pb.player.PlayerSomeThingUpdateProto.PlayerSomeThingUpdateMsg;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;

@Deprecated
@Cmd(code = Protocol.S_RE_WRITE_AVATAR_ENERGY, desc = "center重写仙气值")
public class AvatarEnergyReWriteCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		PlayerSomeThingUpdateMsg msg = PlayerSomeThingUpdateMsg.parseFrom(packet.getBytes());
		army.getPlayer().setAvatarEnergy(msg.getCount());
	}

}
