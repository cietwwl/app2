package com.chuangyou.xianni.campaign.cmd;

import com.chuangyou.common.protobuf.pb.campaign.CreateCampaignByNpcProto.CreateCampaignByNpcMsg;
import com.chuangyou.xianni.campaign.action.CampaignCreateAction;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;

@Cmd(code = Protocol.S_CREATE_CAMPAIGN_BY_NPC, desc = "根据NPC创建副本")
public class CreateCampaingByNpcCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		CreateCampaignByNpcMsg msg = CreateCampaignByNpcMsg.parseFrom(packet.getBytes());
		if (army.getPlayer().getField() == null) {
			return;
		}
		if (msg.getNpcId() > 0) {
			Living npc = army.getPlayer().getField().getLiving(msg.getNpcId());
			if (npc == null) {
				return;
			}
			army.getPlayer().getField().leaveField(npc);
		}
		CampaignCreateAction createAction = new CampaignCreateAction(army, msg.getCampaignId(), -1);
		army.enqueue(createAction);
	}

}
