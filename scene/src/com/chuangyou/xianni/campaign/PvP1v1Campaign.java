package com.chuangyou.xianni.campaign;

import com.chuangyou.common.protobuf.pb.battle.BattleResultMsgProto.BattleResultMsg;
import com.chuangyou.common.protobuf.pb.campaign.PvP1v1BattleResultProto.PvP1v1BattleResultMsg;
import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.campaign.action.CampaignLeaveAction;
import com.chuangyou.xianni.campaign.state.StopState;
import com.chuangyou.xianni.constant.PlayerState;
import com.chuangyou.xianni.entity.campaign.CampaignTemplateInfo;
import com.chuangyou.xianni.exec.DelayAction;
import com.chuangyou.xianni.netty.GatewayLinkedSet;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.objects.Player;
import com.chuangyou.xianni.world.ArmyProxy;

public class PvP1v1Campaign extends Campaign {
	private ArmyProxy			red;
	private ArmyProxy			blue;
	private volatile int		result				= 0;	// 1 红方胜，0 平局，-1 蓝方胜
	private volatile boolean	sendResul2Center	= false;

	public static final int		CAMPAIGN_ID			= 50002;

	public PvP1v1Campaign(CampaignTemplateInfo tempInfo, ArmyProxy red, ArmyProxy blue) {
		super(tempInfo, red, 0);
		this.red = red;
		this.blue = blue;
		red.getPlayer().recovery();
		blue.getPlayer().recovery();
	}

	public boolean agreedToEnter(ArmyProxy army) {
		return super.agreedToEnter(army) && (army.getPlayerId() == red.getPlayerId() || army.getPlayerId() == blue.getPlayerId());
	}

	public void die(long playerId) {
		if (this.red.getPlayerId() == playerId) {
			result = -1;
			sendBattleResult(this.blue.getPlayerId(), playerId);
		}
		if (this.blue.getPlayerId() == playerId) {
			result = 1;
			sendBattleResult(this.red.getPlayerId(), playerId);
		}
		sendBattleResult();
		OverDelayAction action = new OverDelayAction(this);
		enDelayQueue(action);
	}

	public void fail() {
		sendBattleResult();
		Player redPlayer = red.getPlayer();
		if (redPlayer != null && redPlayer.isDie() && red.getOnlineStatu() == PlayerState.ONLINE) {
			redPlayer.renascence();
		}

		Player bluePlayer = blue.getPlayer();
		if (bluePlayer != null && bluePlayer.isDie() && red.getOnlineStatu() == PlayerState.ONLINE) {
			bluePlayer.renascence();
		}
		endTime = System.currentTimeMillis() + 5 * 1000;
	}

	/** 是否可以创建分身进入 */
	public boolean isBuilder(long playerId) {
		return true;
	}

	/**
	 * 副本下线
	 */
	public void unline(ArmyProxy army) {
		CampaignLeaveAction action = new CampaignLeaveAction(this, army, true);
		enqueue(action);

		if (this.red.getPlayerId() == army.getPlayerId()) {
			result = -1;
			sendBattleResult(this.blue.getPlayerId(), army.getPlayerId());
		}
		if (this.blue.getPlayerId() == army.getPlayerId()) {
			result = 1;
			sendBattleResult(this.red.getPlayerId(), army.getPlayerId());
		}
		sendBattleResult();
		OverDelayAction overAction = new OverDelayAction(this);
		enDelayQueue(overAction);
	}

	public void sendBattleResult() {
		if (sendResul2Center) {
			return;
		}
		PvP1v1BattleResultMsg.Builder builder = PvP1v1BattleResultMsg.newBuilder();
		builder.setBlue(blue.getPlayerId());
		builder.setRedId(red.getPlayerId());
		builder.setResult(result);
		PBMessage message = MessageUtil.buildMessage(Protocol.C_PVP_1V1_BATTLE_RESULT, builder);
		GatewayLinkedSet.send2Server(message);
		sendResul2Center = true;
	}

	public void sendBattleResult(long winner, long loser) {
		BattleResultMsg.Builder builder = BattleResultMsg.newBuilder();
		builder.setLoser(loser);
		builder.setWinner(winner);
		PBMessage message = MessageUtil.buildMessage(Protocol.U_BATTLE_RESULT, builder);
		red.sendPbMessage(message);

		BattleResultMsg.Builder builder2 = BattleResultMsg.newBuilder();
		builder2.setLoser(loser);
		builder2.setWinner(winner);
		PBMessage message2 = MessageUtil.buildMessage(Protocol.U_BATTLE_RESULT, builder2);
		blue.sendPbMessage(message2);
	}

	public Vector3 getBornNode(ArmyProxy army) {
		if (army.getPlayerId() == red.getPlayerId()) {
			return super.getBornNode(army);
		}
		return starField.getFieldInfo().getPosition();
	}

	private class OverDelayAction extends DelayAction {
		PvP1v1Campaign campaign;

		public OverDelayAction(PvP1v1Campaign campaign) {
			super(campaign, 5000);
			this.campaign = campaign;
		}

		@Override
		public void execute() {
			campaign.stateTransition(new StopState(campaign));
			for (ArmyProxy army : JoinArmys) {
				Player cp = army.getPlayer();
				if (cp != null) {
					if (cp.isDie()) {
						cp.renascence();
					} else {
						cp.recovery();
					}
				}
			}
		}
	}
}
