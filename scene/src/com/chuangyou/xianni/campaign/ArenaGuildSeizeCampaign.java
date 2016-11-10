package com.chuangyou.xianni.campaign;

import com.chuangyou.common.protobuf.pb.arena.ArenaResultProto.ArenaResultMsg;
import com.chuangyou.common.protobuf.pb.army.RobotInfoProto.RobotInfoMsg;
import com.chuangyou.xianni.constant.CampaignConstant;
import com.chuangyou.xianni.entity.campaign.CampaignTemplateInfo;
import com.chuangyou.xianni.netty.GatewayLinkedSet;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.world.ArmyProxy;

public class ArenaGuildSeizeCampaign extends ArenaBattleCampaign {

	public ArenaGuildSeizeCampaign(CampaignTemplateInfo tempInfo, ArmyProxy creater, RobotInfoMsg robotDATA) {
		super(tempInfo, creater, robotDATA);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void win() {
		// TODO Auto-generated method stub
		if (isOver) {
			return;
		}
		isOver = true;
		ArenaResultMsg.Builder msg = ArenaResultMsg.newBuilder();
		msg.setResult(CampaignConstant.ChallengeResult.WIN);
		msg.setOpponent(robotDATA.getSimpInfo().getPlayerId());
		PBMessage message = MessageUtil.buildMessage(Protocol.C_GUILD_SEIZE_RESULT, creater, msg);
		GatewayLinkedSet.send2Server(message);

		sendBattleResult(creater, robotDATA.getSimpInfo().getPlayerId());

		OverDelayAction action = new OverDelayAction(this);
		enDelayQueue(action);
	}

	@Override
	public void playerFail() {
		if (isOver) {
			return;
		}
		isOver = true;
		ArenaResultMsg.Builder msg = ArenaResultMsg.newBuilder();
		msg.setResult(CampaignConstant.ChallengeResult.FAIL);
		msg.setOpponent(robotDATA.getSimpInfo().getPlayerId());
		PBMessage message = MessageUtil.buildMessage(Protocol.C_GUILD_SEIZE_RESULT, creater, msg);
		GatewayLinkedSet.send2Server(message);

		sendBattleResult(robotDATA.getSimpInfo().getPlayerId(), creater);

		OverDelayAction action = new OverDelayAction(this);
		enDelayQueue(action);
	}

}
