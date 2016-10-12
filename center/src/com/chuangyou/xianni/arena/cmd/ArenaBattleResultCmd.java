package com.chuangyou.xianni.arena.cmd;

import com.chuangyou.common.protobuf.pb.arena.ArenaResultProto.ArenaResultMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.constant.CampaignConstant;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_ARENA_RESULT, desc = "竞技场比赛结果")
public class ArenaBattleResultCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		ArenaResultMsg msg = ArenaResultMsg.parseFrom(packet.getBytes());
		// 胜利
		if (msg.getResult() == CampaignConstant.ChallengeResult.WIN && player.getArenaInventory() != null) {
			player.getArenaInventory().updataWinCount(msg.getOpponent());
		}
	}

}
