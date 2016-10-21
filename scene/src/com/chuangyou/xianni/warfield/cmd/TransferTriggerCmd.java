package com.chuangyou.xianni.warfield.cmd;

import com.chuangyou.common.protobuf.pb.map.TransferTriggerProto.TransferTriggerMsg;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.objects.Transfer;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.warfield.FieldMgr;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;

@Cmd(code = Protocol.S_TRANSFER_TRIGGER, desc = "传送门触发")
public class TransferTriggerCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		TransferTriggerMsg req = TransferTriggerMsg.parseFrom(packet.getBytes());
		Field field = FieldMgr.getIns().getField(req.getMapId());
		if(field == null){
			return;
		}
		Transfer transfer = (Transfer)field.getLiving(req.getId());
		if(transfer == null) return;
		
		transfer.transfer(army);
	}

}
