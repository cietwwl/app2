package com.chuangyou.xianni.inverseBead.cmd;

import com.chuangyou.common.protobuf.pb.campaign.CreateCampaignMsgProto.CreateCampaignMsg;
import com.chuangyou.xianni.inverseBead.action.CreateCampaignAction;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;

@Cmd(code = Protocol.S_CREATE_INVERSE_BEAD_CAMPAIGN, desc = "请求创建天逆珠秘境副本")
public class CampaignCreateCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		CreateCampaignMsg msg = CreateCampaignMsg.parseFrom(packet.getBytes());
		CreateCampaignAction createAction = new CreateCampaignAction(army, msg.getCampaign(), 1);
		army.enqueue(createAction);
	}

}
