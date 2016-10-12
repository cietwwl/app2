package com.chuangyou.xianni.campaign.cmd;

import com.chuangyou.common.protobuf.pb.campaign.Create1V1PvPCampaignProto.Create1V1PvPCampaignMsg;
import com.chuangyou.common.protobuf.pb.campaign.PvP1v1BattleResultProto.PvP1v1BattleResultMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.campaign.Campaign;
import com.chuangyou.xianni.campaign.CampaignFactory;
import com.chuangyou.xianni.campaign.CampaignMgr;
import com.chuangyou.xianni.campaign.CampaignTempMgr;
import com.chuangyou.xianni.campaign.PvP1v1Campaign;
import com.chuangyou.xianni.entity.campaign.CampaignTemplateInfo;
import com.chuangyou.xianni.netty.GatewayLinkedSet;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;
import com.chuangyou.xianni.world.WorldMgr;

@Cmd(code = Protocol.S_CREATE_1V1_BATTLE, desc = "创建1V1比赛")
public class CreatePvP1v1CampaignCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		Create1V1PvPCampaignMsg msg = Create1V1PvPCampaignMsg.parseFrom(packet.getBytes());
		ArmyProxy attacker = WorldMgr.getArmy(msg.getRedId());
		ArmyProxy defencer = WorldMgr.getArmy(msg.getBlueId());

		if (attacker == null || defencer == null) {
			cancel(msg.getRedId(), msg.getBlueId());
			return;
		}

		CampaignTemplateInfo temp = CampaignTempMgr.get(PvP1v1Campaign.CAMPAIGN_ID);
		if (temp == null) {
			Log.error("playerId: " + army.getPlayerId() + " create campaign if fail ,campaignId:" + PvP1v1Campaign.CAMPAIGN_ID);
			return;
		}

		Campaign campaign = CampaignFactory.createPvP1v1Campaign(temp, attacker, defencer);
		CampaignMgr.add(campaign);
		campaign.start();
		campaign.onPlayerEnter(attacker);
		campaign.onPlayerEnter(defencer);
	}

	// 创建副本失败，取消比赛
	public void cancel(long redId, long blueId) {
		PvP1v1BattleResultMsg.Builder builder = PvP1v1BattleResultMsg.newBuilder();
		builder.setBlue(blueId);
		builder.setRedId(redId);
		builder.setResult(2);
		PBMessage message = MessageUtil.buildMessage(Protocol.C_PVP_1V1_BATTLE_RESULT, builder);
		GatewayLinkedSet.send2Server(message);

	}

}
