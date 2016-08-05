package com.chuangyou.xianni.inverseBead.cmd;

import java.util.List;

import com.chuangyou.common.protobuf.pb.inverseBead.SyncMonsterPoolMsgProto.SyncMonsterPoolMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.inverseBead.manager.InverseBeadManager;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_INVERSE_MONSTER_SPAWN, desc = "同步怪物")
public class SyncMonsterCmd extends AbstractCommand {
	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		SyncMonsterPoolMsg msg = SyncMonsterPoolMsg.parseFrom(packet.getBytes());
		List<Integer> list = msg.getMonsterRefreshIdList();
		InverseBeadManager.moveMonster(player, list);
	}

}
