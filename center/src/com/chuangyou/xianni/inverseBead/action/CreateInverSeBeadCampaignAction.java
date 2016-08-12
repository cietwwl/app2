package com.chuangyou.xianni.inverseBead.action;

import java.util.List;

import com.chuangyou.common.protobuf.pb.campaign.CampaignResponeMsgProto.CampaignResponeMsg;
import com.chuangyou.common.protobuf.pb.inverseBead.ReqEnterCampaignMsgProto.ReqEnterCampaignMsg;
import com.chuangyou.xianni.campaign.CampaignTempMgr;
import com.chuangyou.xianni.constant.CampaignRspCode;
import com.chuangyou.xianni.entity.campaign.CampaignTemplateInfo;
import com.chuangyou.xianni.entity.inverseBead.PlayerBeadTimeInfo;
import com.chuangyou.xianni.exec.Action;
import com.chuangyou.xianni.inverseBead.InverseBeadInventory;
import com.chuangyou.xianni.inverseBead.manager.InverseBeadManager;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;

public class CreateInverSeBeadCampaignAction extends Action {
	GamePlayer player;

	public CreateInverSeBeadCampaignAction(GamePlayer player) {
		super(player.getActionQueue());
		this.player = player;
	}

	@Override
	public void execute() {
		CampaignTemplateInfo temp = CampaignTempMgr.getTempInfo(InverseBeadInventory.campaignId);
		int rspCode = CampaignRspCode.SUCCESS;
		if (temp == null) {
			rspCode = CampaignRspCode.TEMP_NOT_EXISTS;
		}
		PlayerBeadTimeInfo playerTimeInfo = player.getInverseBeadRefreshInventory().getplayerBeadTimeInfo();
		String beadRefreshId = playerTimeInfo.getBeadRefreshId();

		List<Integer> list = InverseBeadManager.getBeadRefreshId(beadRefreshId);
		if (list.size() == 0) {
			rspCode = CampaignRspCode.COUNT_LESS;
		}

		if (rspCode != CampaignRspCode.SUCCESS) {
			CampaignResponeMsg.Builder rsp = CampaignResponeMsg.newBuilder();
			rsp.setRspCode(rspCode);
			PBMessage pbMessage = MessageUtil.buildMessage(Protocol.U_CAMPAIGN_RESP, rsp);
			player.sendPbMessage(pbMessage);
			return;
		}

		// 通知scence服务器 创建副本
		ReqEnterCampaignMsg.Builder msg = ReqEnterCampaignMsg.newBuilder();
		msg.setCampaign(InverseBeadInventory.campaignId);
		PBMessage c2s = MessageUtil.buildMessage(Protocol.S_CREATE_INVERSE_BEAD_CAMPAIGN, msg);
		player.sendPbMessage(c2s);
	}

}
