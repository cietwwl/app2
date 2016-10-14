package com.chuangyou.xianni.campaign.cmd;

import com.chuangyou.common.protobuf.pb.state.CreateStateCampaignProto.CreateStateCampaignMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.campaign.Campaign;
import com.chuangyou.xianni.campaign.CampaignFactory;
import com.chuangyou.xianni.campaign.CampaignMgr;
import com.chuangyou.xianni.campaign.CampaignTempMgr;
import com.chuangyou.xianni.entity.campaign.CampaignTemplateInfo;
import com.chuangyou.xianni.entity.state.StateConfig;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.state.template.StateTemplateMgr;
import com.chuangyou.xianni.warfield.FieldMgr;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;

@Cmd(code=Protocol.S_CREATE_STATE_CAMPAIGN,desc = "创建境界副本")
public class CreateStateCampaignCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		CreateStateCampaignMsg msg = CreateStateCampaignMsg.parseFrom(packet.getBytes());
			
		Field curField = FieldMgr.getIns().getField(army.getFieldId());
		// 不允许在副本中创建副本
		if (curField != null && curField.getCampaignId() > 0) {
			Log.error("user request create campaign but is aleady in campaign. playerId : " + army.getPlayerId());
			return;
		}

		CampaignTemplateInfo temp = CampaignTempMgr.get(msg.getCampaignId());
		if (temp == null) {
			Log.error("playerId: " + army.getPlayerId() + " create campaign if fail ,campaignId:" + msg.getCampaignId());
			return;
		}

		StateConfig config = StateTemplateMgr.getStates().get(msg.getStateId());
		if(config==null){
			Log.error("playerId: " + army.getPlayerId() + "查不到境界配置表:::"+msg.toString());
			return;
		}
		Campaign campaign = CampaignFactory.createStateConfig(temp, army, config);
		CampaignMgr.add(campaign);
		campaign.start();
		campaign.onPlayerEnter(army);
		
		System.out.println("==========>playerId: " + army.getPlayerId() + "进入渡节副本<====================");
	}

}
