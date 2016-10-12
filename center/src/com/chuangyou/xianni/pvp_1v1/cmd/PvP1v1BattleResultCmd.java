package com.chuangyou.xianni.pvp_1v1.cmd;

import com.chuangyou.common.protobuf.pb.campaign.PvP1v1BattleResultProto.PvP1v1BattleResultMsg;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.pvp_1v1.PvP1v1Manager;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.socket.Command;
import com.chuangyou.xianni.word.WorldMgr;

import io.netty.channel.Channel;

@Cmd(code = Protocol.C_PVP_1V1_BATTLE_RESULT, desc = "1V1比赛结果")
public class PvP1v1BattleResultCmd implements Command {

	@Override
	public void execute(Channel channel, PBMessage packet) throws Exception {
		PvP1v1BattleResultMsg msg = PvP1v1BattleResultMsg.parseFrom(packet.getBytes());
		// 红方
		GamePlayer red = WorldMgr.getPlayer(msg.getRedId());
		// 蓝方
		GamePlayer blue = WorldMgr.getPlayer(msg.getBlue());
		// 平局
		if (msg.getResult() == 0) {
			PvP1v1Manager.settlement(red, 0);
			PvP1v1Manager.settlement(blue, 0);
		}
		// 红方胜
		if (msg.getResult() == 1) {
			PvP1v1Manager.settlement(red, 1);
			PvP1v1Manager.settlement(blue, -1);
		}
		// 蓝方胜
		if (msg.getResult() == -1) {
			PvP1v1Manager.settlement(red, -1);
			PvP1v1Manager.settlement(blue, 1);
		}
		// 比赛取消
		if (msg.getResult() == 2) {
			PvP1v1Manager.settlement(red, -1);
			PvP1v1Manager.settlement(blue, -1);
		}
	}

}
