package com.chuangyou.xianni.campaign;

import com.chuangyou.common.protobuf.pb.arena.ArenaResultProto.ArenaResultMsg;
import com.chuangyou.common.protobuf.pb.army.RobotInfoProto.RobotInfoMsg;
import com.chuangyou.common.protobuf.pb.battle.BattleResultMsgProto.BattleResultMsg;
import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.constant.CampaignConstant;
import com.chuangyou.xianni.entity.campaign.CampaignTemplateInfo;
import com.chuangyou.xianni.entity.field.FieldInfo;
import com.chuangyou.xianni.exec.DelayAction;
import com.chuangyou.xianni.netty.GatewayLinkedSet;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.objects.Player;
import com.chuangyou.xianni.role.objects.Robot;
import com.chuangyou.xianni.world.ArmyProxy;

public class ArenaBattleCampaign extends Campaign {

	protected RobotInfoMsg	robotDATA;			// 机器人数据
	protected boolean		isOver		= false;

	public static final int	CAMPAIGN_ID	= 50001;

	public ArenaBattleCampaign(CampaignTemplateInfo tempInfo, ArmyProxy creater, RobotInfoMsg robotDATA) {
		super(tempInfo, creater, 0);
		this.robotDATA = robotDATA;
		creater.getPlayer().recovery();
	}

	public void start() {
		super.start();
		createRobot();
	}

	public boolean agreedToEnter(ArmyProxy army) {
		return super.agreedToEnter(army) && army.getPlayerId() == creater;
	}

	private void createRobot() {
		Robot robot = new Robot(this);
		robot.instill(robotDATA);

		FieldInfo fieldInfo = starField.getFieldInfo();
		Vector3 vector3 = new Vector3(fieldInfo.getPosition().x, fieldInfo.getPosition().y, fieldInfo.getPosition().z, fieldInfo.getPosition().angle);
		robot.setPostion(vector3);
		starField.enterField(robot);
	}

	public void win() {
		if (isOver) {
			return;
		}
		isOver = true;
		ArenaResultMsg.Builder msg = ArenaResultMsg.newBuilder();
		msg.setResult(CampaignConstant.ChallengeResult.WIN);
		msg.setOpponent(robotDATA.getSimpInfo().getPlayerId());
		PBMessage message = MessageUtil.buildMessage(Protocol.C_ARENA_RESULT, creater, msg);
		GatewayLinkedSet.send2Server(message);

		sendBattleResult(creater, robotDATA.getSimpInfo().getPlayerId());

		OverDelayAction action = new OverDelayAction(this);
		enDelayQueue(action);
	}

	public void fail() {
		if (isOver) {
			return;
		}
		isOver = true;
		ArenaResultMsg.Builder msg = ArenaResultMsg.newBuilder();
		msg.setResult(CampaignConstant.ChallengeResult.FAIL);
		msg.setOpponent(robotDATA.getSimpInfo().getPlayerId());
		PBMessage message = MessageUtil.buildMessage(Protocol.C_ARENA_RESULT, creater, msg);
		GatewayLinkedSet.send2Server(message);

		sendBattleResult(robotDATA.getSimpInfo().getPlayerId(), creater);

		OverDelayAction action = new OverDelayAction(this);
		enDelayQueue(action);
	}

	public void sendBattleResult(long winner, long loser) {
		BattleResultMsg.Builder builder = BattleResultMsg.newBuilder();
		builder.setLoser(loser);
		builder.setWinner(winner);
		PBMessage message = MessageUtil.buildMessage(Protocol.U_BATTLE_RESULT, builder);
		for (ArmyProxy army : JoinArmys) {
			army.sendPbMessage(message);
		}
	}

	public void over() {
		// 不会结束第二遍
		if (areadyOver()) {
			return;
		}
		fail();
		OverDelayAction action = new OverDelayAction(this);
		enDelayQueue(action);
	}

	public void delayOver() {
		super.over();
	}

	protected class OverDelayAction extends DelayAction {
		ArenaBattleCampaign campaign;

		public OverDelayAction(ArenaBattleCampaign campaign) {
			super(campaign, 3000);
			this.campaign = campaign;
		}

		@Override
		public void execute() {
			campaign.delayOver();
			for (ArmyProxy army : JoinArmys) {
				Player cp = army.getPlayer();
				if (cp != null && cp.isDie()) {
					cp.renascence();
				}
			}
		}
	}

}