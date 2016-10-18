package com.chuangyou.xianni.campaign.action;

import com.chuangyou.common.protobuf.pb.campaign.CampaignResponeMsgProto.CampaignResponeMsg;
import com.chuangyou.common.protobuf.pb.campaign.CreateCampaignMsgProto.CreateCampaignMsg;
import com.chuangyou.xianni.campaign.CampaignTempMgr;
import com.chuangyou.xianni.constant.CampaignRspCode;
import com.chuangyou.xianni.entity.campaign.CampaignTemplateInfo;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.exec.Action;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;

public class CreateCampaignAction extends Action {
	GamePlayer			player;
	int					campaignId;
	int					taskId;
	static final int	count	= 3;

	public CreateCampaignAction(GamePlayer player, int campaignId, int taskId) {
		super(player.getActionQueue());
		this.player = player;
		this.campaignId = campaignId;
		this.taskId = taskId;
	}

	@Override
	public void execute() {
		CampaignTemplateInfo temp = CampaignTempMgr.getTempInfo(campaignId);
		int rspCode = CampaignRspCode.SUCCESS;
		if (temp == null) {
			rspCode = CampaignRspCode.TEMP_NOT_EXISTS;
		}

		// 等级次数等等判断
		if (player.getCampaignCount() >= count) {
			rspCode = CampaignRspCode.COUNT_LESS;
		}

		// 扣除进入副本消耗物品
		// if (costItem(temp) == false) {
		// rspCode = CampaignRspCode.ITEM_NOT_ENOUGHT;
		// }
		// 如果在副本内，不允许创建副本
		if (player.getCurCampaign() != 0) {
			// rspCode = CampaignRspCode.ALREAD_IN_CAMPAIGN;
		}
		if (rspCode != CampaignRspCode.SUCCESS) {
			CampaignResponeMsg.Builder rsp = CampaignResponeMsg.newBuilder();
			rsp.setRspCode(rspCode);
			PBMessage pbMessage = MessageUtil.buildMessage(Protocol.U_CAMPAIGN_RESP, rsp);
			player.sendPbMessage(pbMessage);
			return;
		}
		CreateCampaignMsg.Builder builder = CreateCampaignMsg.newBuilder();
		builder.setCampaign(campaignId);
		builder.setTaskId(taskId);
		// 通知scence服务器，用户请求创建副本
		PBMessage c2s = MessageUtil.buildMessage(Protocol.S_CREATE_CAMPAIGN, builder);
		player.sendPbMessage(c2s);
	}

	private boolean costItem(CampaignTemplateInfo temp) {
		int[] arrItems = temp.getArrCostItems();
		if (arrItems == null || arrItems.length == 0) {
			return true;
		}
		boolean hasEnough = true;
		for (int itemId : arrItems) {
			if (player.getBagInventory().isEnought(itemId, 1) == false) {
				hasEnough = false;
				continue;
			}
		}
		if (hasEnough) {
			for (int itemId : arrItems) {
				player.getBagInventory().removeItem(itemId, 1, ItemRemoveType.JOIN_CAMPAIGN);
			}
		}
		return hasEnough;

	}
}
