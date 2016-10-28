package com.chuangyou.xianni.campaign;

import com.chuangyou.common.protobuf.pb.avatar.PassAvatarCampaignProto.PassAvatarCampaignMsg;
import com.chuangyou.xianni.entity.campaign.CampaignTemplateInfo;
import com.chuangyou.xianni.netty.GatewayLinkedSet;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.world.ArmyProxy;

public class AvatarCampaign extends Campaign {

	private int rewardCount = 10;// 副本使用奖励层数物品

	public AvatarCampaign(CampaignTemplateInfo tempInfo, ArmyProxy creater) {
		super(tempInfo, creater, 0);
	}

	/** 通关副本 */
	public void success() {
		// 分身副本，额外通过发放奖励
		PassAvatarCampaignMsg.Builder builder = PassAvatarCampaignMsg.newBuilder();
		builder.setPlayerId(creater);
		builder.setTempId(tempId);
		builder.setRewardCount(rewardCount);
		PBMessage message = MessageUtil.buildMessage(Protocol.C_PASS_CAMPAIGN_N2CENTER, builder);
		GatewayLinkedSet.send2Server(message);
		endTime = System.currentTimeMillis() + 120 * 1000;
	}

}
