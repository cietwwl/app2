package com.chuangyou.xianni.truck.cmd;

import java.util.ArrayList;
import java.util.Set;

import com.chuangyou.common.protobuf.pb.truck.ReqTruckMoveProto.ReqTruckMove;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.objects.Truck;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.warfield.FieldMgr;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.warfield.helper.NotifyNearHelper;
import com.chuangyou.xianni.warfield.helper.selectors.ExcludePetSelector;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;

@Cmd(code = Protocol.S_REQ_TRUCK_MOVE, desc = "镖车请求移动")
public class TruckReqMoveCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		Field f = FieldMgr.getIns().getField(army.getFieldId());
		if (f == null)
			return;
		ReqTruckMove movemsg = ReqTruckMove.parseFrom(packet.getBytes());
		Truck truck = (Truck) f.getLiving(movemsg.getTruckid());
		if(truck == null)
			return;
		ExcludePetSelector selector = new ExcludePetSelector(truck);
		Set<Long> near = truck.getNears(selector);
		NotifyNearHelper.notifyMove(truck, new ArrayList<Long>(near), movemsg.getCur(), movemsg.getTar());
	}

}
