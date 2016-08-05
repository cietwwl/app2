package com.chuangyou.xianni.campaign.task;

import com.chuangyou.common.protobuf.pb.campaign.CampaignTaskInfoMsgProto.CampaignTaskInfoMsg;
import com.chuangyou.xianni.campaign.Campaign;
import com.chuangyou.xianni.entity.campaign.CampaignTaskTemplateInfo;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.world.ArmyProxy;

/** 副本任务,任意状态，只有在副本通关时候，才结算奖励 */
public class CampaignTask {
	private Campaign		campaign;	// 副本
	private CTBaseCondition	conditon;	// 条件

	private State			state;		// 任务状态

	private enum State {
		PROCESSING(1), SUCCESS(2), FAIL(3), BILLING(4);
		int code;

		private State(int code) {
			this.code = code;
		}
	}

	public CampaignTask(Campaign campaign, CampaignTaskTemplateInfo tempInfo) {
		this.campaign = campaign;
		conditon = CTBaseCondition.createCondition(tempInfo);
		if (conditon.isComplated()) {
			state = State.SUCCESS;
		} else {
			state = State.PROCESSING;
		}
		init();
	}

	public void init() {
		if (getConditionType() == CTBaseCondition.CREATE_SPECIES_MONSTER) {
			
		}
	}

	public int getConditionType() {
		return conditon.getTempInfo().getConditionType();
	}

	public CampaignTaskTemplateInfo getTemp() {
		return conditon.getTempInfo();
	}

	/** 任务更新 */
	public void update(ArmyProxy army) {
		CampaignTaskInfoMsg.Builder builder = CampaignTaskInfoMsg.newBuilder();
		builder.setCampaignId(campaign.getIndexId());
		builder.setStatus(state.code);
		builder.setProgress(conditon.getRecord().getProgress());
		builder.setTaskId(conditon.getTempInfo().getTaskId());

		PBMessage message = MessageUtil.buildMessage(Protocol.U_CAMPAIGN_TASK_INFO, builder.build());
		army.sendPbMessage(message);
	}

	/** 任务更新 */
	public void updateAll() {
		CampaignTaskInfoMsg.Builder builder = CampaignTaskInfoMsg.newBuilder();
		builder.setCampaignId(campaign.getIndexId());
		builder.setStatus(state.code);
		builder.setProgress(conditon.getRecord().getProgress());
		builder.setTaskId(conditon.getTempInfo().getTaskId());

		PBMessage message = MessageUtil.buildMessage(Protocol.U_CAMPAIGN_TASK_INFO, builder.build());
		for (ArmyProxy army : campaign.getAllArmys()) {
			army.sendPbMessage(message);
		}
	}

	/** 触发任务事件 */
	public void notityEvent(int param) {
		if (conditon.addProgress(param) == false) {
			return;
		}
		State older = state;
		if (conditon.isComplated()) {
			state = State.SUCCESS;
		} else {
			if (older == State.SUCCESS) {
				state = State.FAIL;
			}
		}

		if (older != state || getConditionType() != CTBaseCondition.PASS_TIME_LIMIT) {
			updateAll();
		}
	}

}