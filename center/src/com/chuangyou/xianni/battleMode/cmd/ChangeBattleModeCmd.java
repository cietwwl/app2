package com.chuangyou.xianni.battleMode.cmd;

import com.chuangyou.common.protobuf.pb.battle.ReqBattleModeMsgProto.ReqBattleModeMsg;
import com.chuangyou.common.protobuf.pb.battle.ResBattleModeMsgProto.ResBattleModeMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.battleMode.manager.BattleModeManager;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_BATTLE_MODE, desc = "变更战斗模式")
public class ChangeBattleModeCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		ReqBattleModeMsg req = ReqBattleModeMsg.parseFrom(packet.getBytes());
		int battleMode = req.getBattleMode();

		boolean result = BattleModeManager.changeBattleMode(player, battleMode, packet.getCode());
		if (result) {
			ResBattleModeMsg.Builder res = ResBattleModeMsg.newBuilder();
			res.setBattleMode(player.getBasePlayer().getPlayerInfo().getBattleMode());
			PBMessage p = MessageUtil.buildMessage(Protocol.U_BATTLE_MODE, res);
			player.sendPbMessage(p);
		}
	}

}
