package com.chuangyou.xianni.campaign.action;

import com.chuangyou.common.protobuf.pb.campaign.CampaignInfoMsgProto.CampaignInfoMsg;
import com.chuangyou.common.protobuf.pb.campaign.CampaignStatuMsgProto.CampaignStatuMsg;
import com.chuangyou.xianni.campaign.Campaign;
import com.chuangyou.xianni.campaign.PlaneCampaign;
import com.chuangyou.xianni.campaign.state.CampaignState;
import com.chuangyou.xianni.constant.CampaignConstant.CampaignStatu;
import com.chuangyou.xianni.exec.Action;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.warfield.action.EnterFieldAction;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.world.ArmyPositionRecord;
import com.chuangyou.xianni.world.ArmyProxy;

public class CampaignExitAction extends Action {

	ArmyProxy	army;
	Campaign	campaign;
	
	public CampaignExitAction(Campaign campaign, ArmyProxy army) {
		super(campaign);
		this.army = army;
		this.campaign = campaign;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

		CampaignStatuMsg.Builder cstatu = CampaignStatuMsg.newBuilder();
		cstatu.setIndexId(campaign.getIndexId());
		cstatu.setTempId(campaign.getTemp().getTemplateId());
		cstatu.setStatu(CampaignStatu.NOTITY2C_OUT);// 退出
		PBMessage statuMsg = MessageUtil.buildMessage(Protocol.C_CAMPAIGN_STATU, cstatu);
		army.sendPbMessage(statuMsg);

		campaign.removeArmy(army, true);
		army.getPlayer().removeCampaignBuffer();

		CampaignInfoMsg.Builder infoMsg = CampaignInfoMsg.newBuilder();
		infoMsg.setId(campaign.getIndexId());
		infoMsg.setCount(campaign.getAllArmys().size());
		infoMsg.setCreaterId(campaign.getCreaterId());
		infoMsg.setState(CampaignState.STOP);
		infoMsg.setTempId(campaign.getTemp().getTemplateId());
		PBMessage message = MessageUtil.buildMessage(Protocol.U_CAMPAIGN_INFO, infoMsg);
		army.sendPbMessage(message);
		
		if (campaign instanceof PlaneCampaign) {
			int outFiledId = campaign.getTemp().getTemplateId() % (Field.MAX_ID);
			EnterFieldAction action = new EnterFieldAction(army, outFiledId, outFiledId, army.getPlayer().getPostion());
			army.enqueue(action);
		} else{
			// 退出至上一个节点
			ArmyPositionRecord posRecord = army.getPosRecord();
			if (posRecord == null) {
				army.returnBornMap();
				return;
			}
			EnterFieldAction action = new EnterFieldAction(army, posRecord.getMapId(), posRecord.getMapTempId(), posRecord.getPos());
			army.enqueue(action);
		}
	}

}
