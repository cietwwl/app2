package com.chuangyou.xianni.battle.cmd;

import com.chuangyou.common.protobuf.pb.magicwp.MagicwpBanGetInfoRespProto.MagicwpBanGetInfoRespMsg;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;

@Cmd(code = Protocol.S_MAGICWP_DATA_UPDATE, desc = "法定战斗相关数据更新")
public class SyncMagicwpDataCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		MagicwpBanGetInfoRespMsg req = MagicwpBanGetInfoRespMsg.parseFrom(packet.getBytes());
		army.getPlayer().loadMagicwpData(req);
	}

}
