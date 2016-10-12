package com.chuangyou.xianni.truck.cmd;

import com.chuangyou.common.protobuf.pb.truck.ReqTruckSyncPosProto.ReqTruckSyncPos;
import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.common.Vector3BuilderHelper;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.objects.ActiveLiving;
import com.chuangyou.xianni.role.objects.Truck;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.warfield.FieldMgr;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.warfield.helper.NotifyNearHelper;
import com.chuangyou.xianni.warfield.helper.selectors.ExcludePetSelector;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;

@Cmd(code = Protocol.S_REQ_TRUCK_SYNCPOS, desc = "镖车请求移动")
public class TruckReqSyncPosCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		Field f = FieldMgr.getIns().getField(army.getFieldId());
		if (f == null)
			return;
		ReqTruckSyncPos syncPos = ReqTruckSyncPos.parseFrom(packet.getBytes());
		Vector3 current = Vector3BuilderHelper.get(syncPos.getCur());
		Truck truck = (Truck) f.getLiving(syncPos.getTruckid());
		if (truck == null)
			return;
		// System.out.println("TruckReqSyncPosCmd current = " + current);
		NotifyNearHelper.notifyHelper(f, truck, current,
				new ExcludePetSelector(truck));
	}

}
