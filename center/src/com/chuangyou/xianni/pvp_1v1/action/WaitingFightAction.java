package com.chuangyou.xianni.pvp_1v1.action;

import com.chuangyou.common.protobuf.pb.campaign.Create1V1PvPCampaignProto.Create1V1PvPCampaignMsg;
import com.chuangyou.xianni.constant.PlayerState;
import com.chuangyou.xianni.exec.ActionQueue;
import com.chuangyou.xianni.exec.DelayAction;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.pvp_1v1.PvP1v1Manager;
import com.chuangyou.xianni.word.WorldMgr;

public class WaitingFightAction extends DelayAction {
	private long	red;
	private long	blue;

	public WaitingFightAction(ActionQueue queue, long red, long blue, int delay) {
		super(queue, delay);
		this.red = red;
		this.blue = blue;
	}

	@Override
	public void execute() {
		GamePlayer attacker = WorldMgr.getPlayer(red);
		GamePlayer defencer = WorldMgr.getPlayer(blue);

		if (attacker == null || attacker.getPlayerState() == PlayerState.OFFLINE) {
			PvP1v1Manager.settlement(defencer, 1);
			return;
		}
		if (defencer == null || defencer.getPlayerState() == PlayerState.OFFLINE) {
			PvP1v1Manager.settlement(attacker, 1);
			return;
		}
		// 发送创建1V1_PVP副本
		Create1V1PvPCampaignMsg.Builder builder = Create1V1PvPCampaignMsg.newBuilder();
		builder.setRedId(red);
		builder.setBlueId(blue);
		PBMessage message = MessageUtil.buildMessage(Protocol.S_CREATE_1V1_BATTLE, builder);
		attacker.sendPbMessage(message);
	}

}
