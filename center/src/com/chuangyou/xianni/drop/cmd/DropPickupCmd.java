package com.chuangyou.xianni.drop.cmd;

import com.chuangyou.common.protobuf.pb.drop.DropPickupCenterProto.DropPickupCenterMsg;
import com.chuangyou.common.protobuf.pb.drop.DropPickupResultProto.DropPickupResultMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.entity.item.ItemAddType;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_DROP_PICKUP, desc = "掉落物拾取")
public class DropPickupCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		DropPickupCenterMsg req = DropPickupCenterMsg.parseFrom(packet.getBytes());
		
		boolean result = player.getBagInventory().addItem(req.getItemId(), req.getCount(), ItemAddType.DROP_PICKUP, true);
		
		DropPickupResultMsg.Builder msg = DropPickupResultMsg.newBuilder();
		msg.setPackageId(req.getPackageId());
		msg.setDropItemId(req.getDropItemId());
		msg.setResult(result);
		player.sendPbMessage(MessageUtil.buildMessage(Protocol.S_DROP_PICKUP_RESULT, msg));
	}

}
