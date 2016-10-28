package com.chuangyou.xianni.avatar.cmd;

import com.chuangyou.common.protobuf.pb.player.PlayerSomeThingUpdateProto.PlayerSomeThingUpdateMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_AVATAR_ENERGY_COST, desc = "仙气回写")
public class AvatarEnergyCostCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		PlayerSomeThingUpdateMsg msg = PlayerSomeThingUpdateMsg.parseFrom(packet.getBytes());
		int count = msg.getCount();
		if (player.getAvatarInventory() != null) {
			player.getAvatarInventory().costAvatarEnergy(count);
		}
	}

}
