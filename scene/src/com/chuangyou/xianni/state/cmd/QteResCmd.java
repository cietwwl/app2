package com.chuangyou.xianni.state.cmd;

import com.chuangyou.common.protobuf.pb.state.StateQteReqProto.StateQteReqMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.campaign.CampaignMgr;
import com.chuangyou.xianni.campaign.CampaignTempMgr;
import com.chuangyou.xianni.campaign.StateCampaign;
import com.chuangyou.xianni.constant.CampaignConstant.CampaignType;
import com.chuangyou.xianni.entity.campaign.CampaignTemplateInfo;
import com.chuangyou.xianni.entity.state.StateEventConfig;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.state.QteTemp;
import com.chuangyou.xianni.state.template.StateTemplateMgr;
import com.chuangyou.xianni.warfield.FieldMgr;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;

@Cmd(code = Protocol.S_STATE_QTE_RES,desc="境界QTE的反馈")
public class QteResCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		
		Field curField = FieldMgr.getIns().getField(army.getFieldId());
		if (curField == null ){
			Log.error("不在副本中。" + army.getPlayerId());
			return;
		}
		
		CampaignTemplateInfo temp = CampaignTempMgr.get(curField.getMapKey());
		if (temp == null) {
			Log.error("获取不到副本模板playerId: " + army.getPlayerId() + "campaignMapKey:" + curField.getMapKey());
			return;
		}
		
		StateCampaign campaign = (StateCampaign) CampaignMgr.getCampagin(curField.getCampaignId());
		if(campaign==null){
			Log.error("查找不到对应的副本playerId: " + army.getPlayerId() + "campaignID:" + curField.getCampaignId());
			return;
		}
		
		if(temp.getType()!=CampaignType.STATE){
			Log.error("非进界副本playerId: " + army.getPlayerId() + "campaignMapKey:" + curField.getMapKey());
			return;
		}
		
		StateQteReqMsg req = StateQteReqMsg.parseFrom(packet.getBytes());
		synchronized (campaign.getQteTempMap()) {			
			QteTemp qte = campaign.getQteTempMap().get(req.getQteId());
			if(qte!=null){
				campaign.getQteTempMap().remove(req.getQteId());
				StateEventConfig stateEventConfig = StateTemplateMgr.getEvents().get(req.getEventId()); 
				if(stateEventConfig!=null){
					if(req.getCode() == 2){
						campaign.execOneEvent(stateEventConfig);
					}else if(req.getCode() == 1){
						StateEventConfig success = StateTemplateMgr.getEvents().get(stateEventConfig.getQteSucessEventID());
						if(success!=null){
							campaign.execOneEvent(success);
						}
					}
				}
			}
		}
		
	}

}
