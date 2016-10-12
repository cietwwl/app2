package com.chuangyou.xianni.warfield.cmd;

import com.chuangyou.common.protobuf.pb.army.HeroInfoMsgProto.HeroInfoMsg;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.objects.Player;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;
import com.chuangyou.xianni.world.WorldMgr;

@Cmd(code = Protocol.S_PLAYER_UPDATE, desc = "玩家数据更新")
public class UpdatePlayerCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		HeroInfoMsg msg = HeroInfoMsg.parseFrom(packet.getBytes());
		long playerId = msg.getPlayerId();
		ArmyProxy pArmy = WorldMgr.getArmy(playerId);
		if (pArmy != null) {
			Player player = pArmy.getPlayer();
			player.updateHeroInfo(msg);
		}
	}

}
