package com.chuangyou.xianni.player.cmd;

import com.chuangyou.common.protobuf.pb.player.PlayerManaUpdateProto.PlayerManaUpdateMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_PLAYER_MANA_WRITEBACK, desc = "灵力更新回写")
public class PlayerManaUpdateCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		PlayerManaUpdateMsg req = PlayerManaUpdateMsg.parseFrom(packet.getBytes());
		player.getBasePlayer().getPlayerJoinInfo().setMana(req.getMana());
	}

}
