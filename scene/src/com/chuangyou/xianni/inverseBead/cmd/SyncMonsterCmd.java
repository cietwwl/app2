package com.chuangyou.xianni.inverseBead.cmd;

import java.util.List;

import com.chuangyou.common.protobuf.pb.inverseBead.SyncMonsterPoolMsgProto.SyncMonsterPoolMsg;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;

@Cmd(code = Protocol.S_CREATE_INVERSE_SYNC_MONSTER, desc = "同步怪物")
public class SyncMonsterCmd extends AbstractCommand {
	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		SyncMonsterPoolMsg msg = SyncMonsterPoolMsg.parseFrom(packet.getBytes());
		List<Integer> list = msg.getMonsterRefreshIdList();
		army.getPlayer().reSetMonsterRefreshIdList(list);
	}

}
