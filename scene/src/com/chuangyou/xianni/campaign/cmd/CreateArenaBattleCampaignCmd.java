package com.chuangyou.xianni.campaign.cmd;

import com.chuangyou.common.protobuf.pb.army.RobotInfoProto.RobotInfoMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.campaign.ArenaBattleCampaign;
import com.chuangyou.xianni.campaign.Campaign;
import com.chuangyou.xianni.campaign.CampaignFactory;
import com.chuangyou.xianni.campaign.CampaignMgr;
import com.chuangyou.xianni.campaign.CampaignTempMgr;
import com.chuangyou.xianni.entity.campaign.CampaignTemplateInfo;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.warfield.FieldMgr;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;

@Cmd(code = Protocol.S_CREATE_ARENA_BATTLE, desc = "创建竞技场战场副本")
public class CreateArenaBattleCampaignCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		RobotInfoMsg msg = RobotInfoMsg.parseFrom(packet.getBytes());

		Field curField = FieldMgr.getIns().getField(army.getFieldId());
		// 不允许在副本中创建副本
		if (curField != null && curField.getCampaignId() > 0) {
			Log.error("user request create campaign but is aleady in campaign. playerId : " + army.getPlayerId());
			return;
		}

		CampaignTemplateInfo temp = CampaignTempMgr.get(ArenaBattleCampaign.CAMPAIGN_ID);
		if (temp == null) {
			Log.error("playerId: " + army.getPlayerId() + " create campaign if fail ,campaignId:" + ArenaBattleCampaign.CAMPAIGN_ID);
			return;
		}

		Campaign campaign = CampaignFactory.createArenaBattleCampaign(temp, army, msg);
		CampaignMgr.add(campaign);
		campaign.start();
		campaign.onPlayerEnter(army);
	}

}
