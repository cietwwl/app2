package com.chuangyou.xianni.campaign.cmd;

import com.chuangyou.common.protobuf.pb.arena.ArenaResultProto.ArenaResultMsg;
import com.chuangyou.common.protobuf.pb.army.RobotInfoProto.RobotInfoMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.campaign.Campaign;
import com.chuangyou.xianni.campaign.CampaignFactory;
import com.chuangyou.xianni.campaign.CampaignMgr;
import com.chuangyou.xianni.campaign.CampaignTempMgr;
import com.chuangyou.xianni.campaign.state.StartState;
import com.chuangyou.xianni.constant.CampaignConstant;
import com.chuangyou.xianni.entity.campaign.CampaignTemplateInfo;
import com.chuangyou.xianni.netty.GatewayLinkedSet;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.warfield.FieldMgr;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;

@Cmd(code = Protocol.S_GUILD_SEIZE_CHALLENGE, desc = "副入门派夺权副本")
public class CreateGuildSeizeCampaignCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		RobotInfoMsg req = RobotInfoMsg.parseFrom(packet.getBytes());
		
		Field curField = FieldMgr.getIns().getField(army.getFieldId());
		
		//玩家已经在副本中，挑战无法开始
		if(curField != null && curField.getCampaignId() > 0){
			ArenaResultMsg.Builder msg = ArenaResultMsg.newBuilder();
			msg.setResult(CampaignConstant.ChallengeResult.ALREADY_IN_CAMPAIGN);
			msg.setOpponent(req.getSimpInfo().getPlayerId());
			PBMessage message = MessageUtil.buildMessage(Protocol.C_GUILD_SEIZE_RESULT, army.getPlayerId(), msg);
			GatewayLinkedSet.send2Server(message);
			
			return;
		}
		
		CampaignTemplateInfo temp = CampaignTempMgr.get(50003);
		
		if(temp == null){
			ArenaResultMsg.Builder msg = ArenaResultMsg.newBuilder();
			msg.setResult(CampaignConstant.ChallengeResult.START_FAIL);
			msg.setOpponent(req.getSimpInfo().getPlayerId());
			PBMessage message = MessageUtil.buildMessage(Protocol.C_GUILD_SEIZE_RESULT, army.getPlayerId(), msg);
			GatewayLinkedSet.send2Server(message);
			
			Log.error("playerId: " + army.getPlayerId() + " create campaign if fail ,campaignId:" + 50003);
			return;
		}
		
		Campaign campaign = CampaignFactory.createGuildSeizeCampaign(temp, army, req);
		CampaignMgr.add(campaign);
		campaign.stateTransition(new StartState(campaign));
		campaign.onPlayerEnter(army);
	}

}
