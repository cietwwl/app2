package com.chuangyou.xianni.pvp_1v1.cmd;

import com.chuangyou.common.protobuf.pb.pvp1v1.PvP1v1OptionProto.PvP1v1OptionMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.pvp_1v1.PvP1v1Manager;
import com.chuangyou.xianni.pvp_1v1.action.JoinOrChallengeAction;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_PVP_1V1_OPTION, desc = "1v1竞技操作")
public class PvP1v1OptionCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		PvP1v1OptionMsg msg = PvP1v1OptionMsg.parseFrom(packet.getBytes());
		// 请求开始匹配
		if (msg.getType() == 1) {
			JoinOrChallengeAction action = new JoinOrChallengeAction(player, PvP1v1Manager.getActionQueue());
			PvP1v1Manager.getActionQueue().enqueue(action);
		}
		// 查看排行榜
		if (msg.getType() == 2) {
			PvP1v1Manager.sendRankInfo(player, null, true);
			PvP1v1Manager.sendRankInfo_20(player);
		}
		// 取消匹配
		if (msg.getType() == 3) {
			PvP1v1Manager.cancel(player);
		}

		// 请求信息
		if (msg.getType() == 4) {
			PvP1v1Manager.getPvPRankInfo(player);
		}
	}

}